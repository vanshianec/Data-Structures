import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        LinkedStack<Integer> linkedStack = new LinkedStack<>();
        linkedStack.push(5);
        linkedStack.push(54);
        linkedStack.push(21);
        linkedStack.push(555);
        linkedStack.push(41);
        linkedStack.push(56);
        linkedStack.pop();
        linkedStack.pop();
        linkedStack.pop();
        linkedStack.push(1);
        Arrays.stream(linkedStack.toArray()).forEach(System.out::println);
    }
}
