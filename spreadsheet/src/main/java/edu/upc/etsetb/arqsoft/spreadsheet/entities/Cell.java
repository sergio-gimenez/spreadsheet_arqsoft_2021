/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.upc.etsetb.arqsoft.spreadsheet.entities;

import edu.upc.etsetb.arqsoft.spreadsheet.entities.content.Argument;
import edu.upc.etsetb.arqsoft.spreadsheet.entities.content.Content;
import edu.upc.etsetb.arqsoft.spreadsheet.entities.content.Formula;
import edu.upc.etsetb.arqsoft.spreadsheet.entities.content.FormulaComponent;
import edu.upc.etsetb.arqsoft.spreadsheet.entities.content.Number;
import edu.upc.etsetb.arqsoft.spreadsheet.entities.content.Operand;

/**
 *
 * @author osboxes
 */
public class Cell implements FormulaComponent, Operand, Argument {

    private Content content;
    private final Coordinate coord;

    public Cell(Coordinate coord, Content content) {
        this.coord = coord;
        this.content = content;
    }

    public Content getContent() {
        return this.content;
    }

    public Coordinate getCoordinate() {
        return this.coord;
    }

    @Override
    public double getValue() {
        return this.getContent().getValueAsDouble();
    }

}
