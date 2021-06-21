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
public abstract class Content {

    protected String text;

    public String getText() {
        return this.text;
    }

    public void setText(String s) {
        this.text = s;
    }
    
}
