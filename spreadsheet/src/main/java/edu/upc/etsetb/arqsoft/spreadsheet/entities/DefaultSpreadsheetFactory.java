/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.upc.etsetb.arqsoft.spreadsheet.entities;

import edu.upc.etsetb.arqsoft.spreadsheet.usecases.token.TokenizerGenerator;
import edu.upc.etsetb.arqsoft.spreadsheet.usecases.token.Tokenizer;
import edu.upc.etsetb.arqsoft.spreadsheet.entities.content.Formula;
import edu.upc.etsetb.arqsoft.spreadsheet.entities.content.FormulaComponent;
import edu.upc.etsetb.arqsoft.spreadsheet.entities.content.Number;
import edu.upc.etsetb.arqsoft.spreadsheet.entities.content.Text;
import java.util.List;

/**
 *
 * @author gerard
 */
public class DefaultSpreadsheetFactory extends SpreadsheetFactory {

    @Override
    public Tokenizer getTokenizerInstance() {
        return TokenizerGenerator.getInstance();
    }

    @Override
    public Number createNumber(Double number) {
       return new Number(number); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Text createText(String content) {
        return new Text(content);}

    @Override
    public Formula createFormula(String text, List<FormulaComponent> components, Double value) {
        return new Formula(text,components, value);
    }

}
