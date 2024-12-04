public class Fil extends Thread {
    String name;
    public Fil(String name) {
        this.name = name;
    }

    //Comportament 1
    @Override
    public void run() {
        try {
            for (int i = 1; i <= 9; i++) {
                System.out.println(name + " " + i);
                Thread.sleep(100);
            }
        } catch (InterruptedException e) {
            System.out.println("El fil ha estat interromput.");
        }
        System.out.println("Termina el fil " + name);
    }
}