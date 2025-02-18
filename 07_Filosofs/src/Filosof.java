
import java.util.Random;

public class Filosof implements Runnable {
    //una forquillaDreta i una forquillaEsquerra
    private Forquilla forquillaDreta;
    private Forquilla forquillaEsquerra;
    private Random random;
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
        if (forquillaEsquerra.getEnUs() == false && forquillaDreta.getEnUs() == false) {
            forquillaEsquerra.setEnUs(true);
            forquillaDreta.setEnUs(true);
            
            System.out.println(nom + " està menjant.");
            try {
                Thread.sleep(1000 + random.nextInt(1000));
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            
            System.out.println(nom + " ha acabat de menjar.");
            forquillaDreta.setEnUs(false);
            forquillaEsquerra.setEnUs(false);
            gana = 0;
        } else {
            // Esperar un tiempo antes de volver a intentarlo
            try {
                Thread.sleep(500 + random.nextInt(500));
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
    //@Override
    public void run() {
        while (true) {
            pensar();
            menjar();
        }
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
