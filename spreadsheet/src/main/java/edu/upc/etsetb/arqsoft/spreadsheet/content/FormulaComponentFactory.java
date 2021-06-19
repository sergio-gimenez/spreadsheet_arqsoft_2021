/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.upc.etsetb.arqsoft.spreadsheet.content;

import edu.upc.etsetb.arqsoft.spreadsheet.content.token.TokenEnum;
import edu.upc.etsetb.arqsoft.spreadsheet.content.token.Token;
import edu.upc.etsetb.arqsoft.spreadsheet.entities.Coordinate;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author gerard
 */
public class FormulaComponentFactory {
    public static List<FormulaComponent> fromTokenList(List<Token> tokens){
        List<FormulaComponent> components = new ArrayList<FormulaComponent>(tokens.size());
        for(Token token: tokens){
            final FormulaComponent component;
            switch(token.type){
                case CELL:
                    component = new Coordinate(token.sequence);
                    break;
                case NUMBER:
component = new Coordinate(token.sequence);
                    break;
                case RANGE:
                    component = 
                case OPERATOR:
                case FUNCTION:
                case DELIMITER:
                    
                default:
                    break;
            }
            components.add(component);
        }
        return components;
    }
}
