// Payroll.java

import java.io.*;
import java.util.Scanner;

/**
 * This class is an implementation of a payroll.
 * @author Chris Banci
 * @version 2.0 - 4/15/16
 */
public class Payroll {
	private PrintWriter pw;
	private ObjectList database; //will update with changes from events.
	private ObjectList pay_list;
	private ObjectList hire_list;
	private ObjectList fire_list;
	private ObjectList raise_list;
	private ObjectList woman_list;
	private ObjectList sorted_list;
	private ObjectList loyal_list;
	
	/**
	 * This is the default constructor for payroll.
	 * @param pw
	 */
	public Payroll(PrintWriter pw){
		this.pw = pw;
		
		this.database = new ObjectList();
		this.pay_list = new ObjectList();
		this.hire_list = new ObjectList();
		this.fire_list = new ObjectList();
		this.raise_list = new ObjectList();
		this.woman_list = new ObjectList();
		this.sorted_list = new ObjectList();
		this.loyal_list = new ObjectList();
	}
	
	/**
	 * This method calls the methods to parse the data from payfile, hirefile and firefile.
	 * @throws FileNotFoundException
	 */
	public void parseData() throws FileNotFoundException {
		getPayList();
		getHireList();
		getFireList();
	}
	
	/**
	 * This method parses the data from payfile.
	 * @throws FileNotFoundException
	 */
	public void getPayList() throws FileNotFoundException {
		Scanner payScan = new Scanner(new File("payfile.txt"));
		pay_list = new ObjectList();
		
		while(payScan.hasNext()){
			String buf = payScan.nextLine();
			String[] tokens = buf.split("[ ]+");
			
			Employee temp = new Employee(tokens[0], tokens[1], tokens[2].charAt(0),
			Integer.parseInt(tokens[3]), tokens[4].charAt(0), Float.parseFloat(tokens[5]));
			
			pay_list.addLast(temp);
		}
		
		payScan.close();
		database = pay_list.copyList();
	}
	
	/**
	 * This method parses the data from hirefire.
	 * @throws FileNotFoundException
	 */
	public void getHireList() throws FileNotFoundException {
		Scanner hireScan = new Scanner(new File("hirefile.txt"));
		hire_list = new ObjectList();
		
		while(hireScan.hasNext()){
			String buf = hireScan.nextLine();
			String[] tokens = buf.split("[ ]+");
			
			Employee temp = new Employee(tokens[0], tokens[1], tokens[2].charAt(0),
			Integer.parseInt(tokens[3]), tokens[4].charAt(0), Float.parseFloat(tokens[5]));
			
			hire_list.addLast(temp);
		}
		
		hireScan.close();
	}
	
	/**
	 * This method parses the data from firefile.
	 * @throws FileNotFoundException
	 */
	public void getFireList() throws FileNotFoundException {
		Scanner fireScan = new Scanner(new File("firefile.txt"));
		fire_list = new ObjectList();
		
		while(fireScan.hasNext()){
			String buf = fireScan.nextLine();
			String[] tokens = buf.split("[ ]+");
			
			Employee temp = new Employee(tokens[0], tokens[1]);
			
			fire_list.addLast(temp);
		}
		
		fireScan.close();
	}
	
	/**
	 * This method gets the women in payroll.
	 */
	public void getWomen() {
		ObjectListNode p = pay_list.getFirstNode();
		woman_list = new ObjectList();
		
		while (p != null) {
			Employee temp = (Employee) p.getInfo();
			
			if (temp.get_gender() == 'F') {
				woman_list.addLast(temp);
			}
			
			p = p.getNext();
		}
	}
	
	
	/**
	 * This method gets the loyal employees within the payroll.
	 * star employees are ones who make more than $35k a year 
	 * and have been with company at least +5 years.
	 */
	public void getLoyalityList() {
		ObjectListNode p = database.getFirstNode();
		double annualSalary = 0;
		
		while (p != null) {
			Employee temp = (Employee) p.getInfo();
			annualSalary = (temp.get_salary() * 52);
			
			if ((temp.get_rate() == 'W') && (annualSalary >= 35000.00) && temp.get_tenure() >= 5) {
				loyal_list.addLast(temp);
			}
			
			p = p.getNext();
		}
	}
	
	/**
	 * This method gives a raise to employees with specific criteria.
	 * Criteria: 
	 * 		75c raise = 0Employee is paid on hourly bases and makes more than 10$ per hour.
	 *  	50$ raise = Employee is paid weekly and make less than 350% per week.
	 */
	public void giveRaise() {
		ObjectListNode p = database.getFirstNode();
		raise_list = new ObjectList();
		
		while (p != null) {
			Employee temp = (Employee) p.getInfo();
			
			if ((temp.get_rate() == 'H') && (temp.get_salary() <= 10.00)) {
				temp.set_salary(temp.get_salary() + (double) .75);
				p.setInfo(temp);
				
				raise_list.addLast(temp);
			}
			
			if ((temp.get_rate() == 'W') && (temp.get_salary() <= 350.00)) {
				temp.set_salary(temp.get_salary() + (double) 50.00);
				p.setInfo(temp);
				
				raise_list.addLast(temp);
			}
			
			p = p.getNext();
		}
	}
	
	
	/**
	 * This method returns the list size.
	 * @param list
	 */
	public void listSize(ObjectList list) {
		System.out.println("[Employee List size = " + list.size() + "]");
		pw.println("[Employee List size] = " + list.size());
		
	}
	
	/**
	 * This method sorts the payroll by last name.
	 */
	public void sortList() {
		ObjectListNode p = database.getFirstNode();
		
		while (p != null) {
			Employee temp = (Employee) database.removeFirst();
			sorted_list.insert(temp);
			
			p = database.getFirstNode();
		}
		
		database = sorted_list.copyList();
	}
	
	/**
	 * This method adds employees to payroll using hirefile.
	 */
	public void hire(){
		ObjectListNode p = hire_list.getFirstNode();
		
		while(p != null) {
			Employee temp = (Employee) p.getInfo();
			database.insert(temp);
			
			p = p.getNext();
		}
	}
	
	/**
	 * This method removes employees from payroll using firefile.
	 */
	public void fire(){
		ObjectListNode p = fire_list.getFirstNode();
		
		while(p != null) {
			Employee temp = (Employee) p.getInfo();
			database.remove(temp);
			
			p = p.getNext();
		}
	}
	
	/**
	 * This method outputs the paylist.
	 * Output all data.
	 */
	public void output_paylist() {
		outputHeader();
		
		ObjectListNode p = pay_list.getFirstNode();
		
		while(p != null) {
			Employee temp = (Employee) p.getInfo();
			
			System.out.println(String.format("%-8s %-8s %8s %8s %8s %15.2f", 
					temp.get_firstName(), 
					temp.get_lastName(), 
					temp.get_gender(), 
					temp.get_tenure(), 
					temp.get_rate(), 
					temp.get_salary()));
			
			pw.println(String.format("%-8s %-8s %8s %8s %8s %15.2f", 
					temp.get_firstName(), 
					temp.get_lastName(), 
					temp.get_gender(), 
					temp.get_tenure(), 
					temp.get_rate(), 
					temp.get_salary()));
			
			p = p.getNext();
		}
		
		listSize(pay_list);
	}
	
	/**
	 * This method outputs the updated database after certain events.
	 * Output only first name and last name
	 */
	public void outputDatabase() {
		outputHeader();
		
		ObjectListNode p = database.getFirstNode();
		
		while(p != null) {
			Employee temp = (Employee) p.getInfo();
			
			System.out.println(String.format("%-8s %-8s", 
					temp.get_firstName(), 
					temp.get_lastName()));
			
			pw.println(String.format("%-8s %-8s", 
					temp.get_firstName(), 
					temp.get_lastName()));
			
			p = p.getNext();
		}
		
		listSize(database);
	}
	
	/**
	 * This method outputs sorted_list.
	 * Output only first name, last name, and salary.
	 */
	public void outputSortedList() {
		outputHeader();
		
		ObjectListNode p = database.getFirstNode();
		
		while(p != null) {
			Employee temp = (Employee) p.getInfo();
			
			System.out.println(String.format("%-8s %-8s %8s %8s %8s %15.2f", 
					temp.get_firstName(), 
					temp.get_lastName(), 
					"", 
					"", 
					"", 
					temp.get_salary()));
			
			pw.println(String.format("%-8s %-8s %8s %8s %8s %15.2f", 
					temp.get_firstName(), 
					temp.get_lastName(), 
					"", 
					"", 
					"", 
					temp.get_salary()));
			
			p = p.getNext();
		}
		
		listSize(database);
	}
	
	/**
	 * This method outputs all the women on the payroll.
	 * Output only firstname
	 */
	public void outputWoman_list() {
		outputHeader();
		
		ObjectListNode p = woman_list.getFirstNode();
		
		while(p != null) {
			Employee temp = (Employee) p.getInfo();
			
			System.out.println(String.format("%-8s", temp.get_firstName()));
			pw.println(String.format("%-8s", temp.get_firstName()));
			
			p = p.getNext();
		}
		
		listSize(woman_list);
	}

	
	/**
	 * This method outputs the employees that have a salary higher than $35,0000
	 * and have been the company for atleast 5 years.
	 * Output only first name, last name, and salary.
	 */
	public void outputWeeklySalaryTenure() {
		outputHeader();
		ObjectListNode p = loyal_list.getFirstNode();
		
		while(p != null) {
			Employee temp = (Employee) p.getInfo();
			
			System.out.println(String.format("%-8s %-8s %8s %8s %8s %15.2f", 
					temp.get_firstName(), 
					temp.get_lastName(), 
					"", 
					"", 
					"", 
					temp.get_salary()));
			
			pw.println(String.format("%-8s %-8s %8s %8s %8s %15.2f", 
					temp.get_firstName(), 
					temp.get_lastName(), 
					"", 
					"", 
					"", 
					temp.get_salary()));
	
			p = p.getNext();
		}
		
		listSize(loyal_list);
	}
	
	/**
	 * This method outputs the employees that got a raise.
	 * Output only first name, last name and updated salary.
	 */
	public void outputRaise_list() {
		outputHeader();
		
		ObjectListNode p = raise_list.getFirstNode();
		
		while(p != null) {
			Employee temp = (Employee) p.getInfo();
			
			System.out.println(String.format("%-8s %-8s %8s %8s %8s %15.2f", 
					temp.get_firstName(), 
					temp.get_lastName(), 
					"", 
					"", 
					"", 
					temp.get_salary()));
			
			pw.println(String.format("%-8s %-8s %8s %8s %8s %15.2f", 
					temp.get_firstName(), 
					temp.get_lastName(), 
					"", 
					"", 
					"", 
					temp.get_salary()));
			
			p = p.getNext();
		}
		
		listSize(raise_list);
	}
	
	/**
	 * This method outputs the header for the employee data.
	 */
	public void outputHeader() {
		System.out.println(String.format("%-8s %-8s %11s %8s %7s %14s", 
				"First", 
				"Last", 
				"Gender", 
				"Years", 
				"Rate", 
				"Salary"));
		System.out.println("--------------------------------------------------------------");
		
		pw.println(String.format("%-8s %-8s %11s %8s %7s %14s", 
				"First", 
				"Last", 
				"Gender", 
				"Years", 
				"Rate", 
				"Salary"));
		pw.println("--------------------------------------------------------------");
	}
}
