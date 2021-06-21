/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.upc.etsetb.arqsoft.spreadsheet.entities.content.function;

import edu.upc.etsetb.arqsoft.spreadsheet.entities.ContentException;

/**
 *
 * @author gerard
 */
public class FunctionFactory {
      public static Function getInstance(String s) throws ContentException {

        try {
            if (s != null) {
                s = s.toUpperCase();
                Class functionClass = Function.functionClassMap.get(s);
                
                if (functionClass == null) {
                    throw new ContentException();
                }
                return (Function) functionClass.getConstructor().newInstance();
            }
        } catch (Exception e) {
            throw new ContentException("Function not valid");
        }
        return null;
    }
}
