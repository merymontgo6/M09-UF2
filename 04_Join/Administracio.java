

public class Administracio extends Thread {

    private final int num_poblacio_activa = 50;
    private final Treballador[] poblacio_activa;

    public Administracio() {
        poblacio_activa = new Treballador[num_poblacio_activa];
        for (int i = 0; i < num_poblacio_activa; i++) {
            poblacio_activa[i] = new Treballador("Ciutadà-" + i, 25000, 20, 65);
        }
    }

    @Override
    public void run() {
        for (Treballador treballador : poblacio_activa) {
            treballador.start();
        }

        for (Treballador treballador : poblacio_activa) {
            try {
                treballador.join();
            } catch (InterruptedException e) {}
        }
    }

    public static void main(String[] args) {
        Administracio administracio = new Administracio();
        administracio.start();
        try {
            administracio.join();
        } catch (InterruptedException e) {}

        for (int i = 0; i < administracio.poblacio_activa.length; i++) {
            Treballador treballador = administracio.poblacio_activa[i];
            System.out.println("Ciutadà " + i + " -> edat: " + treballador.getEdat() + " / total: " + treballador.getCobrat());
        }
    }
}
