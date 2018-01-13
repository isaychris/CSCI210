//	InfixToPostfix.java

import java.io.*;

/**
 * This is an implementation of a class that converts infix to postfix.
 * @author Chris Banci
 * @version 2.0 - 2/25/16
 */
public class InfixToPostfix {
	private PrintWriter pw;
	private ObjectStack s_operator;
	private String infix;
	private String postfix;
	private char ch;
	
	/**
	 * This method is the default constructor for InfixToPostfix.
	 * @param buf = is the infix expression
	 * @param pw = is the printWriter object for file output.
	 */
	public InfixToPostfix(String buf, PrintWriter pw) {
		this.pw = pw;
		this.s_operator= new ObjectStack();
		this.infix = buf;
		this.postfix = "";
	}
	
	/**
	 * This method returns the operators priority level.
	 * @param operator = operator character
	 * @return priority level
	 */
	private int priority(char operator){
		switch (operator) {
			case '^': return 3;
			case '*':
			case '/': return 2;
			case '+':
			case '-': return 1;
			default: return 0;
		}
	}
	
	/**
	 * 
	 * This method converts the infix expression to postfix expression.
	 * @return postfix = expression converted from infix.
	 */
	public String convert(){
		char temp_top;
		
		for (int i = 0; i < infix.length(); i++) {
			ch = infix.charAt(i);
			
			//rule 1
			//if operand, copy to postfix string.
			if (ch >= '0' && ch <= '9') {
				postfix = postfix + ch;
			}
			
			//rule 2
			//If operator, pop stack until empty OR operator on top of stack
			//has lower priority than operator in infix expression.
			//Then push operator from infix expression to stack.
			else if (ch == '+'|| ch == '-' || ch == '*' || ch == '/' || ch == '^'){
				while(!s_operator.isEmpty()){
					temp_top = ((Character) s_operator.top()).charValue();
					
					//rule 5
					//If left parenthesis encountered, stop popping stack.
					if (ch == '('){
						break;
					}
					
					//if top has lower priority than current, stop popping.
					if (checkPriority(ch, temp_top) == true) {
						break;
					}
					postfix = postfix + s_operator.pop();
				}
				
				s_operator.push(new Character(ch));
			}
			
			//rule 4
			//If right parenthesis encountered, push to stack.
			else if (ch == '('){
				s_operator.push(new Character(ch));
			}
			
			//rule 6
			//If left parenthesis encountered, pop until left parenthesis is top of stack.
			//Then, discard right parenthesis.
			else if (ch == ')') {
				while(!s_operator.isEmpty() && !s_operator.top().equals('(')) {
					postfix = postfix + s_operator.pop();
				}
				s_operator.pop();
			}
		}
		
		//rule 3
		//when parse done, pop remaining elements in stack.
		while(!s_operator.isEmpty()){
			{
				postfix = postfix + s_operator.pop();
			}
		}
		return postfix;
	}
	
	/**
	 * this method boolean checks and compares the priority of the operator in the expression and in the top of stack,
	 * @param current_operator = operator in infix expression
	 * @param current_top = top operator in stack
	 * @return true if operator in infix is greater than operator in stack; false if otherwise or equal.
	 */
	private boolean checkPriority(char current_operator, char current_top){
		if (priority(current_operator) > priority(current_top)) {
			return true;
		}
		
		else if (priority(current_operator) == priority(current_top)) {
			return false;
		}
		
		else  {
			return false;
		}
	}
	
	/** 
	 * This method prints out the postfix equivalent of infix expression.
	 */
	public void output(){
		System.out.println("Postfix: " + postfix );
		pw.println("Postfix: " + postfix);
		
	}
}