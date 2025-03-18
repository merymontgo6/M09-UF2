public class Barri {
    private Estanc estanc;
    private Fumador[] fumadors = new Fumador[3];

    public Barri() {
        estanc = new Estanc(new java.util.ArrayList<>(), new java.util.ArrayList<>(), new java.util.ArrayList<>());
        for (int i = 0; i < 3; i++) {
            fumadors[i] = new Fumador(estanc, i);
        }
    }

    public void start() {
        // Creem un fil per a l'Estanc
        Thread estancThread = new Thread(estanc);
        estancThread.start();

        // Creem un fil per a cada fumador
        Thread[] fumadorThreads = new Thread[3];
        for (int i = 0; i < 3; i++) {
            fumadorThreads[i] = new Thread(fumadors[i]);
            fumadorThreads[i].start();
        }

        // Esperem a que tots els fumadors acabin
        for (Thread fumadorThread : fumadorThreads) {
            try {
                fumadorThread.join();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

        // Tanquem l'estanc un cop han acabat tots els fumadors
        estanc.tancarEstanc();

        // Esperem a que l'estanc tanqui
        try {
            estancThread.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public static void main(String[] args) {
        Barri barri = new Barri();
        barri.start();
    }
}