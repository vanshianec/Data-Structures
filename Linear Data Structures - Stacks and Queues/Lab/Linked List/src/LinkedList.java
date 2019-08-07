import java.util.Iterator;

public class LinkedList<E> implements Iterable<E> {

    private int size;
    private Node head;
    private Node tail;

    public int size() {
        return this.size;
    }

    private void setSize(int size) {
        this.size = size;
    }

    public void addFirst(E item) {
        if (this.size == 0) {
            this.head = this.tail = new Node(item);
        } else {
            Node newHead = new Node(item);
            newHead.next = this.head;
            this.head = newHead;
        }
        this.size++;
    }

    public void addLast(E item) {
        if (this.size == 0) {
            this.head = this.tail = new Node(item);
        } else {
            Node oldTail = this.tail;
            this.tail = new Node(item);
            oldTail.next = this.tail;
        }
        this.size++;
    }

    public E removeFirst() {
        if (this.size == 0) {
            throw new IllegalArgumentException("List is empty");
        }
        E value = this.head.value;
        this.head = this.head.next;
        if (this.head == null) {
            this.tail = null;
        }
        this.size--;
        return value;
    }

    public E removeLast() {
        if (this.size == 0) {
            throw new IllegalArgumentException("List is empty");
        }
        E value = this.tail.value;
        if (this.size == 1) {
            this.head = this.tail = null;
        } else {
            Node newTail = this.getSecondToLast();
            newTail.next = null;
            this.tail = newTail;
        }
        this.size--;
        return value;
    }

    private Node getSecondToLast() {
        Node currentNode = this.head;
        while (currentNode != null) {
            if (currentNode.next == this.tail) {
                return currentNode;
            }
            currentNode = currentNode.next;
        }
        return null;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            Node currentNode = head;

            @Override
            public boolean hasNext() {
                return currentNode != null;
            }

            @Override
            public E next() {
                E value = currentNode.value;
                currentNode = currentNode.next;
                return value;
            }
        };
    }

    private class Node {

        private E value;
        private Node next;

        public Node(E value) {
            this.value = value;
        }

        public E getValue() {
            return value;
        }

        public void setValue(E value) {
            this.value = value;
        }

        public Node getNext() {
            return next;
        }

        public void setNext(Node next) {
            this.next = next;
        }
    }

}
