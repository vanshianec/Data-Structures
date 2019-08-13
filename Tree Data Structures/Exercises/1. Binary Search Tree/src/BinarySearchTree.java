import java.util.ArrayDeque;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.function.Consumer;

public class BinarySearchTree<T extends Comparable<T>> {

    private Node root;

    public BinarySearchTree() {
    }

    public BinarySearchTree(Node root) {
        this.copy(root);
    }

    public Node getRoot() {
        return this.root;
    }

    public void insert(T value) {
        if (this.root == null) {
            this.root = new Node(value);
            return;
        }
        Node current = root;

        while (true) {
            if (current.value.compareTo(value) < 0) {
                if (current.right == null) {
                    current.right = new Node(value);
                    return;
                }
                current = current.right;
            } else if (current.value.compareTo(value) > 0) {
                if (current.left == null) {
                    current.left = new Node(value);
                    return;
                }
                current = current.left;
            } else {
                return;
            }
        }
    }

    public boolean contains(T value) {
        Node current = root;

        while (current != null) {
            if (current.value.compareTo(value) < 0) {
                current = current.right;
            } else if (current.value.compareTo(value) > 0) {
                current = current.left;
            } else {
                return true;
            }
        }

        return false;
    }

    public BinarySearchTree<T> search(T item) {
        Node current = root;

        while (current != null) {
            if (current.value.compareTo(item) < 0) {
                current = current.right;
            } else if (current.value.compareTo(item) > 0) {
                current = current.left;
            } else {
                return new BinarySearchTree<>(current);
            }
        }

        return null;
    }

    public void eachInOrder(Consumer<T> consumer) {
        eachInOrder(root, consumer);
    }

    private void eachInOrder(Node node, Consumer<T> consumer) {
        if (node == null) {
            return;
        }
        eachInOrder(node.left, consumer);
        consumer.accept(node.value);
        eachInOrder(node.right, consumer);
    }

    public Iterable<T> range(T from, T to) {
        List<T> result = new LinkedList<>();
        insertInRange(root, result, from, to);
        return result;
    }

    private void insertInRange(Node root, List<T> result, T from, T to) {
        if (root == null) {
            return;
        }

        insertInRange(root.left, result, from, to);
        if (root.value.compareTo(from) >= 0 && root.value.compareTo(to) <= 0) {
            result.add(root.value);
        }
        insertInRange(root.right, result, from, to);
    }

    private void copy(Node node) {

        if (node == null) {
            return;
        }

        this.insert(node.value);
        this.copy(node.left);
        this.copy(node.right);
    }

    class Node {
        private T value;
        private Node left;
        private Node right;

        public Node(T value) {
            this.value = value;
        }

        public T getValue() {
            return this.value;
        }

        public void setValue(T value) {
            this.value = value;
        }

        public Node getLeft() {
            return this.left;
        }

        public void setLeft(Node left) {
            this.left = left;
        }

        public Node getRight() {
            return this.right;
        }

        public void setRight(Node right) {
            this.right = right;
        }
    }
}

