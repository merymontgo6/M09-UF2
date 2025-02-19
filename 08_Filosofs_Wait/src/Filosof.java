
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
        //Metode de l'activitat passada, s'ha de canviar i afegir el nous metodes 
        if (!forquillaEsquerra.getEnUs()) {
            forquillaEsquerra.setEnUs(true);
            System.out.println("Filòsof: " + nom + " agafa la forquilla esquerra " + forquillaEsquerra.getNumPropietari());

            // Intentar agafar la forquilla dreta
            if (!forquillaDreta.getEnUs()) {
                forquillaDreta.setEnUs(true);
                System.out.println("Filòsof: " + nom + " agafa la forquilla dreta " + forquillaDreta.getNumPropietari());

                // Menjar
                System.out.println("Filòsof: " + nom + " menja");
                try {
                    Thread.sleep(1000 + random.nextInt(1000)); // Menjar entre 1s i 2s
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }

                // Deixar les forquilles
                forquillaDreta.setEnUs(false);
                forquillaEsquerra.setEnUs(false);
                System.out.println("Filòsof: " + nom + " ha acabat de menjar");
                gana = 0;
            } else {
                // Si no pot agafar la forquilla dreta, deixar l'esquerra i esperar
                forquillaEsquerra.setEnUs(false);
                System.out.println("Filòsof: " + nom + " deixa l'esquerra (" + forquillaEsquerra.getNumPropietari() + ") i espera (dreta ocupada)");
                gana++;
                System.out.println("Filòsof: " + nom + " gana=" + gana);
                try {
                    Thread.sleep(500 + random.nextInt(500)); // Esperar entre 0.5s i 1s
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        } else {
            // Si no pot agafar la forquilla esquerra, esperar
            System.out.println("Filòsof: " + nom + " no pot agafar l'esquerra (" + forquillaEsquerra.getNumPropietari() + ") i espera");
            try {
                Thread.sleep(500 + random.nextInt(500)); // Esperar entre 0.5s i 1s
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    public void agafarForquilles() {
        synchronized (forquillaEsquerra) {
            agafaForquillaEsquerra();
            synchronized (forquillaDreta) {
                agafaForquillaDreta();
                if (forquillaEsquerra.getEnUs() && forquillaDreta.getEnUs()) {
                    menjar();
                }
            }
        }
    }

    public void agafaForquillaEsquerra() {
        while (forquillaEsquerra.getEnUs()) {
            try {
                forquillaEsquerra.wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        forquillaEsquerra.setEnUs(true);
    }

    public void agafaForquillaDreta() {
        while (forquillaDreta.getEnUs()) {
            try {
                forquillaDreta.wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        forquillaDreta.setEnUs(true);
    }

    public void deixarForquilles() {
        synchronized (forquillaEsquerra) {
            forquillaEsquerra.setEnUs(false);
            forquillaEsquerra.notifyAll();
        }
        synchronized (forquillaDreta) {
            forquillaDreta.setEnUs(false);
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