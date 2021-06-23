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
import java.util.HashMap;
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
        this.dependenciesMap = new HashMap<Coordinate, Set<Coordinate>>();
    }

    public void editSpreadsheet(String cellCoord, String content) throws ContentException, BadCoordinateException, NoNumberException {
        this.setCellContent(cellCoord, content);
    }

    private void setCellContent(String cellCoord, String content) throws ContentException, BadCoordinateException, NoNumberException {
        Coordinate coord = new Coordinate(cellCoord);
        Cell cell = this.spreadsheet.getCell(coord);
        Content oldContent;
        if (cell == null) {

            oldContent = null;
        } else {
            oldContent = cell.getContent();
        }
        Content newContent = processStringToContent(coord, content);
        this.spreadsheet.setContent(coord, newContent);

        Set<Coordinate> oldDependencies = null, newDependencies = null;
        if (oldContent instanceof Formula) {
            oldDependencies = getDependenciesOfFormula((Formula) oldContent);
        }
        if (newContent instanceof Formula) {
            newDependencies = getDependenciesOfFormula((Formula) newContent);
        }

        Set<Coordinate> toRemove, toAdd;
        toRemove = getLeftElements(oldDependencies, newDependencies);
        toAdd = getLeftElements(newDependencies, oldDependencies);

        removeDependencies(toRemove, coord);
        addDependencies(toAdd, coord);

        update(coord);
    }

    private void update(Coordinate coord) throws ContentException, BadCoordinateException, NoNumberException {
        Set<Coordinate> toUpdate = this.dependenciesMap.get(coord);
        if (toUpdate == null) {
            return;
        }
        for (Coordinate c : toUpdate) {
            Content updatedContent = this.updateFormula(c);
            this.spreadsheet.setContent(c, updatedContent);
            update(c);
        }
    }

    private Set<Coordinate> getLeftElements(Set<Coordinate> left, Set<Coordinate> right) {
        if (left == null || left.isEmpty()) {
            return null;
        }
        if (right == null || right.isEmpty()) {
            return left;
        }

        Set<Coordinate> difLeft = new HashSet<>();
        for (Coordinate idx : left) {
            if (!right.contains(idx)) {
                difLeft.add(idx);
            }
        }
        return difLeft;
    }

    private void addDependencies(Set<Coordinate> toAdd, Coordinate index) {
        if (toAdd == null) {
            return;
        }
        for (Coordinate idx : toAdd) {
            if (this.dependenciesMap.containsKey(idx)) {
                this.dependenciesMap.get(idx).add(index);
            } else {
                Set<Coordinate> dps = new HashSet<>();
                dps.add(index);
                this.dependenciesMap.put(idx, dps);
            }
        }
    }

    private void removeDependencies(Set<Coordinate> toRemove, Coordinate index) {
        if (toRemove == null) {
            return;
        }
        for (Coordinate idx : toRemove) {
            if (this.dependenciesMap.containsKey(idx)) {
                this.dependenciesMap.get(idx).remove(index);
            }
        }
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
            Formula formula = factory.createFormula(formulaText, components, formulaResult);
            this.checkCircularDependencies(coord, formula);
            return formula;

        } catch ( NoNumberException ex) {
            throw new ContentException(ex.getMessage());
        }
    }

    private Formula updateFormula(Coordinate coord) throws ContentException, BadCoordinateException, NoNumberException {
        Content content = this.spreadsheet.getCell(coord).getContent();
        String formulaText = content.getText();
        tokenizer.tokenize(formulaText);
        List<Token> tokens = tokenizer.getTokens();
        List<Token> postfix = ShuntingYard.infixToRpn(tokens);
        List<FormulaComponent> components = FormulaComponentFactory.generateFormulaComponentList(postfix, this.spreadsheet);
        Double formulaResult = FormulaEvaluator.getResult(components, this.spreadsheet);
        Formula formula = factory.createFormula(formulaText, components, formulaResult);
        this.checkCircularDependencies(coord, formula);
        return formula;
    }

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
