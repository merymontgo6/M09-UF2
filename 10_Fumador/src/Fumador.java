import java.util.Random;

public class Fumador implements Runnable {
    private final Estanc estanc;
    private final int id;
    private Tabac tabac;
    private Llumi llumi;
    private Paper paper;
    private int numFumades = 0;

    public Fumador(Estanc estanc, int id) {
        this.estanc = estanc;
        this.id = id;
    }

    public void fuma() throws InterruptedException{
        try {
            System.out.println("Fumador " + id + " fumant");
            Thread.sleep(500 + new Random().nextInt(500));
            tabac = null;
            paper = null;
            llumi = null;
            numFumades++;
            System.out.println("Fumador " + id + " ha fumat " + numFumades + " vegades");
        } catch (InterruptedException e) {
        }
    }

    public void compraTabac() throws InterruptedException {
        tabac = estanc.venTabac();
        System.out.println("Fumador " + id + " comprant Tabac");
    }

    public void compraPaper() throws InterruptedException {
        paper = estanc.venPaper();
        System.out.println("Fumador " + id + " comprant Paper");
    }

    public void compraLlumi() throws InterruptedException {
        llumi = estanc.venLlumi();
        System.out.println("Fumador " + id + " comprant Llumí");
    }

    @Override
    public void run() {
        try {
            while (numFumades < 3) {
                compraTabac();
                compraPaper();
                compraLlumi();
                fuma();
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }     
    }
}