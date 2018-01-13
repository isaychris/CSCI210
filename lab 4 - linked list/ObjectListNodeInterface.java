// OjbectListNodeInterface;

/**
 * This is an interface which holds the signatures for ObjectListNode class.
 * @author Chris Banci
 * @version 2.0 - 4/15/16
 */
public interface ObjectListNodeInterface {
	public void setInfo(Object o);
    public Object getInfo();
    public void setNext(ObjectListNode p);
    public ObjectListNode getNext();
}
