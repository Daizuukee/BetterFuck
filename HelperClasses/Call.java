package HelperClasses;

import java.util.ArrayList;

public class Call implements Operation {
    public final int ID;
    public final String name;
    public final ArrayList<Variable> Variables;

    public Call(int ID, String name, ArrayList<Variable> Variables) {
        this.ID = ID;
        this.name = name;
        this.Variables = Variables;
    }

    @Override
    public String toString() {
        String vars = " Variables: ";
        for (Variable i : Variables)
            vars += i + "; ";
        return "Call: " + name + ": " + ID + vars + "\n";
    }
}