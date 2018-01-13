//	ObjectStackInterface.java

/**
 * This is an implementation of an object stack interface for ObjectStack class.
 * @author Chris Banci
 * @version 2.0 - 2/25/16
 */
public interface ObjectStackInterface {
		public boolean isEmpty();
		public void clear();
		public void push(Object o);
		public Object pop();
		public Object top();
}