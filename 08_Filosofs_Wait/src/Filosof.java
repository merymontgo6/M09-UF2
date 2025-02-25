
import java.util.Random;

public class Filosof implements Runnable{
    private final Forquilla forquillaDreta;
    private final Forquilla forquillaEsquerra;
    private int gana;
    private final Random random;
    private final int numComensal;
    private final String nom;

    public Filosof(String nom, Forquilla forquillaEsquerra, Forquilla forquillaDreta, int numComensal) {
        this.nom = nom;
        this.forquillaDreta = forquillaDreta;
        this.forquillaEsquerra = forquillaEsquerra;
        this.numComensal = numComensal;
        this.gana = 0;
        this.random = new Random();
    }

    public void menjar() {
        System.out.println("Filòsof: " + nom + " menja");
        try {
            Thread.sleep(1000 + random.nextInt(1000)); // menjar entre 1s i 2s
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        gana = 0; // després de menjar, la gana es reinicia
        System.out.println("Filòsof: " + nom + " ha acabat de menjar");
    }
    

    public void agafarForquilles() {
        while (true) {
            agafaForquillaEsquerra();
            boolean dretaAdquirida = false;
            // Intentem agafar la dreta sense quedar bloquejat indefinidament
            synchronized (forquillaDreta) {
                if (!forquillaDreta.getEnUs()) {
                    forquillaDreta.setEnUs(true);
                    forquillaDreta.setNumPropietari(numComensal);
                    dretaAdquirida = true;
                }
            }
            if (dretaAdquirida) {
                System.out.println("Filòsof: " + nom + " agafa la forquilla dreta " + forquillaDreta.getNumPropietari());
                break;// S'han aconseguit les dues forquilles
                
            } else {
                // No ha pogut agafar la dreta: allibera la esquerra i espera
                synchronized (forquillaEsquerra) {
                    forquillaEsquerra.setEnUs(false);
                    forquillaEsquerra.setNumPropietari(Forquilla.LLIURE);
                    forquillaEsquerra.notifyAll();
                }
                // Incrementem la gana i mostrem el missatge
                gana++;
                System.out.println("Filòsof: " + nom + " gana: " + gana);
                try {
                    Thread.sleep(500 + random.nextInt(500)); // espera entre 0.5s i 1s
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        }
    }

    public void agafaForquillaEsquerra() {
        synchronized (forquillaEsquerra) {
            while (forquillaEsquerra.getEnUs()) {
                try {
                    forquillaEsquerra.wait();
                    System.out.println("Filòsof: " + nom + " no pot agafar l'esquerra (" + forquillaEsquerra.getNumPropietari() + ") i espera");
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
            // Marquem la forquilla com a en ús i assignem el número del filòsof
            forquillaEsquerra.setEnUs(true);
            forquillaEsquerra.setNumPropietari(numComensal);
            System.out.println("Filòsof: " + nom + " agafa la forquilla esquerra " + forquillaEsquerra.getNumPropietari());
        }
    }

    public void agafaForquillaDreta() {
        synchronized (forquillaDreta) {
            while (forquillaDreta.getEnUs()) {
                try {
                    forquillaDreta.wait();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
            // Marquem la forquilla com a en ús i assignem el número del filòsof
            forquillaDreta.setEnUs(true);
            forquillaDreta.setNumPropietari(numComensal);
            System.out.println("Filòsof: " + nom + " agafa la forquilla dreta " + forquillaDreta.getNumPropietari());
        }
    }

    public void deixarForquilles() {
        synchronized (forquillaEsquerra) {
            forquillaEsquerra.setEnUs(false);
            forquillaEsquerra.setNumPropietari(Forquilla.LLIURE);
            forquillaEsquerra.notifyAll();
        }
        synchronized (forquillaDreta) {
            forquillaDreta.setEnUs(false);
            forquillaDreta.setNumPropietari(Forquilla.LLIURE);
            forquillaDreta.notifyAll();
        }
    }

    public void pensar() {
        System.out.println("Filòsof: " + nom + " està pensant.");
        try {
            Thread.sleep(1000 + random.nextInt(1000)); // pensar entre 1s i 2s
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        gana++;
    }

    //@Override
    public void run() {
        try {
            while (true) {
                pensar();
                agafarForquilles();
                menjar();
                deixarForquilles();
            }
        } catch (Exception e) {
            Thread.currentThread().interrupt();
        }
    }

    public static void main(String[] args) {
        Taula taula = new Taula(4);
        taula.showTaula();
        taula.cridarATaula();
    }

    public String getNom() {
        return nom;
    }

    public Forquilla getForquillaEsquerra() {
        return forquillaEsquerra;
    }

    public Forquilla getForquillaDreta() {
        return forquillaDreta;
    }

    @Override
    public String toString() {
        return nom + " (Gana: " + gana + ")";
    }
}