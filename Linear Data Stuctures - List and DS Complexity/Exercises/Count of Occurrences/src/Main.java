import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Map;
import java.util.TreeMap;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int[] inputNumbers = Arrays.stream(reader.readLine().split("\\s+")).mapToInt(Integer::parseInt).toArray();
        Map<Integer, Integer> occurrences = new TreeMap<>();

        for (int i = 0; i < inputNumbers.length; i++) {
            int inputNumber = inputNumbers[i];
            if (!occurrences.containsKey(inputNumber)) {
                occurrences.put(inputNumber, 0);
            }
            occurrences.put(inputNumber, occurrences.get(inputNumber) + 1);
        }

        for (Map.Entry<Integer, Integer> entry : occurrences.entrySet()) {
            System.out.printf("%d -> %d times%n", entry.getKey(), entry.getValue());
        }
    }

}
