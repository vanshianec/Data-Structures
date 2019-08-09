import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(reader.readLine());
        int count = 1;
        Queue<Integer> sequence = new ArrayDeque<>();
        sequence.add(n);
        while (true) {
            int s = sequence.poll();
            sequence.add(s + 1);
            sequence.add(s * 2 + 1);
            sequence.add(s + 2);
            count++;
            if (count == 51) {
                System.out.print(s);
                System.out.println();
                return;
            }
            System.out.print(s + ", ");
        }
    }
}
