/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.upc.etsetb.arqsoft.spreadsheet.entities;

import edu.upc.etsetb.arqsoft.spreadsheet.content.Argument;
import edu.upc.etsetb.arqsoft.spreadsheet.content.Operand;
import java.util.Objects;
import java.util.regex.*;

/**
 *
 * @author osboxes
 */
public class Coordinate implements Argument, Operand {

    private static final Pattern COORDINATE_PATTERN = Pattern.compile("^([a-zA-Z]+)(\\d+)$");
    private String column;
    private int row;

    public Coordinate(String column, int row) {
        this.column = column;
        this.row = row;
    }
    
     public Coordinate(int column, int row) {
        this.column = this.getColumnFromInt(column);
        this.row = row;
    }

    public Coordinate(String coordinate) {
        Matcher m = getMatcher(coordinate);
        if (!m.matches()) {
            throw new IllegalArgumentException("Non valid coordinate");
        }

        this.column = m.group(1);
        this.row = Integer.parseInt(m.group(2));

    }

    public String getColumn() {
        return column;
    }
    
     public int getColumnAsInt() {
        char[] cs = this.column.toCharArray();
        int sum = 0;
        for(char c : cs) {
            sum = sum*26 + c - 'A' + 1;
        }
        return sum;
    }
     
      private String getColumnFromInt(int value) {      
        StringBuffer columnBuffer = new StringBuffer();
        
        int res = value;
        int mod;
        while(res > 26){
            mod = res%26;
            columnBuffer.append((char)mod + 'A');
            res = res/26;
        }
        columnBuffer.append((char)res + 'A');
       
        return columnBuffer.reverse().toString();
    }


    public int getRow() {
        return row;
    }
    

    private Matcher getMatcher(String coordinate) {
        return COORDINATE_PATTERN.matcher(coordinate);
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

    @Override
    public float getValue() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
