public class Fil extends Thread {
    private String name;

    public Fil(String name) {
        this.name = name;
    }

    // comportamet 1 i 2:
    @Override
    public void run() {
      
            for (int i = 1; i <= 9; i++) {
                System.out.println(name + " " + i);
            }   
        System.out.println("Termina el fil " + name);
    }

    // comportamet 3:
    /*@Override
    public void run() {
        for (int i = 1; i <= 9; i++) {
            System.out.println(name + " " + i);
            try {
                // pausa per controlar l'ordre d'execució entre fils
                Thread.sleep(100);
            } catch (InterruptedException e) {
            }
        }
        System.out.println("Termina el fil " + name);
    }*/
}