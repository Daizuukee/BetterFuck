package HelperClasses;

public class Value implements Variable {
    public int v;

    public Value(int v) {
        this.v = v;
    }

    @Override
    public String toString() {
        return "" + v;
    }
}
