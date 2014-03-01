import java.util.Iterator;

public class Deque<Item> implements Iterable<Item> {
  private class Node   // structure for linked list
  {
    Item item;
    Node next;
    Node prior;
  }

  private Node head;        // head node in LL
  private Node tail;        // tail node in LL
  private int N;            // size of LL

  public Deque()  // construct an empty deque
  {
    head = new Node();
    tail = new Node();    // will repoint 'tail' later -- does this work???
    N = 0;                // size of LL is 0 (and head.item s.b. null)
  }

  public boolean isEmpty()               { return N == 0; }  // is the deque empty?
  public int size()                      { return N; }  // return the number of items on the deque

  public void addFirst(Item item)         // insert the item at the front
  {
    // be careful/complain
    if ( item == null ) { throw new NullPointerException("Can't add a null to deque"); }

    Node oldhead = head;                // save our place

    head = new Node();                   // new node for our data
    head.item = item;
    head.next = oldhead;                // keep linked list linked in order
    head.next.prior = head;             // my, isn't that pretty
    N++;                                  // keep track of list size
  }

  public void addLast(Item item)          // insert the item at the end
  {
    // be careful/complain
    if ( item == null ) { throw new NullPointerException("Can't add a null to deque"); }

    Node oldtail = tail;                  // we'll need to link up, save our place

    tail = new Node();                    // new node for our data
    tail.item = item;
    oldtail.next = tail;                  // keep linked list linked in order
    tail.prior = oldtail;                 // double link
    N++;                                  // keep track of list size
  }

  // public Item removeFirst()              { return ; }  // delete and return the item at the front
  // public Item removeLast()               { return ; }  // delete and return the item at the end
  public Iterator<Item> iterator()        // return an iterator over items in order from front to end
  { return new ForwardLLIterator(); }

  private class ForwardLLIterator implements Iterator<Item>
  {
    private Node current = head;         // start at front of linked list (deque.head)
    public boolean hasNext()  { return current.next != null; }

    public Item next()            // return content of current node & advance 'current' iter
    {
      if( !hasNext() ) { throw new java.util.NoSuchElementException(); }

      Item item = current.item;
      current = current.next;
      return item;
    }

    public void remove()      { throw new UnsupportedOperationException("remove() is scary"); }
  }

  public static void main(String[] args)   // unit testing
  {
    StdOut.printf("Initiate a blank deque...");

    Deque deck;
    deck = new Deque();

    StdOut.println("done.");
    StdOut.printf("Size of deque is %d. (", deck.size() );
    if(!deck.isEmpty()) { StdOut.printf("not "); }
    StdOut.println("empty.)"); 
  }

}
