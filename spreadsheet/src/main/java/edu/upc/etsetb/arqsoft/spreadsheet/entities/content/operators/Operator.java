/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.upc.etsetb.arqsoft.spreadsheet.entities.content.operators;

import edu.upc.etsetb.arqsoft.spreadsheet.entities.content.FormulaComponent;
import edu.upc.etsetb.arqsoft.spreadsheet.entities.content.Operand;
import edu.upc.etsetb.arqsoft.spreadsheet.entities.content.Number;

import java.util.HashMap;

/**
 *
 * @author osboxes
 */
public abstract class Operator implements FormulaComponent {

    public abstract Number compute(Operand firstOperand, Operand secondOperand);

    public static final HashMap<String, OperatorEnum> operatorStringMap = new HashMap<>();
    static {
        operatorStringMap.put("+", OperatorEnum.ADD);
        operatorStringMap.put("-", OperatorEnum.SUBSTRACT);
        operatorStringMap.put("*", OperatorEnum.MULTIPLY);
        operatorStringMap.put("/", OperatorEnum.DIVIDE);
    }

    public static final HashMap<OperatorEnum, Class> operatorsClassMap = new HashMap<>();

    static {
        operatorsClassMap.put(OperatorEnum.ADD, OperatorAdd.class);
        operatorsClassMap.put(OperatorEnum.SUBSTRACT, OperatorSubstract.class);
        operatorsClassMap.put(OperatorEnum.MULTIPLY, OperatorMultiply.class);
        operatorsClassMap.put(OperatorEnum.DIVIDE, OperatorDivide.class);
    }

    public static boolean isValidOperator(String operator) {
        return operatorStringMap.containsKey(operator);
    }
    
}
