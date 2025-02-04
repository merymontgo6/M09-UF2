package src;
import java.util.ArrayList;
import java.util.List;

public class Esdeveniment{
    //amb una List<Assistent>
    private final List<Assistent> assistents;
    //variable placesDisponibles
    private int placesDisponibles;
    //un constructor que accepti el número de places màxim
    public Esdeveniment(int placesInicials) {
        this.placesDisponibles = placesInicials;
        this.assistents = new ArrayList<>();
    }
    
    //un mètode ferReserva(Assistent) que afegeixi a l'assistent a la llista i resti un a placesDisponibles()
    public synchronized void ferReserva(Assistent a) throws InterruptedException {
        while (placesDisponibles == 0) { //Espera si no hi ha reserves
            try {
                System.out.println("Assistent " + a.getName() + " no ha pogut fer una reserva inexistent. Places disponibles:" + placesDisponibles);
                wait();
            } catch (InterruptedException e) {}
        } 
        assistents.add(a);
        placesDisponibles--;
        System.out.println("Assistent " + a.getName() + " ha fet una reserva. Places disponibles:" + placesDisponibles);
        notifyAll(); // Notifica als altres que una plaça s'ha ocupat

    }
    //un mètode cancelaReserva(Assistent) que si l'Assistent està a la llista l'elimini i incrementi el numero de placesDisponibles()
    public synchronized void cancelaReserva(Assistent a) {
        while (!assistents.contains(a)) {
            return;
        }
        assistents.remove(a);
        placesDisponibles++;
        System.out.println("Assistent " + a.getName() + " ha cancel·lat una reserva. Places disponibles: " + placesDisponibles);
        notifyAll(); // Notifica que hi ha una plaça lliure
    }
}
