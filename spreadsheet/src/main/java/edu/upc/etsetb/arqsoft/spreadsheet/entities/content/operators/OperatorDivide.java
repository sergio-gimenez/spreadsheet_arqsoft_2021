/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.upc.etsetb.arqsoft.spreadsheet.entities.content.operators;

import edu.upc.etsetb.arqsoft.spreadsheet.entities.ContentException;
import edu.upc.etsetb.arqsoft.spreadsheet.entities.content.Operand;
import edu.upc.etsetb.arqsoft.spreadsheet.entities.content.Number;

/**
 *
 * @author gerard
 */
public class OperatorDivide extends Operator {

    @Override
    public Number compute(Operand x, Operand y) {
        if (y == null || y.getValue() == 0) {
            //  throw new ContentException("Divided by zero");
            Double value = 0.0;
            return new Number(value);
        }
        return new Number(x.getValue() / y.getValue());
    }
}
