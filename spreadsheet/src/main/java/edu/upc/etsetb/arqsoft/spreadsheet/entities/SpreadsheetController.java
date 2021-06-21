/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.upc.etsetb.arqsoft.spreadsheet.entities;

import edu.upc.etsetb.arqsoft.spreadsheet.content.Content;
import edu.upc.etsetb.arqsoft.spreadsheet.content.Formula;
import edu.upc.etsetb.arqsoft.spreadsheet.content.FormulaComponent;
import edu.upc.etsetb.arqsoft.spreadsheet.content.ShuntingYard;
import edu.upc.etsetb.arqsoft.spreadsheet.content.token.Token;
import edu.upc.etsetb.arqsoft.spreadsheet.content.token.Tokenizer;
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

    private Content processStringToContent(String strContent) throws ContentException {
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

    
    
    private Formula processFormula(String strContent) throws ContentException {
         String formula = strContent.substring(1);
            try {
                tokenizer.tokenize(formula);
                List<Token> tokens = tokenizer.getTokens();
                List<Token> postfix = ShuntingYard.infixToRpn(tokens);
                List<FormulaComponent> components = new ArrayList<>();
                //COnseguir lista de formula components
                //Obtener el valor de la formula
                //construir la formula con valor, lista y string
                return factory.createFormula(components);

            } catch (Tokenizer.ParserException ex) {
                throw new ContentException(ex.getMessage());
            }
    }
    
    
    private void recomputeFormula(Cell cell){
        //obtener formula
        // a partir de su lista de componentes
    }
}
