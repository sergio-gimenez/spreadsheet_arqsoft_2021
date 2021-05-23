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
import java.util.LinkedList;

class ShuntingYard {

	// Method is used to get the precedence of operators
	private static boolean letterOrDigit(char c)
	{
		// boolean check
		if (Character.isLetterOrDigit(c))
			return true;
		else
			return false;
	}

	// Operator having higher precedence
	// value will be returned
	static int getPrecedence(char ch)
	{

		if (ch == '+' || ch == '-')
			return 1;
		else if (ch == '*' || ch == '/')
			return 2;
		else if (ch == '^')
			return 3;
		else
			return -1;
	}

	// Method converts given infixto postfix expression
	// to illustrate shunting yard algorithm
	static String infixToRpn(LinkedList<Tokenizer> tokens)
	{
		// Initalising an empty String
		// (for output) and an empty stack
		Stack<Character> stack = new Stack<>();

		// Initially empty string taken
		String output = new String("");

		// Iterating ovet tokens using inbuilt
		// .length() functon
		for (Token tok : tokens) {
			// Finding character at 'i'th index
			char c = tok.sequence;
b
			// If the scanned Token is an
			// operand, add it to output
			if (letterOrDigit(c))
				output += c;

			// If the scanned Token is an '('
			// push it to the stack
			else if (c == '(')
				stack.push(c);

			// If the scanned Token is an ')' pop and append
			// it to output from the stack until an '(' is
			// encountered
			else if (c == ')') {
				while (!stack.isEmpty()
					&& stack.peek() != '(')
					output += stack.pop();

				stack.pop();
			}

			// If an operator is encountered then taken the
			// furthur action based on the precedence of the
			// operator

			else {
				while (
					!stack.isEmpty()
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
			if (stack.peek() == '(')
				return "This expression is invalid";
			output += stack.pop();
		}
		return output;
	}

	// Main driver code
	public static void main(String[] args)
	{
		// Considering random infix string notation
		String expression = "5+2/(3-8)^5^2";

		// Printing RPN for the above infix notation
		// Illustrating shunting yard algorithm
		System.out.println(infixToRpn(expression));
	}
}

