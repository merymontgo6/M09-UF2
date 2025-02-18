public class Taula extends Thread {
    private final Filosof[] comensals; //amb uns comensals(Filosofs)
    private final Forquilla[] forquilles; //amb unes forquilles(Forquilla)

    //Un constructor amb el número de filòsofs que cree els filòsofs, les forquilles i les assignacions
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

    //un metode showTaula que mostri cada filosof i les seves forquilles
    public void showTaula() {
        for (Filosof filosof : comensals) {
            System.out.println("Comensal: " + filosof.getNom() + 
            " esq: " + filosof.getForquillaEsquerra().getNum() + 
            " dret: " + filosof.getForquillaDreta().getNum());
        }
    }

    //un metode cridarATaula que inicii els filosofs de la taula
    public void cridarATaula() {
        for (Filosof filosof : comensals) {
            new Thread(filosof).start();
        }
    }
}
