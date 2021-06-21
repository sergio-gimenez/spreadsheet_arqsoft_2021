/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.upc.etsetb.arqsoft.spreadsheet.entities.content.function;

import edu.upc.etsetb.arqsoft.spreadsheet.entities.content.Argument;
import java.util.ArrayList;

/**
 *
 * @author sergio
 */
public class MinFunction extends Function{

    @Override
    double processFunction(ArrayList<Argument> args) {
        double min = 0;
        for (Argument arg : args) {
            if (min > arg.getValue()) {
                min = arg.getValue();
            }
        }
        return min;
    }
     
}
