/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.upc.etsetb.arqsoft.spreadsheet.content;

import edu.upc.etsetb.arqsoft.spreadsheet.content.token.Token;
import java.util.List;

/**
 *
 * @author osboxes
 */
public class Formula implements Content{
        
    private List <Token> formulaComponentList;

    public Formula(List<Token> formulaComponentList) {
        this.formulaComponentList = formulaComponentList;
    }
    
    
    
    
    
    
}
