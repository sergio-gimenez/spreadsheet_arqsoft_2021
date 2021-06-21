/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.upc.etsetb.arqsoft.spreadsheet.entities.content;

import edu.upc.etsetb.arqsoft.spreadsheet.entities.BadCoordinateException;
import edu.upc.etsetb.arqsoft.spreadsheet.entities.ContentException;
import edu.upc.etsetb.arqsoft.spreadsheet.entities.content.function.FunctionFactory;
import edu.upc.etsetb.arqsoft.spreadsheet.entities.content.operators.Operator;
import edu.upc.etsetb.arqsoft.spreadsheet.entities.content.operators.OperatorFactory;
import edu.upc.etsetb.arqsoft.spreadsheet.usecases.token.TokenEnum;
import edu.upc.etsetb.arqsoft.spreadsheet.usecases.token.Token;
import edu.upc.etsetb.arqsoft.spreadsheet.entities.Coordinate;
import edu.upc.etsetb.arqsoft.spreadsheet.entities.Spreadsheet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author gerard
 */
public class FormulaComponentFactory {

    public static List<FormulaComponent> generateFormulaComponentList(List<Token> tokens, Spreadsheet spreadsheet) throws BadCoordinateException, ContentException {
        List<FormulaComponent> components = new ArrayList<FormulaComponent>(tokens.size());

        for (Token token : tokens) {
            FormulaComponent component = null;
            switch (token.type) {
                case COORDINATE:
                    Coordinate coord = new Coordinate(token.sequence);
                    component = spreadsheet.getCell(coord);
                    break;
                case NUMBER:
                    component = new Number(Double.parseDouble(token.sequence));
                    break;
                case RANGE:
                    components.addAll(new Range(token.sequence, spreadsheet).getCells());
                    break;
                case OPERATOR:
                    component = OperatorFactory.getInstance(token.sequence);
                    break;
                case FUNCTION:
                    component = FunctionFactory.getInstance(token.sequence);
                    break;
                case DELIMITER:
                    component = new Delimiter();
                    break;
                default:
                    throw new ContentException("Token cannot be converted into Formula Componenet");
            }
            if (component != null) {
                components.add(component);
            }
        }
        return components;
    }

}
