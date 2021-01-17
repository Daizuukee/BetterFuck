package HelperClasses;

public class Point implements Variable {
    public final int p;
    public final int count;
    public int v;
    public Boolean set;

    public Point(int p) {
        this.p = p;
        count = 1;
        set = false;
    }
    
    public Point(int p, int count) {
        this.p = p;
        this.count = count;
        set = false;
    }
    
    @Override
    public String toString() {
        return p + "*";
    }
}
