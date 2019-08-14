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
        String[] longestPath = getLongestPath();
        System.out.printf("Longest path: %s", String.join(" ", longestPath));
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

    private static String[] getLongestPath() {
        int maxDepth = 0;
        Tree<Integer> deepestNode = null;
        String[] longestPath;
        for (Tree<Integer> tree : nodes.values()) {
            if (tree.getParentsCount() > maxDepth) {
                maxDepth = tree.getParentsCount();
                deepestNode = tree;
            }
        }
        longestPath = new String[maxDepth + 1];
        int index = maxDepth;
        while (deepestNode != null) {
            longestPath[index--] = String.valueOf(deepestNode.getValue());
            deepestNode = deepestNode.getParent();
        }
        return longestPath;
    }
}
