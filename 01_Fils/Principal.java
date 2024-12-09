public class Principal {
    //main comportament 1
    /*public static void main(String[] args){
        Fil fil1 = new Fil("Juan");
        Fil fil2 = new Fil("Pepe");
        fil1.start();
        fil2.start();

        System.out.println("Termina thread main");
    }*/

    //main comportyament 2
    public static void main(String[] args){
        Fil fil1 = new Fil("Juan");
        Fil fil2 = new Fil("Pepe");
        fil1.setPriority(Thread.MIN_PRIORITY); 
        fil2.setPriority(Thread.MAX_PRIORITY); 

        fil2.start();
        fil1.start();

        System.out.println("Termina thread main");
    }

}

