/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.upc.etsetb.arqsoft.spreadsheet.entities;

import java.util.regex.*;

/**
 *
 * @author osboxes
 */
public class Coordinate {

    private String column;
    private int row;

    public Coordinate(String column, int row) {
        this.column = column;
        this.row = row;
    }

    public boolean isCoordinateValid(String coordinate) {
        // Regex that validates if the coordinate follows the 
        // pattern [letter(s),number(s)]
        Pattern pattern = Pattern.compile("^[a-zA-Z]+\\d+$"); // TODO mayusculas y minusculas?

        Matcher matcher = pattern.matcher(coordinate);
        return matcher.matches();
    }

    public String getColumn() {
        return column;
    }

    public int getRow() {
        return row;
    }

}
