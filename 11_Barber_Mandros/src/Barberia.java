import java.util.LinkedList;
import java.util.Queue;

public class Barberia {
    private Queue<Client> salaEspera;
    private int maxCadires;
    private Object condBarber;

    public Barberia(int maxCadires) {
        this.salaEspera = new LinkedList<>();
        this.maxCadires = maxCadires;
        this.condBarber = new Object();
    }

    public Client seguentClient() {
        synchronized (salaEspera) {
            return salaEspera.poll();
        }
    }

    public void entrarClient(Client client) {
        synchronized (salaEspera) {
            if (salaEspera.size() < maxCadires) {
                salaEspera.offer(client);
                System.out.println(client.getNom() + " en espera");
                synchronized (condBarber) {
                    condBarber.notify();
                }
            } else {
                System.out.println("No queden cadires, client " + client.getNom() + " se'n va");
            }
        }
    }

    public void execucio() {
        for (int i = 1; i <= 10; i++) {
            Client client = new Client(i);
            entrarClient(client);
            try {
                Thread.sleep(500); // client 0.5s
            } catch (InterruptedException e) {
            }
        }

        try {
            Thread.sleep(10000); // Espera 10s
        } catch (InterruptedException e) {        }

        for (int i = 11; i <= 20; i++) {
            Client client = new Client(i);
            entrarClient(client);
            try {
                Thread.sleep(500); //
            } catch (InterruptedException e) {            }
        }
    }

    public Object getCondBarber() {
        return condBarber;
    }

    public static void main(String[] args) {
        Barberia barberia = new Barberia(3);
        Barber barber = new Barber("Pepe");
        barber.setBarberia(barberia);
        barber.start();
        barberia.execucio();
    }
}