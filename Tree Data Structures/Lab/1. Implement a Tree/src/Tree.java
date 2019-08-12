import java.util.*;
import java.util.function.Consumer;

public class Tree<T> {

    private T value;
    private List<Tree<T>> children;

    public Tree(T value, Tree<T>... children) {
        this.value = value;
        this.children = new ArrayList<>();
        for (Tree<T> child : children) {
            this.children.add(child);
        }
    }

    // append output to builder
    public String print(int indent, StringBuilder builder) {
        StringBuilder separator = new StringBuilder();
        for (int i = 0; i < indent * 2; i++) {
            separator.append(" ");
        }
        builder.append(separator.toString());
        builder.append(this.value).append("\n");
        for (Tree<T> child : children) {
            child.print(indent + 1, builder);
        }
        return builder.toString();
    }

    public void each(Consumer<T> consumer) {
        consumer.accept(this.value);
        for (Tree<T> child : children) {
            child.each(consumer);
        }
    }

    public Iterable<T> orderDFS() {
        List<T> result = new LinkedList<>();
        this.dfs(this, result);
        return result;
    }

    private void dfs(Tree<T> tree, List<T> result) {
        for (Tree<T> child : tree.getChildren()) {
            dfs(child, result);
        }
        result.add(tree.value);
    }

    public Iterable<T> orderBFS() {
        List<T> result = new LinkedList<>();
        Queue<Tree<T>> queue = new ArrayDeque<>();

        queue.add(this);

        while (!queue.isEmpty()) {
            Tree<T> current = queue.poll();
            for (Tree<T> child : current.getChildren()) {
                queue.add(child);
            }
            result.add(current.value);
        }
        return result;
    }


    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    public List<Tree<T>> getChildren() {
        return children;
    }

    public void setChildren(List<Tree<T>> children) {
        this.children = children;
    }
}