public class Taula extends Thread {
    //amb uns comensals(Filosofs)
    private Filosof[] comensals;

    //amb unes forquilles(Forquilla)
    private Forquilla[] forquilles;

    //Un constructor amb el número de filòsofs que cree els filòsofs, les forquilles i les assignacions
    public Taula(int num) {
        comensals = new Filosof[num];
        forquilles = new Forquilla[num];
        
        //asignació de cada forquilla i cada filosof
        for (int i = 0; i < num; i++) {
            forquilles = new Forquilla[i];
        }
        for (int i = 0; i < num; i++) {
            comensals = new Filosof[i];
        }
    }

    //un metode showTaula que mostri cada filosof i les seves forquilles
    public void showTaula() {
        for (Filosof filosof : comensals) {
            for (Forquilla forquilla : forquilles) {
                System.out.println("Comensal: " + filosof + " esq: " + forquilla + "dret: " + forquilla);
            }
        }
    }

    //un metode cridarATaula que inicii els filosofs de la taula
    public void cridarATaula() {
        for (Filosof filosof : comensals) {
            new Thread(filosof).start();
        }
    }

    public static void main(String[] args) {
        Taula taula = new Taula(4);
        taula.showTaula();
        taula.cridarATaula();
    }
}
