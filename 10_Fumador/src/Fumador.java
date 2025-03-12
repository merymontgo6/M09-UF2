public class Fumador extends Thread {
    private Estanc estanc;
    private int id;
    private Tabac tabac;
    private Llumi llumi;
    private Paper paper;
    private int numFumadas = 0;

    public Fumador(Estanc estanc, int id) {
        this.estanc = estanc;
        this.id = id;
    }

    public void fuma() {
        try {
            
        } catch (Exception e) {
        }
    }

    public void compraTabac() {
        tabac = estanc.venTabac();
    }

    public void compraPaper() {
        paper = estanc.venPaper();
    }

    public void compraLlumi() {
        llumi = estanc.venLlumi();
    }

    @Override
    public void run() {
        while (numFumadas < 3) {
            try {
                compraTabac();
                compraPaper();
                compraLlumi();
                
                if (tabac != null && paper != null && llumi != null) {
                    System.out.println("Fumador " + id + " fumant");
                    fuma();
                    numFumadas++;
                } else {
                    Thread.sleep(100); // Wait if ingredients not available
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
        }
        System.out.println("Fumador " + id + " ha fumat " + numFumadas + " vegades");
    }
}
