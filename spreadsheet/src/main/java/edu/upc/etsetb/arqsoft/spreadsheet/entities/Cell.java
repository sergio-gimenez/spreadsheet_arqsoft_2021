/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.upc.etsetb.arqsoft.spreadsheet.entities;

import edu.upc.etsetb.arqsoft.spreadsheet.content.Content;

/**
 *
 * @author osboxes
 */
public class Cell {

    private Content content;

    public Cell(Content content) {
        this.content = content;
    }

    public Double getContentAsDouble() {
        //    return content;
        throw new Exception("Method not implemented")
    }

    public Content getContentAsString() {
        return content;
    }

    public String getFormula() {
        return null;
        // TODO
    }

}
