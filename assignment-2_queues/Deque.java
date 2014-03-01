import java.util.Iterator;

public class Deque<Item> implements Iterable<Item> {
   public Deque()                         {}  // construct an empty deque
   public boolean isEmpty()               { return true; }  // is the deque empty?
   public int size()                      { return 7; }  // return the number of items on the deque
   public void addFirst(Item item)        {}  // insert the item at the front
   public void addLast(Item item)         {}  // insert the item at the end
   // public Item removeFirst()              { return ; }  // delete and return the item at the front
   // public Item removeLast()               { return ; }  // delete and return the item at the end
   // public Iterator<Item> iterator()       { return; }  // return an iterator over items in order from front to end
   public static void main(String[] args) {}  // unit testing
}
