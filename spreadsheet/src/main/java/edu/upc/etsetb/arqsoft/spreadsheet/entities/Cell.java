/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.upc.etsetb.arqsoft.spreadsheet.entities;

import edu.upc.etsetb.arqsoft.spreadsheet.entities.content.Content;
import edu.upc.etsetb.arqsoft.spreadsheet.entities.content.Formula;
import edu.upc.etsetb.arqsoft.spreadsheet.entities.content.FormulaComponent;
import edu.upc.etsetb.arqsoft.spreadsheet.entities.content.Number;
import edu.upc.etsetb.arqsoft.spreadsheet.entities.content.Operand;

/**
 *
 * @author osboxes
 */
public class Cell implements FormulaComponent, Operand {

    private Content content;

    public Cell(Content content) {
        this.content = content;
    }

      public Content getContent() {
        return this.content;
    }

    @Override
    public double getValue() {
        return this.getContent().getValueAsDouble();
    }



}
