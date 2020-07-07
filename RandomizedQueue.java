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
        // StdOut.println("enq==> first: " + first + ", size: " + size + ", len="
        //                        + d.length);
        size++;
    }

    // remove and return a random item
    public Item dequeue() {
        emptyAssert();
        if (size <= (d.length / 4)) {
            resize(d.length / 2);
        }
        int idx = random(first, d.length);
        // StdOut.println("deque==> first: " + first + ", size: " + size + ", idx: " + idx + " len="
        //                        + d.length);
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
        // StdOut.println("sample==> first: " + first + ", size: " + size + ", idx: " + idx + " len="
        //                        + d.length);
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
        // StdOut.println("resize==> first: " + first + ", size: " + size + ", temp: " + temp.length);
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
            flag = new int[d.length - first];
            int pointer = 0;
            // StdOut.println(
            //         "iterator const==> first: " + first + ", size: " + size + ", flag: "
            //                 + flag.length);
            for (int j = first; j < d.length; j++) {
                flag[pointer++] = j;
            }
            i = 0;
            StdRandom.shuffle(flag);
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
            //StdOut.println("Dequed=" + rq.dequeue() + rq.size());
            ct++;
        }
        StdOut.println(
                "check==> size" + rq.size() + ", first:" + rq.first);
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

        // RandomizedQueue<Integer> rq = new RandomizedQueue<Integer>();
        // StdOut.println("Size= " + rq.size());  //      ==> 0
        // StdOut.println("Size= " + rq.size());  //        ==> 0
        // StdOut.println("Size= " + rq.size());  //        ==> 0
        // StdOut.println("Size= " + rq.size());  //       ==> 0
        // rq.enqueue(69);
        // rq.enqueue(34);
        // //rq.enqueue(64);
        // rq.enqueue(35);
        // StdOut.println("Value= " + rq.sample());

        // RandomizedQueue<Integer> rq = new RandomizedQueue<Integer>();
        // rq.enqueue(373);
        //
        // StdOut.println("sam= " + rq.sample());
        // StdOut.println("deq = " + rq.dequeue());    // ==>373
        //
        // StdOut.println("emp = " + rq.isEmpty());    //==>true
        // StdOut.println("s = " + rq.size());    //==>0
        // StdOut.println("s = " + rq.size());    //==>0
        // StdOut.println("emp = " + rq.isEmpty());    //==>true
        // rq.enqueue(145);
        // rq.enqueue(411);
        // StdOut.println("size= " + rq.size());
        // StdOut.println("sam= " + rq.sample());
        // StdOut.println("Value= " + rq.dequeue());  // ==>145
        // StdOut.println("Value= " + rq.dequeue());  // ==>null
    }

}
