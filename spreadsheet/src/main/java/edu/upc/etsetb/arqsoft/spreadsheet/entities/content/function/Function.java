/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.upc.etsetb.arqsoft.spreadsheet.entities.content.function;

import edu.upc.etsetb.arqsoft.spreadsheet.entities.content.Number;
import edu.upc.etsetb.arqsoft.spreadsheet.entities.content.Argument;
import edu.upc.etsetb.arqsoft.spreadsheet.entities.content.Operand;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author osboxes
 */
public abstract class Function implements Operand, Argument {
    
    public ArrayList<Argument> args;
    
    abstract double processFunction(ArrayList<Argument> args);
    
    @Override
    public double getValue() {
        return this.processFunction(args);
    }

    
    public static final HashMap<String,Class> functionClassMap = new HashMap<>();
    static {
        functionClassMap.put(FunctionEnum.SUM.toString(), SumFunction.class);
        functionClassMap.put(FunctionEnum.AVG.toString(), AvgFunction.class);
        functionClassMap.put(FunctionEnum.MAX.toString(), MaxFunction.class);
        functionClassMap.put(FunctionEnum.MIN.toString(), MinFunction.class);
    }
    
    public static boolean isValidFunction(String s) {
        s = s.toUpperCase();
        return functionClassMap.containsKey(s);
    }
    
}
