import java.util.List;

public class Estanc {
    private final List<Tabac> tabac;
    private final List<Llumi> llumis;
    private final List<Paper> paper;
    private boolean obert = true;

    public Estanc (List<Tabac> tabac, List<Llumi> llumis, List<Paper> paper) {
        this.tabac = tabac;
        this.llumis = llumis;
        this.paper = paper;
    }

    public void nouSubministrament() {
        try {
            Thread.sleep((int)(Math.random() * 501) + 500);
            
            switch((int)(Math.random() * 3)) {
                case 0 -> { 
                    addTabac(new Tabac());
                    System.out.println("Afegint Tabac");
                }
                case 1 -> { 
                    addLlumi(new Llumi());
                    System.out.println("Afegint Llumi");
                }
                case 2 -> { 
                    addPaper(new Paper());
                    System.out.println("Afegint Paper");
                }
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public void addTabac(Tabac tabac) {
        this.tabac.add(tabac);
    }

    public void addLlumi(Llumi llumi) {
        this.llumis.add(llumi);
    }

    public void addPaper(Paper paper){
        this.paper.add(paper);
    }

    public Tabac venTabac() {
        return tabac.remove(0);
    }
    public Paper venPaper() {
        return paper.remove(0);
    }

    public Llumi venLlumi() {
        return llumis.remove(0);
    }

    public void tancarEstanc() {
        //ha de aturar el metode d'execucio
        Thread.currentThread().interrupt();
    }

    public void run() {
        try {
            nouSubministrament();
            Thread.sleep((int)(Math.random() * 1001) + 500);
        } catch (InterruptedException e) {
            obert = false;
            Thread.currentThread().interrupt();
        }
    }
}
