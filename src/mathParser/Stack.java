package mathParser;
/*
 * Stack Implementation
 */
public class Stack
   {
      private LLNode top;
   
      public Stack () {
         top = null;
      }
   
      public boolean isEmpty () {
         return top == null;
      }
   
      public Object pop () {
         if (top != null) {
            Object data = top.getData ();
            top = top.getNext ();
            return data;
         }
         else
            return null;
      }
   
      public boolean push (Object item) {
         LLNode temp = new LLNode(item, top);
         top = temp;
         return true;
      }
   
      public boolean isFull () {
         return false;
      }
      
      public Object peek() 
      {
         if (top == null)
            throw new RuntimeException("peek: empty stack");
         else {
            Object data = top.getData ();
            return data;
         }
      }
   }
