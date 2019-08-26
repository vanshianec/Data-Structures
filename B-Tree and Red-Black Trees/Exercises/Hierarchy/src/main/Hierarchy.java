package main;

import java.util.*;

public class Hierarchy<T> implements IHierarchy<T> {

    private Node root;

    public Hierarchy(T element) {
        this.root = new Node(element);
    }

    public void add(T parent, T element) {
        if (!contains(parent) || contains(element)) {
            throw new IllegalArgumentException();
        }
        if (this.root == null) {
            this.root = new Node(element);
        } else {
            Node parentNode = getNode(parent);
            Node childNode = new Node(element);
            parentNode.addChild(childNode);
            childNode.setParent(parentNode);
        }
    }

    public int getCount() {
        Queue<Node> nodes = new ArrayDeque<>();
        nodes.add(this.root);
        int count = 0;
        while (!nodes.isEmpty()) {
            Node current = nodes.poll();
            for (Node node : current.getChildren()) {
                nodes.add(node);
            }
            count++;
        }
        return count;
    }

    public void remove(T element) {
        if (!contains(element)) {
            throw new IllegalArgumentException();
        }
        Node current = getNode(element);
        if (current != null) {
            if (current.parent == null) {
                throw new IllegalStateException();
            }
            Node parent = current.getParent();
            List<Node> children = current.children;
            parent.children.remove(current);
            for (Node child : children) {
                parent.addChild(child);
                child.setParent(parent);
            }
        }
    }

    public boolean contains(T element) {
        Queue<Node> nodes = new ArrayDeque<>();
        nodes.add(this.root);
        while (!nodes.isEmpty()) {
            Node current = nodes.poll();
            if (current.getElement().equals(element)) {
                return true;
            }
            for (Node node : current.getChildren()) {
                nodes.add(node);
            }
        }
        return false;
    }

    public T getParent(T element) {
        if (!contains(element)) {
            throw new IllegalArgumentException();
        }
        Queue<Node> nodes = new ArrayDeque<>();
        nodes.add(this.root);
        while (!nodes.isEmpty()) {
            Node current = nodes.poll();
            if (current.getElement().equals(element) && current.parent != null) {
                return current.parent.element;
            }
            for (Node node : current.getChildren()) {
                nodes.add(node);
            }
        }
        return null;
    }

    private Node getNode(T element) {
        Queue<Node> nodes = new ArrayDeque<>();
        nodes.add(this.root);
        while (!nodes.isEmpty()) {
            Node current = nodes.poll();
            if (current.getElement().equals(element)) {
                return current;
            }
            for (Node node : current.getChildren()) {
                nodes.add(node);
            }
        }
        return null;
    }

    public Iterable<T> getChildren(T element) {
        if (!contains(element)) {
            throw new IllegalArgumentException();
        }
        Queue<Node> nodes = new ArrayDeque<>();
        nodes.add(this.root);
        while (!nodes.isEmpty()) {
            Node current = nodes.poll();
            if (current.getElement().equals(element)) {
                return current.getChildrenElements();
            }
            for (Node node : current.getChildren()) {
                nodes.add(node);
            }
        }
        return null;
    }

    public Iterable<T> getCommonElements(IHierarchy<T> other) {
        Queue<Node> nodes = new ArrayDeque<>();
        nodes.add(this.root);
        List<T> commonElements = new LinkedList<>();
        while (!nodes.isEmpty()) {
            Node current = nodes.poll();
            if (other.contains(current.getElement())) {
                commonElements.add(current.getElement());
            }
            for (Node node : current.getChildren()) {
                nodes.add(node);
            }
        }
        return commonElements;
    }

    @Override
    public Iterator<T> iterator() {
        Queue<Node> nodes = new ArrayDeque<>();
        nodes.add(this.root);
        return new Iterator<T>() {
            @Override
            public boolean hasNext() {
                return !nodes.isEmpty();
            }

            @Override
            public T next() {
                Node current = nodes.poll();
                for (Node child : current.getChildren()) {
                    nodes.add(child);
                }
                return current.element;
            }
        };
    }

    private class Node {

        private T element;
        private Node parent;
        private List<Node> children;

        public Node(T element) {
            this.element = element;
            this.children = new LinkedList<>();
        }

        public T getElement() {
            return element;
        }

        public void setElement(T element) {
            this.element = element;
        }

        public Node getParent() {
            return parent;
        }

        public void setParent(Node parent) {
            this.parent = parent;
        }

        public List<Node> getChildren() {
            return children;
        }

        public void setChildren(List<Node> children) {
            this.children = children;
        }

        public List<T> getChildrenElements() {
            List<T> result = new LinkedList<>();
            for (Node child : children) {
                result.add(child.getElement());
            }
            return result;
        }

        private void addChild(Node child) {
            this.children.add(child);
        }
    }
}