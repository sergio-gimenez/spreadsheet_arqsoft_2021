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
public class SumFunction extends Function {

    @Override
    float processFunction(ArrayList<Argument> args) {
        float sum = 0;
        for(Argument arg: args){
            sum += arg.getValue();
        }
        return sum;
    }

    @Override
    public float getValue() {
        return this.processFunction(args);
    }

}
