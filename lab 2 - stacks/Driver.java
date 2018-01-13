//	Driver.java

import java.io.*;
import java.util.Scanner;

/**
 * This is an implementation of a Driver class that runs the whole program.
 * @author Chris Banci
 * @version 2.0 - 2/25/16
 */
public class Driver {
	/**
	 * This method contains main which drives the program.
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {
		// Chris Banci
		// ID: 001031304
		Scanner fileScan = new Scanner(new File("infix.txt"));
		PrintWriter pw = new PrintWriter (new FileWriter("csis.txt"));
		String postfix;
		
		System.out.println("//Infix to Postfix conversion \n");
		pw.println("//Infix to Postfix conversion \r\n");
		
		while (fileScan.hasNext()) {
			String buf = fileScan.nextLine();
			buf = buf.replaceAll(" ", "");
			
			ErrorCheck verify = new ErrorCheck(buf, pw);
			
			System.out.println("Infix: " + buf);
			pw.println("Infix: " + buf);
			
			if(verify.isValid() == true) {
				InfixToPostfix infix = new InfixToPostfix(buf, pw);
				postfix = infix.convert();
				infix.output();
				
				EvalPostFix eval = new EvalPostFix(postfix, pw);
				eval.evaluate();
				eval.output();
			}
		}
		fileScan.close();
		pw.close();
	}
}

		