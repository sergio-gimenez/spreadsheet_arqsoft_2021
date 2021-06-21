/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.upc.etsetb.arqsoft.spreadsheet.entities.content.operators;

/**
 *
 * @author gerard
 */
public class OperatorFactory {

    public static Operator getInstance(String s) throws OperatorInvalidException {

        try {
            if (s != null) {
                Class operatorClass = Operator.operatorsClassMap.get(Operator.operatorStringMap.get(s));

                if (operatorClass == null) {
                    throw new OperatorInvalidException();
                }
                return (Operator) operatorClass.getConstructor().newInstance();
            }
        } catch (Exception e) {
            throw new OperatorInvalidException();
        }
        return null;
    }
}
