/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.upc.etsetb.arqsoft.spreadsheet.entities.content;

/**
 *
 * @author osboxes
 */
public class Text extends Content {


    public Text(String value) {
        this.text = value;
    }

    @Override
    public Double getValueAsDouble() {
        throw new UnsupportedOperationException("Text cannot provide a Double."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
}
