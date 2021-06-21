/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.upc.etsetb.arqsoft.spreadsheet.entities.content.function;

import edu.upc.etsetb.arqsoft.spreadsheet.entities.content.Argument;
import edu.upc.etsetb.arqsoft.spreadsheet.entities.content.function.Function;
import java.util.List;

/**
 *
 * @author sergio
 */
public class AvgFunction extends Function {
    @Override
    double processFunction(List<Argument> args) {
        double res = 0;
        for (Argument arg : args) {
            res += arg.getValue();
        }
        res = res / args.size();
        return res;
    }
    
    
}
