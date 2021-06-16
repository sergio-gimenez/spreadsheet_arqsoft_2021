/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.upc.etsetb.arqsoft.spreadsheet.entities;

import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author osboxes
 */
public class SpreadsheetImpl implements Spreadsheet {

    private HashMap<Coordinate, Cell> cellMap;

    public SpreadsheetImpl() {
        this.cellMap = new HashMap<Coordinate, Cell>();
    }

    @Override
    public Cell getCell(Coordinate coordinate) {
        return this.cellMap.get(coordinate);
    }

    // TODO This shouldn't probably be here
    public boolean isCoordinateValid(String coordinate) {
        // Regex that validates if the coordinate follows the 
        // pattern [letter(s),number(s)]
        Pattern pattern = Pattern.compile("^[a-zA-Z]+\\d+$"); // TODO mayusculas y minusculas?

        Matcher matcher = pattern.matcher(coordinate);
        return matcher.matches();
    }

    public Content processStringToContent(String strContent){
        
    }

    @Override
    public void setContent(String coordinate, String content) throws MalformedCoordinateException {

        if (isCoordinateValid(coordinate)) {

        } else {
            throw new MalformedCoordinateException("Wrong format for coordinate");
        }

        Cell cell = new cellMap.put(coordinate,);
    }

    @Override
    public Content getContent(Cell cell) {

        return null;

    }

    @Override
    public HashMap<Coordinate, Cell> getCellMap() {
        return cellMap;
    }

}
