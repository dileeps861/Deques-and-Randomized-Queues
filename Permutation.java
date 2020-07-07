import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;

public class Permutation {
    
    public static void main(String[] args) {
        //Permutation with improved memory. This algorithm usage only K size of RandomizedQueue.
        
        int k = Integer.parseInt(args[0]);
        RandomizedQueue<String> s = new RandomizedQueue<>();
        int n = 0;
        while (!StdIn.isEmpty()) {
            n++;    // Need to increase n before taking random to improve probablity
            if (k <= 0) StdIn.readString(); //  In case of k<0 do nothing but read string from StdIn
            else if (n <= k) {
                s.enqueue(StdIn.readString());  //  In case of count of read string from StdIn are less than k, no need to opt out the string
            }
            else {
                if (StdRandom.uniform(n) < k) {  // Take random uniformly in [0, n).
                    s.dequeue();
                    s.enqueue(StdIn.readString());
                }
                else {
                    StdIn.readString();     //  Skip the String if the RandomizedQueue is already full;
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
