/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.upc.etsetb.arqsoft.spreadsheet.content.token;

import edu.upc.etsetb.arqsoft.spreadsheet.content.FormulaComponent;

/**
 *
 * @author osboxes
 */
public class Token {

    public final TokenEnum type;
    public final String sequence;

    public Token(TokenEnum type, String sequence) {
        super();
        this.type = type;
        this.sequence = sequence;
    }

    public boolean isOfType(TokenEnum type) {
        return this.type == type;
    }

}   
