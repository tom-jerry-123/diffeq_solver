package mathParser;
/*
 * Node class
 */
public class BTNode {
	private Object data;
    private BTNode right, left;
 
    public BTNode ()
    {
       this (null, null, null);
    }
 
 
    public BTNode (Object data, BTNode left, BTNode right)
    {
       this.data = data;
       this.right = right;
       this.left = left;
    }
 
 
    public BTNode (Object data)
    {
       this.data = data;
       this.right = null;
       this.left = null;
    }
 
 
    public void setData (Object data)
    {
       this.data = data;
    }
 
 
    public void setRight (BTNode right)
    {
       this.right = right;
    }
 
 
    public void setLeft (BTNode left)
    {
       this.left = left;
    }
 
 
    public Object getData ()
    {
       return data;
    }
 
 
    public BTNode getRight ()
    {
       return right;
    }
 
 
    public BTNode getLeft ()
    {
       return left;
    }
}
