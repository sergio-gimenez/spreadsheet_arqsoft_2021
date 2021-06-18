/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.upc.etsetb.arqsoft.spreadsheet.entities;

import java.util.Objects;
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

    public String getColumn() {
        return column;
    }

    public int getRow() {
        return row;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 17 * hash + Objects.hashCode(this.column);
        hash = 17 * hash + this.row;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Coordinate other = (Coordinate) obj;
        if (this.row != other.row) {
            return false;
        }
        if (!Objects.equals(this.column, other.column)) {
            return false;
        }
        return true;
    }

}
