// Hash.java
import java.io.*;

/**
* This class is an implementation of hashing which is used to omitt words from the binary tree.
* It contains the hash function which returns hashcode for the hash table.
* @author Chris Banci
* @version 5.0 - 5/5/16
*/
public class Hash {
	private PrintWriter pw;
	private String[] hashTable;
	private int size;
	private int code;
	private int collisions;
	private int omittCount;
	
	/**
	* This is the default constructor for Hash.
	* @param pw
	*/
	public Hash(PrintWriter pw) {
		this.pw = pw;
		
		this.size = 37;
		this.hashTable = new String[size];
		
		this.collisions = 0;
		this.omittCount = 0;
	}
	
	/**
	* This method is used to map a key such as a word to the hash table by using hashFunction()
	* to generate the words hash code. The has code is used to insert it word into its code
	* position by the use of the insert() method.
	* @param key
	*/
	public void map(String key) {
		code = hashFunction(key);
		
		insert(code, key);
	}
	
	/**
	* This method is the hash function which generates a unique hash code
	* that can be inserted into hash table of size 37 and produce 1 collision.
	* The method hashFunctionDescription() describes the whole process.
	* @param key
	* @return code
	*/
	public int hashFunction(String key) {
		code = 0;
		int sum = 0;
		
		for (int i = 0; i < key.length(); i++) {
			sum += (int) key.charAt(i);
		}
		
		code = (sum^2 * 306) % size;
		
		return code;
	}
	
	/**
	* This method is a minimal hash function which generates unique hash codes
	* that can be inserted into hash table of size 27 and produce 0 collisions.
	* Since we know the exact set of keys ahead of time, we can use a switch 
	* statement to assign each key a unique hash code.
	* @param key
	* @return code
	*/
	public int minimalHashFunction(String key) {
		code = 0;
		
		switch (key) {
			case "a": code = 0; break;
			case "after": code = 1; break;
			case "all": code = 2; break;
			case "and": code = 3; break;
			case "because": code = 4; break;
			case "every": code = 5; break;
			case "for": code = 6; break;
			case "from": code = 7; break;
			case "had": code = 8; break;
			case "have": code = 9; break;
			case "in": code = 10; break;
			case "is": code = 11; break;
			case "it": code = 12; break;
			case "its": code = 13; break;
			case "now": code = 14; break;
			case "of": code = 15; break;
			case "on": code = 16; break;
			case "so": code = 17; break;
			case "that": code = 18; break;
			case "the": code = 19; break;
			case "their": code = 20; break;
			case "there": code = 21; break;
			case "to": code = 22; break;
			case "was": code = 23; break;
			case "were": code = 24; break;
			case "which": code = 25; break;
			case "with": code = 26; break;
		}
		return code;
	}
	
	/**
	* This method inserts the word into the Hash table by its hash code.
	* If the index array is empty, it is inserted. If the index array is already occupied with
	* another value and the word is trying to be inserted to that location, it is a collision
	* and Linear Probing is used to resolve them. In Linear probing, if a collision occurs,
	* the word is inserted to the next array that is empty. The arrays that were probed to find
	* an empty array are all considered to be collisions.
	* @param code
	* @param key
	*/
	public void insert(int code, String key) {
		int probe = code;
		
		if (hashTable[code] == null) {
			hashTable[code] = key;
		}
		else {
			
			//Linear Probing
			while (hashTable[probe] != null) {
				collisions++;
				probe++;
				
				if (probe > size - 1) {
					probe = 0;
				}
			}
			
			if (hashTable[probe] ==  null){
				hashTable[probe] = key;
			}
		}
	}
	
	/**
	* This method is used to search a word in a hash table. This is done by checking the value
	* inside the index array of the hash code of the word. If the index array is empty,
	* its a miss search. If the index array value matches the word its a success. If the index array
	* value does not match the word, the array list is probed until its a success. If the value is not
	* matched by the end of wrapping around the table, its a fail.
	* @param key
	* @return boolean
	*/
	public boolean search(String key) {
		code = hashFunction(key);
		
		int probe = code;
		boolean wrap = false;
		
		if(hashTable[code] == null){
			return false;
		}
		
		else if (hashTable[code].equals(key)) {
			omittCount++;
			return true;
		}
		
		else {
			
			//Linear Probing
			while (hashTable[probe] != null && !(hashTable[probe].equals(key))) {
				probe++;
				
				if (probe > size - 1) {
					probe = 0;
					wrap = true;
				}
				
				if (hashTable[probe] != null && hashTable[probe].equals(key)){
					omittCount++;
					return true;
				}
				
				if (wrap == true && probe == code) {
					return false;
				}
			}
		}
		
		return false;
	}
	
	/**
	* This method returns the number of words that are omitted from binary tree.
	* @return omittCount
	*/
	public int getOmittCount() {
		return omittCount;
	}
	
	/**
	* This method prints out the hash table and the number of collisions.
	* If the array is not empty, it prints out its value.
	* If the array is empty, it prints out "-".
	*/
	public void printHashTable(){
		System.out.println("------------------------------");
		System.out.println("  Code \t|\tKey");
		System.out.println("------------------------------");
		
		pw.println("------------------------------");
		pw.println("  Code \t|\tKey");
		pw.println("------------------------------");
		
		for(int index = 0; index < size; index++){
			if(hashTable[index] != null){
				System.out.println("   " + index + "\t|\t" + hashTable[index]);
				pw.println("   " + index + "\t|\t" + hashTable[index]);
				
			}
			
			else if (hashTable[index] == null){
				System.out.println("   " + index +"\t|\t" + "-" );
				pw.println("   " + index +"\t|\t" + "-" );
			}
		}
		
		System.out.println("------------------------------");
		System.out.println("Collisions: " + "\t" + collisions);
		System.out.println("------------------------------\n");
		
		pw.println("------------------------------");
		pw.println("Collisions: " + "\t" + collisions);
		pw.println("------------------------------\n");
	}
	
	/**
	* This method outputs the hash function description.
	*/
	public void hashFunctionDescription() {
		System.out.println("----------------------------------------------------------------");
		System.out.println("A hash function is a function in which it transforms a key, such as a word,");
		System.out.println("into a unique number called a hash code. This hash code is very important as");
		System.out.println("it is the index in the hash table where the word will be placed and where it");
		System.out.println("will be searched. \n");
		
		System.out.println("The hash function below is able to generate a set of hash codes that can be");
		System.out.println("mapped into the hash table with only 1 collision. To create these hash codes,");
		System.out.println("the hash function first calculates the ascii values of each character in the");
		System.out.println("word and is added together to get a sum. Next, the sum goes through a bitwise");
		System.out.println("operation (XOR) with the number 2. In the bitwise operation (XOR), same bits");
		System.out.println("are flipped to 0 and different bits are flipped to 1. The new number from the");
		System.out.println("bitwise operation is then multiplied by the value of 306. The number 306 is a");
		System.out.println("value i found through trial and error. Lastly, the product is modulused by 37");
		System.out.println("to get a remainder that is in index range of the hash table.");
		System.out.println("The resulting number is the hash code.");
		System.out.println("----------------------------------------------------------------");
		
		
		pw.println("----------------------------------------------------------------");
		pw.println("A hash function is a function in which it transforms a key, such as a word,");
		pw.println("into a unique number called a hash code. This hash code is very important as");
		pw.println("it is the index in the hash table where the word will be placed and where it");
		pw.println("will be searched. \n\r\n\r");
		
		pw.println("The hash function below is able to generate a set of hash codes that can be");
		pw.println("mapped into the hash table with only 1 collision. To create these hash codes,");
		pw.println("the hash function first calculates the ascii values of each character in the");
		pw.println("word and is added together to get a sum. Next, the sum goes through a bitwise");
		pw.println("operation (XOR) with the number 2. In the bitwise operation (XOR), same bits");
		pw.println("are flipped to 0 and different bits are flipped to 1. The new number from the");
		pw.println("bitwise operation is then multiplied by the value of 306. The number 306 is a ");
		pw.println("value i found through trial and error. Lastly, the product is modulused by 37");
		pw.println("to get a remainder that is in index range of the hash table.");
		pw.println("The resulting number is the hash code.");
		pw.println("----------------------------------------------------------------");
		
	}
}