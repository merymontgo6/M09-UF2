import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

//Primerament ha de llegir la potencia al coet, despres demanar la potencia i fer el setpotencia, si llegir i set potencia son iguals startall (tots els motors a la vegada començen), despres mentre while potencia <> 0 {llegir();, setPotencia();}
//Segon, al Motor, while (true) {while(potenciaActual = potenciaObjectiu) ...} despres un sleep(100):, despres si (potencia == 0) break;

public class Coet {
    private Motor[] motors;

    public Coet() {
        motors = new Motor[4]; // 4 motors
        for (int i = 0; i < 4; i++) {
            motors[i] = new Motor(i); // Asignar num a cada motor
        }
    }

    //Primerament ha de llegir la potencia al coet
    public int llegir() throws NumberFormatException, IOException { 
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        
        System.out.print("Introdueix la potència objectiu (0 per aturar): ");
        int potencia = Integer.parseInt(reader.readLine());
        return potencia;
    }

    //Despres demanar la potencia i fer el setpotencia
    public void passaAPotencia(int p) throws NumberFormatException, IOException {
        while (true) {
            do {
                p = llegir();
                if (p < 0 || p > 10) {
                    System.out.println("Error: La potència ha de ser entre 0 i 10.");
                }
            } while (p < 0 || p > 10); // Solicita nuevamente si el valor no es válido.
    
            System.out.println("Passant a potència " + p);
            for (Motor motor : motors) {
                motor.setPotencia(p);
            }
            startAll();
        }
    }

    //si llegir i set potencia son iguals startall, aixo ho fa el metode run de Motor.java
    public void startAll() {
        for (Motor motor : motors) {
            new Thread(motor).start();
        }
    }

    public static void main(String[] args) throws IOException {
        Coet coet = new Coet();
        coet.passaAPotencia(0);
    }
}