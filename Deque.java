import edu.princeton.cs.algs4.StdOut;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {

    private Node<Item> first;
    private Node<Item> last;
    private int size = 0;

    // construct an empty deque
    public Deque() {

    }

    // is the deque empty?
    public boolean isEmpty() {
        return size == 0;
    }

    // return the number of items on the deque
    public int size() {
        return size;
    }

    // add the item to the front
    public void addFirst(Item item) {
        nullAssert(item);
        Node<Item> n = new Node<>();
        n.data = item;
        if (isEmpty()) {
            first = n;
            last = n;
        }
        else {
            Node<Item> n1 = first;
            n1.prev = n;    //chain ref of new element to prev element 
            n.next = n1;    //chain ref to new element of prev element
            first = n;      //Make new element as first element
        }
        size++;
    }

    // add the item to the back
    public void addLast(Item item) {
        nullAssert(item);
        Node<Item> n = new Node<>();
        n.data = item;
        
        //In case of no elements , need to make first and last elements ref to same object
        if (isEmpty()) {
            last = n;
            first = n;
        }
        else {
            Node<Item> n1 = last;
            n.prev = n1;
            n1.next = n;
            last = n; 
        }
        size++;
    }

    // remove and return the item from the front
    public Item removeFirst() {
        emptyAssert();
        Item item = null;
        
        if (size == 1) {    
            //In case of removing the only element, need to make first and last elements ref to null
            item = first.data;
            first = null;
            last = null;
        }
        else {
            item = first.data;
            first = first.next;
            first.prev = null;
        }
        size--;
        return item;
    }

    // remove and return the item from the back
    public Item removeLast() {
        emptyAssert();
        Item item = null;
        if (size == 1) {
            //In case of removing the only element, need to make first and last elements ref to null
            item = last.data;
            last = null;
            first = null;
        }
        else {
            item = last.data;
            last = last.prev;
            last.next = null;
        }
        size--;
        return item;
    }

    // return an iterator over items in order from front to back
    public Iterator<Item> iterator() {

        return new DequeIterator();
    }
    
    // Iterator to iterate over elements from start to last
    private class DequeIterator implements Iterator<Item> {
        private Node<Item> i = first;

        public boolean hasNext() {
            return (i != null && i.data != null) && !isEmpty();
        }
        
        public void remove() {
            removeAssert();
        }
    
        public Item next() {
            noItemAssert();
            Item it = i.data;
            i = i.next;
            return it;
        }

        // check for operation on empty deque
        private void noItemAssert() {
            if (i == null || isEmpty() || (first == null && last == null))
                throw new NoSuchElementException();
        }

        // check for remove operation
        private void removeAssert() {
            throw new UnsupportedOperationException();
        }
    }

    private class Node<Item> {
        private Item data;
        private Node<Item> next;
        private Node<Item> prev;
    }

    // check for illegal argument
    private void nullAssert(Item item) {
        if (item == null) throw new IllegalArgumentException();
    }

    // check for operation on empty deque
    private void emptyAssert() {
        if (isEmpty() || (first == null && last == null))
            throw new NoSuchElementException();
    }

    // unit testing (required)
    public static void main(String[] args) {
        Deque<Integer> deque = new Deque<Integer>();
        int o = 0;
        while (o < 10) {
            deque.addLast(o);
            o++;
        }
        Iterator<Integer> iterator1 = deque.iterator();
        while (iterator1.hasNext()) {
            StdOut.println("Iterator= " + iterator1.next());
        }

        o = 0;
        while (o < 10) {
            StdOut.println("Remove= " + deque.removeFirst()
                                   + ", size= " + deque.size());
            o++;
        }
        deque.addFirst(1);
        
        Iterator<Integer> it = deque.iterator();
        StdOut.println(it.hasNext());
        StdOut.println(deque.size());

        Deque<Integer> deque1 = new Deque<Integer>();
        
        deque1.addFirst(1);
        StdOut.println("1--" + deque1.removeLast());    //==> 1
        deque1.addFirst(3);
        deque1.addLast(4);
        deque1.addLast(5);
        StdOut.println("1--" + deque1.removeFirst());   //==> 3
        StdOut.println("rem1--" + deque1.removeLast());
        StdOut.println(deque1.size());
       
        Iterator<Integer> iterator = deque1.iterator();
        iterator.next();
        while (iterator.hasNext()) {
            StdOut.println("--" + iterator.next());
        }
       
        Deque<Integer> deque2 = new Deque<Integer>();
        deque2.addFirst(1);
        StdOut.println("deq data:" + deque2.removeLast()); //==>1
        deque2.addFirst(3);
        deque2.addLast(4);
        deque2.addFirst(5);
        StdOut.println("deq data:" + deque2.removeFirst()); //==>5
        deque2.addLast(7);
        StdOut.println("deq data:" + deque2.removeLast()); //==>7

        Iterator<Integer> iterator2 = deque2.iterator();
        while (iterator2.hasNext()) {
            StdOut.println("Data:" + iterator2.next());
        }

    }

}
