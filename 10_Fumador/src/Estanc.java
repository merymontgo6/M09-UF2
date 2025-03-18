import java.util.List;
import java.util.Random;

public class Estanc implements Runnable{
    private final List<Tabac> tabac;
    private final List<Llumi> llumis;
    private final List<Paper> paper;
    private boolean obert = true;
    private final Random random = new Random();
    
    public Estanc (List<Tabac> tabac, List<Llumi> llumis, List<Paper> paper) {
        this.tabac = tabac;
        this.llumis = llumis;
        this.paper = paper;
    }

    public synchronized void nouSubministrament() {
        int opcio = random.nextInt(3);
        switch (opcio) {
            case 0:
                addTabac();
                break;
            case 1:
                addPaper();
                break;
            case 2:
                addLlumi();
                break;
        }
    }

    public synchronized void addTabac() {
        tabac.add(new Tabac());
        System.out.println("Afegint tabac");
        notifyAll();
    }

    public synchronized void addPaper() {
        paper.add(new Paper());
        System.out.println("Afegint Paper");
        notifyAll();
    }

    public synchronized void addLlumi() {
        llumis.add(new Llumi());
        System.out.println("Afegint Llumí");
        notifyAll();
    }

    public synchronized Tabac venTabac() throws InterruptedException {
        while (tabac.isEmpty() && obert) {
            wait();
        }
        if (!obert) return null;
        return tabac.remove(0);
    }

    public synchronized Paper venPaper() throws InterruptedException {
        while (paper.isEmpty() && obert) {
            wait();
        }
        if (!obert) return null;
        return paper.remove(0);
    }

    public synchronized Llumi venLlumi() throws InterruptedException {
        while (llumis.isEmpty() && obert) {
            wait();
        }
        if (!obert) return null;
        return llumis.remove(0);
    }

    public synchronized void tancarEstanc() {
        obert = false;
        notifyAll();
    }

    @Override
    public void run() {
        System.out.println("Estanc obert");
        while (obert) {
            nouSubministrament();
            try {
                Thread.sleep(500 + random.nextInt(1000));
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        System.out.println("Estanc tancat");
    }
}
