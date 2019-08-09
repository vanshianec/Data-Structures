public class LinkedQueue<T> {

    private int size;
    private Node head;
    private Node tail;

    public LinkedQueue() {
    }

    public int size() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public void enqueue(T element) {
        if (this.size == 0) {
            this.head = this.tail = new Node(element);
        } else {
            Node newTail = new Node(element);
            this.tail.nextNode = newTail;
            this.tail = newTail;
        }
        this.size++;
    }

    public T dequeue() {
        if (this.size == 0) {
            throw new IllegalArgumentException("The queue is empty");
        }
        T value = this.head.value;
        this.head = this.head.nextNode;
        this.size--;
        return value;
    }

    public T[] toArray() {
        T[] arrayResult = (T[]) new Object[this.size];
        Node currentNode = this.head;
        for (int i = 0; i < this.size; i++) {
            arrayResult[i] = currentNode.value;
            currentNode = currentNode.nextNode;
        }
        return arrayResult;
    }

    private class Node {

        private T value;
        private Node nextNode;

        public Node(T value) {
            this.value = value;
        }

        public T getValue() {
            return value;
        }

        public void setValue(T value) {
            this.value = value;
        }

        public Node getNextNode() {
            return nextNode;
        }

        public void setNextNode(Node nextNode) {
            this.nextNode = nextNode;
        }
    }
}
