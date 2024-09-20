package mathParser;
/*
 * LinkedList implementation
 */

public class LinkedList
   {
      
	LLNode header;
	int size;
   
	LinkedList () {
     header = null;
     size = 0;
    }
   
   
      private LLNode traverse (int i)
      {
         LLNode n = header;
      
         if (i < 0)
            return null;
      
         for (int j = 0 ; j < i ; j++)
         {
            if (n == null)
               return null;
            n = n.getNext ();
         }
         return n;
      }
   
   
      public boolean insert (int i, Object item)
      {
         LLNode prev;
         LLNode newLLNode = new LLNode (item);
      
         if (i == 0)
         {
            if (!isEmpty ())
               newLLNode.setNext (header);
            header = newLLNode;
         }
         else
         {
            prev = traverse (i - 1);
            if (prev == null)
               return false;
            newLLNode.setNext (prev.getNext ());
            prev.setNext (newLLNode);
         }
         size++;
         return true;
      }
   
   
      public int size ()
      {
         return size;
      }
   
   
      public boolean isEmpty ()
      {
         return header == null;
      }
   
   
      public Object lookUp (int i)
      {
         LLNode n = traverse (i);
         if (n == null)
            return null;
         return n.getData ();
      }
   
   
      public boolean delete (int i)
      {
         LLNode prev;
      
         if (isEmpty ())
            return false;
      
         if (i == 0)
            header = header.getNext ();
         else
         {
            prev = traverse (i - 1);
            if (prev.getNext () == null)
               return false;
            prev.setNext (prev.getNext ().getNext ());
         }
         size--;
         return true;
      }
   
   
      public boolean replace (int i, Object item)
      {
         LLNode n = traverse (i);
         if (n == null)
            return false;
         n.setData (item);
         return true;
      }
   
   
      public void displayReverse ()
      {
         printReverse (header);
      }
   
   
      private void printReverse (LLNode head)
      {
         if (head == null)
         {
         }
         else
         {
            printReverse (head.getNext ());
            System.out.println ((String) head.getData ());
         }
      }
   }
