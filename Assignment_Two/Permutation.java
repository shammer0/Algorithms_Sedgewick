
import edu.princeton.cs.algs4.StdIn;

public class Permutation {
    public static void main(String[] args) {
        RandomizedQueue<Object> test = new RandomizedQueue<>();
        while(!StdIn.isEmpty()) {
            test.enqueue(StdIn.readString());
        }
        int input = Integer.parseInt(args[0]);
        for(int i = 0; i < input; i++) {
            System.out.println(test.dequeue());
        }
    }
 }