/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.upc.etsetb.arqsoft.spreadsheet.entities;

import java.util.ArrayList;

/**
 *
 * @author osboxes
 */
public abstract class Function implements Operand, Argument {
    
    public ArrayList<Argument> args;
    
    abstract float processFunction(ArrayList<Argument> args);
    
}
