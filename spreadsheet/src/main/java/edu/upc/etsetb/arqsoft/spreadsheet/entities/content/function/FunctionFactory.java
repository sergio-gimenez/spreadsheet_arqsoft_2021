/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.upc.etsetb.arqsoft.spreadsheet.entities.content.function;

/**
 *
 * @author gerard
 */
public class FunctionFactory {
      public static Function getInstance(String s) throws InvalidFunctionException {

        try {
            if (s != null) {
                s = s.toUpperCase();
                Class functionClass = Function.functionClassMap.get(s);
                
                if (functionClass == null) {
                    throw new InvalidFunctionException();
                }
                return (Function) functionClass.getConstructor().newInstance();
            }
        } catch (Exception e) {
            throw new InvalidFunctionException();
        }
        return null;
    }
}
