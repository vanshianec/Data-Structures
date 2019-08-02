import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        List<Integer> inputNumbers = Arrays.stream(reader.readLine()
                .split("\\s+"))
                .map(Integer::parseInt)
                .collect(Collectors.toList());

        List<Integer> longestSubsequence = new ArrayList<>();
        int currentStreak = 0;
        int longestStreak = 0;
        int mostCommonNumber = 0;
        for (int i = 0; i < inputNumbers.size() - 1; i++) {
            if (inputNumbers.get(i).equals(inputNumbers.get(i + 1))) {
                currentStreak++;
                if (currentStreak > longestStreak) {
                    longestStreak = currentStreak;
                    mostCommonNumber = inputNumbers.get(i);
                }
            } else {
                currentStreak = 0;
            }
        }

        for (int i = 0; i < longestStreak + 1; i++) {
            longestSubsequence.add(mostCommonNumber);
        }

        System.out.println(longestSubsequence.stream().map(String::valueOf).collect(Collectors.joining(" ")));

    }
}
