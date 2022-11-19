import edu.princeton.cs.algs4.StdRandom;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {
    private Item[] rq;
    private int count = 0;

    // construct an empty randomized queue
    public RandomizedQueue() {
        rq = (Item[]) new Object[1];
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
        checkNull(item);
        count += 1;
        if (rq.length < count) {
            doubleSize();
        }
        rq[count - 1] = item;
    }

    // remove and return a random item
    public Item dequeue() {
        checkEmpty();
        int randomIndex = StdRandom.uniformInt(count);
        Item temp = rq[randomIndex];
        rq[randomIndex] = rq[count - 1];
        count -= 1;
        if (count == rq.length / 4) {
            halfSize();
        }
        return temp;
    }

    // return a random item (but do not remove it)
    public Item sample() {
        checkEmpty();
        int randomIndex = StdRandom.uniformInt(count - 1);
        return rq[randomIndex];
    }

    // return an independent iterator over items in random order
    public Iterator<Item> iterator() {
        return new ReverseArrayIterator();
    }

    private class ReverseArrayIterator implements Iterator<Item> {
        private int i = count;

        public boolean hasNext() {
            return i > 0;
        }

        public Item next() {
            if (i - 1 < 0) {
                throw new NoSuchElementException("there are no more elements");
            }
            return rq[--i];
        }

        public void remove() {
            throw new UnsupportedOperationException("remove not enabled");
        }

    }

    private void doubleSize() {
        int currentLength = rq.length;
        Item[] tempRq = (Item[]) new Object[currentLength * 2];
        for (int i = 0; i < currentLength; i++) {
            tempRq[i] = rq[i];
        }
        rq = tempRq;
    }

    private void halfSize() {
        int currentLength = rq.length;
        Item[] tempRq = (Item[]) new Object[(int) (currentLength / 2)];
        for (int i = 0; i < count; i++) {
            tempRq[i] = rq[i];
        }
        rq = tempRq;
    }

    private void checkNull(Item item) {
        if (item == null) {
            throw new IllegalArgumentException("null item enqueues are "
                                                       + "disallowed");
        }
    }

    private void checkEmpty() {
        if (count == 0) {
            throw new NoSuchElementException("randomized queue is empty, "
                                                     + "no elements can be "
                                                     + "removed");
        }
    }

    // unit testing (required)
    public static void main(String[] args) {
        RandomizedQueue<Object> test = new RandomizedQueue<>();
        if (test.isEmpty()) {
            System.out.println("PASS isEmpty test");
        }
        test.enqueue(1);
        test.enqueue(2);
        test.enqueue(3);
        test.enqueue(4);
        if (test.size() == 4) {
            System.out.println("PASS size test");
        }
        int randomInt = (int) test.dequeue();
        System.out.println(randomInt + " dequeued, is it random?");
        randomInt = (int) test.dequeue();
        System.out.println(randomInt + " dequeued, is it random?");
        randomInt = (int) test.dequeue();
        System.out.println(randomInt + " dequeued, is it random?");
        randomInt = (int) test.dequeue();
        System.out.println(randomInt + " dequeued, is it random?");
        if (test.size() == 3) {
            System.out.println("PASS size test");
        }
    }
}
