

public class Administracio extends Thread {

    private final int num_poblacio_activa = 50;
    private final Treballador[] poblacio_activa;

    public Administracio() {
        poblacio_activa = new Treballador[num_poblacio_activa];
        for (int i = 0; i < num_poblacio_activa; i++) {
            poblacio_activa[i] = new Treballador("Ciutadà-" + i, 25000, 20, 65);
        }
    }

    public static void main(String[] args) {
        Administracio administracio = new Administracio();
        // Inicia
        for (Treballador treballador : administracio.poblacio_activa) {
            treballador.start();
        }

        // Esperar
        for (Treballador treballador : administracio.poblacio_activa) {
            try {
                treballador.join();
            } catch (InterruptedException e) {}
        }

        // Mostrar resultats
        for (int i = 0; i < administracio.poblacio_activa.length; i++) {
            Treballador treballador = administracio.poblacio_activa[i];
            System.out.printf("Ciutadà-%d -> edat: %d / total: %.2f\n",
                i, treballador.getEdat(), treballador.getCobrat());
        }
    }
}
