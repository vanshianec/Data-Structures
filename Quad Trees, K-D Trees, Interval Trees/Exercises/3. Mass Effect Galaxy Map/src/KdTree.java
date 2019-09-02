import java.util.Stack;

public class KdTree {

    private Node root;

    public Node getRoot() {
        return this.root;
    }

    public boolean contains(Point2D point) {
        Node node = contains(this.root, point, 0);
        return node != null;
    }

    private Node contains(Node node, Point2D point, int depth) {
        if (node == null) {
            return null;
        }
        int cmp = compare(point, node.point2D, depth);
        if (cmp < 0) {
            return contains(node.left, point, depth + 1);
        } else if (cmp > 0) {
            return contains(node.right, point, depth + 1);
        }
        return node;
    }

    public void insert(Point2D point, int galaxySize) {
        this.root = insert(this.root, point, 0, new int[]{0, 0, galaxySize, galaxySize});
    }

    private Node insert(Node node, Point2D point, int depth, int[] coords) {
        if (node == null) {
            return new Node(point, coords);
        }

        int cmp = compare(point, node.point2D, depth);
        if (cmp < 0) {
            if (depth % 2 == 0) {
                //lessen x_max
                coords[2] = node.point2D.getX();
            } else {
                //lessen y_max
                coords[3] = node.point2D.getY();
            }
            node.left = insert(node.left, point, depth + 1, coords);
        } else if (cmp > 0) {
            if (depth % 2 == 0) {
                //increase x_min
                coords[0] = node.point2D.getX();
            } else {
                //increase y_min
                coords[1] = node.point2D.getY();
            }
            node.right = insert(node.right, point, depth + 1, coords);
        }

        return node;
    }

    private int compare(Point2D a, Point2D b, int depth) {
        int cmp = 0;
        if (depth % 2 == 0) {
            cmp = Double.compare(a.getX(), b.getX());
            if (cmp == 0) {
                cmp = Double.compare(a.getY(), b.getY());
            }
        } else {
            cmp = Double.compare(a.getY(), b.getY());
            if (cmp == 0) {
                cmp = Double.compare(a.getX(), b.getX());
            }
        }
        return cmp;
    }

    public int findPointsInArea(GalaxyArea area) {
        Stack<Node> nodes = new Stack<>();
        findPointsInArea(this.root, area, nodes);
        return nodes.size();
    }

    private void findPointsInArea(Node node, GalaxyArea area, Stack<Node> nodes) {
        if (node.point2D.isInside(area)) {
            nodes.push(node);
        }
        if (node.left != null && area.intersects(node.left.area)) {
            findPointsInArea(node.left, area, nodes);
        }
        if (node.right != null && area.intersects(node.right.area)) {
            findPointsInArea(node.right, area, nodes);
        }
    }

    public class Node {

        private Point2D point2D;
        private GalaxyArea area;
        private Node left;
        private Node right;

        public Node(Point2D point, int[] coords) {
            this.setPoint2D(point);
            this.area = new GalaxyArea(coords[0], coords[1], coords[2] - coords[0], coords[3] - coords[1]);
        }

        public Point2D getPoint2D() {
            return this.point2D;
        }

        public void setPoint2D(Point2D point2D) {
            this.point2D = point2D;
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

        public GalaxyArea getArea() {
            return area;
        }

        public void setArea(GalaxyArea area) {
            this.area = area;
        }

        public void setRight(Node right) {
            this.right = right;
        }
    }
}
