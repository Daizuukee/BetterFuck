package HelperClasses;

import java.util.ArrayList;

public class Create {

    public static ArrayList<Operation> fromString(String code, ArrayList<String> functionNames) {
        ArrayList<Operation> Commands = new ArrayList<Operation>();
        if (code.length() > 0) {
            while (code.length() > 0) {
                switch (code.charAt(0)) {
                    case '0', '1', '2', '3', '4', '5', '6', '7', '8', '9':
                        int o = 1;
                        Boolean amountEnd = false;
                        while (!amountEnd && o < code.length()) {
                            if (("0123456789").indexOf("" + code.charAt(o)) == -1) // Budge way to check if a character
                                                                                   // is a
                                                                                   // Command
                                amountEnd = true;
                            o++;
                        }
                        int n = Integer.parseInt(code.substring(0, o - 1));
                        code = code.substring(o - 1);
                        Boolean b = false;
                        if (code.charAt(0) == '*') {
                            b = true;
                            code = code.substring(1);
                        }
                        switch (code.charAt(0)) {
                            case '+':
                                Commands.add(new Command(0, 1, b, n));
                                break;
                            case '-':
                                Commands.add(new Command(0, -1, b, n));
                                break;
                            case '>':
                                Commands.add(new Command(1, 1, b, n));
                                break;
                            case '<':
                                Commands.add(new Command(1, -1, b, n));
                                break;
                            case ',':
                                Commands.add(new Command(2, 1, b, n));
                                break;
                            case '.':
                                Commands.add(new Command(3, 1, b, n));
                                break;
                            default:
                                break;
                        }
                        break;
                    case '+':
                        Commands.add(new Command(0));
                        break;
                    case '-':
                        Commands.add(new Command(0, -1));
                        break;
                    case '>':
                        Commands.add(new Command(1));
                        break;
                    case '<':
                        Commands.add(new Command(1, -1));
                        break;
                    case ',':
                        Commands.add(new Command(2));
                        break;
                    case '.':
                        Commands.add(new Command(3));
                        break;
                    case '[':
                        int brackets = 0;
                        int c = 1;
                        while (brackets >= 0) {
                            if (code.charAt(c) == '[')
                                brackets++;
                            if (code.charAt(c) == ']')
                                brackets--;
                            c++;
                        }
                        Commands.add(new Loop(code.substring(1, c - 1), functionNames));
                        code = code.substring(c - 1);
                        break;
                    default:
                        int i = 1;
                        Boolean nameEnd = false;
                        while (!nameEnd) {
                            if (i >= code.length() || code.charAt(i) == '(') // Budge way to check if a character is a
                                                                             // Command
                                nameEnd = true;
                            i++;
                        }
                        if (functionIndex(code.substring(0, i - 1), functionNames) == -1)
                            System.out.println("NO NAME: " + code.substring(0, i - 1));// return null;
                        else {
                            int j = i;
                            ArrayList<Variable> vars = new ArrayList<Variable>();
                            String curnum = "";
                            Boolean varsEnd = false;
                            while (!varsEnd) {
                                if (j >= code.length() || code.charAt(j) == ')') {
                                    try {
                                        if (curnum.charAt(curnum.length() - 1) == '*') {
                                            vars.add(new Point(
                                                    Integer.parseInt(curnum.substring(0, curnum.length() - 1))));
                                        } else {
                                            vars.add(new Value(Integer.parseInt(curnum)));
                                        }
                                    } catch (Exception e) {

                                    }
                                    varsEnd = true;
                                } else if (code.charAt(j) == ';') {
                                    try {
                                        if (curnum.charAt(curnum.length() - 1) == '*') {
                                            vars.add(new Point(
                                                    Integer.parseInt(curnum.substring(0, curnum.length() - 1))));
                                        } else {
                                            vars.add(new Value(Integer.parseInt(curnum)));
                                        }
                                    } catch (Exception e) {

                                    }
                                    curnum = "";
                                } else {
                                    curnum += code.charAt(j);
                                }
                                j++;
                            }
                            Commands.add(new Call(functionIndex(code.substring(0, i - 1), functionNames),
                                    code.substring(0, i - 1), vars));
                            code = code.substring(j - 1);
                        }
                        break;
                }
                code = code.substring(1);
            }
            Operation last = Commands.get(0);
            for (int i = 1; i < Commands.size();) {
                if (Commands.get(i).getClass() == Command.class && last.getClass() == Command.class // Only combine
                                                                                                    // Commands
                        && ((Command) Commands.get(i)).ID == ((Command) last).ID // Check that they are the same Command
                        && ((Command) Commands.get(i)).Amount.getClass() == Value.class && // Check that they do not
                                                                                           // lead to Variables
                        ((Command) last).Amount.getClass() == Value.class // Check that they do not lead to Variables
                ) {
                    ((Value) ((Command) last).Amount).v += ((Value) ((Command) Commands.get(i)).Amount).v;
                    Commands.remove(i);
                } else {
                    last = Commands.get(i);
                    i++;
                }
            }
        }
        return Commands;
    }

    public static int functionIndex(String s, ArrayList<String> functionNames) {
        for (int x = 0; x < functionNames.size(); x++) {
            if (s.equalsIgnoreCase(functionNames.get(x)))
                return x;
        }
        return -1;
    }
}
