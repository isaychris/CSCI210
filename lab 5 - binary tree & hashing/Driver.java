// Driver.java
import java.io.*;
import java.util.Scanner;

/**
* This is the driver class which contains the main method to run the whole program.
* @author Chris Banci
* @version 5.0 - 5/5/16
*/
public class Driver {
	
	/**
	* This is the main method for the driver.
	* @param args
	* @throws IOException
	*/
	public static void main(String[] args) throws IOException {
		//Chris Banci
		//ID: 010031304
		
		PrintWriter pw = new PrintWriter(new FileWriter("csis.txt"));
		Scanner getty = new Scanner(new File("getty.txt"));
		Scanner omitt = new Scanner(new File("omit.txt"));
		
		Hash h = new Hash(pw);
		Xref x = new Xref(pw, getty, omitt, h);
		Query q = new Query(pw, x);
		
		System.out.println("// Getty Address");
		pw.println("// Getty Address");
		x.printGetty();
		
		
		System.out.println("\n\n// Hash Function Description");
		pw.println("\r\n\r\n// Hash Function Description");
		h.hashFunctionDescription();
		
		
		System.out.println("\n\n// Hash Table");
		pw.println("\r\n\r\n// Hash Table");
		x.parseOmitt();
		h.printHashTable();
		
		
		System.out.println("\n\n// Cross Reference");
		pw.println("\r\n\r\n// Cross Reference");
		x.parseGetty();
		x.printTree();
		
		
		System.out.println("\n\n// Query Search");
		pw.println("\r\n\r\n// Query Search");
		q.search();
		
		pw.close();
	}
}
