public class Taula extends Thread{
    private final Filosof[] comensals;
    private final Forquilla[] forquilles;

    public Taula(int num) {
        comensals = new Filosof[num];
        forquilles = new Forquilla[num];
        
        //asignació de cada forquilla i cada filosof
        for (int i = 0; i < num; i++) {
            forquilles[i] = new Forquilla(Forquilla.LLIURE);
        }
        for (int i = 0; i < num; i++) {
            comensals[i] = new Filosof("fil" + i, forquilles[i], forquilles[(i + 1) % num], i);
        }
    }

    public void showTaula() {
        for (Filosof filosof : comensals) {
            System.out.println("Comensal: " + filosof.getNom() + 
            " esq: " + filosof.getForquillaEsquerra().getNumPropietari() + 
            " dret: " + filosof.getForquillaDreta().getNumPropietari());
        }
    }

    public void cridarATaula() {
        for (Filosof filosof : comensals) {
            new Thread(filosof).start();
        }
    }

}
