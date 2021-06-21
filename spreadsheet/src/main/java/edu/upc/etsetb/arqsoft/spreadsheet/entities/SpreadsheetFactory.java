/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.upc.etsetb.arqsoft.spreadsheet.entities;

import edu.upc.etsetb.arqsoft.spreadsheet.usecases.token.Tokenizer;
import edu.upc.etsetb.arqsoft.spreadsheet.entities.content.Formula;
import edu.upc.etsetb.arqsoft.spreadsheet.entities.content.FormulaComponent;
import edu.upc.etsetb.arqsoft.spreadsheet.entities.content.Text;
import edu.upc.etsetb.arqsoft.spreadsheet.entities.content.Number;
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

    public abstract Number createNumber(Double number);

    public abstract Text createText(String content);
    
    public abstract Formula createFormula(List<FormulaComponent> components);      
    
    public abstract Tokenizer getTokenizerInstance();

}
