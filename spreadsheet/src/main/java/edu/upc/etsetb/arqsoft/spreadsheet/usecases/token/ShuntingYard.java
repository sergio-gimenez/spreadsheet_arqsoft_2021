package edu.upc.etsetb.arqsoft.spreadsheet.usecases.token;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author osboxes
 */
// Java Implemention of Shunting Yard Algorithm
// Importing stack class for stacks DS
import edu.upc.etsetb.arqsoft.spreadsheet.usecases.token.TokenEnum;
import edu.upc.etsetb.arqsoft.spreadsheet.usecases.token.Token;
import edu.upc.etsetb.arqsoft.spreadsheet.entities.ContentException;
import java.util.Stack;
// Importing specific character class as
// dealing with only operators and operands
import java.lang.Character;
import java.util.ArrayList;
import java.util.List;

public class ShuntingYard {

    // Method is used to get the precedence of operators
    private static boolean letterOrDigit(char c) {
        // boolean check
        if (Character.isLetterOrDigit(c)) {
            return true;
        } else {
            return false;
        }
    }

    // Method is used to get the precedence of operators
    private static boolean isDigit(char c) {
        // boolean check
        if (Character.isDigit(c)) {
            return true;
        } else {
            return false;
        }
    }

    // Operator having higher precedence
    // value will be returned
    static int getPrecedence(char ch) {

        switch (ch) {
            case '(':
            case '[':
            case '{':
                return 0;
            case '+':
            case '-':
                return 1;
            case '*':
            case '/':
                return 2;
            case '^':
                return 3;
            case ')':
            case ']':
            case '}':
                return 4;
            default:
                return -1;
        }
    }

    // Method converts given infixto postfix expression
    // to illustrate shunting yard algorithm
    public static List<Token> infixToRpn(List<Token> tokens) throws ContentException {

        // Initalising an empty stack
        Stack<Token> stack = new Stack<>();
        ArrayList<Token> outputList = new ArrayList<>();
        int bracketCount = 0;

        for (Token token : tokens) {
            if (bracketCount == 0 && !stack.isEmpty() && stack.peek().isOfType(TokenEnum.FUNCTION) && !token.isOfType(TokenEnum.LEFT_BRACKET)) {
                throw new ContentException("Left bracket is missing in function");
            } else {
                if (token.isOfType(TokenEnum.LEFT_BRACKET)) {
                    bracketCount++;
                } else if (token.isOfType(TokenEnum.RIGHT_BRACKET)) {
                    bracketCount--;
                }
            }
            if (token.isOfType(TokenEnum.NUMBER) || token.isOfType(TokenEnum.COORDINATE) || token.isOfType(TokenEnum.RANGE)) {
                outputList.add(token);
            } else if (token.isOfType(TokenEnum.FUNCTION)) {
                stack.push(token);
                Token delimiter = new Token(TokenEnum.DELIMITER, "|");
                outputList.add(delimiter);
            } else if (token.isOfType(TokenEnum.OPERATOR)) {

                while (!stack.isEmpty()
                        && stack.peek().isOfType(TokenEnum.OPERATOR)
                        && (getPrecedence(token.sequence.charAt(0))
                        <= getPrecedence(stack.peek().sequence.charAt(0)))) {

                    outputList.add(stack.pop());
                }
                stack.push(token);
            } else if (token.isOfType(TokenEnum.LEFT_BRACKET)) {
                stack.push(token);
            } else if (token.isOfType(TokenEnum.RIGHT_BRACKET)) {
                while (!stack.isEmpty() && !stack.peek().isOfType(TokenEnum.LEFT_BRACKET)) {
                    outputList.add(stack.pop());
                }
                if (!stack.peek().isOfType(TokenEnum.LEFT_BRACKET)) {
                    throw new ContentException("Bracket mismatch in formula");
                }
                stack.pop();
                if (!stack.isEmpty() && stack.peek().isOfType(TokenEnum.FUNCTION)) {
                    outputList.add(stack.pop());
                }
            }
        }

        // pop all the remaining operators from
        // the stack and append them to output
        while (!stack.isEmpty()) {
            if (stack.peek().isOfType(TokenEnum.LEFT_BRACKET)) {
                throw new ContentException("Bracket mismatch in formula");
            }
            outputList.add(stack.pop());
        }
        return outputList;
    }
// Method to evaluate value of a postfix expression

    static int evaluatePostfix(String exp) {
        //create a stack
        Stack<Integer> stack = new Stack<>();

        // Scan all characters one by one
        for (int i = 0; i < exp.length(); i++) {
            char c = exp.charAt(i);

            // If the scanned character is an operand (number here),
            // push it to the stack.
            if (Character.isDigit(c)) {
                stack.push(c - '0');
            } // If the scanned character is an operator, pop two
            // elements from stack apply the operator
            else {
                int val1 = stack.pop();
                int val2 = stack.pop();

                switch (c) {
                    case '+':
                        stack.push(val2 + val1);
                        break;

                    case '-':
                        stack.push(val2 - val1);
                        break;

                    case '/':
                        stack.push(val2 / val1);
                        break;

                    case '*':
                        stack.push(val2 * val1);
                        break;
                }
            }
        }
        return stack.pop();
    }
}
