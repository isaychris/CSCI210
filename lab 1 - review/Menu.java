
import java.io.*;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * This is an implementation of a Menu class which is used to display the conversion menu to user.
 * @author Chris Banci
 * @version 2.0 - February 4, 2016
 */
public class Menu {
	public PrintWriter pw;
	public int selection;
	
	/**
	 * This is the default constructor for Menu.
	 * PrintWriter object is passed through this method for file writing in Menu Class.
	 * @param pw is passed.
	 */
	public Menu(PrintWriter pw) 
	{
		this.pw = pw;
	}
	
	/**
	 * This method displays the main menu that contains options for conversions.
	 */
	public void display()
	{
		System.out.println("  _________________________________  ");
		System.out.println("<| ========   Main Menu   ======== |>");
		System.out.println("<|_________________________________|>");
		System.out.println("[1] Decimal -> Binary");
		System.out.println("[2] Decimal -> Hex");
		System.out.println("[3] Binary -> Decimal");
		System.out.println("[4] Binary -> Hex");
		System.out.println("[5] Hex -> Decimal");
		System.out.println("[6] Hex -> Binary");
		System.out.println("[7] Exit & Save\n");
		System.out.print("Select option[?]: ");
		
		pw.println("  _________________________________  ");
		pw.println("<| ========   Main Menu   ======== |>");
		pw.println("<|_________________________________|>");
		pw.println("[1] Decimal -> Binary");
		pw.println("[2] Decimal -> Hex");
		pw.println("[3] Binary -> Decimal");
		pw.println("[4] Binary -> Hex");
		pw.println("[5] Hex -> Decimal");
		pw.println("[6] Hex -> Binary");
		pw.println("[7] Exit & Save\r\n");
		pw.print("Select option[?]: ");
	}
	
	/**
	 * This method receives input from user and is validated using boolean and if statement.
	 * Input is not valid when input is out of range.
	 */
	public void inputCheck()
	{
		Boolean isValid = false;
		
		while(!isValid) {
			try {
				Scanner keyboard = new Scanner(System.in);
				selection = keyboard.nextInt();
				
				pw.println(selection);
			
				if (selection < 1 || selection > 7 ) {
					System.out.print("[Error] Invalid option, enter again: ");
					pw.print("[Error] Invalid option, enter again: ");
					isValid = false;
				}
				else {
					isValid = true;
				}
			}
			catch(InputMismatchException exception) {
				pw.println(selection);
				System.out.print("[Error] Invalid option, enter again: ");
				pw.print("[Error] Invalid option, enter again: ");
			}
		}
	}
	
	/**
	 * This method returns the selected menu option from inputCheck().
	 * @return int selection
	 */
	public int getSelection()
	{
		inputCheck();
		return selection;
	}
}
