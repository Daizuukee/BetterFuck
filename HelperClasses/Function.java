package HelperClasses;

import java.util.ArrayList;

public class Function {
    public final String name;
    public final ArrayList<Operation> Commands;

    public Function(String name, String code, ArrayList<String> functionNames) {
        this.name = name;

        Commands = Create.fromString(code, functionNames);
    }

    @Override
    public String toString() {
        String s = name + " { \n";
        for (Operation o : Commands) {
            s += o;
        }
        return s + "}\n";
    }
}
