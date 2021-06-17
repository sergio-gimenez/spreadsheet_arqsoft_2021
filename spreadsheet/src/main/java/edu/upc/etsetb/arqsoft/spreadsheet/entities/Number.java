/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.upc.etsetb.arqsoft.spreadsheet.entities;

/**
 *
 * @author osboxes
 */
public class Number implements Argument, Operand, Content {

    private float value; 
    
    public Number(Float value){
        this.value = value;        
    }

    @Override
    public float getValue() {
        return value;
    }            
}
