import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Coet {
    private Motor[] motors;

    public Coet() {
        motors = new Motor[4]; // 4 motores
        for (int i = 0; i < 4; i++) {
            motors[i] = new Motor(i); // Asignar un id a cada motor
        }
    }

    public void passaAPotencia(int p) {
        if (p < 0 || p > 10) {
            System.out.println("Error: La potència ha de ser entre 0 i 10.");
            return;
        }
        // Establece la potencia en todos los motores
        System.out.println("Passant a potència " + p);
        for (Motor motor : motors) {
            motor.setPotencia(p);
        }
    }

    public void arranca() {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            int potencia;
            do {
                System.out.print("Introdueix la potència objectiu (0 per aturar): ");
                potencia = Integer.parseInt(reader.readLine());
                if (potencia != 0) {
                    passaAPotencia(potencia);
                }
            } while (potencia != 0);
            System.out.println("Coet aturat.");
        } catch (IOException | NumberFormatException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Coet coet = new Coet();
        coet.arranca();
    }
}
