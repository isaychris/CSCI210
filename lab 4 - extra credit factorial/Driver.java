// Driver.java
import java.io.*;

/**
 * This is the driver class which contains the main method to run the program.
 * @author Chris Banci
 * @version 2.0 - 4/15/16
 */
public class Driver {
	public static void main(String[] args) throws IOException {
		//Chris Banci
		//ID: 010031304
		PrintWriter pw = new PrintWriter(new FileWriter("csis.txt"));
		
		Factorial fact = new Factorial(pw);
		fact.calc_factorial();
		
		pw.close();
	}
}