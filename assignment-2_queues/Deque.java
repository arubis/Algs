import java.util.Iterator;

public class Deque<Item> implements Iterable<Item> {
  private class Node   // structure for linked list
  {
    Item item;
    Node next;
  }

  public Deque()  // construct an empty deque
  {
    Node first = new Node();
    Node last = first;
  }

  public boolean isEmpty()               { return true; }  // is the deque empty?
  public int size()                      { return 7; }  // return the number of items on the deque
  public void addFirst(Item item)        {}  // insert the item at the front
  public void addLast(Item item)         {}  // insert the item at the end
  // public Item removeFirst()              { return ; }  // delete and return the item at the front
  // public Item removeLast()               { return ; }  // delete and return the item at the end
  public Iterator<Item> iterator()        // return an iterator over items in order from front to end
  { return new ForwardLLIterator(); }


  private class ForwardLLIterator implements Iterator<Item>
  {
    private int i = 0;            // start at front of array (index 0)

    public boolean hasNext()  { return i < N; }
    public Item next()        { return ; }

    public void remove()      { throw new UnsupportedOperationException("remove() is scary"); }
  }

  public static void main(String[] args) {}  // unit testing
}
