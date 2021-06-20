/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.upc.etsetb.arqsoft.spreadsheet.entities;

import edu.upc.etsetb.arqsoft.spreadsheet.content.token.TokenizerGenerator;
import edu.upc.etsetb.arqsoft.spreadsheet.content.token.Tokenizer;
import edu.upc.etsetb.arqsoft.spreadsheet.content.Formula;
import edu.upc.etsetb.arqsoft.spreadsheet.content.token.Token;
import edu.upc.etsetb.arqsoft.spreadsheet.content.Number;
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
    public Number createNumber(Float number) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String createText(String content) {
         
    }

    @Override
    public Formula createFormula(List<Token> tokens) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
