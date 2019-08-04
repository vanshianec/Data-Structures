import java.util.Arrays;
import java.util.Iterator;

@SuppressWarnings(value = "unchecked")
public class ReversedList<T> implements Iterable<T> {

    private final int DEFAULT_CAPACITY = 2;
    private T[] array;
    private int count;

    public ReversedList() {
        this.array = (T[]) new Object[DEFAULT_CAPACITY];
        this.count = 0;
    }

    public ReversedList(int capacity) {
        this.array = (T[]) new Object[capacity];
        this.count = 0;
    }

    public int count() {
        return this.count;
    }

    public int capacity() {
        return this.array.length;
    }

    public void add(T item) {
        if (isFull()) {
            grow();
        }
        this.array[this.count++] = item;
    }

    private boolean isFull() {
        return this.array.length == this.count;
    }

    private void grow() {
        int newCapacity = this.array.length * 2;
        this.array = Arrays.copyOf(this.array, newCapacity);
    }

    public T get(int index) {
        if (index < 0 || index > this.count - 1) {
            throw new IndexOutOfBoundsException();
        }
        return this.array[this.count - index - 1];
    }

    public void set(int index, T element) {
        if (index < 0 || index > this.count - 1) {
            throw new IndexOutOfBoundsException();
        }
        this.array[this.count - index - 1] = element;
    }

    public void removeAt(int index) {
        int reversedOrderIndex = this.count - index - 1;
        this.array[reversedOrderIndex] = null;
        this.count--;
        shift(reversedOrderIndex);
    }

    private void shift(int index) {
        for (int i = index; i < this.count; i++) {
            this.array[i] = this.array[i + 1];
        }
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private int counter = count - 1;

            @Override
            public boolean hasNext() {
                return counter >= 0;
            }

            @Override
            public T next() {
                return array[counter--];
            }
        };
    }
}
