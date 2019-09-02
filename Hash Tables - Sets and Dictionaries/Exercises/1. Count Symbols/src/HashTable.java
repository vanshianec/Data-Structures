import java.util.*;

public class HashTable<TKey, TValue> implements Iterable<KeyValue<TKey, TValue>> {

    private final int INITIAL_CAPACITY = 16;
    private final float LOAD_FACTOR = 0.75f;
    private LinkedList<KeyValue<TKey, TValue>>[] slots;
    private int size;

    public HashTable() {
        this.slots = new LinkedList[INITIAL_CAPACITY];
        this.size = 0;
    }

    public HashTable(int capacity) {
        this.slots = new LinkedList[capacity];
        this.size = 0;
    }

    public void add(TKey key, TValue value) {
        growIfNeeded();
        int slotNumber = getSlotNumber(key);
        if (this.slots[slotNumber] == null) {
            this.slots[slotNumber] = new LinkedList<>();
        }
        checkForDuplicateKeys(key, this.slots[slotNumber]);
        KeyValue<TKey, TValue> newElement = new KeyValue<>(key, value);
        this.slots[slotNumber].addLast(newElement);
        this.size++;
    }

    public int size() {
        return this.size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int capacity() {
        return this.slots.length;
    }

    public boolean addOrReplace(TKey key, TValue value) {
        growIfNeeded();
        int slotNumber = getSlotNumber(key);
        if (this.slots[slotNumber] == null) {
            this.slots[slotNumber] = new LinkedList<>();
        }
        for (KeyValue<TKey, TValue> element : this.slots[slotNumber]) {
            if (element.getKey().equals(key)) {
                element.setValue(value);
                return false;
            }
        }
        KeyValue<TKey, TValue> newElement = new KeyValue<>(key, value);
        this.slots[slotNumber].addLast(newElement);
        this.size++;
        return true;
    }

    public TValue get(TKey key) {
        KeyValue<TKey, TValue> element = find(key);
        if (element == null) {
            throw new IllegalArgumentException();
        }
        return element.getValue();
    }

    public boolean tryGetValue(TKey key, TValue value) {
        KeyValue<TKey, TValue> element = find(key);
        return element != null;
    }

    public KeyValue<TKey, TValue> find(TKey key) {
        int slotNumber = getSlotNumber(key);
        LinkedList<KeyValue<TKey, TValue>> elements = this.slots[slotNumber];
        if (elements != null) {
            for (KeyValue<TKey, TValue> element : elements) {
                if (element.getKey().equals(key)) {
                    return element;
                }
            }
        }
        return null;
    }

    public boolean containsKey(TKey key) {
        KeyValue<TKey, TValue> element = find(key);
        return element != null;
    }

    public boolean remove(TKey key) {
        int slotNumber = getSlotNumber(key);
        LinkedList<KeyValue<TKey, TValue>> elements = this.slots[slotNumber];
        if (elements != null) {
            KeyValue<TKey, TValue> elementToBeRemoved = null;
            for (KeyValue<TKey, TValue> element : elements) {
                if (element.getKey().equals(key)) {
                    elementToBeRemoved = element;
                    break;
                }
            }
            if (elementToBeRemoved != null) {
                elements.remove(elementToBeRemoved);
                this.size--;
                return true;
            }
        }
        return false;
    }

    public void clear() {
        this.size = 0;
        this.slots = new LinkedList[INITIAL_CAPACITY];
    }

    public Iterable<TKey> keys() {
        List<TKey> keys = new LinkedList<>();
        this.forEach(kv -> keys.add(kv.getKey()));
        return keys;
    }

    public Iterable<TValue> values() {
        List<TValue> values = new LinkedList<>();
        this.forEach(kv -> values.add(kv.getValue()));
        return values;
    }

    @Override
    public Iterator<KeyValue<TKey, TValue>> iterator() {
        Queue<KeyValue<TKey, TValue>> result = new ArrayDeque<>();
        for (LinkedList<KeyValue<TKey, TValue>> elements : this.slots) {
            if (elements != null) {
                for (KeyValue<TKey, TValue> element : elements) {
                    result.add(element);
                }
            }
        }
        return new Iterator<KeyValue<TKey, TValue>>() {
            @Override
            public boolean hasNext() {
                return result.size() > 0;
            }

            @Override
            public KeyValue<TKey, TValue> next() {
                return result.poll();
            }
        };
    }

    private void growIfNeeded() {
        if ((float) (this.size + 1) / this.capacity() > LOAD_FACTOR) {
            grow();
        }
    }

    private void grow() {
        HashTable<TKey, TValue> newHashTable = new HashTable<>(2 * this.capacity());
        for (KeyValue<TKey, TValue> element : this) {
            newHashTable.add(element.getKey(), element.getValue());
        }
        this.slots = newHashTable.slots;
        this.size = newHashTable.size;
    }

    private int getSlotNumber(TKey key) {
        return Math.abs(key.hashCode()) % this.slots.length;
    }

    private void checkForDuplicateKeys(TKey key, LinkedList<KeyValue<TKey, TValue>> slot) {
        for (KeyValue<TKey, TValue> element : slot) {
            if (element.getKey().equals(key)) {
                throw new IllegalArgumentException("Key already exists: " + key);
            }
        }
    }
}
