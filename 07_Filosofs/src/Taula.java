public class Taula extends Thread {
    private Filosof[] comensals; //amb uns comensals(Filosofs)
    private Forquilla[] forquilles; //amb unes forquilles(Forquilla)

    //Un constructor amb el número de filòsofs que cree els filòsofs, les forquilles i les assignacions
    public Taula(int num) {
        comensals = new Filosof[num];
        forquilles = new Forquilla[num];
        
        //asignació de cada forquilla i cada filosof
        for (int i = 0; i < num; i++) {
            forquilles = new Forquilla[i];
        }
        for (int i = 0; i < num; i++) {
            comensals[i] = new Filosof("fil" + i, forquilles[i], forquilles[(i + 1) % num]);
        }
    }

    //un metode showTaula que mostri cada filosof i les seves forquilles
    public void showTaula() {
        for (Filosof filòsof : comensals) {
            System.out.println("Comensal: " + filòsof.getNom() + 
            " esq: " + filòsof.getForquillaEsquerra().getNum() + 
            " dret: " + filòsof.getForquillaDreta().getNum());
        }
    }

    //un metode cridarATaula que inicii els filosofs de la taula
    public void cridarATaula() {
        for (Filosof filosof : comensals) {
            new Thread().start();
        }
    }

    public static void main(String[] args) {
        Taula taula = new Taula(4);
        taula.showTaula();
        taula.cridarATaula();
    }
}
