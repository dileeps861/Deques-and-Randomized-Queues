import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {
    private Item[] d;
    private int first = 0;
    private int last = 0;
    private int size = 0;

    // construct an empty deque
    public Deque() {
        d = (Item[]) new Object[1];
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
        if (first <= 0 && !isEmpty()) {
            if (d.length < 12) resize(2 * d.length);
            else resize(d.length + d.length / 8);
        }
        if (!isEmpty() && (size) <= d.length / 8) {
            resize(d.length / 2);
        }
        //StdOut.println("size: " + size + ", len: " + d.length);
        if (!isEmpty()) d[--first] = item;
        else {
            d[first] = item;
            last = first;
        }
        size++;
    }

    // add the item to the back
    public void addLast(Item item) {
        nullAssert(item);
        if ((d.length - 1) <= last && !isEmpty()) {
            if (d.length < 12) resize(2 * d.length);
            else resize(d.length + d.length / 8);
        }
        if (!isEmpty() && (size) <= d.length / 8) {
            resize(d.length / 2);
        }
        //StdOut.println("size: " + size + ", len: " + d.length);
        if (d.length - 1 <= last) {
            if (d.length < 12) resize(2 * d.length);
            else resize(d.length + d.length / 8);
        }
        if (!isEmpty()) d[++last] = item;
        else {
            d[last] = item;
            first = last;
        }
        size++;
    }

    // Resize the array
    private void resize(int capacity) {
        Item[] temp = (Item[]) new Object[capacity];
        int tempFirst = (temp.length / 2) - (size / 2);
        for (int i = first; i < first + size; i++) {
            temp[tempFirst++] = d[i];
        }
        first = (temp.length / 2) - (size / 2);
        last = first + size - 1;
        d = temp;
    }

    // remove and return the item from the front
    public Item removeFirst() {
        emptyAssert();
        if ((size) <= d.length / 4) {
            resize(d.length / 2);
        }
        size--;
        Item item = d[first];
        d[first] = null;
        first++;
        if (isEmpty()) {
            first = 0;
            last = 0;
        }
        return item;
    }

    // remove and return the item from the back
    public Item removeLast() {
        emptyAssert();
        if ((size) <= d.length / 4) {
            resize(d.length / 2);
        }
        size--;
        Item item = d[last];
        d[last] = null;
        last--;
        if (isEmpty()) {
            first = 0;
            last = 0;
        }
        return item;
    }

    // return an iterator over items in order from front to back
    public Iterator<Item> iterator() {

        return new DequeIterator();
    }

    private class DequeIterator implements Iterator<Item> {
        private int i = first;

        public boolean hasNext() {
            return i <= last && !isEmpty();
        }

        public void remove() {
            removeAssert();
        }

        public Item next() {
            noItemAssert();
            return d[i++];
        }

        // check for operation on empty deque
        private void noItemAssert() {
            if (i > last) throw new NoSuchElementException();
        }

        // check for remove operation
        private void removeAssert() {
            throw new UnsupportedOperationException();
        }
    }

    // check for illegal argument
    private void nullAssert(Item item) {
        if (item == null) throw new IllegalArgumentException();
    }

    // check for operation on empty deque
    private void emptyAssert() {
        if (isEmpty()) throw new NoSuchElementException();
    }

    // unit testing (required)
    public static void main(String[] args) {
        // Deque<Integer> deque = new Deque<Integer>();
        // int o = 0;
        // while (o < 1024) {
        //     int r = StdRandom.uniform(0, 2);
        //     if (r == 0) deque.addFirst(o);
        //     else if (r == 1) deque.addLast(o);
        //     else if (r == 2 && !deque.isEmpty()) deque.removeFirst();
        //     else {
        //         if (!deque.isEmpty()) deque.removeLast();
        //     }
        //     o++;
        // }
        // deque.addFirst(1);
        // //deque.removeFirst();
        // Iterator<Integer> it = deque.iterator();
        // StdOut.println(it.hasNext());
        // StdOut.println(deque.size());

        Deque<Integer> deque1 = new Deque<Integer>();
        deque1.size();         // ==> 0
        deque1.addFirst(2);
        deque1.addLast(3);
    }

}
