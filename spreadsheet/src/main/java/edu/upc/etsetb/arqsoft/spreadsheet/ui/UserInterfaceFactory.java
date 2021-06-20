/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.upc.etsetb.arqsoft.spreadsheet.ui;

/**
 *
 * @author gerard
 */
public class UserInterfaceFactory {
     public static UserInterfaceFactory getInstance(String factoryType) throws NoExistingUIException{
        if(factoryType.equalsIgnoreCase("text")){
            return new TextUIFactory() ;
        }
        throw new NoExistingUIException("No concrete factory identified for type: " + factoryType) ;
        
    }
     
     public abstract Client createUIClient();
    public abstract UISpreadsheet createUISpreadsheet();
}