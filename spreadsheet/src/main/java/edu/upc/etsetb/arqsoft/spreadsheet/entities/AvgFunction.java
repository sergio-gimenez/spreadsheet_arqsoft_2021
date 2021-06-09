/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.upc.etsetb.arqsoft.spreadsheet.entities;

import java.util.ArrayList;

/**
 *
 * @author sergio
 */
public class AvgFunction extends Function {

    @Override
    public float getValue() {
        return this.processFunction(args);
    }

    @Override
    float processFunction(ArrayList<Argument> args) {
        float res = 0;
        for (Argument arg : args) {
            res += arg.getValue();
        }
        res = res / args.size();
        return res;
    }
    
    
}
