import java.util.Iterator;

public class Deque<Item> implements Iterable<Item> {
  private class Node   // structure for linked list
  {
    private Item item;
    private Node next;
    private Node prior;
  }

  private Node head = null;        // head node in LL
  private Node tail = null;        // tail node in LL
  private int N = 0;               // size of LL

  public Deque()  // construct an empty deque (but do we even need to?)
  {
    // head = new Node();
    // tail = new Node();    // will repoint 'tail' later -- does this work???
    // N = 0;                // size of LL is 0 (and head.item s.b. null)
  }

  public boolean isEmpty()  { return N == 0; }  // is the deque empty?
  public int size()  { return N; }  // return the number of items on the deque

  public void addFirst(Item item)         // insert the item at the front
  {
    // be careful/complain
    if (item == null) 
      { throw new NullPointerException("Can't add a null to deque"); 
    }

    // if (head != null) 
    // { 
    //   StdOut.printf("Current head is %s containing '%s'. Adding '%s'...",
    //     head.toString(), head.item, item); 
    // }
    // else { StdOut.printf("Current head is null; adding '%s'...", item); }

    Node oldhead = head;                // save our place

    head = new Node();                   // new node for our data
    head.item = item;
    head.next = oldhead;                    // keep linked list linked in order
    if (head.next != null) { 
      // StdOut.printf("(Linking %s.prior to head(%s)",
      //   head.next.toString(), head.toString());
      head.next.prior = head; 
    }
    if (oldhead == null) {
      tail = head; 
      // StdOut.printf("(Linking 'tail' to %s) ", tail.toString());
    }   // if we just started from scratch, link up 'last'

    N++;                                  // keep track of list size

    // StdOut.printf("\nHead is %s (%s) and Tail is %s (%s) \n\n",
    //   head.toString(), head.item, tail.toString(), tail.item);
  }

  public void addLast(Item item)          // insert the item at the end
  {
    // be careful/complain
    if (item == null) { 
      throw new NullPointerException("Can't add a null to deque"); 
    }

    // if (tail != null)
    // { 
    //   StdOut.printf("Current tail is %s containing '%s'. Adding '%s'...",
    //     tail.toString(), tail.item, item); 
    // }
    // else { StdOut.printf("Current tail is null; adding '%s'...", item); }

    Node oldtail = tail;               // we'll need to link up, save our place

    tail = new Node();                    // new node for our data
    tail.item = item;
    tail.prior = oldtail;                 // double link

    if (tail.prior != null) { 
      // StdOut.printf("(Linking %s.next to tail(%s)", 
      //   tail.prior.toString(), tail.toString());
      tail.prior.next = tail; 
    }
    if (oldtail == null) 
    { 
      head = tail; 
      // StdOut.printf("(Linking 'head' to %s) ", head.toString()); 
    }   // if we just started from scratch, link up 'head'

    N++;                                  // keep track of list size

    /* StdOut.printf("\nHead is %s (%s) and Tail is %s (%s) \n\n",
         head.toString(), head.item, tail.toString(), tail.item); */
    // StdOut.printf("\nHead is ");
    // if (head != null) 
    //   { StdOut.printf("%s (%s)", head.toString(), head.item); }
    // else
    //   { StdOut.printf("(null)"); }
    // StdOut.printf(" and Tail is %s (%s)", tail.toString(), tail.item);

  }

  public Item removeFirst()               // delete and return the item at the front
  {
    // be careful/complain if we try to remove off empty queue
    if (N == 0 || head == null) 
      { throw new java.util.NoSuchElementException("Queue empty"); }

    // StdOut.printf("dequeueing head@%s (%s)...", head.toString(), head.item);

    Item item = head.item;
    head.item = null;                                  // really, don't loiter
    if (head.next != null) head.next.prior = null;     // don't loiter
    head = head.next;

    N--;                        // shrink the list

    // catch edge case of emptying out list (and don't loiter either):
    if (isEmpty()) tail = null;

    // StdOut.printf("state: N=%d, ", N);
    // if (head != null) StdOut.printf("head->%s", head.toString());
    // if (tail != null) StdOut.printf("tail->%s", tail.toString());
    // StdOut.println();
    return item;
  }


  public Item removeLast()       // delete and return the item at the end
  {
    // be careful/complain if we try to remove off empty dequeue
    if (N == 0 || tail == null) 
      { throw new java.util.NoSuchElementException("Stack empty"); }

    // StdOut.printf("popping tail@%s (%s)...", tail.toString(), tail.item);

    Item item = tail.item;
    tail.item = null;                                   // really, don't loiter
    if (tail.prior != null) tail.prior.next = null;     // don't loiter
    tail = tail.prior;

    N--;                        // shrink the list
    // catch edge case of emptying out list (and don't loiter either)
    if (isEmpty()) head = null; 

    // StdOut.printf("state: N=%d, ", N);
    // if (head != null) StdOut.printf("head->%s", head.toString());
    // if (tail != null) StdOut.printf("tail->%s", tail.toString());
    // StdOut.println();
    return item;
  }

  // return an iterator over items in order from front to end
  public Iterator<Item> iterator() 
  { return new ForwardLLIterator(); }

  private class ForwardLLIterator implements Iterator<Item>
  {
    private Node current = head; // start at front of linked list (deque.head)
    public boolean hasNext()  { return current != null; }

    public Item next() // return content of current node & advance 'current' iter
    {
      if (!hasNext()) { throw new java.util.NoSuchElementException(); }

      Item item = current.item;
      current = current.next;
      return item;
    }

    public void remove() 
      { throw new UnsupportedOperationException("remove() is scary"); }
  }

  public static void main(String[] args)   // unit testing
  {
    String test1 = "first test";
    String test2 = "second test";
    String test3 = "third test";
    String test4 = "fourth test";
    String test5 = "fifth test";
    String test6 = "sixth test";

    StdOut.printf("Initiate a blank deque...");

    Deque<String> deck = new Deque<String>();

    StdOut.println("done.");
    StdOut.printf("Size of deque is %d. (", deck.size());
    if (!deck.isEmpty()) { StdOut.printf("not "); }
    StdOut.println("empty.)\n");

    deck.addLast(test4);
    deck.addLast(test5);
    deck.addLast(test6);

    StdOut.printf("Size of deque is %d. (", deck.size());
    if (!deck.isEmpty()) { StdOut.printf("not "); }
    StdOut.println("empty.)\n");

    deck.addFirst(test1);
    deck.addFirst(test2);
    deck.addFirst(test3);

    StdOut.printf("Size of deque is %d. (", deck.size());
    if (!deck.isEmpty()) { StdOut.printf("not "); }
    StdOut.println("empty.)\n");

    StdOut.println("Contents of deque:");
    for (String s : deck) {
      StdOut.println(s);
    }

    StdOut.println("Now remove all elements alternately...");

    StdOut.printf("dequeued %s \n", deck.removeFirst());
    StdOut.printf("dequeued %s \n", deck.removeFirst());

    StdOut.printf("Size of deque is %d. (", deck.size());
    if (!deck.isEmpty()) { StdOut.printf("not "); }
    StdOut.println("empty.)\n");

    StdOut.println("Contents of deque:");
    for (String s : deck) {
      StdOut.println(s);
    }

    StdOut.printf("popped %s \n", deck.removeLast());
    StdOut.printf("popped %s \n", deck.removeLast());

    StdOut.printf("popped %s \n", deck.removeLast());
    StdOut.printf("dequeued %s \n", deck.removeFirst());

    StdOut.printf("Size of deque is %d. (", deck.size());
    if (!deck.isEmpty()) { StdOut.printf("not "); }
    StdOut.println("empty.)\n");

    StdOut.println("Contents of deque:");
    for (String s : deck) {
      StdOut.println(s);
    }

    /******** DO IT AGAIN! **************/

    deck.addLast(test4);
    deck.addLast(test5);
    deck.addLast(test6);

    StdOut.printf("Size of deque is %d. (", deck.size());
    if (!deck.isEmpty()) { StdOut.printf("not "); }
    StdOut.println("empty.)\n");

    deck.addFirst(test1);
    deck.addFirst(test2);
    deck.addFirst(test3);

    StdOut.printf("Size of deque is %d. (", deck.size());
    if (!deck.isEmpty()) { StdOut.printf("not "); }
    StdOut.println("empty.)\n");

    StdOut.println("Contents of deque:");
    for (String s : deck) {
      StdOut.println(s);
    }

    StdOut.println("Now remove all elements alternately...");

    StdOut.printf("dequeued %s \n", deck.removeFirst());
    StdOut.printf("dequeued %s \n", deck.removeFirst());

    StdOut.printf("Size of deque is %d. (", deck.size());
    if (!deck.isEmpty()) { StdOut.printf("not "); }
    StdOut.println("empty.)\n");

    StdOut.println("Contents of deque:");
    for (String s : deck) {
      StdOut.println(s);
    }

    StdOut.printf("popped %s \n", deck.removeLast());
    StdOut.printf("popped %s \n", deck.removeLast());

    StdOut.printf("popped %s \n", deck.removeLast());
    StdOut.printf("dequeued %s \n", deck.removeFirst());

    StdOut.printf("Size of deque is %d. (", deck.size());
    if (!deck.isEmpty()) { StdOut.printf("not "); }
    StdOut.println("empty.)\n");

    StdOut.println("Contents of deque:");
    for (String s : deck) {
      StdOut.println(s);
    }

  }

}
