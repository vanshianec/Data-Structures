public class LinkedStack<E> {

    private Node<E> firstNode;
    private int size;


    public int size() {
        return this.size;
    }

    private void setSize(int size) {
        this.size = size;
    }

    public void push(E element) {
        this.firstNode = new Node(element, this.firstNode);
        this.size++;
    }

    public E pop() {
        if (this.size == 0) {
            throw new IllegalArgumentException("The stack is empty");
        }
        E value = this.firstNode.value;
        this.firstNode = this.firstNode.nextNode;
        this.size--;
        return value;
    }

    public E[] toArray() {
        E[] arrayResult = (E[]) new Object[this.size];
        Node currentNode = this.firstNode;
        for (int i = 0; i < this.size; i++) {
            arrayResult[i] = (E) currentNode.value;
            currentNode = currentNode.nextNode;
        }
        return arrayResult;
    }

    private class Node<E> {

        private E value;
        private Node<E> nextNode;

        public Node(E value) {
            this.value = value;
        }

        public Node(E value, Node<E> nextNode) {
            this.value = value;
            this.nextNode = nextNode;
        }

        public Node<E> getNextNode() {
            return this.nextNode;
        }

        public void setNextNode(Node<E> nextNode) {
            this.nextNode = nextNode;
        }
    }
}