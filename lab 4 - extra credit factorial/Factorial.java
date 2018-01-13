// Factorial.java

import java.io.*;
import java.util.Scanner;

/**
* This class is an implementation of a Factorial.
* @author Chris Banci
* version 2.0 - 4/15/16
*/
public class Factorial {
	private PrintWriter pw;
	private ObjectList expression;
	private ObjectList help;
	private int fact;
	
	/**
	* Default constructor for factorial which has pw paramater.
	* @param pw
	*/
	public Factorial(PrintWriter pw){
		this.pw = pw;
		
		this.expression= new ObjectList();
		this.help = new ObjectList();
		this.fact = 0;
	}
	
	/**
	* This method gets the desired factorial from user.
	*/
	public void getInput() {
		Scanner keyboard = new Scanner(System.in);
		System.out.print("Enter Factorial: ");
		pw.print("Enter Factorial: ");
		
		fact = keyboard.nextInt();
		pw.println(fact);
	}
	
	/**
	* This method creates an object with int x and attaches it to the end of a an objectlist.
	* @param help
	* @param x
	*/
	public void attach(ObjectList help, int x) {
		Storage temp = new Storage(x);
		help.addLast(temp);
	}
	
	/**
	 * This method calculates the factorial.
	 * Separate cases must be made for factorials below 10 and factorials over 10.
	 * 
	 */
	public void calc_factorial() {
		getInput();
		attach(expression, 1);
		
		for (int i=1; i<= fact; i++) {
			// result = result*i;
			
			if (i <= 10) {
				ObjectListNode p = expression.getFirstNode();
				multiply(p, i, i);
				addHelpers(expression, help, i);
			}
			
			if (i > 10) {
				int intLength = Integer.toString(i).length();
				int currentDigit = 0;
				
				if (intLength == 2) {
					ObjectList original = expression.copyList();
					ObjectList list1 = expression.copyList();
					ObjectList list2 = expression.copyList();

					for (int k = 0; k < 2; k++) {
						currentDigit = place(i,k);
						
						if ( k == 0) {
							ObjectListNode p = list1.getFirstNode();
							multiply(p, currentDigit, i);
							addHelpers(list1, help, i);
							
						}
						else if ( k == 1) {
							ObjectListNode p = list2.getFirstNode();
							multiply(p, currentDigit, i);
							addHelpers(list2, help, i);
							
							p = list2.getFirstNode();
							multiply(p, 10, i);
							addHelpers(list2, help, 10);
						}
						
						expression = addTogether(list2, list1).copyList();
						ObjectListNode p = expression.getFirstNode();	
						
						while(p != null) {
							Storage temp = (Storage) p.getInfo();
							checkSumCarry(temp);
							
							p = p.getNext();
						}
						addHelpers(expression, help, i);
					}
				}
			}
		}
		outputResult();
	}
	
	/**
	 * This method adds together two lists.
	 * @param first
	 * @param second
	 * @return newList
	 */
	public ObjectList addTogether(ObjectList first, ObjectList second) {
		ObjectList newList = new ObjectList();
		
		ObjectListNode p = first.getFirstNode();
		ObjectListNode q = second.getFirstNode();

		while (q != null) {
			Storage ptemp = (Storage) p.getInfo();
			Storage qtemp  = (Storage) q.getInfo();
			
			int newNumber = (ptemp.getStorage() + qtemp.getStorage());
			attach(newList, newNumber);
			q = q.getNext();
		}

		return newList;
	}
	
	
	/**
	* This method multiplys the factorial by i
	* @param p
	* @param i
	* @param current
	*/
	public void multiply(ObjectListNode p, int i, int current) {
		while(p != null) {
			Storage temp = (Storage) p.getInfo();
			temp.setStorage(temp.getStorage() * i);
			
			
			System.out.println(current + "! = [" + temp.getStorage() + "]");
			pw.println(current + "! = [" + temp.getStorage() + "]");
			
			checkSumCarry(temp);
			
			p = p.getNext();
		}
	}
	
	/**
	* This method checks if storage is less or greater than 999.
	* If less than 999, 0 is added to help list.
	* If greater than 999, the sum carry is added to help list.
	* @param temp
	*/
	public void checkSumCarry(Storage temp) {	
		if (temp.getStorage() > 999) {
			String intString = Integer.toString(temp.getStorage());
			int inthelper = intString.charAt(0) - '0';
			int newStorage = temp.getStorage() - (inthelper * 1000);
			
			temp.setStorage(newStorage);
			attach(help, inthelper);
			
		}
	}
	
	/**
	* This method adds the helper list to the expression.
	* @param list
	* @param help
	* @param i
	*/
	public void addHelpers(ObjectList list, ObjectList help, int i) {
		if(!help.isEmpty() && list.size() == 1) {
			
			ObjectListNode r = help.getFirstNode();
			Storage temp = (Storage) r.getInfo();
			
			list.addFirst(temp);
			help.clear();
		}
		
		if(!help.isEmpty() && list.size() >= 2) {
			ObjectListNode q = list.getFirstNode();
			ObjectListNode r = help.getFirstNode();
			
			
			if (i < 10) {
				Storage qtemp = (Storage) q.getInfo();
				Storage rtemp = (Storage) r.getInfo();
				
				while(r != null) {
					qtemp.setStorage(qtemp.getStorage() + rtemp.getStorage());
					r = r.getNext();
				}
				help.clear();
				
			}
			
			else if (i == 10) {
				shift(list);
			}
		}
	}
	
	
	/**
	* This method shifts number in the link lists to the left by 1 digit.
	* @param list
	*/
	public void shift(ObjectList list) {
		ObjectListNode q = list.getFirstNode();
		ObjectListNode r = help.getFirstNode();
		
		Storage qtemp = (Storage) q.getInfo();
		Storage rtemp = (Storage) r.getInfo();
		
		list.addFirst(rtemp);
		r = r.getNext();
		
		while(r != null) {
			qtemp = (Storage) q.getInfo();
			rtemp = (Storage) r.getInfo();
			
			qtemp.setStorage(qtemp.getStorage() + rtemp.getStorage());
			r = r.getNext();
		}
	}
	
	
	/**
	* This method returns the digit of a number at a specific place.
	* @param num
	* @param digit
	* @return location
	*/
	public int place(int num, int digit) {
		int location = ((num / (int) Math.pow(10, digit)) % 10);
		return location;
	}
	
	/**
	* This method outputs the desired factorial.
	*/
	public void outputResult() {
		ObjectListNode p = expression.getFirstNode();
		
		System.out.print("Linked List = ");
		pw.print("Linked List = ");

		
		while(p != null) {
			Storage store = (Storage) p.getInfo();
			
			System.out.print("[" + store.getStorage() + "]");
			pw.print("[" + store.getStorage() + "]");
			p = p.getNext();
		}
	}
	
}