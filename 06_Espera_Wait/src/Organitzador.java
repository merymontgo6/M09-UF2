package src;
import java.util.ArrayList;
import java.util.List;

public class Organitzador extends Thread {
    public static void main(String[] args) {
        //crea un Esdeveniment amb 5 places màxim
        Esdeveniment esdeveniment = new Esdeveniment(5);
        //crea 10 assistents
        List<Assistent> assistents = new ArrayList<>();

        //  afegeicx 10 assistents a la llista
        for (int i = 0; i < 10; i++) {
            Assistent assistent = new Assistent("Assistent " + i, esdeveniment);
            assistents.add(assistent);
        }
        //els inicia
        for (Assistent assistent : assistents) {
            assistent.start();
        }
    }
}
