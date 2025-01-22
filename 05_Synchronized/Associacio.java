

public class Associacio extends Thread {
    private final int numSocis = 1000; //numSocis = 1000
    private final Soci[] socis; //socis[]

    //Un constructor
    private Associacio() {
        this.socis = new Soci[numSocis];
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
        for (int i = 0; i <= numSocis; i++) {
            System.out.println("Saldo: " + socis[i].getCompte().getSaldo());
        }
    }

    public static void main(String[] args) {
        Associacio associacio = new Associacio();

        for(Soci soci : associacio.socis) {
            soci.start();
        }
        for(Soci soci : associacio.socis) {
            try {
                soci.join();
            } catch (InterruptedException e) {
            }
        }
        //Associacio, new associacio se li dona compte i per compte s'anomena els metodes.
        for (int i = 0; i < associacio.numSocis; i++) {
            associacio.esperaPeriodeSocis();
            associacio.mostraBalancComptes();
            associacio.iniciaCompteTempsSocis();
        }
    }

}
