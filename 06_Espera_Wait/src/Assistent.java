package src;
import java.util.Random;

public class Assistent extends Thread {
    //té un Esdeveniment
    private final Esdeveniment esdeveniment;
    private final Random random;

    //un constructor amb un nom i Esdeveniment
    public Assistent(String nom, Esdeveniment e) {
        super(nom);
        esdeveniment = e;
        random = new Random();
    }
    //en l'execució ha de executar eternament:
    //50% de probabilitats de fer una reserva
    //50% de probabilitats de cancelar una reserva
    //espera un temps aleatori entre 0 i 1 segon
    //es fa amb wait() i notifyAll()
    //si es fa una reserva i no hi ha places disponibles s'ha de quedar esperant
    //si es cancel·la una reserva i no hi ha ningú esperant s'ha de quedar esperant
    //si es cancel·la una reserva i hi ha algú esperant s'ha de notificar
    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(random.nextInt(1000));
                if (random.nextBoolean()) { // 50% de probabilitats de fer una reserva
                    esdeveniment.ferReserva(this);
                } else {
                    esdeveniment.cancelaReserva(this);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
