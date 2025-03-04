import java.util.Random;

public class Filosof implements Runnable{
    private long iniciGana;
    private long fiGana;
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
    
    public void menjar() throws InterruptedException{
        fiGana = System.currentTimeMillis();
        gana = calcularGana();
        System.out.println(nom + " menja amb gana " + gana);

        Thread.sleep(1000 + random.nextInt(1000));
        
        System.out.println(nom + " ha acabat de menjar");
        ganaCero();
    }
    
    private void ganaCero() {
        gana = 0;
    }

    private int calcularGana() {
        gana = (int) ((System.currentTimeMillis() - iniciGana) / 1000);
        return gana;
    }

    public void agafarForquilles() {
        agafarForquillaEsquerra();
        agafarForquillaDreta();
        System.out.println(nom + " té forquilles esq(" + 
            forquillaEsquerra.getNum() + ") dreta(" + 
            forquillaDreta.getNum() + ")");    
    }

    public void agafarForquillaEsquerra() {
        forquillaEsquerra.agafar();
    }

    public void agafarForquillaDreta() {
        forquillaDreta.agafar();
    }

    public void deixarForquilles() {
        forquillaDreta.deixar();
        forquillaEsquerra.deixar();
        System.out.println("Filòsof: " + nom + " ha deixat les forquilles");
    }

    public void pensar() throws InterruptedException {
        System.out.println(nom + " pensant");
        iniciGana = (int) System.currentTimeMillis();
        Thread.sleep(1000 + random.nextInt(1000));
    }

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) { // Millor pràctica
            try {
                pensar();
                agafarForquilles();
                menjar();
                deixarForquilles();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.out.println(nom + " ha estat interromput");
                break;
            }
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
