import java.util.Random;

public class Futbolista extends Thread{
    private int ngols;
    private int ntirades;

    private static final int num_jugador = 11;
    private static final int num_tirades = 20;
    private static final float probabilitat = 0.5f;
    private static final Random random = new Random();

    public Futbolista(String nomJugador) {
        super(nomJugador);
        this.ngols = 0;
        this.ntirades = 0;
    }

    public void marcarGol() {
        ngols++;
    }

    public void ferTirada() {
        ntirades++;
    }

    public int obtenirGols() {
        return ngols;
    }

    public int obtenirTirades() {
        return ntirades;
    }
    @Override
    public void run() {
        for (int i = 0; i < num_tirades; i++) {
            ntirades++;
            if (random.nextFloat() < probabilitat) {
                ngols++;
            }
        }
    }

    public static void main(String[] args) {
        // 1. Es creen tots els fils
        String[] nomsJugadors = {
            "Piqué", "Vinicius", "Torres", "Ramos", 
            "Ronaldo", "Lewan", "Belli", "Arnau", 
            "Aspas", "Messi", "MBapé"
        };

        Futbolista[] jugadors = new Futbolista[num_jugador];
        for (int i = 0; i < num_jugador; i++) {
            jugadors[i] = new Futbolista(nomsJugadors[i]); // Es crea un fil per a cada jugador
        }

        System.out.println("Inici dels xuts --------------------");

        // 2. S’inicien tots els fils
        for (Futbolista jugador : jugadors) {
            jugador.start(); // Inicia el fil
        }

        // 3. S’espera a tots els fils a que acabin
        for (Futbolista jugador : jugadors) {
            try {
                jugador.join(); // Espera que el fil acabi
            } catch (InterruptedException e) {
            }
        }

        System.out.println("Fi dels xuts -----------------------");
        System.out.println("--- Estadístiques ------");

        // 4. Es mostren les estadístiques dels xuts
        for (Futbolista jugador : jugadors) {
            System.out.println(jugador.getName() + " -> " + jugador.obtenirGols() + " gols");
        }
    }
}