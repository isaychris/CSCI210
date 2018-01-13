
import java.io.*;

/**
 * This is an implementation of a Driver class which is used to drive the whole conversion program.
 * @author Chris Banci
 * @version 2.0 - February 4, 2016
 */
public class Driver {
	public static void main(String[] args) throws IOException {
		//Chris Banci
		//ID: 010031304
		int choice;
		
		PrintWriter pw = new PrintWriter (new FileWriter("csis.txt"));
		Decimal dec = new Decimal (pw);
		Binary bin = new Binary (pw);
		Hexadecimal hex = new Hexadecimal (pw);
		Menu menu = new Menu(pw);
		
		do {
			menu.display();
			choice = menu.getSelection();
			switch (choice) {
				case 1 : dec.decToBin(); break;
				case 2 : dec.decToHex(); break;
				case 3 : bin.binToDec(); break;
				case 4 : bin.binToHex(); break;
				case 5 : hex.hexToDec(); break;
				case 6 : hex.hexToBin(); break;
			}
		} while (choice != 7);
		pw.close();
	}
}