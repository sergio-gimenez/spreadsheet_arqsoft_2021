/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.upc.etsetb.arqsoft.spreadsheet.entities;

import edu.upc.etsetb.arqsoft.spreadsheet.content.token.Tokenizer;
import edu.upc.etsetb.arqsoft.spreadsheet.content.token.Token;
import edu.upc.etsetb.arqsoft.spreadsheet.content.InvalidFormulaException;
import edu.upc.etsetb.arqsoft.spreadsheet.content.ShuntingYard;
import edu.upc.etsetb.arqsoft.spreadsheet.content.Content;
import edu.upc.etsetb.arqsoft.spreadsheet.content.FormulaComponent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author osboxes
 */
public class SpreadsheetImpl implements Spreadsheet {

    private SpreadsheetFactory factory;
    private HashMap<Coordinate, Cell> cellMap;
    private Tokenizer tokenizer;

    public SpreadsheetImpl(SpreadsheetFactory factory) {
        this.factory = factory;
        this.cellMap = new HashMap<Coordinate, Cell>();
        this.tokenizer = this.factory.getTokenizerInstance();
    }

    @Override
    public Cell getCell(Coordinate coordinate) {
        return this.cellMap.get(coordinate);
    }

    public Content processStringToContent(String strContent) throws InvalidFormulaException {
        if (strContent.charAt(0) == '=') {
            String formula = strContent.substring(1);

            try {
                tokenizer.tokenize(formula);
                List<Token> tokens = tokenizer.getTokens();
                List<Token> postfix = ShuntingYard.infixToRpn(tokens);
                
                List<FormulaComponent> components = new ArrayList<>();
                
                return factory.createFormula(components);
                
            } catch (Tokenizer.ParserException ex) {
                throw new InvalidFormulaException(ex.getMessage());
            }

        } else {

            try {
                Float number = Float.parseFloat(strContent);
                return factory.createNumber(number);

            } catch (NumberFormatException ex) {
                return factory.createText(strContent);
            }
        }
        return null;
    }

    @Override
    public void setContent(String coordinate, String content) throws MalformedCoordinateException, InvalidFormulaException {

        Content classifiedContent = processStringToContent(content);
                Cell cell = new cellMap.put(coordinate, classifiedContent);
    }

    @Override
    public Content getContent(Cell cell) {

        return null;

    }

    @Override
    public HashMap<Coordinate, Cell> getCellMap() {
        return cellMap;
    }

    @Override
    public void setContent(Coordinate coordinate, String content) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
