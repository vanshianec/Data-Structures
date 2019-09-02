import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in));
        String sentence = consoleReader.readLine();
        HashTable<String, Integer> occurrences = new HashTable<>();
        Arrays.stream(sentence.split("")).forEach(symbol -> {
            if (!occurrences.containsKey(symbol)) {
                occurrences.add(symbol, 0);
            }
            occurrences.addOrReplace(symbol, occurrences.get(symbol) + 1);
        });
        List<KeyValue<String, Integer>> sortedOccurrences = new ArrayList<>();
        for (KeyValue<String, Integer> occurrence : occurrences) {
            sortedOccurrences.add(occurrence);
        }
        sortedOccurrences.stream()
                .sorted((a, b) -> a.getKey().compareTo(b.getKey()))
                .forEach(kv -> System.out.printf("%s: %d time/s%n", kv.getKey(), kv.getValue()));
    }
}
