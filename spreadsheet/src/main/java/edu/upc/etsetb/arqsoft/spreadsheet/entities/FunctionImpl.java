/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.upc.etsetb.arqsoft.spreadsheet.entities;

import java.util.ArrayList;

/**
 *
 * @author sergio
 */
public abstract class FunctionImpl implements Function {

    protected ArrayList<Argument> argumentsArrayList;

    public FunctionImpl() {
        argumentsArrayList = new ArrayList<Argument>();
    }
}
