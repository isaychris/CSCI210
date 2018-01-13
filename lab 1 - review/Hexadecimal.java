
import java.io.*;
import java.util.Scanner;

/**
 * This is an implementation of a Hexadecimal class which is used for decimal and binary conversion.
 * @author Chris Banci
 * @version 2.0 - February 4, 2016
 */
public class Hexadecimal {
	private PrintWriter pw;
	private String input;
	private String bin;
	private String hex;
	private int decimal;
	
	/**
	 * This is the default constructor for Binary.
	 * PrintWriter object is passed through this constructor for file writing.
	 * Class variables are also initialized to default values;
	 * @param pw is passed.
	 */
	public Hexadecimal(PrintWriter pw) 
	{
		this.pw = pw;
		input = "";
		bin = "";
		decimal = 0;
		hex = "";
	}
	
	/**
	 * This method re-initializes class variables for next use.
	 */
	private void Reset() 
	{
		input = "";
		bin = "";
		decimal = 0;
		hex = "";
	}
	
	/**
	 * This method converts hexadecimal to decimal using three methods.
	 * <p>
	 * inputHex() receives input from user, toDec() does the conversions, and outDec(); outputs result.
	 * After conversion result is printed out, class variables are reset for next use.
	 * <p>
	 * This method is called when user chooses hexadecimal to decimal option from menu.
	 * 
	 */
	public void hexToDec()
	{
		System.out.println("-------------------------------------");
		System.out.println("[5] Hex -> Decimal \n");
		pw.println("-------------------------------------");
		pw.println("[5] Hex -> Decimal\r\n");
		
		inputHex();
		toDec();
		outDec();
		
		Reset();
	}
	
	/**
	 * This method converts hexadecimal to binary using three methods.
	 * <p>
	 * inputHex() receives input from user, toBin() does the conversions, and outBin() outputs result.
	 * After conversion result is printed out, class variables are reset for next use.
	 * <p>
	 * This method is called when user chooses hexadecimal to binary option from menu.
	 */
	public void hexToBin()
	{
		System.out.println("-------------------------------------");
		System.out.println("[6] Hex -> Binary\n");
		pw.println("-------------------------------------");
		pw.println("[6] Hex -> Binary\r\n");
		
		inputHex();
		toBin();
		outBin();
		
		Reset();
	}
	
	/**
	 * This method receives input from user and is validated with boolean and if statements.
	 * Input is not valid when it does not contain hexadecimal characters or length is longer than 8.
	 */
	private void inputHex()
	{
		boolean isValid = false;
		
		while(!isValid) {
			System.out.print("Enter Hexadecimal: ");
			pw.print("Enter Hexadecimal: ");
			
			Scanner keyboard = new Scanner(System.in);
			input = keyboard.nextLine();
			
			pw.println(input);
			
			if (input.matches("-?[0-9a-fA-F]+") && input.length() <= 8){
				hex = input;
				isValid = true;
			}
			else if (input.length() > 8){
				System.out.println("[Error] Input must have length 8 or lower");
				pw.println("[Error] Input must have length 8 or lower");
			}
			else {
				System.out.println("[Error] Invalid input");
				pw.println("[Error] Invalid input");
			}
		}
	}
	
	/**
	 * This method is where the hexadecimal to decimal conversion calculations takes place.
	 * The method directly converts to hex to decimal using mapping.
	 * <p>
	 * Algorithm:
	 * <p>
	 * Reading from left to right, each hexadecimal is first mapped to its decimal equivalent.
	 * Each digit is then multiplied by 16 to power of digits location and is all summed up together.
	 */
	private void toDec()
	{
		int hexdigit = 0;
		int power = 0;
		
		hex  = hex.toUpperCase();
		
		for (int i = 0; i < hex.length(); i++){
			if (hex.charAt(i) == 'A'){
				hexdigit = 10;
			}
			else if (hex.charAt(i) == 'B') {
				hexdigit = 11;
			}
			else if (hex.charAt(i) == 'C'){
				hexdigit = 12;
			}
			else if (hex.charAt(i) == 'D'){
				hexdigit = 13;
			}
			else if (hex.charAt(i) == 'E'){
				hexdigit = 14;
			}
			else if (hex.charAt(i) == 'F'){
				hexdigit = 15;
			}
			else {
				hexdigit = Character.getNumericValue(hex.charAt(i));
			}
			
			power = hex.length()-1-i;
			decimal = decimal + (hexdigit * (int)Math.pow(16, power));
		}
	}
	
	/**
	 * This method is where the hexadecimal to binary conversion calculations takes place.
	 * The method directly converts from hex to binary using mapping.
	 * <p>
	 * Algorithm:
	 * <p>
	 * Reading from left to right, each hexadecimal is first converted to its decimal equivalent.
	 * Then each digit is converted to its binary number equivalent.
	 */
	private void toBin()
	{
		hex  = hex.toUpperCase();
		
		for (int i = 0; i < hex.length(); i++){
			if (hex.charAt(i) == '0') {
				bin = bin.concat("0000");
			} else if (hex.charAt(i) == '1') {
				bin = bin.concat("0001");
			}  else if (hex.charAt(i) == '2') {
				bin = bin.concat("0010");
			}  else if (hex.charAt(i) == '3') {
				bin = bin.concat("0011");
			}  else if (hex.charAt(i) == '4') {
				bin = bin.concat("0100");
			}   else if (hex.charAt(i) == '5') {
				bin = bin.concat("0101");
			}   else if (hex.charAt(i) == '6') {
				bin = bin.concat("0110");
			}   else if (hex.charAt(i) == '7') {
				bin = bin.concat("0111");
			}   else if (hex.charAt(i) == '8') {
				bin = bin.concat("1000");
			}   else if (hex.charAt(i) == '9') {
				bin = bin.concat("1001");
			}   else if (hex.charAt(i) == 'A') {
				bin = bin.concat("1010");
			}   else if (hex.charAt(i) == 'B') {
				bin = bin.concat("1011");
			}   else if (hex.charAt(i) == 'C') {
				bin = bin.concat("1100");
			}   else if (hex.charAt(i) == 'D') {
				bin = bin.concat("1101");
			}   else if (hex.charAt(i) == 'E') {
				bin = bin.concat("1110");
			}   else if (hex.charAt(i) == 'F') {
				bin = bin.concat("1111");
			}
		}
		
		StringBuilder temp = new StringBuilder(bin);
		
		while (temp.length() != 32){
			temp.insert(0, "0");
		}
		
		for (int i = 4; i <= 34; i += 5) {
			temp.insert(i, " ");
		}
		
		bin = temp.toString();
	}
	
	/**
	 * This method outputs the hexadecimal input and decimal conversion result.
	 */
	private void outDec()
	{
		System.out.println("[Hex] " + input + " = " + decimal  + " [Decimal]");
		pw.println("[Hex] " + input + " = " + decimal  + " [Decimal]");
		
	}
	
	/**
	 * This method outputs the hexadecimal input and binary conversion result.
	 */
	private void outBin()
	{
		System.out.println("[Hex] " + input + " = " + bin  + " [Binary]");
		pw.println("[Hex] " + input + " = " + bin  + " [Binary]");
	}
}