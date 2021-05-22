/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.upc.etsetb.arqsoft.spreadsheet.entities;

/**
 *
 * @author osboxes
 */
public class Cell {
    
    private Content content;
    private Coordinate coordinate;    
    
    public Cell (Content content, Coordinate coordinate){
        this.content = content;
        this.coordinate = coordinate;
    }
    
    public Content getContent(){
        return content;
    }
    
    public String getFormula(){
        return null;
        // TODO
    }
    
}
