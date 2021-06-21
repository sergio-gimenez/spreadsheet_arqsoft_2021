/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.upc.etsetb.arqsoft.spreadsheet.entities;

import edu.upc.etsetb.arqsoft.spreadsheet.entities.content.Content;
import edu.upc.etsetb.arqsoft.spreadsheet.entities.content.FormulaComponent;
import edu.upc.etsetb.arqsoft.spreadsheet.entities.content.Number;

/**
 *
 * @author osboxes
 */
public class Cell implements FormulaComponent {

    private Content content;

    public Cell(Content content) {
        this.content = content;
    }

    public Double getContentAsDouble() throws  NoNumberException{
        if (this.content instanceof Number) {
            return Double.parseDouble(this.content.getText());
        } else {
            throw new NoNumberException("The content is not a number");
        }
    }

    public String getContentAsString() {
        return content.getText();
    }

    public Content getContent() {
        return this.content;
    }

}
