// Driver.java

import java.io.*;

/**
 * This is the driver class which contains the main method to run the whole program.
 * @author Chris Banci
 * @version 2.0 - 4/15/16
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
		Payroll pay = new Payroll(pw);
		
		pay.parseData();
		
        System.out.println("// Payroll");
        pw.println("// Payroll");
        pay.output_paylist();
        
        
		System.out.println("\n // Women in Payroll");
		pw.println("");
		pw.println("// Women in Payroll");
        pay.getWomen();
        pay.outputWoman_list();
        
        
		System.out.println("\n // Employees who make more than 35,000$ a year and 5+ tenure");
		pw.println("");
		pw.println("// Employees who make more than 35,000$ a year and 5+ tenure");
		pay.getLoyalityList();
		pay.outputWeeklySalaryTenure();
        
		
		System.out.println("\n // Employees with raise - new salary");
		pw.println("");
		pw.println("// Employees with raise");
        pay.giveRaise();
        pay.outputRaise_list();
        
        
		System.out.println("\n // Sorted (Last Name) - Updated Database");
		pw.println("");
		pw.println("// Sorted (Last Name) - Updated Database");
        pay.sortList();
        pay.outputSortedList();
        
        
		System.out.println("\n // Hired - Updated Database");
		pw.println("");
		pw.println("// Hired - Updated Database");
        pay.hire();
        pay.outputDatabase();
        
        
		System.out.println("\n // Fired - Updated Database");
		pw.println("");
		pw.println("// Fired - Updated Database");
        pay.fire();
        pay.outputDatabase();
        
        
		pw.close();
	}
}
