public class Client {
    private String nom;

    public Client(int id) {
        this.nom = "Client-" + id;
    }

    public void tallarseElCabell() {
        System.out.println(nom + " tallant-se el cabell");
    }

    public String getNom() {
        return nom;
    }
}