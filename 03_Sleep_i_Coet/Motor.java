import java.util.Random;

public class Motor extends Thread {
    private int potenciaActual;
    private int potenciaObjectiu;
    private int idMotor;

    public Motor(int idMotor) {
        this.idMotor = idMotor;
        this.potenciaActual = 0; // Inicialmente aturado
        this.potenciaObjectiu = 0;
    }

    public int getPotenciaActual() {
        return potenciaActual;
    }

    public void setPotencia(int p) {
        potenciaObjectiu = p;
        this.start(); // Comienza el hilo para cambiar la potencia
    }

    @Override
    public void run() {
        // Se incrementa o decrementa la potencia en pasos de 1
        int increment = (potenciaActual < potenciaObjectiu) ? 1 : -1;

        try {
            // Cambia la potencia en pasos de 1 hasta alcanzar la potencia objetivo
            while (potenciaActual != potenciaObjectiu) {
                potenciaActual += increment; // Incrementa o decrementa en pasos de 1
                System.out.println("Motor " + idMotor + ": Incre. Objectiu: " + potenciaObjectiu + " Actual: " + potenciaActual);
                Thread.sleep(new Random().nextInt(1000) + 1000); // Tiempo aleatorio entre 1 y 2 segundos
            }

            // Cuando un motor llega a su potencia objetivo, muestra que terminó
            System.out.println("Motor " + idMotor + ": FerRes. Objectiu: " + potenciaObjectiu + " Actual: " + potenciaActual);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
