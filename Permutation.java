
import edu.princeton.cs.algs4.StdIn;

public class Permutation {
    public static void main(String[] args) {
        RandomizedQueue<Object> test = new RandomizedQueue<>();
        while(!StdIn.readString().isEmpty()) {
            test.enqueue(StdIn.readString());
        }

        while(!test.isEmpty()) {
            System.out.println(test.dequeue());
        }
    }
 }