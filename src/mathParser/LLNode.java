package mathParser;
/*
 * Node for linked list. Can be used for linked list; linkedlist implementations of queues and stacks
 */
public class LLNode {
	private Object data;
    private LLNode next;
 
    public LLNode () {
       this (null, null);
    }
 
    public LLNode (Object data) {
    	this.data = data;
    	this.next = null;
    }
 
    public LLNode (Object data, LLNode next) {
       this.data = data;
       this.next = next;
    }
 
 
    public void setData (Object data) {
       this.data = data;
    }
 
    public void setNext (LLNode next) {
       this.next = next;
    }
 
    public Object getData () {
       return data;
    }
 
    public LLNode getNext () {
       return next;
    }
}
