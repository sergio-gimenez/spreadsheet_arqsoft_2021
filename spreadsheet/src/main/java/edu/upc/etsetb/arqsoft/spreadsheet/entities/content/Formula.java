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
public class Formula extends Content {

    private List<FormulaComponent> formulaComponentList;
    private Double value;

    public Formula(String text, List<FormulaComponent> formulaComponentList, Double value) {
        this.text = text;
        this.formulaComponentList = formulaComponentList;
        this.value = value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    @Override
    public Double getValueAsDouble() {
        return value;
    }

    public List<FormulaComponent> getFormulaComponents() {
        return this.formulaComponentList;
    }

}
