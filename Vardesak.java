/**
 * Maxim Saffarini -19980925
 * Emil Alic - 19980129
 */

abstract public class Vardesak implements Comparable <Vardesak>{


    public int compareTo(Vardesak o){
        return this.namn.compareToIgnoreCase(o.getNamn());
    }
    private String namn;

    public Vardesak(String namn) {
        this.namn = namn;
    }

    public String getNamn() {
        return namn;
    }

    abstract public double getVarde();

    public double getRealVarde() {
        return getVarde() * 1.25;
    }
}