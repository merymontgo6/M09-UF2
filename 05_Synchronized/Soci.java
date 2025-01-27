import java.util.Random;

public class Soci extends Thread {

    //té la instància de Compte
    private final Compte compte;

    private final float aportacio = 10f; //la aportacio = 10f
    private final int esperaMax = 100; //esperaMax=100
    private final Random random; //Random
    private final int maxAnys = 10; //maxAnys=10

    //un constructor
    public Soci (Compte compte) {
        this.random = new Random();
        this.compte = compte;
    }
    
    //getter de compte
    public Compte getCompte() {
        return compte;
    }
    //mètode d’execució on
    //s’itera al llarg dels anys que van fins a maxAnys
    //s’itera al llarg dels mesos
    //els mesos parells fa un ingrés de la quota i
    //els mesos imparells la treu

    @Override
    public void run() {
        for (int i = 1; i <= maxAnys; i++) { // Iterar per anys
            for (int j = 1; j <= 12; j++) { // Iterar per mesos
                try {
                    if (j % 2 == 0) { // Mesos parells: fer ingrés
                        float nouSaldo = compte.getSaldo() + aportacio;
                        compte.setSaldo(nouSaldo);
                    } else { // Mesos imparells: fer retirada
                        float nouSaldo = compte.getSaldo() - aportacio;
                        compte.setSaldo(nouSaldo);
                    }
                    // Simular espera aleatòria
                    Thread.sleep(random.nextInt(esperaMax));
                } catch (InterruptedException e) {}
            }
        }
    }
}