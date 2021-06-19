/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.upc.etsetb.arqsoft.spreadsheet.content.token;

import java.util.regex.Pattern;

/**
 *
 * @author osboxes
 */
public class TokenInfo {

    public final Pattern regex;
    public final TokenEnum type;

    public TokenInfo(Pattern regex, TokenEnum type ){
        super();
        this.regex = regex;
        this.type = type;
    }
}
