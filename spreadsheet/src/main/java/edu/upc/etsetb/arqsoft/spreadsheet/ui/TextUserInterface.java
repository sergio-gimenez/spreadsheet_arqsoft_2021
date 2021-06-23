/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.upc.etsetb.arqsoft.spreadsheet.ui;

import edu.upc.etsetb.arqsoft.spreadsheet.controllers.SpreadsheetController;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author osboxes
 */
public class TextUserInterface extends UserInterface {

    private Scanner scanner;
    private SpreadsheetController controller;

    public TextUserInterface() {
        this.scanner = new Scanner(System.in);

    }

    @Override
    public void handleDialog() {
        boolean exitProgram = false;
        String line;
        while (!exitProgram) {
            System.out.println("Write one of the following options:");
            System.out.println("C - Create a New Spreadshhet");
            System.out.println("E <E <cell coordinate> <new cell content> - Edit a cell from the Spreadsheet");
            System.out.println("L <SV2 file pathname> - Load a Spreadsheet from a file");
            System.out.println("S <SV2 file pathname> - Save the Spreadsheet to a file");
            System.out.println("EXIT - Exit to close the program");
            line = this.scanner.nextLine();

            String command, args;
            int separator = line.contains(" ") ? line.indexOf(" ") : line.length();
            command = line.substring(0, separator).toUpperCase();
            if (command.equals("EXIT")) {
                exitProgram = true;
            } else {

            }
        }
    }

}
