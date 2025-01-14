public class DormAleatori extends Thread {
    
    private final long creacio;

    public DormAleatori(String nom) {
        super(nom);
        this.creacio = System.currentTimeMillis();
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++ ){ //10 vegades
            // Generar un interval aleatori
            int intervalAleatori = (int) (Math.random() * 1000); // intervalAleatori
            long tempsTotal = System.currentTimeMillis() - creacio; //temps de la creació del fil

            System.out.println(getName() + "(" + i + ") a dormir " + intervalAleatori + " ms total " + tempsTotal);

            try { // Dormir durant l'interval aleatori generat
                Thread.sleep(intervalAleatori);
            } catch (InterruptedException e) {}
        }
    }

    public static void main (String[] args) throws InterruptedException{

        String[] noms = {"Joan", "Pep"};
        DormAleatori[] dorms = new DormAleatori[noms.length]; //array de fils per noms

        for (int i = 0; i < noms.length; i++) {
            dorms[i] = new DormAleatori(noms[i]); //es crea els fils
            dorms[i].start();
        }
        Thread.sleep(50);
        System.out.println("Fi de main --------------------");
    }
}