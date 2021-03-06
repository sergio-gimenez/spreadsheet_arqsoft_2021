/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.upc.etsetb.arqsoft.spreadsheet.entities;

import edu.upc.etsetb.arqsoft.spreadsheet.entities.content.Content;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author osboxes
 */
public interface Spreadsheet {
    
    public Cell getCell(Coordinate coordinate);
    
    public void setContent(Coordinate coordinate, Content content);
    
    public void printCells() ;
    
    public HashMap<Coordinate, Cell> getCellMap();
    
    public List<List<String>> getListofCells();       
    
}
