public class Fil extends Thread {
    private String name;

    public Fil(String name) {
        this.name = name;
    }

    // comportamet 1:
    @Override
    public void run() {
      
            for (int i = 1; i <= 9; i++) {
                System.out.println(name + " " + i);
            }   
        System.out.println("Termina el fil " + name);
    }


}