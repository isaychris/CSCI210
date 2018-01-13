// Storage.java

/**
 * This class is an implementation of numbers within a factorial.
 * @author Chris Banci
 * @version 2.0 - 4/15/16
 */
public class Storage {
	private int number;
	
	/**
	 * Default constructor for storage.
	 */
	public Storage() {
		this.number = 0;
	}
	
	/**
	 * constructor that creates storage object with the number set to int x.
	 * @param x
	 */
	public Storage(int x) {
		this.number = x;
	}
	
	/**
	 * This method sets storage to int x;
	 * @param x
	 */
	public void setStorage(int x){
		number = x;
	}
	
	/**
	 * This method returns the number.
	 * @return number
	 */
	public int getStorage(){
		return number;
	}
}
