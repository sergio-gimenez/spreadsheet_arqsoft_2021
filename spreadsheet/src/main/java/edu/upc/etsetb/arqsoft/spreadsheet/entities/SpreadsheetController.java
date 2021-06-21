/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.upc.etsetb.arqsoft.spreadsheet.entities;

/**
 *
 * @author gerard
 */
public final class SpreadsheetController {
    private Spreadsheet spreadsheet;    
    
    public SpreadsheetController(Spreadsheet spreadsheet){
        this.spreadsheet = spreadsheet;
    }
    
    public void setCellContent(String cellCoord, String content)  throws ContentException, BadCoordinateException{}
    ;
    public double getCellContentAsDouble(String coord) throws BadCoordinateException, NoNumberException{
    
      throw new NoNumberException("Method not implemented");
    };
    
    public String getCellContentAsString(String cooord) throws BadCoordinateException{
         throw new BadCoordinateException("Method not implemented");
    }
}
