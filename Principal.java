public class Principal {
    public static void main(String[] args) {
        Fil fil1 = new Fil("Juan");
        Fil fil2 = new Fil("Pepe");
        fil1.start();
        fil2.start();
        System.out.println("Fils iniciats des de Principal.");
    }
}