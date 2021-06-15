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

    public SpreadsheetImpl() {
        this.cellMap = new HashMap<Coordinate, Cell>();
    }
    
    @Override
    public Cell getCell(Coordinate coordinate) {
        return this.cellMap.get(coordinate);
    }
    

    @Override
    public void setContent(Coordinate coordinate, String content) {
        
        
        
        Cell cell = new 
        
        cellMap.put(coordinate, );
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
