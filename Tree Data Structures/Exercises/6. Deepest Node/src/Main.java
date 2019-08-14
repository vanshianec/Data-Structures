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
        int deepestNodeValue = getDeepestLeftMostNode();
        System.out.printf("Deepest node: %d", deepestNodeValue);
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

    private static int getDeepestLeftMostNode() {
        int maxDepth = 0;
        int deepestNodeValue = 0;
        for (Tree<Integer> tree : nodes.values()) {
            if (tree.getParentsCount() > maxDepth) {
                maxDepth = tree.getParentsCount();
                deepestNodeValue = tree.getValue();
            }
        }
        return deepestNodeValue;
    }
}
