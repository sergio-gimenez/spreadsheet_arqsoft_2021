/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.upc.etsetb.arqsoft.spreadsheet.entities;

import edu.upc.etsetb.arqsoft.spreadsheet.content.Argument;

/**
 *
 * @author osboxes
 */
public class Range extends Coordinate implements Argument{
    
    public Range(String column, int row) {
        super(column, row);
    }
    
    
}
