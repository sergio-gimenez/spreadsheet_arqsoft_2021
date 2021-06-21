/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.upc.etsetb.arqsoft.spreadsheet.entities.content.operators;

import edu.upc.etsetb.arqsoft.spreadsheet.entities.ContentException;

/**
 *
 * @author gerard
 */
public class OperatorFactory {

    public static Operator getInstance(String s) throws ContentException {

        try {
            if (s != null) {
                Class operatorClass = Operator.operatorsClassMap.get(Operator.operatorStringMap.get(s));

                if (operatorClass == null) {
                    throw new ContentException();
                }
                return (Operator) operatorClass.getConstructor().newInstance();
            }
        } catch (Exception e) {
            throw new ContentException();
        }
        return null;
    }
}
