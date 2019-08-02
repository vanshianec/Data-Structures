import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) throws IOException {

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        List<Integer> inputNumbers = Arrays.stream(bufferedReader.readLine().split("\\s+")).map(Integer::parseInt).collect(Collectors.toList());
        Map<Integer, Integer> occurrences = new HashMap<>();

        for (int i = 0; i < inputNumbers.size(); i++) {
            int number = inputNumbers.get(i);
            if (!occurrences.containsKey(number)) {
                occurrences.put(number, 0);
            }
            occurrences.put(number, occurrences.get(number) + 1);
        }

        inputNumbers.stream().filter(number -> occurrences.get(number) % 2 == 0).forEach(number -> System.out.printf("%d ", number));
    }
}
