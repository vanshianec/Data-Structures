import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        ArrayStack<Integer> stack = new ArrayStack<>();
        stack.push(5);
        stack.push(7);
        stack.push(9);
        stack.push(2);
        stack.push(2);
        stack.push(2);
        stack.pop();
        stack.pop();
        stack.push(2);
        Arrays.stream(stack.toArray()).forEach(System.out::println);
    }
}
