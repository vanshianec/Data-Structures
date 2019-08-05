import javax.naming.OperationNotSupportedException;
import java.util.Iterator;
import java.util.function.Consumer;

public class DoublyLinkedList<T> implements Iterable<T> {

    private ListNode<T> head;
    private ListNode<T> tail;
    private int size;

    public int size() {
        return this.size;
    }

    private void setSize(int size) {
        this.size = size;
    }

    public void addFirst(T element) {
        if (this.size == 0) {
            this.head = this.tail = new ListNode<>(element);
        } else {
            ListNode<T> newHead = new ListNode<>(element);
            newHead.nextNode = this.head;
            this.head.previousNode = newHead;
            this.head = newHead;
        }
        this.size++;
    }

    public void addLast(T element) {
        if (this.size == 0) {
            this.head = this.tail = new ListNode<>(element);
        } else {
            ListNode<T> newTail = new ListNode<>(element);
            newTail.previousNode = this.tail;
            this.tail.nextNode = newTail;
            this.tail = newTail;
        }
        this.size++;
    }

    public T removeFirst() {
        if (this.size == 0) {
            throw new IllegalArgumentException("List empty");
        }
        T firstElement = this.head.value;
        this.head = this.head.nextNode;
        if (this.head != null) {
            this.head.previousNode = null;
        } else {
            this.tail = null;
        }
        this.size--;
        return firstElement;
    }

    public T removeLast() {
        if (this.size == 0) {
            throw new IllegalArgumentException("List empty");
        }
        T lastElement = this.tail.value;
        this.tail = this.tail.previousNode;
        if (this.tail != null) {
            this.tail.nextNode = null;
        } else {
            this.head = null;
        }
        this.size--;
        return lastElement;
    }

    public T[] toArray() {
        T[] array = (T[]) new Object[this.size];
        int index = 0;
        ListNode<T> currentNode = this.head;
        while (currentNode != null) {
            array[index++] = currentNode.value;
            currentNode = currentNode.nextNode;
        }
        return array;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            ListNode<T> currentNode = head;

            @Override
            public boolean hasNext() {
                return currentNode != null;
            }

            @Override
            public T next() {
                T currentNodeValue = currentNode.value;
                currentNode = currentNode.nextNode;
                return currentNodeValue;
            }
        };
    }

    @Override
    public void forEach(Consumer<? super T> action) {
        ListNode<T> currentNode = this.head;
        while (currentNode != null) {
            action.accept(currentNode.value);
            currentNode = currentNode.nextNode;
        }
    }


    private class ListNode<T> {
        private T value;
        private ListNode<T> previousNode;
        private ListNode<T> nextNode;

        public ListNode(T value) {
            this.value = value;
        }

        public T getValue() {
            return value;
        }

        public ListNode<T> getPreviousNode() {
            return previousNode;
        }

        public ListNode<T> getNextNode() {
            return nextNode;
        }

        public void setValue(T value) {
            this.value = value;
        }

        public void setPreviousNode(ListNode<T> previousNode) {
            this.previousNode = previousNode;
        }

        public void setNextNode(ListNode<T> nextNode) {
            this.nextNode = nextNode;
        }
    }
}
