public class Motor extends Thread {
    private int potenciaActual;
    private int potenciaObjectiu;
    private int idMotor;

    //Primerament ha de llegir la potencia al coet, despres demanar la potencia i fer el setpotencia, startall (tots els motors a la vegada començen), despres mentre while potencia <> 0 {llegir();, setPotencia();}
    //Segon, al Motor, while (true) {while(potenciaActual = potenciaObjectiu) ...} despres un sleep(100):, despres si (potencia == 0) break;

    //Inicialment està aturat i per tant la potència objectiu i la potència actuals són 0
    public Motor(int idMotor) {
        this.idMotor = idMotor;
        this.potenciaActual = 0;
        this.potenciaObjectiu = 0;
    }

    //se li estableix la potència objectiu amb el setter
    public void setPotencia(int p) {
        potenciaObjectiu = p;
    }
    
//set potencia, despres start de tots els motors, sense synchronized
//while true com a condicio dels motors
//mentre pob sigui dif poten actual sleep aleatori  i fer que aarribi a potencia actual amb un while
//hi han dos whiles
//sleep 100  quan ja son iguals per donar temps de que s'afegeix un altre potencia
//perimer lectura, set potencia, start, while pote ! 0, while true poob = poobj

//System.out.println("Motor " + idMotor + ": Incre. Objectiu: " + potenciaObjectiu + " Actual: " + potenciaActual);
//System.out.println("Motor " + idMotor + ": FerRes. Objectiu: " + potenciaObjectiu + " Actual: " + potenciaActual);

    @Override
    public void run() {
        boolean haMostratFerRes = false; // Per evitar imprimir FerRes múltiples vegades

        while (true) {
            try {
                if (potenciaActual < potenciaObjectiu) {
                    potenciaActual++;
                    System.out.println("Motor " + idMotor + ": Incre. Objectiu: " + potenciaObjectiu + " Actual: " + potenciaActual);
                    haMostratFerRes = false; // Reset si fem un canvi
                } else if (potenciaActual > potenciaObjectiu) {
                    potenciaActual--;
                    System.out.println("Motor " + idMotor + ": Decre. Objectiu: " + potenciaObjectiu + " Actual: " + potenciaActual);
                    haMostratFerRes = false; // Reset si fem un canvi
                } else if (!haMostratFerRes) {
                    System.out.println("Motor " + idMotor + ": FerRes Objectiu: " + potenciaObjectiu + " Actual: " + potenciaActual);
                    haMostratFerRes = true; // Marquem que ja s'ha mostrat "FerRes"
                }

                if (potenciaObjectiu == 0) {
                    break;
                }
                Thread.sleep(100);
            } catch (InterruptedException ex) {}
        }
    }
}