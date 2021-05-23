/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.upc.etsetb.arqsoft.spreadsheet.entities;

import java.util.HashMap;

/**
 *
 * @author osboxes
 */
public class SpreadsheetImpl implements Spreadsheet{
    
    private HashMap<Coordinate, Cell> cellMap;

    public SpreadsheetImpl(HashMap<Coordinate, Cell> cellMap) {
        this.cellMap = cellMap;
    }
    
    

    @Override
    public Cell getCell(Coordinate coordinate) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setContent(Coordinate coordinate, String content) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Content getContent(Cell cell) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public HashMap<Coordinate, Cell> getCellMap() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
