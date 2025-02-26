public class Taula extends Thread{
    private final Filosof[] comensals;
    private final Forquilla[] forquilles;

    public Taula(int num) {
        comensals = new Filosof[num];
        forquilles = new Forquilla[num];
        
        //asignació de cada forquilla i cada filosof
        for (int i = 0; i < num; i++) {
            forquilles[i] = new Forquilla(i);
        }
        for (int i = 0; i < num; i++) {
            comensals[i] = new Filosof("fil" + i, forquilles[i], forquilles[(i + 1) % num]);
        }
    }

    public void showTaula() {
        for (Filosof filosof : comensals) {
            System.out.println("Comensal: " + filosof.getNom() + 
            " esq: " + filosof.getForquillaEsquerra().getNum() + 
            " dret: " + filosof.getForquillaDreta().getNum());
        }
    }

    public void cridarATaula() {
        for (Filosof filosof : comensals) {
            new Thread(filosof).start();
        }
    }

    //El mètode principal que mosti la taula i cridi a taula
    public static void main (String[] args) {
        Taula taulal = new Taula(4);
        taulal.showTaula();
        taulal.cridarATaula();
    }

}