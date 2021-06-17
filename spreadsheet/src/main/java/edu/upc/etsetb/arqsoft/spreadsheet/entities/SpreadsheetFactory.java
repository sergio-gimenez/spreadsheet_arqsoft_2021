/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.upc.etsetb.arqsoft.spreadsheet.entities;

import java.util.List;

/**
 *
 * @author gerard
 */
public abstract class SpreadsheetFactory {

    public static SpreadsheetFactory getInstance(String type) throws UnknownFactoryExcepcion {

        if (type.toUpperCase().equals("DEFAULT")) {
            return new DefaultSpreadsheetFactory();
        } else {
            throw new UnknownFactoryExcepcion("Unknown factory of type " + type);
        }
    }

    public abstract Number createNumber(Float number);

    public abstract Text createText(String content);
    
    public abstract Formula createFormula(List<Token> tokens);      
    
    public abstract Tokenizer getTokenizerInstance();

}
