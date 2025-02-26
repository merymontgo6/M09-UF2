import java.util.Random;

public class Filosof implements Runnable{
    private int iniciGana;
    private int fiGana;
    private int gana; 
    private final Forquilla forquillaEsquerra;
    private final Forquilla forquillaDreta;
    private final String nom;
    private final Random random = new Random();

    public Filosof (String nom, Forquilla forquillaEsquerra, Forquilla forquillaDreta) {
        this.nom = nom;
        this.forquillaEsquerra = forquillaEsquerra;
        this.forquillaDreta = forquillaDreta;
    }

    public void menjar() {
        forquillaEsquerra.agafar();
        forquillaDreta.agafar();
        gana = (int) System.currentTimeMillis() - iniciGana;
        System.out.println("Filòsof: " + nom + " menja");
        //FALTA: Després espera reseteja la gana i deixa les forquilles
        forquillaEsquerra.deixar();
        forquillaDreta.deixar();
    }

    public void agafarForquilles() {
        agafarForquillaEsquerra();
        agafarForquillaDreta();
        System.out.println("Filòsof: " + nom + " ha agafat les dues forquilles");
    }

    public void agafarForquillaEsquerra() {
        forquillaEsquerra.agafar();
        System.out.println("Filòsof: " + nom + " agafa la forquilla esquerra " + forquillaEsquerra.getNum());
    }

    public void agafarForquillaDreta() {
        forquillaDreta.agafar();
        System.out.println("Filòsof: " + nom + " agafa la forquilla dreta " + forquillaDreta.getNum());
    }

    public void deixarForquilles() {
        forquillaDreta.deixar();
        forquillaEsquerra.deixar();
        System.out.println("Filòsof: " + nom + " ha deixat les forquilles");
    }

    public void pensar() {
        //iniciGana es un comptador de temps en segons real
        iniciGana = (int) System.currentTimeMillis();
        System.out.println("Filòsof: " + nom + " està pensant.");
        try {
            Thread.sleep(1000 + random.nextInt(1000)); // pensar entre 1s i 2s
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        //gana++;
    }

    @Override
    public void run() {
        pensar();
        agafarForquilles();
        menjar();
        deixarForquilles();
    }

    public void calcularGana() {

    }

    public void resetGana() {
        gana = 0;
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
