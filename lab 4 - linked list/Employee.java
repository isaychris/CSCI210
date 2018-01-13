// Employee.java

/**
 * This class is an implentation of an Employee which implements Comparable.
 * @author Chris Banci
 * @version 2.0 - 4/15/16
 */
public class Employee implements Comparable {
	private String firstName;
	private String lastName;
	private char gender;
	private int tenure;
	private char rate;
	private double salary;
	
	/**
	 * This is the constructor for Employee.
	 * @param firstName
	 * @param lastName
	 * @param gender
	 * @param tenure
	 * @param rate
	 * @param salary
	 */
	public Employee(String firstName, String lastName, char gender, int tenure, char rate, double salary) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.gender = gender;
		this.tenure = tenure;
		this.rate = rate;
		this.salary = salary;
	}
	
	/**
	 * This Employee constructor is used to store employee data from firelist.
	 * @param firstName
	 * @param lastName
	 */
	public Employee(String firstName, String lastName) {
		this.firstName = firstName;
		this.lastName = lastName;
	}
		
	/**
	 * This method gets the Employees first name.
	 * @return firstName
	 */
	public String get_firstName(){
		return firstName;
	}
	
	/**
	 * This method gets the Employees last name
	 * @return lastName
	 */
	public String get_lastName(){
		return lastName;
	}
	
	/**
	 * This method gets the Employees gender.
	 * @return gender
	 */
	public char get_gender(){
		return gender;
	}
	
	/**
	 * This method gets the Employees tenure.
	 * @return tenure
	 */
	public int get_tenure(){
		return tenure;
	}
	
	/**
	 * This method gets the employees wage type.
	 * @return rate
	 */
	public char get_rate(){
		return rate;
	}
	
	/**
	 * This method gets the employees salary.
	 * @return salary
	 */
	public double get_salary(){
		return salary;
	}
	
	/**
	 * This method sets the Employees first name.
	 * @param first
	 */
	public void set_firstName(String first){
		firstName = first;
	}
	
	/**
	 * This method sets the Employees last name.
	 * @param last
	 */
	public void set_lastName(String last){
		lastName = last;
	}
	
	/**
	 * This method sets the employees gender.
	 * @param g
	 */
	public void set_gender(char g){
		gender = g;
	}
	
	/**
	 * This method sets the Employees salary.
	 * @param d
	 */
	public void set_salary(double d){
		salary = d;
	}
	
	/**
	 * This method compares two object types using recursion.
	 * @return lastName.compareTo(temp.get_lastName())
	 */
	public int compareTo (Object o) {
		Employee temp = (Employee) o;
		return lastName.compareTo(temp.get_lastName());
	}
}
