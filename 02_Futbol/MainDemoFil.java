public class MainDemoFil {
    public static void main(String[] args) {
        // Captura del fil actual
        Thread filActual = Thread.currentThread();

        // Mostra les propietats del fil principal
        System.out.println("MainDemoFil.main:");
        System.out.println("Prioritat -> " + filActual.getPriority() + ", Nom -> " + filActual.getName());
        System.out.println("toString() -> " + filActual.toString());
    }   
}
