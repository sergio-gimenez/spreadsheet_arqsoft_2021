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
public class SumFunction extends Function {

    @Override
    double processFunction(List<Argument> args) {
        double sum = 0;
        for(Argument arg: args){
            sum += arg.getValue();
        }
        return sum;
    }
}
