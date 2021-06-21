/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.upc.etsetb.arqsoft.spreadsheet.entities;

import edu.upc.etsetb.arqsoft.spreadsheet.content.Content;
import java.util.HashMap;

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
        Cell cell = new Cell(content);
        this.cellMap.put(coordinate, cell);
    }

}
