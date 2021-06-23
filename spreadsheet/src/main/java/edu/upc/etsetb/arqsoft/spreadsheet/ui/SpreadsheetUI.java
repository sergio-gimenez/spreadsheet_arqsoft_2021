/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.upc.etsetb.arqsoft.spreadsheet.ui;

import java.util.Scanner;

/**
 *
 * @author gerard
 */
public class SpreadsheetUI {

    private Scanner scanner;

    public SpreadsheetUI() {
        this.scanner = new Scanner(System.in);
    }

    public void initDialog() throws InvalidUIException {
        String line;

        System.out.println("Select the User Interface:");
        System.out.println("Text");
        System.out.println("Graphic");
        line = this.scanner.nextLine();

        line = line.replaceAll("\\s", "");
        this.initUI(line);
    }

    private void initUI(String uiSelected) throws InvalidUIException {
        UserInterfaceFactory factory = UserInterfaceFactory.getInstance(uiSelected);
        UserInterface ui = factory.createUserInterface();
        ui.handleDialog();
    }

    public static void main(String[] args) throws InvalidUIException {
        SpreadsheetUI spreadsheetUI = new SpreadsheetUI();
        spreadsheetUI.initDialog();
    }
}
