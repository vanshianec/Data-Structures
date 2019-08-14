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
        System.out.printf("Subtrees of sum %d:%n", sum);
        printAllSubtreesWithGivenSum(sum, mainTree);
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

    private static void printAllSubtreesWithGivenSum(int sum, Tree<Integer> tree) {
        int nodesSum = getTreeNodesSum(tree);
        if (nodesSum == sum) {
            printTreeInPreOrder(tree);
            System.out.println();
        }
        for (Tree<Integer> child : tree.getChildren()) {
            printAllSubtreesWithGivenSum(sum, child);
        }
    }


    private static int getTreeNodesSum(Tree<Integer> tree) {
        int sum = 0;
        Queue<Tree<Integer>> nodes = new ArrayDeque<>();
        nodes.add(tree);
        while (!nodes.isEmpty()) {
            Tree<Integer> current = nodes.poll();
            sum += current.getValue();
            for (Tree<Integer> child : current.getChildren()) {
                nodes.add(child);
            }
        }
        return sum;
    }

    private static void printTreeInPreOrder(Tree<Integer> tree) {
        System.out.print(tree.getValue() + " ");
        for (Tree<Integer> child : tree.getChildren()) {
            printTreeInPreOrder(child);
        }
    }

}
