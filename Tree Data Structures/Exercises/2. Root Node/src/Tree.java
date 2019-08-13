import java.util.LinkedList;
import java.util.List;

public class Tree<T> {

    private T value;
    private Tree<T> parent;
    private List<Tree<T>> children;

    public Tree() {
        this.value = null;
        this.parent = null;
        this.children = null;
    }

    public Tree(T value, Tree<T>... children) {
        this.value = value;
        this.children = new LinkedList<>();
        for (Tree<T> child : children) {
            child.parent = this;
            this.children.add(child);
        }
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    public Tree<T> getParent() {
        return parent;
    }

    public void setParent(Tree<T> parent) {
        this.parent = parent;
    }

    public List<Tree<T>> getChildren() {
        return children;
    }

    public void setChildren(List<Tree<T>> children) {
        this.children = children;
    }

    public void addChild(Tree<T> child){
        this.children.add(child);
    }
}
