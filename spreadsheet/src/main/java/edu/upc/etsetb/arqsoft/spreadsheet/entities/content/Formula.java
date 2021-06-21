/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.upc.etsetb.arqsoft.spreadsheet.entities.content;

import java.util.List;

/**
 *
 * @author osboxes
 */
public class Formula extends Content{
    
    private String text;
    private List <FormulaComponent> formulaComponentList;
    private Double value;

    public Formula(String text, List<FormulaComponent> formulaComponentList) {
        this.text = text;
        this.formulaComponentList = formulaComponentList;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }
    
    
    
    
    
    
    
    
}
