/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.upc.etsetb.arqsoft.spreadsheet.entities.content;

import edu.upc.etsetb.arqsoft.spreadsheet.entities.content.function.FunctionFactory;
import edu.upc.etsetb.arqsoft.spreadsheet.entities.content.function.InvalidFunctionException;
import edu.upc.etsetb.arqsoft.spreadsheet.entities.content.operators.Operator;
import edu.upc.etsetb.arqsoft.spreadsheet.entities.content.operators.OperatorFactory;
import edu.upc.etsetb.arqsoft.spreadsheet.entities.content.operators.OperatorInvalidException;
import edu.upc.etsetb.arqsoft.spreadsheet.usecases.token.TokenEnum;
import edu.upc.etsetb.arqsoft.spreadsheet.usecases.token.Token;
import edu.upc.etsetb.arqsoft.spreadsheet.entities.Coordinate;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author gerard
 */
public class FormulaComponentFactory {

    public static List<FormulaComponent> generateFormulaComponentList(List<Token> tokens) throws OperatorInvalidException, InvalidFunctionException, InvalidFormulaComponentException {
        List<FormulaComponent> components = new ArrayList<FormulaComponent>(tokens.size());

        for (Token token : tokens) {
            final FormulaComponent component;
            switch (token.type) {
                case CELL:
                    component = new Coordinate(token.sequence);
                    break;
                case NUMBER:
                    component = new Coordinate(token.sequence);
                    break;
                case RANGE:
                    component = new Range(token.sequence);
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
                    throw new InvalidFormulaComponentException("Token cannot be converted into Formula Componenet");
            }
            components.add(component);
        }
        return components;
    }

}
