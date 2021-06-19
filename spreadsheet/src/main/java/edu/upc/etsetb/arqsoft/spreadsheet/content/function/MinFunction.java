/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.upc.etsetb.arqsoft.spreadsheet.content.function;

import edu.upc.etsetb.arqsoft.spreadsheet.content.Argument;
import java.util.ArrayList;

/**
 *
 * @author sergio
 */
public class MinFunction extends Function{

    @Override
    float processFunction(ArrayList<Argument> args) {
        float min = 0;
        for (Argument arg : args) {
            if (min > arg.getValue()) {
                min = arg.getValue();
            }
        }
        return min;
    }
    @Override
    public float getValue() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
