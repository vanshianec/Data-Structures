import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class FirstLastList<T extends Comparable<T>> implements IFirstLastList<T> {

    private ArrayList<T> elements = new ArrayList<T>();
    private int count = 0;

    @Override
    public void add(T element) {
        this.elements.add(element);
        this.count++;
    }

    @Override
    public int getCount() {
        return this.count;
    }

    @Override
    public Iterable<T> first(int count) {
        if (count > this.count) {
            throw new IllegalArgumentException();
        }
        List<T> firstElements = new LinkedList<>();
        for (int i = 0; i < count; i++) {
            firstElements.add(elements.get(i));
        }
        return firstElements;
    }

    @Override
    public Iterable<T> last(int count) {
        if (count > this.count) {
            throw new IllegalArgumentException();
        }
        List<T> lastElements = new LinkedList<>();
        for (int i = this.count - 1; i >= this.count - count; i--) {
            lastElements.add(elements.get(i));
        }
        return lastElements;
    }

    @Override
    public Iterable<T> min(int count) {
        if (count > this.count) {
            throw new IllegalArgumentException();
        }
        return this.elements.stream().sorted().limit(count).collect(Collectors.toList());
    }

    @Override
    public Iterable<T> max(int count) {
        if (count > this.count) {
            throw new IllegalArgumentException();
        }
        return this.elements.stream().sorted((a, b) -> b.compareTo(a)).limit(count).collect(Collectors.toList());
    }

    @Override
    public void clear() {
        this.count = 0;
        this.elements.clear();
    }

    @Override
    public int removeAll(T element) {
        this.elements = this.elements.stream().filter(e -> e.compareTo(element) != 0).collect(Collectors.toCollection(ArrayList::new));
        int removedElements = this.count - this.elements.size();
        this.count -= removedElements;
        return removedElements;
    }
}