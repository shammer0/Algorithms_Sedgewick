import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {
    private Item[] rq;
    int count;

    // construct an empty randomized queue
    public RandomizedQueue() {
        rq = (Item[]) new Object[1];
        count = 0;
    }

    // is the randomized queue empty?
    public boolean isEmpty() {
        return count == 0;
    }

    // return the number of items on the randomized queue
    public int size() {
        return count;
    }

    // add the item
    public void enqueue(Item item) {
        if (item == null) {
            throw new IllegalArgumentException("enqueue exception");
        }
        if (count == rq.length) {
            doubleSize();
        }
        rq[count++] = item;
    }

    // remove and return a random item
    public Item dequeue() {
        if (isEmpty()) {
            throw new NoSuchElementException("dequeue exception");
        }
        int rand = StdRandom.uniformInt(0,count);
        Item i = rq[rand];
        if (rand != count - 1) {
            rq[rand] = rq[count - 1];
        }
        count--;
        if (count > 0 && count == rq.length / 4) {
            halfSize();
        }
        return i;
    }

    // return a random item (but do not remove it)
    public Item sample() {
        if (isEmpty()) {
            throw new NoSuchElementException("dequeue exception");
        }
        return rq[StdRandom.uniformInt(0,count)];
    }

    // return an independent iterator over items in random order
    public Iterator<Item> iterator(){ 
        return new ReverseArrayIterator(); 
    }

private class ReverseArrayIterator implements Iterator<Item> {
        private int i = count;
        private boolean[] arr = new boolean[i];

        public boolean hasNext() {
             return i > 0; 
        }

        public void remove() {
            throw new UnsupportedOperationException("remove exception");
        }
        public Item next() {
            if (!hasNext()) {
                throw new NoSuchElementException("next exception");
            }
            int rand = StdRandom.uniformInt(0,count);
            while(arr[rand]) {
                rand = StdRandom.uniformInt(0,count);
            }
            arr[rand] = true;
            i--;
            return rq[rand];
        }
    }

    private void doubleSize() {
        Item[] tempRq = (Item[]) new Object[rq.length * 2];
        for(int i = 0; i < count; i++) {
            tempRq[i] = rq[i];
        }
        rq = tempRq;
    }

    private void halfSize() {
        Item[] tempRq = (Item[]) new Object[rq.length / 2];
        for(int i = 0; i < count; i++) {
            tempRq[i] = rq[i];
        }
        rq = tempRq;
    }

    // unit testing (required)
    public static void main(String[] args) {
        RandomizedQueue<Integer> rndQ = new RandomizedQueue<>();
        StdOut.println(rndQ.isEmpty());
        StdOut.println(rndQ.size());
        rndQ.enqueue(6);
        StdOut.println(rndQ.isEmpty());
        StdOut.println(rndQ.size());
        StdOut.println(rndQ.sample());
        StdOut.println(rndQ.isEmpty());
        StdOut.println(rndQ.size());
        StdOut.println(rndQ.dequeue());
        StdOut.println(rndQ.isEmpty());
        StdOut.println(rndQ.size());
        StdOut.println("Enqueue\n");
        for(int i = 0; i < 10; i++) {
            rndQ.enqueue(i);
        }
        for(Integer i : rndQ) {
            StdOut.println(i);
        }
        StdOut.println("Dequeue\n");
        for(int i = 0; i < 10; i++) {
            rndQ.dequeue();
        }
        for(Integer i : rndQ) {
            StdOut.println(i);
        }
    }
}
