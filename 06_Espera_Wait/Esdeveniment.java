
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
    public synchronized void ferReserva(Assistent a) {
        while (placesDisponibles >= 0) {
            try {
                System.out.println("Assistent-" + assistents.getName() + " no ha pogut fer una reserva inexistent. Places disponibles:" + placesDisponibles);
                wait();
            } catch (InterruptedException e) {}
            if (assistents.contains(a)) {
                assistents.remove(a);
                placesDisponibles++;
            }
            System.out.println("Assistent-" + assistents.getName() + " ha fet una reserva. Places disponibles:" + placesDisponibles);
            notifyAll();
        }

    }
    //un mètode cancelaReserva(Assistent) que si l'Assistent està a la llista l'elimini i incrementi el numero de placesDisponibles()
    public synchronized void cancelaReserva(Assistent a) {
        while(placesDisponibles <=0) {
            try {
                System.out.println("Assistent-" + assistents.getName() + " no ha pogut cancel·lar una reserva inexistent. Places disponibles:" + placesDisponibles);
                wait();
            } catch (InterruptedException e) {}
            placesDisponibles++;
            System.out.println("Assistent-" + assistents.getName() + " ha cancel·lat una reserva. Places disponibles:" + placesDisponibles);
        }
        
    }
}
