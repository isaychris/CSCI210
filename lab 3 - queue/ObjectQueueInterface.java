// ObjectQueueInterface.java

/**
 * This is an implementation of an object queue interface, which holds the objectQueue signatures.
 * @author Chris Banci
 * @version 4.0 - 3/18/2016
 */
public interface ObjectQueueInterface {
	public boolean isEmpty();
	public boolean isFull();
	public void clear();
	public void insert(Object o);
	public Object remove();
	public Object query();
}
