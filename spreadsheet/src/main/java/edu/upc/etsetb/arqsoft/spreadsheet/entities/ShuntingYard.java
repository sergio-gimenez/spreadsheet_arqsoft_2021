package edu.upc.etsetb.arqsoft.spreadsheet.entities;

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
import java.util.Stack;
// Importing specific character class as
// dealing with only operators and operands
import java.lang.Character;

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

    // Operator having higher precedence
    // value will be returned
    static int getPrecedence(char ch) {

        if (ch == '+' || ch == '-') {
            return 1;
        } else if (ch == '*' || ch == '/') {
            return 2;
        } else if (ch == '^') {
            return 3;
        } else {
            return -1;
        }
    }

    // Method converts given infixto postfix expression
    // to illustrate shunting yard algorithm
    static String infixToRpn(String expression) {
        // Initalising an empty String
        // (for output) and an empty stack
        Stack<Character> stack = new Stack<>();

        // Initially empty string taken
        String output = new String("");

        // Iterating ovet tokens using inbuilt
        // .length() functon
        for (int i = 0; i < expression.length(); ++i) {
            // Finding character at 'i'th index
            char c = expression.charAt(i);

            // If the scanned Token is an
            // operand, add it to output
            if (letterOrDigit(c)) {
                output += c;
            } // If the scanned Token is an '('
            // push it to the stack
            else if (c == '(') {
                stack.push(c);
            } // If the scanned Token is an ')' pop and append
            // it to output from the stack until an '(' is
            // encountered
            else if (c == ')') {
                while (!stack.isEmpty()
                        && stack.peek() != '(') {
                    output += stack.pop();
                }

                stack.pop();
            } // If an operator is encountered then taken the
            // furthur action based on the precedence of the
            // operator
            else {
                while (!stack.isEmpty()
                        && getPrecedence(c)
                        <= getPrecedence(stack.peek())) {
                    // peek() inbuilt stack function to
                    // fetch the top element(token)

                    output += stack.pop();
                }
                stack.push(c);
            }
        }

        // pop all the remaining operators from
        // the stack and append them to output
        while (!stack.isEmpty()) {
            if (stack.peek() == '(') {
                return "This expression is invalid";
            }
            output += stack.pop();
        }
        return output;
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
