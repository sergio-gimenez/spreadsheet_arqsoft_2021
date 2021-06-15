/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.upc.etsetb.arqsoft.spreadsheet.entities;

import java.util.LinkedList;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author osboxes
 */
public class Tokenizer {

    public class ParserException extends RuntimeException {

        public ParserException(String msg) {
            super(msg);
        }
    }

    private LinkedList<TokenInfo> tokenInfos;
    private LinkedList<Token> tokens;

    public Tokenizer() {
        tokenInfos = new LinkedList<TokenInfo>();
        tokens = new LinkedList<Token>();
    }

    public void add(String regex, int token) {
        tokenInfos.add(
                new TokenInfo(
                        Pattern.compile("^(" + regex + ")"), token));
    }

    public void tokenize(String str) {
        String s = str.trim();
        tokens.clear();
        while (!s.equals("")) {
            boolean match = false;
            for (TokenInfo info : tokenInfos) {
                Matcher m = info.regex.matcher(s);
                if (m.find()) {
                    match = true;
                    String tok = m.group().trim();
                    s = m.replaceFirst("").trim();
                    tokens.add(new Token(info.token, tok));
                    break;
                }
            }
            if (!match) {
                throw new ParserException("Unexpected character in input: " + s);
            }
        }
    }

    public LinkedList<Token> getTokens() {
        return tokens;
    }

    // Java proram to evaluate value of a postfix expression
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

//	// Driver program to test above functions
//	public static void main(String[] args)
//	{
//		String exp="231*+9-";
//		System.out.println("postfix evaluation: "+evaluatePostfix(exp));
//	}
    public static void main(String[] args) {

        /*
        * For the formulas in a spreadsheet they are: operator, celll identifier,
        * number, opening round bracket, closing round bracket, colon character,
        * semi-colon character, comma, function name, and range.
         */
        Tokenizer tokenizer = new Tokenizer();
        tokenizer.add("sin|cos|exp|ln|sqrt", 1); // function 
        tokenizer.add("\\(", 2); // open bracket
        tokenizer.add("\\)", 3); // close bracket
        tokenizer.add("[+-]", 4); // plus or minus
        tokenizer.add("[*/]", 5); // mult or divide
        tokenizer.add("\\^", 6); // raised
        tokenizer.add("[0-9]+", 7); // integer number
        tokenizer.add("[a-zA-Z][a-zA-Z0-9_]*", 8); // variable

        try {
            tokenizer.tokenize("1+2+3-1*2");

            LinkedList<Token> tokens = tokenizer.getTokens();
            String infix = "";

            for (Token tok : tokens) {
                System.out.println("" + tok.token + " " + tok.sequence);
                infix = infix + tok.sequence;
            }

            String postfix = ShuntingYard.infixToRpn(infix);

            System.out.println("\nPostfix:\n" + postfix);

            int res = ShuntingYard.evaluatePostfix(postfix);

            System.out.println("\nEvaluated postfix = " + res);

        } catch (ParserException e) {
            System.out.println(e.getMessage());
        }

    }

}
