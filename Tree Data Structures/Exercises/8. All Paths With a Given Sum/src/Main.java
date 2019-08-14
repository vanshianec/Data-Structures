import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

public class Main {
    private static Map<Integer, Tree<Integer>> nodes = new LinkedHashMap<>();

    public static void main(String[] args) throws IOException {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(reader.readLine());
        fillNodes(reader, n);
        Tree<Integer> mainTree = nodes.values().stream().findFirst().get();
        int sum = Integer.parseInt(reader.readLine());
        System.out.printf("Paths of sum %d:%n", sum);
        printAllPathsWithAGivenSum(sum, mainTree);
    }

    private static void fillNodes(BufferedReader reader, int n) throws IOException {
        for (int i = 0; i < n - 1; i++) {
            String[] input = reader.readLine().split("\\s+");
            int parent = Integer.parseInt(input[0]);
            int child = Integer.parseInt(input[1]);
            addNodes(parent, child);
        }
    }

    private static void addNodes(int parent, int child) {
        Tree<Integer> parentTree = getNodeByValue(parent);
        Tree<Integer> childTree = getNodeByValue(child);
        parentTree.addChild(childTree);
        childTree.setParent(parentTree);

    }

    private static Tree<Integer> getNodeByValue(int value) {
        if (!nodes.containsKey(value)) {
            nodes.put(value, new Tree<>(value));
        }
        return nodes.get(value);
    }

    private static void printAllPathsWithAGivenSum(int sum, Tree<Integer> tree) {
        int count = 0;
        List<Integer> path = new LinkedList<>();
        printAllPathsWithAGivenSum(sum, tree, count, path);
    }

    private static void printAllPathsWithAGivenSum(int sum, Tree<Integer> tree, int count, List<Integer> path) {
        count += tree.getValue();
        path.add(tree.getValue());
        if (count == sum) {
            System.out.println(path.stream().map(String::valueOf).collect(Collectors.joining(" ")));
        }
        for (Tree<Integer> child : tree.getChildren()) {
            printAllPathsWithAGivenSum(sum, child, count, path);
        }
        count -= tree.getValue();
        path.remove(tree.getValue());
    }
}
