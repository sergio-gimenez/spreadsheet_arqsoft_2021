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
public final class Controller {
    private Spreadsheet spreadsheet;    
    
    public Controller(){
        this.spreadsheet = new SpreadsheetImpl();
    }
}
