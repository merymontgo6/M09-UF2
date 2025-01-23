

public class Associacio extends Thread {
    private final int numSocis = 1000; //numSocis = 1000
    private final Soci[] socis; //socis[]

    //Un constructor
    public Associacio(Compte compte) {
        this.socis = new Soci[numSocis];
        for (int i = 0; i < numSocis; i++) {
            this.socis[i] = new Soci(compte); // Inicializar cada Soci con el Compte pasado
        }
    }
    //iniciaCompteTempsSocis
    private void iniciaCompteTempsSocis() {
        for (Soci soci : socis) {
            soci.start(); // Inicia cada fil de soci
        }
    }

    //esperaPeriodeSocis
    private void esperaPeriodeSocis() {
        for (Soci soci : socis) {
            try {
                soci.join(); // Espera que el fil de cada soci acabi
            } catch (InterruptedException e) {}
        }
    }
    
    //mostraBalancComptes
    private void mostraBalancComptes() {
        for (int i = 0; i < numSocis; i++) {
            System.out.println("Saldo: " + socis[i].getCompte().getSaldo());
        }
    }
    //Associacio, new associacio se li dona compte i per compte s'anomena els metodes.
    public static void main(String[] args) {
        // Crear una instancia de Compte y pasarla a Associacio
        Compte compte = Compte.getInstance();
        Associacio associacio = new Associacio(compte);
        
        associacio.iniciaCompteTempsSocis();
        associacio.esperaPeriodeSocis();
        associacio.mostraBalancComptes();
    }
}
