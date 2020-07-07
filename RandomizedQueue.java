import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {
    private Item[] d;
    private int size;
    private int first;

    // construct an empty randomized queue
    public RandomizedQueue() {
        d = (Item[]) new Object[1];
        first = d.length;
    }

    // is the randomized queue empty?
    public boolean isEmpty() {
        return size == 0;
    }

    // return the number of items on the randomized queue
    public int size() {
        return size;
    }

    // add the item
    public void enqueue(Item item) {
        nullAssert(item);
        if (size >= (d.length)) {
            resize(2 * (d.length));
        }
        d[--first] = item;
        size++;
    }

    // remove and return a random item
    public Item dequeue() {
        emptyAssert();
        if (size <= (d.length / 4)) {
            resize(d.length / 2);
        }
        int idx = random(first, d.length);
        Item item = d[idx];
        d[idx] = d[first];
        d[first++] = null;
        size--;
        return item;
    }

    // return a random item (but do not remove it)
    public Item sample() {
        emptyAssert();
        int idx = random(first, d.length);
        return d[idx];
    }

    private int random(int start, int end) {
        return StdRandom.uniform(start, end);
    }

    // Resize the array
    private void resize(int capacity) {
        Item[] temp = (Item[]) new Object[capacity];
        int c = temp.length;
        for (int i = first; i < d.length; i++) {
            temp[--c] = d[i];
        }
        first = c;
        d = temp;
    }

    // return an independent iterator over items in random order
    public Iterator<Item> iterator() {

        return new RandomQueueIterator();

    }

    private class RandomQueueIterator implements Iterator<Item> {
        private int i;
        int[] flag;

        private RandomQueueIterator() {
            flag = new int[d.length - first];  // Used to take indexes from start till end and put them in the flag;
            int pointer = 0;
            for (int j = first; j < d.length; j++) {
                flag[pointer++] = j;
            }
            i = 0;
            StdRandom.shuffle(flag); // Shuffle the array to get random indexes.
        }

        public boolean hasNext() {
            return i < size();
        }

        public void remove() {
            removeAssert();
        }

        public Item next() {
            noItemAssert();
            return d[flag[i++]];
        }

        // check for operation on empty deque
        private void noItemAssert() {
            if (i >= size()) throw new NoSuchElementException();
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
        RandomizedQueue<String> rq = new RandomizedQueue<>();
        int ct = 0;
        while (ct < 10) {
            rq.enqueue(ct + "DS");
            ct++;
        }
        StdOut.println( "check==> size" + rq.size() + ", first:" + rq.first);
        Iterator<String> iterator = rq.iterator();
        while (iterator.hasNext()) {
            StdOut.println(iterator.next());
        }
        ct = 0;
        while (ct < 50) {
            StdOut.println("Value= " + rq.sample() + ", ct=" + ct);
            ct++;
            //in.remove();
        }      
    }

}
