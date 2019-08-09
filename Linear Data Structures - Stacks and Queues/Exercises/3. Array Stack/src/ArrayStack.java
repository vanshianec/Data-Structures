import java.util.Arrays;

public class ArrayStack<T> {

    private static final int INITIAL_CAPACITY = 16;

    private T[] elements;
    private int size;

    public ArrayStack() {
        this.elements = (T[]) new Object[INITIAL_CAPACITY];
        this.size = 0;
    }

    public ArrayStack(int capacity) {
        this.elements = (T[]) new Object[capacity];
        this.size = 0;
    }

    public int size() {
        return this.size;
    }

    private void setSize(int size) {
        this.size = size;
    }

    public void push(T element) {
        if (isFull()) {
            grow();
        }
        this.elements[this.size++] = element;
    }

    public T pop() {
        if (this.size == 0) {
            throw new IllegalArgumentException("The stack is empty");
        }
        return this.elements[--this.size];
    }

    public T[] toArray() {
        T[] result = (T[]) new Object[this.size];
        int index = 0;
        for (int i = this.size - 1; i >= 0; i--) {
            result[index++] = this.elements[i];
        }
        return result;
    }

    private void grow() {
        this.elements = Arrays.copyOf(this.elements, this.elements.length * 2);
    }

    private boolean isFull() {
        return this.size == this.elements.length;
    }

}