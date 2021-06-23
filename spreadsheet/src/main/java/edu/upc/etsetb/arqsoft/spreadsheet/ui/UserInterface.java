/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.upc.etsetb.arqsoft.spreadsheet.ui;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author osboxes
 */
public abstract class UserInterface {

    protected static final List<String> commands = new ArrayList<>();
    protected UserInterfaceFactory factory;
    static {
        commands.add("C");
        commands.add("L");
        commands.add("S");
        commands.add("E");
    }

    public abstract void handleDialog();

    public void setFactory(UserInterfaceFactory factory){
        this.factory = factory;
    };

}
