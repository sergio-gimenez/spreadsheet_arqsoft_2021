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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

    // TODO This shouldn't probably be here
    public boolean isCoordinateValid(String coordinate) {
        // Regex that validates if the coordinate follows the 
        // pattern [letter(s),number(s)]
        Pattern pattern = Pattern.compile("^[a-zA-Z]+\\d+$"); // TODO mayusculas y minusculas?

        Matcher matcher = pattern.matcher(coordinate);
        return matcher.matches();
    }

    public Content processStringToContent(String strContent) throws InvalidFormulaException {
        if (strContent.charAt(0) == '=') {
            String formula = strContent.substring(1);

            try {
                tokenizer.tokenize(formula);
                List<Token> tokens = tokenizer.getTokens();
                List<Token> postfix = ShuntingYard.infixToRpn(tokens);
                return factory.createFormula(postfix);
                
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

        if (isCoordinateValid(coordinate)) {
            Content classifiedContent = processStringToContent(content);
        } else {
            throw new MalformedCoordinateException("Wrong format for coordinate");
        }

        Cell cell = new cellMap.put(coordinate,);
    }

    @Override
    public Content getContent(Cell cell) {

        return null;

    }

    @Override
    public HashMap<Coordinate, Cell> getCellMap() {
        return cellMap;
    }

}
