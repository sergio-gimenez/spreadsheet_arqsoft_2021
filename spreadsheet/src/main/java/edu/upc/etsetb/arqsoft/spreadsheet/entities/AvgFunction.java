/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.upc.etsetb.arqsoft.spreadsheet.entities;

/**
 *
 * @author sergio
 */
public class AvgFunction extends Function {

    @Override
    public String getFunctionName() {
        return "AVG";
    }

    @Override
    public float processFunction() {
        for (Argument arg : super.argumentsList) {
            
        }
    }
}
