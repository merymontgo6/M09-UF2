
import java.util.Random;

public class Filosof implements Runnable {
    //una forquillaDreta i una forquillaEsquerra
    private final Forquilla forquillaDreta;
    private final Forquilla forquillaEsquerra;
    private final Random random;
    private int gana; //un comptador de la gana que té
    private final String nom; //amb un constructor amb nom

    public Filosof(String nom, Forquilla forquillaEsquerra, Forquilla forquillaDreta) {
        this.nom = nom;
        this.forquillaEsquerra = forquillaEsquerra;
        this.forquillaDreta = forquillaDreta;
        this.gana = 0;
        this.random = new Random();
    }
    
    //un mètode menjar 
    public void menjar() {
        if (!forquillaEsquerra.getEnUs()) {
            forquillaEsquerra.setEnUs(true);
            System.out.println("Filòsof: " + nom + " agafa la forquilla esquerra " + forquillaEsquerra.getNum());

            // Intentar agafar la forquilla dreta
            if (!forquillaDreta.getEnUs()) {
                forquillaDreta.setEnUs(true);
                System.out.println("Filòsof: " + nom + " agafa la forquilla dreta " + forquillaDreta.getNum());

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
                System.out.println("Filòsof: " + nom + " deixa l'esquerra (" + forquillaEsquerra.getNum() + ") i espera (dreta ocupada)");
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
            System.out.println("Filòsof: " + nom + " no pot agafar l'esquerra (" + forquillaEsquerra.getNum() + ") i espera");
            try {
                Thread.sleep(500 + random.nextInt(500)); // Esperar entre 0.5s i 1s
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    // un mètode pensar
    public void pensar() {
        System.out.println("Filòsof: " + nom + " està pensant.");
        try {
            Thread.sleep(1000 + random.nextInt(1000)); // pensar entre 1s i 2s
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        gana++;
    }

    // el mètode d'execució
    @Override
    public void run() {
        while (true) {
            pensar();
            menjar();
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
