//Xref.java
import java.io.*;
import java.util.Scanner;

/**
* This class is used to cross reference information about the words in the getty address.
* It also contains methods to display the getty address and insert words to binary tree.
* @author Chris Banci
* @version 5.0 - 5/5/16
*/
public class Xref {
	private PrintWriter pw;
	private Scanner gettyScan;
	private Scanner omittScan;
	private Hash hashing;
	private ObjectBinaryTree tree;
	
	/**
	* This is the default constructor for Xref.
	* @param pw
	* @param gettyScan
	* @param omittScan
	* @param h
	*/
	public Xref(PrintWriter pw, Scanner gettyScan, Scanner omittScan, Hash h) {
		this.pw = pw;
		this.gettyScan = gettyScan;
		this.omittScan = omittScan;
		
		this.hashing = h;
		this.tree = new ObjectBinaryTree();
	}
	
	/**
	* This method alphabetically sorts and displays the words in the binary tree by in-order traversal.
	*/
	public void printTree() {
		header();
		
		ObjectTreeNode p = tree.getRoot();
		tree.inTrav(p);
		
		System.out.println("----------------------------------------------------------------");
		System.out.println("[Total] # of words omitted: " + hashing.getOmittCount());
		System.out.println("----------------------------------------------------------------");
		
		pw.println("----------------------------------------------------------------");
		pw.println("[Total] # of words omitted: " + hashing.getOmittCount());
		pw.println("----------------------------------------------------------------");
		
	}
	
	/**
	* This method parses the words of the getty address file and is inserted into the binary tree.
	* Before the words are inserted into the binary tree, the words are checked if they exist in the hash table.
	* If it exists in the hash table, the word does not get inserted.
	* @throws FileNotFoundException
	*/
	public void parseGetty() throws FileNotFoundException {
		int line = 1;
		
		while(gettyScan.hasNextLine()){
			String buf = gettyScan.nextLine();
			String[] tokens = buf.replaceAll("[-,;.]", "").toLowerCase().split("\\s+");
			
			for (int i = 0; i < tokens.length; i++) {
				
				ObjectList list_temp = new ObjectList();
				
				if (hashing.search(tokens[i]) == false) {
					LinePosition line_temp = new LinePosition(line, i+1);
					list_temp.addLast(line_temp);
					
					Word word_temp = new Word(pw, tokens[i], 1, list_temp);
					tree.insertBSTDup(word_temp);
				}
			}
			
			line++;
		}
	}
	
	/**
	* This method parses the words that should be omitted from the binary tree.
	* @throws FileNotFoundException
	*/
	public void parseOmitt() throws FileNotFoundException {
		while(omittScan.hasNextLine()){
			String buf = omittScan.nextLine();
			String[] tokens = buf.split("\\s+");
			
			hashing.map(tokens[0]);
		}
	}
	
	/**
	* This method outputs the getty address with the line number to the side.
	* @throws FileNotFoundException
	*/
	public void printGetty() throws FileNotFoundException {
		Scanner gettyScan2 = new Scanner(new File("getty.txt"));
		
		int line = 1;
		System.out.println("----------------------------------------------------------------");
		pw.println("----------------------------------------------------------------");
		
		while (gettyScan2.hasNext()) {
			String word = gettyScan2.nextLine();
			System.out.printf("%-3d %-2s", line, word);
			System.out.println();
			
			pw.printf("%-3d %-2s", line, word);
			pw.println();
			
			line++;
		}
		System.out.println("----------------------------------------------------------------");
		pw.println("----------------------------------------------------------------");
		
		gettyScan2.close();
	}
	
	/**
	* This method returns the binary tree which is used for querying.
	* @return tree
	*/
	public ObjectBinaryTree getTree(){
		return tree;
	}
	
	/**
	* This method outputs the header of the cross reference information.
	*/
	public void header() {
		System.out.println("----------------------------------------------------------------");
		System.out.println(String.format("%-12s %-12s %-8s",
											"[Word]",
											"[Count]",
											"[Line-Position]"));
		System.out.println("----------------------------------------------------------------");
		
		
		pw.println("----------------------------------------------------------------");
		pw.println(String.format("%-12s %-12s %-8s",
									"[Word]",
									"[Count]",
									"[Line-Position]"));
		pw.println("----------------------------------------------------------------");
	}
	
}
