//	ErrorCheck.java

import java.io.*;

/** 
 * This is an implementation of a class that checks the validity of an infix expression.
 * @author Chris Banci
 * @version 2.0 - 2/25/16
 */
public class ErrorCheck {
	private PrintWriter pw;
	private String infix;
	private char ch;

	/**
	 * This method is the default constructor for ErrorCheck class.
	 * @param buf = the infix expression
	 * @param pw = the priterWriter object for output to file.
	 */
	public ErrorCheck(String buf, PrintWriter pw) {
		this.pw = pw;
		this.infix = buf;
	}
	
	/**
	 * This method verifies the validity of the expression by checking parenthesis and adjacency.
	 * @return false if parendCheck or adjacencyCheck is false. true otherwise.
	 */
	public boolean isValid() {
		if (parendCheck() == false) {
			return false;
		}
		
		if (adjacencyCheck() == false) {
			return false;
		}
		return true;
	}
	
	/**
	 * This method checks the expression if there is a parenthesis missing 
	 * or if there is an extra parenthesis.
	 * @return false if missing parenthesis or extra parenthesis. true otherwise.
	 */
	private boolean parendCheck(){
		int leftparend = 0;
		int rightparend = 0;
		
		for (int i = 0; i < infix.length(); i++){
			ch = infix.charAt(i);
			if (ch == '('){
				leftparend++;
			}
			if (ch == ')'){
				rightparend++;
			}
		}
		
		if  (leftparend > rightparend) {
			System.out.println("[Error] missing parenthesis\n");
			pw.println("[Error] missing parenthesis\r\n");
			return false;
		}
		else if  (leftparend < rightparend) {
			System.out.println("[Error] extra parenthesis\n");
			pw.println("[Error] extra parenthesis\r\n");
			return false;
		}
		return true;
	}
	
	 /**
	  * This method checks the expression if operands are adjacent to each other.
	  * @return false if operators or operands are adjacent to each other. true otherwise.
	  */
	private boolean adjacencyCheck(){
		String temp;
		int count = 1;
		boolean isValid = true;
		
		temp = infix;
		temp = temp.replace("(", "").replace(")", "").trim();
		
		int[] exp = new int[temp.length()];
		
		for (int i = 0; i < temp.length(); i++) {
			ch = temp.charAt(i);
			
			if ((ch >= '0' && ch <= '9')) {
				exp[i] = 1;
			}
		}
		for (int i = 0; count < temp.length(); i++) {
			if ((exp[i] == exp[i+1]) && (exp[i] == 1 && exp[i+1] == 1)){
				System.out.println("[Error] Adjacent operands");
				pw.println("[Error] Adjacent operands");
				isValid = false;
			}
			if ((exp[i] == exp[i+1]) && (exp[i] == 0 && exp[i+1] == 0)){
				System.out.println("[Error] Adjacent operators");
				pw.println("[Error] Adjacent operators");
				isValid = false;
			}
			count++;
		}
		if (isValid == false){
			System.out.println("");
			pw.println("\r");
			return false;
		}
		return true;
	}
}