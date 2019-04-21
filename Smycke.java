/**
 * Maxim Saffarini -19980925
 * Emil Alic - 19980129
 */
public class Smycke extends Vardesak {
    private int antalStenar;
    private boolean material;

    public Smycke(String namn, int antalStenar, boolean material) {
        super(namn);
        this.antalStenar = antalStenar;
        this.material = material;
    }

    public int getAntalStenar() {
        return antalStenar;
    }

    public boolean getMaterial() {
        return material;
    }

    public String getMaterialNamn() {
        if (material) { return "Guld";
        } else { return "Silver"; }
    }

    public double getVarde() {
        double tempVarde;
        if (material) {
            tempVarde = 2000.00;
        } else {
            tempVarde = 700.00;
        }
        return (tempVarde + (500 * antalStenar));
    }

    public String toString() {
        return "Namn: " + getNamn() + ", Material: " + getMaterialNamn()
                + ", Värde: " + getRealVarde();
    }
}