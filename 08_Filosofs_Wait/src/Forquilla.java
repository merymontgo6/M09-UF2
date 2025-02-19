public class Forquilla {
    private boolean enUs = false; //un flag enUs
    private int numPropietari;
    public static final int LLIURE = -1;


    public Forquilla(int num) {
        this.numPropietari = num;
    }

    public int getNumPropietari() {
        return numPropietari;
    }

    public void setNumPropietari(int numPropietari) {
        this.numPropietari = numPropietari;
    }

    public boolean getEnUs() {
        return enUs;
    }

    public void setEnUs(boolean enUs) {
        this.enUs = enUs;
    }
}