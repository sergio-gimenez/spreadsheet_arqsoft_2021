/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.upc.etsetb.arqsoft.spreadsheet.controllers;

import edu.upc.etsetb.arqsoft.spreadsheet.entities.content.Content;
import edu.upc.etsetb.arqsoft.spreadsheet.entities.content.Formula;
import edu.upc.etsetb.arqsoft.spreadsheet.entities.content.FormulaComponent;
import edu.upc.etsetb.arqsoft.spreadsheet.usecases.token.ShuntingYard;
import edu.upc.etsetb.arqsoft.spreadsheet.usecases.token.Token;
import edu.upc.etsetb.arqsoft.spreadsheet.usecases.token.Tokenizer;
import edu.upc.etsetb.arqsoft.spreadsheet.entities.BadCoordinateException;
import edu.upc.etsetb.arqsoft.spreadsheet.entities.Cell;
import edu.upc.etsetb.arqsoft.spreadsheet.entities.ContentException;
import edu.upc.etsetb.arqsoft.spreadsheet.entities.Coordinate;
import edu.upc.etsetb.arqsoft.spreadsheet.entities.NoNumberException;
import edu.upc.etsetb.arqsoft.spreadsheet.entities.Spreadsheet;
import edu.upc.etsetb.arqsoft.spreadsheet.entities.SpreadsheetFactory;
import edu.upc.etsetb.arqsoft.spreadsheet.entities.content.FormulaComponentFactory;
import edu.upc.etsetb.arqsoft.spreadsheet.usecases.formulas.evaluator.FormulaEvaluator;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author gerard
 */
public final class SpreadsheetController {

    private Spreadsheet spreadsheet;
    private SpreadsheetFactory factory;
    private Tokenizer tokenizer;

    public SpreadsheetController(Spreadsheet spreadsheet, SpreadsheetFactory factory) {
        this.spreadsheet = spreadsheet;
        this.factory = factory;
        this.tokenizer = this.factory.getTokenizerInstance();
    }
    
    public void editSpreadsheet(String cellCoord, String content) throws ContentException, BadCoordinateException{
        this.setCellContent(cellCoord, content);
    }

    public void setCellContent(String cellCoord, String content) throws ContentException, BadCoordinateException {
        Coordinate coord = new Coordinate(cellCoord);
        Content classifiedContent = processStringToContent(content);
        this.spreadsheet.setContent(coord, classifiedContent);

    }

    public double getCellContentAsDouble(String coord) throws BadCoordinateException, NoNumberException {
        Coordinate selectedCoord =  new Coordinate(coord);
        Cell cell = this.spreadsheet.getCell(selectedCoord);
                   
        return cell.getContentAsDouble();
    }

    
    public String getCellContentAsString(String coord) throws BadCoordinateException {
        Coordinate selectedCoord =  new Coordinate(coord);
        Cell cell = this.spreadsheet.getCell(selectedCoord);
        return cell.getContentAsString();
    }

    private Content processStringToContent(String strContent) throws ContentException, BadCoordinateException {
        if (strContent.charAt(0) == '=') {
           return this.processFormula(strContent);

        } else {

            try {
                Double number = Double.parseDouble(strContent);
                return factory.createNumber(number);

            } catch (NumberFormatException ex) {
                return factory.createText(strContent);
            }
        }
    }   
    
    private Formula processFormula(String strContent) throws ContentException, BadCoordinateException {
         String formula = strContent.substring(1);
            try {
                tokenizer.tokenize(formula);
                List<Token> tokens = tokenizer.getTokens();
                List<Token> postfix = ShuntingYard.infixToRpn(tokens);
                List<FormulaComponent> components = FormulaComponentFactory.generateFormulaComponentList(tokens, this.spreadsheet);
                Double formulaResult = FormulaEvaluator.getResult(components, spreadsheet);
                return factory.createFormula(formula, components, formulaResult);

            } catch (Tokenizer.ParserException ex) {
                throw new ContentException(ex.getMessage());
            }
    }
    
    
    private void recomputeFormula(Cell cell){
        //obtener formula
        // a partir de su lista de componentes
    }
}
