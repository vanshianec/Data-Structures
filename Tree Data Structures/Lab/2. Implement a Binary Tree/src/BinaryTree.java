import java.util.function.Consumer;

public class BinaryTree<T> {

    private T value;
    private BinaryTree<T> leftChild;
    private BinaryTree<T> rightChild;


    public BinaryTree(T value) {
        this.value = value;
        this.leftChild = null;
        this.rightChild = null;
    }

    public BinaryTree(T value, BinaryTree<T> child) {
        this.value = value;
        this.leftChild = child;
        this.rightChild = null;
    }

    public BinaryTree(T value, BinaryTree<T> leftChild, BinaryTree<T> rightChild) {
        this.value = value;
        this.leftChild = leftChild;
        this.rightChild = rightChild;
    }

    // append output to builder
    public String printIndentedPreOrder(int indent, StringBuilder builder) {
        StringBuilder separator = new StringBuilder();
        for (int i = 0; i < indent * 2; i++) {
            separator.append(" ");
        }
        builder.append(separator.toString());
        builder.append(this.value).append("\n");
        if (this.leftChild != null) {
            this.leftChild.printIndentedPreOrder(indent + 1, builder);
        }
        if (this.rightChild != null) {
            this.rightChild.printIndentedPreOrder(indent + 1, builder);
        }
        return builder.toString();
    }

    public void eachInOrder(Consumer<T> consumer) {

        if (this.leftChild != null) {
            this.leftChild.eachInOrder(consumer);
        }
        consumer.accept(this.value);
        if (this.rightChild != null) {
            this.rightChild.eachInOrder(consumer);
        }
    }

    public void eachPostOrder(Consumer<T> consumer) {
        if (this.leftChild != null) {
            this.leftChild.eachPostOrder(consumer);
        }
        if (this.rightChild != null) {
            this.rightChild.eachPostOrder(consumer);
        }
        consumer.accept(this.value);
    }
}
