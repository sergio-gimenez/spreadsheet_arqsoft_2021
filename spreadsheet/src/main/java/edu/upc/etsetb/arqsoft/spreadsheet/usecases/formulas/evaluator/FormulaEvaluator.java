/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.upc.etsetb.arqsoft.spreadsheet.usecases.formulas.evaluator;

import edu.upc.etsetb.arqsoft.spreadsheet.entities.Cell;
import edu.upc.etsetb.arqsoft.spreadsheet.entities.ContentException;
import edu.upc.etsetb.arqsoft.spreadsheet.entities.Coordinate;
import edu.upc.etsetb.arqsoft.spreadsheet.entities.NoNumberException;
import edu.upc.etsetb.arqsoft.spreadsheet.entities.Spreadsheet;
import edu.upc.etsetb.arqsoft.spreadsheet.entities.content.Argument;
import edu.upc.etsetb.arqsoft.spreadsheet.entities.content.Content;
import edu.upc.etsetb.arqsoft.spreadsheet.entities.content.Number;
import edu.upc.etsetb.arqsoft.spreadsheet.entities.content.Delimiter;
import edu.upc.etsetb.arqsoft.spreadsheet.entities.content.Formula;
import edu.upc.etsetb.arqsoft.spreadsheet.entities.content.FormulaComponent;
import edu.upc.etsetb.arqsoft.spreadsheet.entities.content.Operand;
import edu.upc.etsetb.arqsoft.spreadsheet.entities.content.function.Function;
import edu.upc.etsetb.arqsoft.spreadsheet.entities.content.operators.Operator;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 *
 * @author gerard
 */
public class FormulaEvaluator {

    public static Double getResult(List<FormulaComponent> components, Spreadsheet spreadsheet) throws ContentException, NoNumberException {
        Double value = 0.0;
        Stack<FormulaComponent> stack = new Stack<>();
        Operand firstOperand = null;
        Operand secondOperand = null;

        for (FormulaComponent component : components) {
            if (component instanceof Number || component instanceof Delimiter) {
                stack.push(component);
            } else if (component instanceof Cell) {
                Cell cell = (Cell) component;
                if (cell != null && cell.getContent() != null) {
                    Content content = cell.getContent();
                    if (content instanceof Number) {
                        stack.push(component);
                    } else if (content instanceof Formula) {
                        //TODO: QUIZAS CONTENT TIENE UN ERROR
                        Formula formula = (Formula) content;
                        Number number = new Number(formula.getValueAsDouble());
                        stack.push(number);
                    } else {
                        throw new ContentException("Formula evaluator has found an invalid component");
                    }
                } else {
                    stack.push(null);
                }
            } else if (component instanceof Operator) {
                Operator operator = (Operator) component;
                if (stack.peek() instanceof Operand && stack.get(stack.size() - 2) instanceof Operand) {
                    secondOperand = (Operand) stack.pop();
                    firstOperand = (Operand) stack.pop();

                    stack.push(operator.compute(firstOperand, secondOperand));
                }
            } else if (component instanceof Function) {
                Function function = (Function) component;
                List<Argument> args = new ArrayList<>();
                while (!stack.isEmpty() && !(stack.peek() instanceof Delimiter)) {
                    args.add((Argument) stack.pop());
                }
                if (stack.isEmpty() || !(stack.pop() instanceof Delimiter)) {
                    throw new ContentException("Delimiter not found");
                }
                stack.push(function.compute(args));
            } else {
                throw new ContentException("Component not evaluable");

            }
        }

        if (stack.size() != 1 || !(stack.peek() instanceof Operand)) {
            throw new ContentException("Invalid stack for evaluate");
        }

        Operand operand = (Operand) stack.pop();
        return operand.getValue();

    }
}
