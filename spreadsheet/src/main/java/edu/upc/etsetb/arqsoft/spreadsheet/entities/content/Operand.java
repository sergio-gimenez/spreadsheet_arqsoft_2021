/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.upc.etsetb.arqsoft.spreadsheet.entities.content;

import edu.upc.etsetb.arqsoft.spreadsheet.entities.NoNumberException;

/**
 *
 * @author osboxes
 */
public interface Operand extends FormulaComponent{
    //TODO: Lanza la excepcion por el caso de cell, ver si es posible evitarlo. 
    public double getValue();
    
}
