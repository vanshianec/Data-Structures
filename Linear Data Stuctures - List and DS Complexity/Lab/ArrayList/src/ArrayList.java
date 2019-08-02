import java.util.Arrays;

@SuppressWarnings(value = "unchecked")
public class ArrayList<T> {

    private final int DEFAULT_CAPACITY = 2;
    private T[] array;
    private int count;

    public ArrayList() {
        this.array = (T[]) new Object[DEFAULT_CAPACITY];
        this.count = 0;
    }

    public ArrayList(int capacity) {
        this.array = (T[]) new Object[capacity];
        this.count = 0;
    }

    public int getCount() {
        return this.count;
    }

    public T get(int index) {
        if (index < 0 || index >= count) {
            throw new IndexOutOfBoundsException("Index is out of bounds");
        }
        return this.array[index];
    }

    public void add(T item) {
        if (isFull()) {
            resize();
        }
        this.array[this.count++] = item;
    }

    public T removeAt(int index) {
        if (index < 0 || index >= count) {
            throw new IndexOutOfBoundsException("Index is out of bounds");
        }
        T item = this.array[index];
        this.array[index] = null;
        this.count--;
        shift(index);
        return item;
    }

    private void shift(int index) {
        for (int i = index; i < this.count; i++) {
            this.array[i] = this.array[i + 1];
            this.array[i + 1] = null;
        }
    }

    private boolean isFull() {
        return this.count == this.array.length;
    }

    private void resize() {
        int newIncreasedCapacity = this.array.length * 2;
        this.array = Arrays.copyOf(this.array, newIncreasedCapacity);
    }

}
