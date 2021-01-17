package HelperClasses;

public class Command implements Operation {
    public final int ID;
    public Variable Amount;

    public Command(int ID) {
        this.ID = ID;
        Amount = new Value(1);
    }

    public Command(int ID, int Amount) {
        this.ID = ID;
        this.Amount = new Value(Amount);
    }

    public Command(int ID, int Amount, Boolean b, int Point) {
        this.ID = ID;
        if (b) {
            this.Amount = new Point(Point,Amount);
        } else {
            this.Amount = new Value(Point*Amount);
        }
    }

    @Override
    public String toString() {
        if (Amount.getClass() == Value.class)
            return "Command: " + ID + " amount: " + Amount + "\n";
        return "Command: " + ID + " Point: " + Amount + " Action: " + ((Point)Amount).count + "\n";
    }
}