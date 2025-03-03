import java.util.Random;

public class Treballador extends Thread {

    private final float sou_anual_brut;
    private final  int edat_inici_treball;
    private final int edat_fi_treball;
    private int edat_actual;
    private float cobrat;
    private final Random random;

    public Treballador(String nom, float sou_anual_brut, int edat_inici_treball, int edat_fi_treball) {
        super(nom);
        this.sou_anual_brut = sou_anual_brut;
        this.edat_inici_treball = edat_inici_treball;
        this.edat_fi_treball = edat_fi_treball;
        this.edat_actual = 0;
        this.cobrat = 0.0f;
        this.random = new Random();
    }

    private void cobra() {
        cobrat += sou_anual_brut / 12;
    }

    private void pagaImpostos() {
        cobrat -= (sou_anual_brut / 12) * 0.24;
    }

    @Override
    public void run() {
        while (edat_actual < edat_fi_treball) {
            if (edat_actual >= edat_inici_treball) {
                for(int i = 0; i < 12; i++) {
                    cobra();
                    pagaImpostos();
                }
            }
            edat_actual++;
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {}
        }
    }

    public float getCobrat() {
        return cobrat;
    }

    public int getEdat() {
        return edat_actual;
    }
}