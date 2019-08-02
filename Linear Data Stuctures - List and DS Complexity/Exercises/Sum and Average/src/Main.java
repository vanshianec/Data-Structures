import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String inputLine = reader.readLine();
        if (inputLine.equals("")) {
            System.out.printf("Sum = 0; Average = 0%n");
            return;
        }
        List<Integer> numbers = Arrays.stream(inputLine.split("\\s+")).map(Integer::parseInt).collect(Collectors.toList());
        int sum = numbers.stream().reduce((a, b) -> a + b).get();
        double average = (double) sum / numbers.size();
        System.out.printf("Sum=%d; Average=%.2f%n", sum, average);
    }
}
