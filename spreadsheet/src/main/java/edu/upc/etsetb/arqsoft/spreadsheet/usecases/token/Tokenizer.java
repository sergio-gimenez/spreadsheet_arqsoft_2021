/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.upc.etsetb.arqsoft.spreadsheet.usecases.token;

import edu.upc.etsetb.arqsoft.spreadsheet.entities.ContentException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author osboxes
 */
public final class Tokenizer {

    public class ParserException extends Exception {

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

    public void add(String regex, TokenEnum type) {
        tokenInfos.add(
                new TokenInfo(
                        Pattern.compile("^(" + regex + ")"), type));
    }

    public void tokenize(String str) throws ParserException {
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
                    tokens.add(new Token(info.type, tok));
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
    public static void main(String[] args) throws ContentException {

        /*
        * For the formulas in a spreadsheet they are: operator, celll identifier,
        * number, opening round bracket, closing round bracket, colon character,
        * semi-colon character, comma, function name, and range.
         */
        Tokenizer tokenizer = new Tokenizer();
        tokenizer.add("SUMA|MIN|MAX|AVG", TokenEnum.FUNCTION); // function
        tokenizer.add("\\(", TokenEnum.LEFT_BRACKET); // open bracket
        tokenizer.add("\\)", TokenEnum.RIGHT_BRACKET); // close bracket
        tokenizer.add("[+-]", TokenEnum.OPERATOR); // plus or minus
        tokenizer.add("[*/]", TokenEnum.OPERATOR); // mult or divide
        tokenizer.add("\\^", TokenEnum.OPERATOR); // raised
        tokenizer.add("[0-9]+", TokenEnum.NUMBER); // integer number
        tokenizer.add("[a-zA-Z]+\\d+:[a-zA-Z]+\\d+", TokenEnum.RANGE); //Cell Range
        tokenizer.add("[a-zA-Z]+\\d+", TokenEnum.COORDINATE); //cell
        tokenizer.add(";", TokenEnum.SEPARATOR); //Argument separator

        try {
            tokenizer.tokenize("(A5*4)/(A2+A2)+SUMA(A1;A2;3;4;5;A6:A12)");
            //          tokenizer.tokenize("1 + ( 2 * 3 -1 ) -2");

            List<Token> tokens = tokenizer.getTokens();

            List<Token> postfix = new ArrayList<>();
            postfix = ShuntingYard.infixToRpn(tokens);

            String infix = "";
            String strPostfix = "";
            for (Token token : postfix) {
                System.out.println("" + token.type + " " + token.sequence);
                strPostfix += token.sequence;
            }

            System.out.println("\nPostfix:\n" + strPostfix);

//           int res = ShuntingYard.evaluatePostfix(strPostfix);
//            System.out.println("\nEvaluated postfix = " + res);
        } catch (ParserException e) {
            System.out.println(e.getMessage());
        }

    }

}
