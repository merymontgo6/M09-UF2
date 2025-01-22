

public class Compte {

    private float saldo; //amb un saldo

    public Compte() {
        this.saldo = 0;
    }

     //el patró singleton implementat de manera que garanteixi que només existeix una instància.
    private static Compte instancia;
    public static Compte getInstance() {
        if (instancia == null) {
            instancia = new Compte(); // Crea la instància si no existeix
        }
        return instancia;
    }

    //El getter i setter de saldo.
    public float getSaldo() {
        return saldo;
    }

    public float setSaldo(float saldo) {
        this.saldo = saldo;
        return saldo;
    }
}