import java.util.Random;

public class Barber extends Thread {
    private String nom;
    private Barberia barberia;

    public Barber(String nom) {
        this.nom = nom;
    }

    @Override
    public void run() {
        while (true) {
            Client client = barberia.seguentClient();
            if (client != null) {
                System.out.println("Li toca al client " + client.getNom());
                System.out.println("Tallant cabell a " + client.getNom());
                try {
                    Thread.sleep(900 + new Random().nextInt(100)); // Temps de tall: 0.9s + random 0.1s
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } else {
                System.out.println("Ningú en espera");
                System.out.println("Barber " + nom + " dormint");
                synchronized (barberia.getCondBarber()) {
                    try {
                        barberia.getCondBarber().wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    public void setBarberia(Barberia barberia) {
        this.barberia = barberia;
    }
}