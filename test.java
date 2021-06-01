import java.util.Iterator;

public class test<Item> implements Iterable<Item> {

    private class Node {
        Item item;
        Node next;
    }

    Node last;
    int size;

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void enqueue(Item item) {
        Node node = new Node();
        node.item = item;

        if (isEmpty()) {
            last = node;
            last.next = last;
        }
        else {
            node.next = last.next;
            last.next = node;
            last = node;
        }
        size++;
    }

    public Item dequeue() {
        if (isEmpty()) {
            return null;
        }
        Item item;

        if (size == 1) {
            item = last.item;
            last = null;
        }
        else {
            item = last.next.item;
            last.next = last.next.next;
        }
        size--;

        return item;
    }

    public Iterator<Item> iterator() {
        return new QueueIterator();
    }

    private class QueueIterator implements Iterator<Item> {
        private Node current;
        int count = 0;

        public QueueIterator() {
            if (last != null && size > 1) {
                current = last.next;
            }
            else {
                current = last;
            }
        }

        public Item next() {
            count++;

            Item item = current.item;
            current = current.next;
            return item;
        }

        public boolean hasNext() {
            return count < size;
        }
    }

    public static void main(String[] args) {
        test<Integer> queue = new test<Integer>();
        queue.enqueue(1);
        queue.enqueue(2);
        queue.enqueue(3);
        queue.enqueue(4);

        for (int i = 0; i < 4; i++) {
            StdOut.println(queue.dequeue());
        }


        StdOut.println("Expected: 1 2 3 4");
    }

}
