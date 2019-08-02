import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        List<String> words = Arrays.stream(reader.readLine().split("\\s+")).sorted().collect(Collectors.toList());
        System.out.println(String.join(" ", words));
    }
}
