
import java.io.*;
import java.util.Scanner;

/**
 * This is an implementation of a Binary class which is used for decimal and hexadecimal conversion.
 * @author Chris Banci
 * @version 2.0 - February 4, 2016
 */
public class Binary {
	private PrintWriter pw;
	private String input;
	private String bin;
	private StringBuilder hex;
	private int decimal;
	
	/**
	 * This is the default constructor for Binary.
	 * PrintWriter object is passed through this constructor for file writing.
	 * Class variables are also initialized to default values;
	 * @param pw is passed.
	 */
	public Binary(PrintWriter pw)
	{
		this.pw = pw;
		input = "";
		bin = "";
		decimal = 0;
	}
	
	/**
	 * This method re-initializes class variables for next use.
	 */
	private void Reset () 
	{
		input = "";
		bin = "";
		decimal = 0;
	}
	
	/**
	 * This method converts binary to decimal using three methods.
	 * <p>
	 * First method gets input from user, second method does the conversions, and last method outputs result.
	 * After conversion result is printed out, class variables are reset for next use.
	 * <p>
	 * This method is called when user chooses binary to decimal option from menu.
	 */
	public void binToDec()
	{
		System.out.println("-------------------------------------");
		System.out.println("[3] Binary -> Decimal \n");
		pw.println("-------------------------------------");
		pw.println("[3] Binary -> Decimal\r\n");

		inputBin();
		toDec();
		outDec();
		
		Reset();
	}
	
	/**
	 * This method converts binary to hexadecimal using three methods.
	 * <p>
	 * First method gets input from user, second method does the conversions, and last method outputs result.
	 * After conversion result is printed out, class variables are reset for next use.
	 * <p>
	 * This method is called when user chooses binary to hexadecimal option from menu.
	 */
	public void binToHex()
	{
		System.out.println("-------------------------------------");
		System.out.println("[4] Binary -> Hex \n");
		pw.println("-------------------------------------");
		pw.println("[4] Binary -> Hex\r\n");
		
		inputBin();
		toHex();
		outHex();
		
		Reset();
	}
	
	/**
	 * This method receives input from user and is validated with boolean and if statements.
	 * Input is not valid when it does not contain binary numbers or is longer than 32-bits.
	 */
	private void inputBin()
	{
		boolean isValid = false;
		
		while(!isValid) {
			System.out.print("Enter 32-bit Binary: ");
			pw.print("Enter 32-bit Binary: ");
			
			Scanner keyboard = new Scanner(System.in);
			input = keyboard.nextLine();
			
			pw.println(input);
			
			input = input.replace(" ", "").trim();
			
			if (input.matches("[01]+") && input.length() <= 32){
				bin = input;
				isValid = true;
			}
			else if (input.length() > 32){
				System.out.println("[Error] Input must be up to 32-bits");
				pw.println("[Error] Input must be up to 32-bits");
			}
			else {
				System.out.println("[Error] Invalid input");
				pw.println("[Error] Invalid input");
			}
		}
	}
	
	/**
	 * This method is where the binary to decimal conversion calculations takes place.
	 * <p>
	 * Algorithm:
	 * <p>
	 * Reading from right to left, each binary digit is multiplied by 2 to the power of 
	 * binary digits location starting from 0.
	 */
	private void toDec() 
	{
		int power = 0;
		
		for (int i = 0; i < bin.length(); i++){
			if (bin.charAt(i) == '1'){
				power = bin.length()-1-i;
				decimal = decimal + (int) Math.pow(2,power);
			}
		}
	}
	
	/**
	 * This method is where the binary to hexadecimal conversion calculations takes place.
	 * This method directly converts from binary to hex using mapping.
	 * <p>
	 * Algorithm:
	 * <p>
	 * Reading from right to left, each binary digit in a nibble is multiplied by 2 to the power 
	 * of binary digits location starting from 0. The decimal for each nibble is then converted to its hex equivalent.
	 * 
	 */
	private void toHex() 
	{		
		hex = new StringBuilder("00000000");
		StringBuilder temp = new StringBuilder(bin);
		
		while (temp.length() != 32){
			temp.insert(0, "0");
		}
		
		bin = temp.toString();
		
		for (int i = 0, j = 0; i < bin.length(); i+=4, j++) {
			if (bin.substring(i, i+4).equals("0000")) {
				hex.setCharAt(j, '0');
			} else if (bin.substring(i, i+4).equals("0001")) {
				hex.setCharAt(j, '1');
			} else if (bin.substring(i, i+4).equals("0010")) {
				hex.setCharAt(j, '2');
			} else if (bin.substring(i, i+4).equals("0011")) {
				hex.setCharAt(j, '3');
			} else if (bin.substring(i, i+4).equals("0100")) {
				hex.setCharAt(j, '4');
			} else if (bin.substring(i, i+4).equals("0101")) {
				hex.setCharAt(j, '5');
			} else if (bin.substring(i, i+4).equals("0110")) {
				hex.setCharAt(j, '6');
			} else if (bin.substring(i, i+4).equals("0111")) {
				hex.setCharAt(j, '7');
			} else if (bin.substring(i, i+4).equals("1000")) {
				hex.setCharAt(j, '8');
			} else if (bin.substring(i, i+4).equals("1001")) {
				hex.setCharAt(j, '9');
			} else if (bin.substring(i, i+4).equals("1010")) {
				hex.setCharAt(j, 'A');
			} else if (bin.substring(i, i+4).equals("1011")) {
				hex.setCharAt(j, 'B');
			} else if (bin.substring(i, i+4).equals("1100")) {
				hex.setCharAt(j, 'C');
			} else if (bin.substring(i, i+4).equals("1101")) {
				hex.setCharAt(j, 'D');
			} else if (bin.substring(i, i+4).equals("1110")) {
				hex.setCharAt(j, 'E');
			} else if (bin.substring(i, i+4).equals("1111")) {
				hex.setCharAt(j, 'F');
			}
		}
	}
	
	/**
	 * This method outputs the binary input and decimal conversion result.
	 */
	private void outDec()
	{
		System.out.println("[Binary] " + input + " = " + decimal  + " [Decimal]");
		pw.println("[Binary] " + input + " = " + decimal  + " [Decimal]");
	}
	
	/**
	 * This method outputs the binary input and hexadecimal conversion result.
	 */
	private void outHex()
	{
		System.out.println("[Binary] " + input + " = " + hex  + " [Hex]");
		pw.println("[Binary] " + input + " = " + hex  + " [Hex]");
	}
}