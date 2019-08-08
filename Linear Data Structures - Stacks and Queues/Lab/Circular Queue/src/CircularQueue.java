public class CircularQueue<E> {

    private final int INITIAL_CAPACITY = 16;
    private E[] elements;
    private int startIndex = 0;
    private int endIndex = 0;

    private int size;

    public CircularQueue() {
        this.elements = (E[]) new Object[INITIAL_CAPACITY];
    }

    public CircularQueue(int initialCapacity) {
        this.elements = (E[]) new Object[initialCapacity];
    }

    public int size() {
        return this.size;
    }

    private void setSize(int size) {
        this.size = size;
    }

    public void enqueue(E element) {
        if (isFull()) {
            grow();
        }
        this.elements[this.endIndex] = element;
        this.endIndex = (this.endIndex + 1) % this.elements.length;
        this.size++;
    }

    public E dequeue() {
        if (this.size == 0) {
            throw new IllegalArgumentException("The queue is empty");
        }
        E result = this.elements[this.startIndex];
        this.startIndex = (this.startIndex + 1) % this.elements.length;
        this.size--;
        return result;
    }

    public E[] toArray() {
        E[] resultArray = (E[]) new Object[this.size];
        this.copyAllElementsTo(resultArray);
        return resultArray;
    }

    private boolean isFull() {
        return this.size >= this.elements.length;

    }

    private void grow() {
        E[] newElements = (E[]) new Object[2 * this.elements.length];
        this.copyAllElementsTo(newElements);
        this.elements = newElements;
        this.startIndex = 0;
        this.endIndex = this.size;
    }

    private void copyAllElementsTo(E[] newElements) {
        int sourceIndex = this.startIndex;
        int destinationIndex = 0;
        for (int i = 0; i < this.size; i++) {
            newElements[destinationIndex++] = this.elements[sourceIndex];
            sourceIndex = (sourceIndex + 1) % this.elements.length;
        }
    }

}
