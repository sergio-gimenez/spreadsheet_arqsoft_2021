/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.upc.etsetb.arqsoft.spreadsheet.entities;

import java.util.regex.Pattern;

/**
 *
 * @author osboxes
 */
public class TokenInfo {

    public final Pattern regex;
    public final int token;

    public TokenInfo(Pattern regex, int token) {
        super();
        this.regex = regex;
        this.token = token;
    }

}