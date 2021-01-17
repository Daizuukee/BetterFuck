package HelperClasses;

import java.util.ArrayList;

public class Loop implements Operation {
    public final ArrayList<Operation> Commands;

    public Loop(String code, ArrayList<String> functionNames) {
        Commands = Create.fromString(code, functionNames);
    }

    @Override
    public String toString() {
        String s = "Loop { \n";
        for (Operation o : Commands) {
            s += o;
        }
        return s + "}\n";
    }
}