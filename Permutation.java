import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;

public class Permutation {
    public static void main(String[] args) {
        int k = Integer.parseInt(args[0]);
        RandomizedQueue<String> s = new RandomizedQueue<>();
        int n = 0;
        while (!StdIn.isEmpty()) {
            n++;
            if (k <= 0) StdIn.readString();
            else if (n <= k) {
                s.enqueue(StdIn.readString());
            }
            else {
                if (StdRandom.uniform(n) < k) {
                    s.dequeue();
                    s.enqueue(StdIn.readString());
                }
                else {
                    StdIn.readString();
                }

            }
        }
        int c = 0;
        Iterator<String> iterator = s.iterator();
        while (iterator.hasNext() && c < k) {
            StdOut.println(iterator.next());
            c++;
        }
    }
}
