/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.upc.etsetb.arqsoft.spreadsheet.entities;

import edu.upc.etsetb.arqsoft.spreadsheet.entities.content.Content;
import edu.upc.etsetb.arqsoft.spreadsheet.entities.content.Formula;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author osboxes
 */
public class SpreadsheetImpl implements Spreadsheet {

    private HashMap<Coordinate, Cell> cellMap;

    public SpreadsheetImpl() {
        this.cellMap = new HashMap<Coordinate, Cell>();
    }

    @Override
    public Cell getCell(Coordinate coordinate) {
        return this.cellMap.get(coordinate);
    }

    @Override
    public HashMap<Coordinate, Cell> getCellMap() {
        return cellMap;
    }

    @Override
    public void setContent(Coordinate coordinate, Content content) {
        Cell cell = new Cell(coordinate, content);
        this.cellMap.put(coordinate, cell);
    }

    @Override
    public void printCells() {
        if (this.cellMap.size() == 0) {
            System.out.println("The spreadsheet is empty.\n");
        } else {
            Cell cell;
            Content content;
            System.out.println("\n");
            for (Coordinate c : this.cellMap.keySet()) {
                cell = this.cellMap.get(c);
                content = cell.getContent();

                if (content instanceof Formula) {
                    Formula formula = (Formula) content;
                    System.out.println(c.getColumn() + c.getRow() + ": " + formula.getText() + " = " + formula.getValueAsDouble());
                } else {
                    System.out.println(c.getColumn() + c.getRow() + ": " + content.getText());
                }
            }
            System.out.println("\n");
        }
    }

    @Override
    public List<List<String>> getListofCells() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
