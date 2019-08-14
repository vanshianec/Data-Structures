import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import java.util.Map;

public class Main {
    private static Map<Integer, Tree<Integer>> nodes = new LinkedHashMap<>();

    public static void main(String[] args) throws IOException {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(reader.readLine());
        fillNodes(reader, n);
        String[] leafs = nodes.values().stream()
                .filter(tree -> tree.getChildren().isEmpty())
                .map(tree -> tree.getValue())
                .sorted()
                .map(v -> String.valueOf(v))
                .toArray(String[]::new);
        System.out.printf("Leaf nodes: %s", String.join(" ", leafs));
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
}
