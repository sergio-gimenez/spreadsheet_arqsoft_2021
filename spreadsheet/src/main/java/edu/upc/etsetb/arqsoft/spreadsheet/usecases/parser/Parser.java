/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.upc.etsetb.arqsoft.spreadsheet.usecases.parser;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

/**
 *
 * @author gerard
 */
public interface Parser {
    public List<String[]> loadSpreadsheetFromFile(String path)throws FileNotFoundException, IOException;
    public void saveSpreadsheetOnFile(String path);
}
