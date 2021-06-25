/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.upc.etsetb.arqsoft.spreadsheet.usecases.parser;

import edu.upc.etsetb.arqsoft.spreadsheet.entities.Spreadsheet;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author gerard
 */
public class FilerParser implements Parser {

    @Override
    public List<List<String>> loadSpreadsheetFromFile(String path) throws FileNotFoundException, IOException {
        FileInputStream stream = null;
        try {
            stream = new FileInputStream(path);
        } catch (FileNotFoundException ex) {
            throw new FileNotFoundException();
        }

        BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
        String line;
        List<List<String>> content = new ArrayList<>();
        try {
            while ((line = reader.readLine()) != null) {
                List<String> row = Arrays.asList(line.split(";"));
                content.add(row);
            }
        } catch (IOException ex) {
            throw new IOException();
        }
        try {
            reader.close();
        } catch (IOException ex) {
            throw new IOException();
        }
        return content;
    }

    @Override
    public void saveSpreadsheetOnFile(String path, List<List<String>> content) throws IOException, ParserException {
        if (path.endsWith(".s2v")) {
            File file = new File(path);
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
                writer.write("");

                for (List<String> row : content) {
                    for (int c = 0; c < row.size(); c++) {
                        writer.append((row.get(c) != null ? row.get(c) : ""));
                        writer.append((c == row.size() - 1 ? "\n" : ";"));
                    }
                }
            }
        } else {
            throw new ParserException("File extension not allowed");
        }

    }

}
