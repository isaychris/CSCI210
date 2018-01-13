
import java.io.*;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * This is an implementation of a Decimal class which is used for binary and hex conversion.
 * @author Chris Banci
 * @version 2.0 - February 4, 2016
 */
public class Decimal {
	private PrintWriter pw;
	private int input;
	private int decimal;
	private String hex;
	private StringBuilder bin;
	
	/**
	 * This is the default constructor for Binary.
	 * PrintWriter object is passed through this constructor for file writing.
	 * Class variables are also initialized to default values;
	 * @param pw is passed.
	 */
	public Decimal(PrintWriter pw) 
	{
		this.pw = pw;
		input = 0;
		decimal = 0;
		hex = "";
	}
	
	/**
	 * This method re-initializes class variables for next use.
	 */
	private void Reset()
	{
		input = 0;
		decimal = 0;
		hex = "";
	}
	
	/**
	 * This method converts decimal to binary using three methods.
	 * <p>
	 * inputDec() receives input from user,toBin() does the conversions, and outBin() outputs result.
	 * After conversion result is printed out, class variables are reset for next use.
	 * <p>
	 * This method is called when user chooses Decimal to binary option from menu.
	 */
	public void decToBin()
	{
		System.out.println("-------------------------------------");
		System.out.println("[1] Decimal -> Binary \n");
		pw.println("-------------------------------------");
		pw.println("[1] Decimal -> Binary\r\n");
		
		inputDec();
		toBin();
		outBin();
		
		Reset();
	}
	
	/**
	 * This method converts decimal to hexadecimal using three methods. 
	 * <p>
	 * inputDec() receives input from user, toHex() does the conversions, and outHex() outputs result.
	 * After conversion result is printed out, class variables are reset for next use.
	 * <p>
	 * This method is called when user chooses decimal to hexadecimal option from menu.
	 * 
	 */
	public void decToHex()
	{
		System.out.println("-------------------------------------");
		System.out.println("[2] Decimal -> Hex \n");
		pw.println("-------------------------------------");
		pw.println("[2] Decimal -> Hex\r\n");
		
		inputDec();
		toHex();
		outHex();
		
		Reset();
	}
	
	/**
	 * This method receives input from user and is validated with boolean and if statements.
	 * Input is not valid when it contains characters or is out of range.
	 */
	private void inputDec()
	{
		boolean isValid = false;
		
		while(!isValid) {
			try {
				System.out.print("Enter Decimal: ");
				pw.print("Enter Decimal: ");
				
				Scanner keyboard = new Scanner(System.in);
				input = keyboard.nextInt();
				
				pw.println(input);
				
				if (input < 0 || input > 2147483647) {
					System.out.println("[Error] Input out of range.");
					pw.println("[Error] Input out of range.");
					
					isValid = false;
				}
				else {
					isValid = true;
					decimal = input;
				}
			}
			
			catch(InputMismatchException exception) {
				pw.println(input);
				System.out.println("[Error] Invalid input");
				pw.println("[Error] Invalid input");
			}
		}
	}
	
	/**
	 * This method is where the conversion calculations take place for decimal to binary.
	 * <p>
	 * Algorithm: 
	 * <p>
	 * Input is continually divide by 2 to get a result 
	 * and remainder of �1� or �0�, until the final result equals zero.
	 * Leading zeros are then  added in front of the result for it to be 32-bits.
	 */ 
	private void toBin()
	{
		String result = "";
		
		for ( int dec = input ; dec > 0 ; dec/=2 ){
			result = dec%2 + result;
		}
		
		bin = new StringBuilder(result);
		
		while (bin.length() != 32){
			bin.insert(0, "0");
		}
		
		for (int i = 4; i <= 34; i += 5) {
			bin.insert(i, " ");
		}
	}
	
	/**
	 * This method is where the conversion calculations take place for decimal to hexadecimal.
	 * <p>
	 * Algorithm: 
	 * 	
	 * Input is continually divide by 16 to get a result and remainder, until the final result equals zero.
	 * Each digit remainder is then mapped to its hexadecimal equivalent.
	 */ 
	private void toHex()
	{
		String key_table = "0123456789ABCDEF";
		
		if (decimal == 0){
			hex = '0' + hex;
		}
		
		while (decimal > 0){
			hex = key_table.charAt(decimal % 16) + hex;
			decimal = decimal / 16;
		}
	}
	
	/**
	 * This method outputs the decimal input and binary conversion result.
	 */
	private void outBin()
	{
		System.out.println("[Decimal] " + input + " = " + bin + " [Binary]");
		pw.println("[Decimal] " + input + " = " + bin + " [Binary]");
	}
	
	/**
	 * This method outputs the decimal input and hexadecimal conversion result.
	 */
	private void outHex()
	{
		System.out.println("[Decimal] " + input + " = " + hex + " [Hex]");
		pw.println("[Decimal] " + input + " = " + hex + " [Hex]");
	}
}
