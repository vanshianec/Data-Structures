import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        LinkedQueue<Integer> linkedQueue = new LinkedQueue<>();
        linkedQueue.enqueue(6);
        linkedQueue.enqueue(1);
        linkedQueue.enqueue(8);
        linkedQueue.enqueue(12);
        linkedQueue.dequeue();
        linkedQueue.dequeue();
        Arrays.stream(linkedQueue.toArray()).forEach(System.out::println);
    }
}
