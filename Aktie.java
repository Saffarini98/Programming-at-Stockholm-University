/**
 * Maxim Saffarini -19980925
 * Emil Alic - 19980129
 */
public class Aktie extends Vardesak {
    private int antal;
    private double kurs;

    public Aktie(String namn, int antal, double kurs) {
        super(namn);
        this.antal = antal;
        this.kurs = kurs;
    }

    public double getKurs() {
        return kurs;
    }

    public int getAntal() {
        return antal;
    }

    public void setKurs(double nyKurs) {
        this.kurs = nyKurs;
    }

    public double getVarde() {
        return (antal * kurs);
    }

    public String toString() {
        return "Namn: " + getNamn() + ", Antal: " + getAntal() + ", Kurs: " + getKurs() + ", Värde: "
                + getRealVarde();
    }
}