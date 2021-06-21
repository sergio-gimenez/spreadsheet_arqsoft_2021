/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.upc.etsetb.arqsoft.spreadsheet.entities.content.operators;

import edu.upc.etsetb.arqsoft.spreadsheet.entities.content.Operand;
import edu.upc.etsetb.arqsoft.spreadsheet.entities.content.Number;

/**
 *
 * @author gerard
 */
public class OperatorMultiply extends Operator {

    @Override
    public Number compute(Operand x, Operand y) {
        double result = (x != null ? x.getValue() : 0) * (y != null ? y.getValue() : 0);
        return new Number(result);
    }
}
