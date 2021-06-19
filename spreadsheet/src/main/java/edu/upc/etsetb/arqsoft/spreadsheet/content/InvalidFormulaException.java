/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.upc.etsetb.arqsoft.spreadsheet.content;

/**
 *
 * @author gerard
 */
public class InvalidFormulaException extends Exception{
        public InvalidFormulaException() {
    }
        public InvalidFormulaException(String msg) {
            super(msg);
    }
}
