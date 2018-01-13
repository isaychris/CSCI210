// TreeComparable.java

/**
* This is an interface which holds signatures for comparing word objects in a binary tree.
* @author Chris Banci
* @version 5.0 - 5/5/16
*/
public interface TreeComparable {
	public int compareTo(Object o);
	public void operate(Object o);
	public void visit();
}
