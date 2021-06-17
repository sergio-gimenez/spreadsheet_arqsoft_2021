/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.upc.etsetb.arqsoft.spreadsheet.entities;

import java.util.List;

/**
 *
 * @author gerard
 */
public class TokenizerGenerator {
    
    private TokenizerGenerator(){
    }    
    
    public static Tokenizer getInstance(){
        Tokenizer tokenizer = new Tokenizer();
        
        tokenizer.add("SUM|MIN|MAX|AVG", 1); // function
        tokenizer.add("\\(", 2); // open bracket
        tokenizer.add("\\)", 3); // close bracket
        tokenizer.add("[+-]", 4); // plus or minus
        tokenizer.add("[*/]", 5); // mult or divide
        tokenizer.add("\\^", 6); // raised
        tokenizer.add("[0-9]+", 7); // integer number
        tokenizer.add("[a-zA-Z]+\\\\d+", 8); //cell
        tokenizer.add("[a-zA-Z]+\\\\d+:[a-zA-Z]+\\\\d+", 9); //Cell Range
        return tokenizer;
    }
}
