import java.util.Iterator;
import java.util.NoSuchElementException;
import edu.princeton.cs.algs4.StdOut;

public class Deque<Item> implements Iterable<Item> {
    private Node head;
    private Node tail;
    private int count;

    private class Node {
        public Item item;
        public Node front;
        public Node back;
    }

    // construct an empty deque
    public Deque() {
        count = 0;
    }

    // is the deque empty?
    public boolean isEmpty() {
        return size() == 0;
    }

    // return the number of items on the deque
    public int size() {
        return count;
    }

    // add the item to the front
    public void addFirst(Item item) {
        if (item == null) {
            throw new IllegalArgumentException("addFirst() Exception");
        }
        if (head == null) {
            head = new Node();
            head.item = item;
            tail = head;
        }
        else {
            Node oldHead = head;
            head = new Node();
            head.item = item;
            head.back = oldHead;
            oldHead.front = head;
        }
        count++;
    }

    // add the item to the back
    public void addLast(Item item) {
        if (item == null) {
            throw new IllegalArgumentException("addLast() Exception");
        }
        if (tail == null) {
            tail = new Node();
            tail.item = item;
            head = tail;
        }
        else {
            Node oldTail = tail;
            tail = new Node();
            tail.item = item;
            tail.front = oldTail;
            oldTail.back = tail;
        }
        count++;
    }

    // remove and return the item from the front
    public Item removeFirst() {
        if(isEmpty()) {
            throw new NoSuchElementException("removeFirst() exception");
        }
        Item item = head.item;
        head = head.back;
        // Special when 1 left
        if (head != null) {
            head.front = null;
        } else {
            tail = null;
        }
        count--;
        return item;
    }

    // remove and return the item from the back
    public Item removeLast() {
        if(isEmpty()) {
            throw new NoSuchElementException("removeFirst() exception");
        }
        Item item = tail.item;
        tail = tail.front;
        // Special when 1 left
        if (tail != null) {
            tail.back = null;
        } else {
            head = null;
        }
        count--;
        return item;
    }

    private class DequeIterator implements Iterator<Item> {
        private Node current = head;

        public boolean hasNext() {
            return current != null;
        }
        public void remove() {
            throw new UnsupportedOperationException("Iterator Exception");
        }
        public Item next() {
            if (!hasNext()) {
                throw new NoSuchElementException("Iterator Exception");
            }
            Item item = current.item;
            current = current.back;
            return item;
        }

    }

    // return an iterator over items in order from front to back
    public Iterator<Item> iterator() {
        return new DequeIterator();
    }

    // unit testing (required)
    public static void main(String[] args) {
        Deque deq = new Deque<>();
        for(int i = 0; i < 100; i++) {
            deq.addLast(i);
            deq.removeLast();
            StdOut.println(deq.isEmpty());
        }
        deq.addLast(1);
        deq.addLast(2);
        deq.addFirst(3);
        deq.removeLast();
        deq.removeFirst();
        deq.addFirst(4);
        Iterator itr = deq.iterator();
        while(itr.hasNext()) {
            StdOut.println(itr.next());
        }
    }

}