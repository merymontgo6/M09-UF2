public class Assistent extends Thread {
    //té un Esdeveniment
    private final Esdeveniment esdeveniment;
    //un constructor amb un nom i Esdeveniment
    public Assistent(String nom, Esdeveniment e) {
        super(nom);
        esdeveniment = e;
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
            synchronized (esdeveniment) {
                try {
                    // Espera un temps aleatori abans d'intentar fer una reserva o cancel·lació
                    esdeveniment.wait((int) (Math.random() * 1000));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                // Genera aleatòriament si es fa una reserva o cancel·lació
                if (Math.random() < 0.5) {
                    esdeveniment.ferReserva(this);
                } else {
                    esdeveniment.cancelaReserva(this);
                }

                // Despertar tots els fils per comprovar l'estat després de cada acció
                esdeveniment.notifyAll();
            }
        }
    }
}
