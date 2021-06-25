/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.upc.etsetb.arqsoft.spreadsheet.ui;

import edu.upc.etsetb.arqsoft.spreadsheet.controllers.SpreadsheetController;
import edu.upc.etsetb.arqsoft.spreadsheet.entities.DefaultSpreadsheetFactory;
import edu.upc.etsetb.arqsoft.spreadsheet.entities.Spreadsheet;
import edu.upc.etsetb.arqsoft.spreadsheet.entities.SpreadsheetFactory;
import edu.upc.etsetb.arqsoft.spreadsheet.entities.SpreadsheetImpl;

/**
 *
 * @author gerard
 */
public class TextUIFactory extends UserInterfaceFactory {
  

    @Override
    public UserInterface createUserInterface() {
        return new TextUserInterface();
    }
    
    @Override
    public SpreadsheetFactory createSpreadsheetFactory() {
        return new DefaultSpreadsheetFactory();
    }

    @Override
    public Spreadsheet createSpreadsheet() {
        return new SpreadsheetImpl();
    }

    @Override
   public SpreadsheetController createSpreadsheetController(Spreadsheet spreadsheet, SpreadsheetFactory factory) {
        return new SpreadsheetController(spreadsheet, factory);
    }
}
