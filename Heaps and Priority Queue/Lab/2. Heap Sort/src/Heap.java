public class Heap {

    public static <E extends Comparable<E>> void sort(E[] array) {
        int n = array.length;
        for (int i = n / 2; i >= 0; i--) {
            heapifyDown(array, i, n);
        }
        for (int i = n - 1; i > 0; i--) {
            swap(array, 0, i);
            heapifyDown(array, 0, n);
        }

    }

    private static <E extends Comparable<E>> void heapifyDown(E[] array, int current, int border) {
        while (current < border / 2) {
            int child = 2 * current + 1;
            if (hasRight(array, child) && isLess(array, child, child + 1)) {
                child = child + 1;
            }
            if (isLess(array, child, current)) {
                break;
            }
            swap(array, current, child);
            current = child;
        }
    }

    private static <E> boolean hasRight(E[] array, int index) {
        try {
            E result = array[2 * index + 2];
        } catch (IndexOutOfBoundsException e) {
            return false;
        }
        return true;
    }

    private static <E extends Comparable<E>> boolean isLess(E[] array, int parent, int index) {
        return array[parent].compareTo((array[index])) < 0;
    }


    private static <E> void swap(E[] array, int first, int second) {
        E oldValue = array[first];
        array[first] = array[second];
        array[second] = oldValue;
    }
}
