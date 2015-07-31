// Queue implementation using a linked list of nodes (see N.java)
// Posted previously, but used for simulation

public class Q1 implements Q {
	private int size;
    private N front;
    private N rear;

    // constructor

    public Q1() {
    	front=null;
    	rear=null;
    	size=0;
    }

    // selectors

    public void add(Object o) {

        if (size == 0) {
          front = new N(o, null);
          rear = front;
        }
        else {
          rear.setNext(new N(o, null));
          rear = rear.getNext();
        }
        size++;
    }

    public Object remove() {

        Object answer;

        if (size == 0)
          return null;
        
        answer = front.getData();
        front = front.getNext();
        size--;
        if (size == 0)
          rear = null;
        return answer;
    }
    
    public Object getfront()
    {   if(front!=null)
    	return front.getData();
       else
    	   return null;
    }
    
    public N getfrontN()
    {
       return front;
    }
    public int length() {
        return size;
    }

    
}  // Q1 class
