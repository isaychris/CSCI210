// ObjectTreeNodeInterface.java

/**
* This is an interface which holds the signatures for ObjectTreeNode class.
* @author Chris Banci
* @version 5.0 - 5/5/16
*/
public interface ObjectTreeNodeInterface {
	public void setInfo(Object o);
	public Object getInfo();
	public void setLeft(ObjectTreeNode p);
	public ObjectTreeNode getLeft();
	public void setRight(ObjectTreeNode p);
	public ObjectTreeNode getRight();
}
