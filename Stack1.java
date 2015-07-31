
public class Stack1 implements Stack{
    private N start = null;

    // constructor

    public Stack1() {}
    
    // selectors

    public void push(Object o) {
        start = new N(o, start);
    }

    public Object pop() {
        if (start == null)
          throw new RuntimeException("Tried to pop an empty stack");
        else {
          Object data = start.getData();
          start = start.getNext();
          return data; 
        }
    }

    public Object top() {
        if (start == null)
          return null;
        else return start.getData();
    }

    public boolean isEmpty() {
        if (start == null)
          return true;
        else return false;
    }

}
