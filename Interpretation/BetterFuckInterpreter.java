package Interpretation;

import java.util.ArrayList;

import HelperClasses.*;

public class BetterFuckInterpreter {
        ArrayList<Function> compiled;
        int pointer;
        byte[] list;
        Boolean newline;

        public BetterFuckInterpreter(ArrayList<Function> compiled, int arraySize) {
            this(compiled, arraySize, arraySize / 2);
        }

        public BetterFuckInterpreter(ArrayList<Function> compiled, int arraySize, int pointerPos) {
            pointer = pointerPos;
            this.compiled = compiled;
            list = new byte[arraySize];
            newline = false;
        }

        public void run(String MainFunction, ArrayList<Variable> vars) {
            if (compiled == null) {
                System.out.println("\n\033[31mNO COMPILE!\033[0m");
            } else {
                for (int x = 0; x < compiled.size(); x++)
                    if (compiled.get(x).name.equalsIgnoreCase(MainFunction)) {
                        System.out.println("\n\033[33mRUNNING...\033[0m");
                        function(compiled.get(x), vars);
                    }
                System.out.println("\n\033[32mFINISHED\033[0m");
            }
            /*
            for(int x = pointer -10; x <= pointer + 10; x++)
                System.out.print(list[x] + ": ");
            /*MEMORY DUMP*/
        }

        public void loop(Loop l, ArrayList<Variable> vars) {
            while (list[pointer] != 0) {
                for (Operation o : l.Commands) {
                    operation(o, vars);
                }
            }
        }

        public void function(Function f, ArrayList<Variable> vars) {
            for (Operation o : f.Commands) {
                operation(o, vars);
            }
        }

        private void command(Command c, ArrayList<Variable> vars) {
            switch (c.ID) {
                case 0:
                    list[pointer] += getVar(c.Amount, vars);
                    break;
                case 1:
                    pointer += getVar(c.Amount, vars);
                    break;
                case 2:
                    newline = true;
                    int co = getVar(c.Amount, vars);
                    for(int i = 0; i < co; i++)
                        System.out.print((char) list[pointer]);
                    break;
                case 3:
                    int cou = getVar(c.Amount, vars);
                    for(int i = 0; i < cou; i++) {
                        if(newline)
                            System.out.println();
                        newline = false;
                        System.out.print("\033[34mEnter a number:\033[0m");
                        list[pointer] = Byte.parseByte(System.console().readLine());
                    }
                    break;
                default:
                    System.out.println("\n\033[31mERROR OCCURED IN THE INTERPRETATION\033[0m");
                    break;
            }
        }

        private int getVar(Variable v, ArrayList<Variable> vars) {
            if(v.getClass() == Point.class) {
                if(((Point)v).p >= vars.size())
                    return 0;
                if(vars.get(((Point)v).p).getClass() == Point.class && ((Point)vars.get(((Point)v).p)).set) {
                    return ((Point)vars.get(((Point)v).p)).v * ((Point)v).count;
                } else if(vars.get(((Point)v).p).getClass() == Value.class) {
                    return ((Value)vars.get(((Point)v).p)).v * ((Point)v).count;
                } else {
                    return 0;
                }
            }
            return ((Value)v).v;
        }

        private void operation(Operation o, ArrayList<Variable> vars) {
            if (o.getClass() == Call.class) {
                if(((Call)o).name.equalsIgnoreCase("debug")) {
                    if(newline)
                        System.out.println();
                    newline = false;
                    System.out.println("\033[36mDebug Position: " + pointer +"\033[0m");
                    for(int x = Math.max(0,Math.min(pointer + 10,list.length) -20); x <= Math.min(Math.max(0,pointer -10) + 20,list.length); x++) {
                        if(x == pointer)
                            System.out.print("\033[32m" + list[x] + ":\033[0m ");
                        else
                            System.out.print(list[x] + ": ");
                    }
                    System.out.println();
                }
                else {
                    ArrayList<Variable> vs = ((Call)o).Variables;
                    for(int i = 0; i < vs.size(); i++) {
                        if(vs.get(i).getClass() == Point.class && ((Point)vs.get(i)).p < vars.size()) {
                            Point n = ((Point)vs.get(i));
                            n.v = getVar(vars.get(n.p), vars);
                            n.set = true;
                            vs.set(i, n);
                        }
                    }
                    function(compiled.get(((Call) o).ID-1), vs);
                }
            } else if (o.getClass() == Command.class) {
                command((Command) o, vars);
            } else if (o.getClass() == Loop.class) {
                loop((Loop) o, vars);
            }
        }

    }
