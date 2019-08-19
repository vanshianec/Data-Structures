import java.util.ArrayList;
import java.util.List;

public class BinaryHeap<T extends Comparable<T>> {

    private List<T> heap;

    public BinaryHeap() {
        this.heap = new ArrayList<>();
    }

    public int size() {
        return this.heap.size();
    }

    public void insert(T element) {
        this.heap.add(element);
        this.heapifyUp(this.size() - 1);
    }

    private void heapifyUp(int index) {
        while (index > 0 && isLess(parent(index), index)) {
            this.swap(index, parent(index));
            index = parent(index);
        }
    }

    private void swap(int index, int parent) {
        T childTemp = this.heap.get(index);
        this.heap.set(index, this.heap.get(parent));
        this.heap.set(parent, childTemp);
    }

    private boolean isLess(int parent, int index) {
        return this.heap.get(parent).compareTo(this.heap.get(index)) < 0;
    }

    private int parent(int index) {
        return (index - 1) / 2;
    }


    public T peek() {
        return this.heap.get(0);
    }

    public T pull() {
        if (this.size() == 0) {
            throw new IllegalArgumentException("The heap is empty");
        }

        T item = this.heap.get(0);
        this.swap(0, this.size() - 1);
        this.heap.remove(this.size() - 1);
        this.heapifyDown(0);
        return item;
    }

    private void heapifyDown(int index) {
        while (index < this.size() / 2) {
            int child = 2 * index + 1;
            if (hasRight(child) && isLess(child, child + 1)) {
                child = child + 1;
            }
            if (isLess(child, index)) {
                break;
            }
            this.swap(index, child);
            index = child;
        }
    }

    private boolean hasRight(int index) {
        try {
            this.heap.get(2 * index + 2);
        } catch (IndexOutOfBoundsException e) {
            return false;
        }
        return true;
    }
}
