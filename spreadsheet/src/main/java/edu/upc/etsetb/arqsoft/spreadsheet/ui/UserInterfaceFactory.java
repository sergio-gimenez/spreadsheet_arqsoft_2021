/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.upc.etsetb.arqsoft.spreadsheet.ui;

import edu.upc.etsetb.arqsoft.spreadsheet.controllers.SpreadsheetController;
import edu.upc.etsetb.arqsoft.spreadsheet.entities.Spreadsheet;

/**
 *
 * @author gerard
 */
public abstract class UserInterfaceFactory {

    public static UserInterfaceFactory getInstance(String factoryType) throws InvalidUIException {
        if (factoryType.equalsIgnoreCase("text")) {
            return new TextUIFactory();
        }
        throw new InvalidUIException("No concrete factory identified for type: " + factoryType);

    }

    public abstract UserInterface createUserInterface();

}
