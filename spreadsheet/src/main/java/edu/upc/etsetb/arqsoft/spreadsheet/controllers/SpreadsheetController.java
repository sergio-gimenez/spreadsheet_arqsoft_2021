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
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author gerard
 */
public final class SpreadsheetController {

    private Spreadsheet spreadsheet;
    private SpreadsheetFactory factory;
    private Tokenizer tokenizer;
    private Map<Coordinate, Set<Coordinate>> dependenciesMap;

    public SpreadsheetController(Spreadsheet spreadsheet, SpreadsheetFactory factory) {
        this.spreadsheet = spreadsheet;
        this.factory = factory;
        this.tokenizer = this.factory.getTokenizerInstance();
    }

    public void editSpreadsheet(String cellCoord, String content) throws ContentException, BadCoordinateException {
        this.setCellContent(cellCoord, content);
    }

    public void setCellContent(String cellCoord, String content) throws ContentException, BadCoordinateException {
        Coordinate coord = new Coordinate(cellCoord);
        Content classifiedContent = processStringToContent(coord, content);
        this.spreadsheet.setContent(coord, classifiedContent);

    }

    public double getCellContentAsDouble(String coord) throws BadCoordinateException, NoNumberException {
        Coordinate selectedCoord = new Coordinate(coord);
        Cell cell = this.spreadsheet.getCell(selectedCoord);

        return cell.getContent().getValueAsDouble();
    }

    public String getCellContentAsString(String coord) throws BadCoordinateException {
        Coordinate selectedCoord = new Coordinate(coord);
        Cell cell = this.spreadsheet.getCell(selectedCoord);
        return cell.getContent().getText();
    }

    private Content processStringToContent(Coordinate coord, String strContent) throws ContentException, BadCoordinateException {
        if (strContent.charAt(0) == '=') {
            return this.processFormula(coord, strContent);

        } else {

            try {
                Double number = Double.parseDouble(strContent);
                return factory.createNumber(number);

            } catch (NumberFormatException ex) {
                return factory.createText(strContent);
            }
        }
    }

    private Formula processFormula(Coordinate coord, String strContent) throws ContentException, BadCoordinateException {
        String formulaText = strContent.substring(1);
        try {
            tokenizer.tokenize(formulaText);
            List<Token> tokens = tokenizer.getTokens();
            List<Token> postfix = ShuntingYard.infixToRpn(tokens);
            List<FormulaComponent> components = FormulaComponentFactory.generateFormulaComponentList(postfix, this.spreadsheet);
            Double formulaResult = FormulaEvaluator.getResult(components, spreadsheet);
            Formula formula =  factory.createFormula(formulaText, components, formulaResult);
            this.checkCircularDependencies(coord, formula);
            return formula;

        } catch (Tokenizer.ParserException | NoNumberException ex) {
            throw new ContentException(ex.getMessage());
        }
    }

//    private void recomputeDependencies(List<FormulaComponent> components) {
//    private void checkCircularDependencies(Coordinate cellCoord, List<FormulaComponent> components) throws ContentException {
//        Set<Coordinate> coordinates = new HashSet<Coordinate>();
//
//        for (FormulaComponent component : components) {
//            if (component instanceof Cell) {
//                Cell cell = (Cell) component;
//                coordinates.add(cell.getCoordinate());
//            }
//        }
//        for (Coordinate coord : coordinates) {
//            Set<Coordinate> otherDependencies = this.dependenciesMap.get(coord);
//            otherDependencies.add(cellCoord);
//
//        }
//
//        Set<Coordinate> myDependencies = this.dependenciesMap.get(cellCoord);
//        for (Coordinate coord : myDependencies) {
//            Set<Coordinate> dependencies = this.dependenciesMap.get(coord);
//            if (dependencies.contains(cellCoord)) {
//                throw new ContentException("Cincular dependy found");
//            }
//        }
//    }
    private void checkCircularDependencies(Coordinate coord, Formula formula) throws ContentException {
        Set<Coordinate> dependencies = getRecurrentDependenciesOfFormula(formula);
        if (dependencies.contains(coord)) {
            throw new ContentException("Content has circular dependencies");
        }
    }

    private Set<Coordinate> getRecurrentDependenciesOfFormula(Formula formula) {
        Set<Coordinate> dependencies = new HashSet();
        Set<Coordinate> directDependencies = getDependenciesOfFormula(formula);
        dependencies.addAll(directDependencies);

        for (Coordinate coord : directDependencies) {
            Content component = this.spreadsheet.getCell(coord).getContent();
            if (component instanceof Formula) {
                dependencies.addAll(getRecurrentDependenciesOfFormula((Formula) component));
            }
        }
        return dependencies;
    }

    private Set<Coordinate> getDependenciesOfFormula(Formula formula) {
        Set<Coordinate> dependencies = new HashSet<>();
        for (FormulaComponent component : formula.getFormulaComponents()) {
            if (component instanceof Cell) {
                Cell cell = (Cell) component;
                Coordinate coord = cell.getCoordinate();
                if (!dependencies.contains(coord)) {
                    dependencies.add(coord);
                }
            }
        }
        return dependencies;
    }

}
