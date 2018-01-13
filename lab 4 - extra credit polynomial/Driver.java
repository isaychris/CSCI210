// Driver.java

import java.io.*;

/**
 * This is the driver class which contains the main method to run the whole program.
 * @author Chris Banci
 * @version 2.0 - 4/15/16
 */
public class Driver {
	public static void main(String[] args) throws IOException {
		//Chris Banci
		//ID: 010031304
		int choice;
		
		PrintWriter pw = new PrintWriter(new FileWriter("csis.txt"));
		SuperOut so = new SuperOut(pw);
		Polynomial poly = new Polynomial(pw, so);
		
		Menu menu = new Menu(pw, so);
		
		do {
			menu.display();
			choice = menu.getSelection();
			switch (choice) {
				case 1 : poly.menu_scalar(); break;
				case 2 : poly.menu_subtract(); break;
				case 3 : poly.menu_derivative(); break;
				case 4 : poly.menu_evaluate(); break;
			}
			
		} while (choice != 5);
		so.close();
	}
}