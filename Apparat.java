/**
 * Maxim Saffarini -19980925
 * Emil Alic - 19980129
 */
public class Apparat extends Vardesak {
    private double inkopspris;
    private int slitage;

    public Apparat(String namn, double inkopspris, int slitage) {
        super(namn);
        this.inkopspris = inkopspris;
        this.slitage = slitage;
    }

    public int getSlitage() {
        return slitage;
    }

    public double getVarde() {
        return (inkopspris * (slitage/10.0));
    }

    public String toString() {
        return "Namn: " + getNamn() + ", Inköpspris: " + inkopspris
                + ", Slitage: " + getSlitage() + ", Värde: " + getRealVarde();
    }
}