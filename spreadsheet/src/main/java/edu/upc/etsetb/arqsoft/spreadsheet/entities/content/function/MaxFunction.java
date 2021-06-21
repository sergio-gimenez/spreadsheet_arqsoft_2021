/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.upc.etsetb.arqsoft.spreadsheet.entities.content.function;

import edu.upc.etsetb.arqsoft.spreadsheet.entities.content.Argument;
import java.util.List;

/**
 *
 * @author sergio
 */
public class MaxFunction extends Function {

    @Override
    double processFunction(List<Argument> args) {
        double max = 0;
        for (Argument arg : args) {
            if (max < arg.getValue()) {
                max = arg.getValue();
            }
        }
        return max;
    }
}
