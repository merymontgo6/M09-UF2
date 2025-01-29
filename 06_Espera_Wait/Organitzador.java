public class Organitzador extends Thread {
    //crea un Esdeveniment amb 5 places màxim
    private final Esdeveniment esdeveniment = new Esdeveniment(5);
    //crea 10 assistents
    private final Assistent[] assistents = new Assistent[10];
    //els inicia
    private void iniciaAssistent() {
        for (Assistent assistent : assistents) {
            assistent.start();
        }
    }

    public static void main(String[] args) {
        Organitzador organitzador = new Organitzador();
        organitzador.iniciaAssistent();
        

    }
}
