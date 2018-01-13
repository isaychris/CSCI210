// Word.java
import java.io.*;

/**
* This class is used to create a Word object, which holds a word, a word count,
* and a linked list that holds its line number and position from the getty address.
* The class implements TreeComparable to define methods for operating, visiting and
* comparing to binary tree.
*
* @author Chris Banci
* @version 5.0 - 5/5/16
*/
public class Word implements TreeComparable {
	private PrintWriter pw;
	private String word;
	private int count;
	private ObjectList list;
	
	/**
	* This is the default constructor for Word.
	* @param pw
	*/
	public Word(PrintWriter pw) {
		this.word = "";
		this.count = 0;
		this.list = new ObjectList();
	}
	
	/**
	* This method is a constructor to create a Word Object.
	* @param pw
	* @param word
	* @param count
	* @param list
	*/
	public Word(PrintWriter pw, String word, int count, ObjectList list) {
		this.pw = pw;
		
		this.word = word;
		this.count = count;
		this.list = list;
		
	}
	
	/**
	* This method is a constructor to create a Word Object for query searching.
	* @param word
	*/
	public Word(String word) {
		this.word = word;
	}
	
	/**
	* This method returns the word of the word object.
	* @return word
	*/
	public String getWord() {
		return word;
	}
	
	/**
	* This method returns the number of times the word appears in the text
	* @return count
	*/
	public int getCount() {
		return count;
	}
	
	/**
	* This method returns the linked list that holds the line position object.
	* @return list
	*/
	public ObjectList getList() {
		return list;
	}
	
	/**
	* This method is used to compare two words.
	* @param o
	* @return word.compareTo(temp.getWord())
	*/
	public int compareTo(Object o) {
		Word temp = (Word) o;
		
		return word.compareTo(temp.getWord());
	}
	
	/**
	* This method is used in the insertBSTDup() method to define what happens when a duplicate word
	* is inserted into the binary tree. The word count is incremented and the duplicates linked list
	* that holds its line position gets added to the original linked list.
	* @param o
	*/
	public void operate(Object o) {
		Word word_temp = (Word) o;
		
		ObjectListNode p = word_temp.getList().getFirstNode();
		
		count = count + 1;
		list.addLast(p);
	}
	
	/**
	* This method is used in the insertBSTDup() method to define what happens a node is visited
	* in the binary tree. When the node is visited, the word, its count and linked list is printed.
	*/
	public void visit() {
		System.out.print(String.format("%-15s %-10s", word, count));
		pw.print(String.format("%-15s %-10s", word, count));
		
		ObjectListNode p = list.getFirstNode();
		
		while (p != null) {
			LinePosition temp = (LinePosition) p.getInfo();
			System.out.print(temp.getLine() + "-" + temp.getPosition() + " ");
			pw.print(temp.getLine() + "-" + temp.getPosition() + " ");
			
			p = p.getNext();
		}
		
		System.out.println("");
		pw.println("");
	}
	
}
