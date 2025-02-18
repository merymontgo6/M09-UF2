public class Forquilla {
    private int num;
    private boolean enUs = false; //un flag enUs

    //Un constructor amb el número 
    public Forquilla(int num) {
        this.num = num;
    }

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