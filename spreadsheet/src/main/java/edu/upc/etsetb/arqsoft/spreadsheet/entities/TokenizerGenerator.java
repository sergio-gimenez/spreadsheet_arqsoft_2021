/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.upc.etsetb.arqsoft.spreadsheet.entities;

import edu.upc.etsetb.arqsoft.spreadsheet.content.token.TokenEnum;
import edu.upc.etsetb.arqsoft.spreadsheet.content.token.Tokenizer;
import java.util.List;

/**
 *
 * @author gerard
 */
public class TokenizerGenerator {

    private TokenizerGenerator() {
    }

    public static Tokenizer getInstance() {
        Tokenizer tokenizer = new Tokenizer();
        tokenizer.add("SUM|MIN|MAX|AVG", TokenEnum.FUNCTION); // function
        tokenizer.add("\\(", TokenEnum.LEFT_BRACKET); // open bracket
        tokenizer.add("\\)", TokenEnum.RIGHT_BRACKET); // close bracket
        tokenizer.add("[+-]", TokenEnum.OPERATOR); // plus or minus
        tokenizer.add("[*/]", TokenEnum.OPERATOR); // mult or divide
        tokenizer.add("\\^", TokenEnum.OPERATOR); // raised
        tokenizer.add("[0-9]+", TokenEnum.NUMBER); // integer number
        tokenizer.add("[a-zA-Z]+\\d+:[a-zA-Z]+\\d+", TokenEnum.RANGE); //Cell Range
        tokenizer.add("[a-zA-Z]+\\d+", TokenEnum.CELL); //cell
        tokenizer.add(",", TokenEnum.COMMA); //Argument separator
        return tokenizer;
    }
}
