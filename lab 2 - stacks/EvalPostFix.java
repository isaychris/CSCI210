//	EvalPostfix.java

import java.io.*;

/**
 * This is an implementation of a class that evaluates a postfix expression.
 * @author Chris Banci
 * @version 2.0 - 2/25/16
 */
public class EvalPostFix {
	private PrintWriter pw;
	private ObjectStack s_operand;
	private String postfix;
	private int result;
	private char ch;

	/**
	 * This method is the default constructor for EvalPostFix class.
	 * @param postfix = expression that is converted from infix.
	 * @param pw = priterWriter object for output to file.
	 */
	public EvalPostFix(String postfix, PrintWriter pw) {
		this.postfix = postfix;
		this.pw = pw;
		this.s_operand = new ObjectStack();
		this.result = 0;
	}
	
	/**
	 * This method evaluates postfix expression then returns the result.
	 * @return result = evaluated postfix expression
	 */
	public int evaluate() {
		int num1 = 0;
		int num2 = 0;
		
		for (int i = 0; i < postfix.length(); i++){
			ch = postfix.charAt(i);
			
			if (ch >= '0' && ch <= '9') {
				s_operand.push(new Integer(ch - 48));
			}
			
			else if (ch == '+'|| ch == '-' || ch == '*' || ch == '/' || ch == '^'){
				num2 = ((Integer) s_operand.pop()).intValue();
				num1 = ((Integer) s_operand.pop()).intValue();
				evaluateValues (num1,num2,ch);
			}
		}
		result = ((Integer) s_operand.pop()).intValue();
		return result;
	}
	
	/**
	 * This method evaluates the two values that are popped off the stack when operator is encountered.
	 * @param num1 second number popped from stack
	 * @param num2 first number popped from stack
	 * @param ch current character in expression parse.
	 */
	private void evaluateValues (int num1,int num2,char ch) {
		switch (ch) {
			case '^': 
				s_operand.push(new Integer((int) Math.pow(num1,num2)));
				break;
			case '*': 
				s_operand.push(new Integer(num1 * num2));
				break;
			case '/': 
				s_operand.push(new Integer(num1 / num2));
				break;
			case '+': 
				s_operand.push(new Integer(num1 + num2));
				break;
			case '-': 
				s_operand.push(new Integer(num1 - num2));
				break;
		}
	}
	
	/**
	 * This method prints out the evaluated expression.
	 */
	public void output(){
		System.out.println("Result: " + result);
		System.out.println(" ");
		pw.println("Result: " + result + "\r\n");
	}
	
}
