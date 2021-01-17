import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import HelperClasses.*;

import Interpretation.*;

public class BetterFuck {

    public static void main(String[] args) throws FileNotFoundException {
        if(args.length > 0) {
        File codeFile = new File(args[0]);
        String input = "";
        Scanner sc = new Scanner(codeFile);
        while(sc.hasNext())
            input += sc.next();
        sc.close();
        ArrayList<Function> f = new BetterFuck().compile(input);
        
        
        
        /*for(Function funk : f)
            System.out.println(funk);
        /* DEBUG DUMP OF THE FUNCTIONS */
        new BetterFuckInterpreter(f, 50000,0).run((args.length > 1)?args[1]:"main",new ArrayList<Variable>());
        }
        else {
            System.out.println("\n\033[31mNO FILE!\033[0m");
        }
    }

    public ArrayList<Function> compile(String code) {
        ArrayList<String> functionNames = new ArrayList<String>();
        functionNames.add("debug");
        ArrayList<String> functionCodes = new ArrayList<String>();
        functionCodes.add("debug");
        ArrayList<Function> output = new ArrayList<Function>();

        code = code.replaceAll("/\\*(.|[\r\n])*?\\*/", "");
        code = code.replaceAll("( |\n|\t)", "");
        System.out.println("\n\033[33mCOMPILING...\033[0m");

        while (code.length() > 0) {
            int i = 1;
            Boolean nameEnd = false;
            while (!nameEnd) {
                if (code.charAt(i) == '{') // Budge way to check if a character is the start of a Function
                    nameEnd = true;
                i++;
            }
            String name = code.substring(0, i - 1);
            functionNames.add(name);
            code = code.substring(i);
            i = 0;
            Boolean codeEnd = false;
            while (!codeEnd) {
                if (code.charAt(i) == '}') // Budge way to check if a character is the end of a Function
                    codeEnd = true;
                i++;
            }
            functionCodes.add(code.substring(0, i - 1));
            code = code.substring(i);
        }
        for (int x = 0; x < functionNames.size()-1; x++) {
            output.add(new Function(functionNames.get(x+1), functionCodes.get(x+1), functionNames));
            if (output.get(x) == null) {
                System.out.println("\n\033[31mFAILED TO COMPILE!\033[0m");
                return null;
            }
        }
        System.out.println("\n\033[32mCOMPILED\033[0m");
        return output;
    }
}