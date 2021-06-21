/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.upc.etsetb.arqsoft.spreadsheet.entities.content;

import edu.upc.etsetb.arqsoft.spreadsheet.entities.BadCoordinateException;
import edu.upc.etsetb.arqsoft.spreadsheet.entities.content.Argument;
import edu.upc.etsetb.arqsoft.spreadsheet.entities.Coordinate;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author osboxes
 */
public class Range implements Argument {

    private Set<Coordinate> range = new HashSet<Coordinate>();
    private static final Pattern RANGE_PATTERN = Pattern.compile("[a-zA-Z]+\\\\d+:[a-zA-Z]+\\\\d+");

    public Range(String range) throws BadCoordinateException {
        Matcher m = getMatcher(range);
        if (!m.matches()) {
            throw new IllegalArgumentException("Invalid range argument");
        }
        Coordinate initCoord, finalCoord;
        initCoord = new Coordinate(m.group(1));
        finalCoord = new Coordinate(m.group(2));

        if (initCoord.getRow() > finalCoord.getRow() || initCoord.getColumnAsInt() > finalCoord.getColumnAsInt()) {
            throw new IllegalArgumentException("Invalid range argument");
        }
        this.range.add(initCoord);

        for (int r = initCoord.getRow(); r <= finalCoord.getRow(); r++) {
            for (int c = initCoord.getColumnAsInt(); c <= finalCoord.getColumnAsInt(); c++) {
                this.range.add(new Coordinate(c, r));
            }
        }
        this.range.add(finalCoord);

    }

    private Matcher getMatcher(String coordinate) {
        return RANGE_PATTERN.matcher(coordinate);
    }

    @Override
    public float getValue() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    

}
