/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.upc.etsetb.arqsoft.spreadsheet.entities.content;

/**
 *
 * @author osboxes
 */
public class Number extends Content implements Argument, Operand {

    private Double value; 
    
    public Number(Double value){
        this.value = value;        
        this.text = String.valueOf(value);
    }   

    @Override
    public double getValue() {
       return this.value;
    }

    @Override
    public Double getValueAsDouble() {
       return this.value;
    }

    
}
