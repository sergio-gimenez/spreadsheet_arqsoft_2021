/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.upc.etsetb.arqsoft.spreadsheet.entities;

import edu.upc.etsetb.arqsoft.spreadsheet.content.token.TokenizerGenerator;
import edu.upc.etsetb.arqsoft.spreadsheet.content.token.Tokenizer;
import edu.upc.etsetb.arqsoft.spreadsheet.content.Formula;
import edu.upc.etsetb.arqsoft.spreadsheet.content.FormulaComponent;
import edu.upc.etsetb.arqsoft.spreadsheet.content.Number;
import edu.upc.etsetb.arqsoft.spreadsheet.content.Text;
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
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Text createText(String content) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Formula createFormula(List<FormulaComponent> components) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
