/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.upc.etsetb.arqsoft.spreadsheet.usecases.parser;

import edu.upc.etsetb.arqsoft.spreadsheet.entities.Spreadsheet;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author gerard
 */
public class FilerParser implements Parser {

    @Override
    public List<String[]> loadSpreadsheetFromFile(String path) throws FileNotFoundException, IOException {
        FileInputStream stream = null;
        try {
            stream = new FileInputStream(path);
        } catch (FileNotFoundException ex) {
            throw new FileNotFoundException();
        }
        
         BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
        String line;
        List<String[]> cells = new ArrayList<String[]>();
         try {
            while ((line = reader.readLine()) != null) {
                String[] rows = line.split(";");
                cells.add(rows);
            }
        } catch (IOException ex) {
            throw new IOException();
        }
        try {
            reader.close();
        } catch (IOException ex) {
           throw new IOException();
        }
        return cells;
    }

    @Override
    public void saveSpreadsheetOnFile(String path) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
