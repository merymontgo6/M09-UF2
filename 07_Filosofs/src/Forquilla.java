public class Forquilla {
    private int num;

    //Un constructor amb el número 
    public Forquilla(int num) {
        this.num = num;
    }

    //un flag enUs
    private boolean enUs = false;

    //setters i getters

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public boolean getEnUs() {
        return enUs;
    }

    public void setEnUs(boolean enUs) {
        this.enUs = enUs;
    }
}