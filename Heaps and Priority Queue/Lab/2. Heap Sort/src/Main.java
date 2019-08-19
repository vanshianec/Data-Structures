import java.util.Arrays;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        Integer[] array = new Integer[]{5, 2, 0, -4, 3, 12};
        System.out.printf("Unsorted: %s%n", Arrays.stream(array).map(String::valueOf).collect(Collectors.joining(" ")));

        Heap.sort(array);

        System.out.printf("Sorted: %s%n", Arrays.stream(array).map(String::valueOf).collect(Collectors.joining(" ")));

    }
}
