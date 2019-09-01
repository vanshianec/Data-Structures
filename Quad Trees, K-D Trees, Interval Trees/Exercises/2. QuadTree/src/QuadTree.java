import java.util.ArrayList;
import java.util.List;

public class QuadTree<T extends Boundable> {

    public static final int DEFAULT_MAX_DEPTH = 5;

    private int maxDepth;

    private Node<T> root;

    private Rectangle bounds;

    private int count;

    public QuadTree(int width, int height) {
        this(width, height, DEFAULT_MAX_DEPTH);
    }

    public QuadTree(int width, int height, int maxDepth) {
        this.root = new Node<T>(0, 0, width, height);
        this.bounds = this.root.getBounds();
        this.maxDepth = maxDepth;
    }

    public Rectangle getBounds() {
        return this.bounds;
    }

    private void setBounds(Rectangle bounds) {
        this.bounds = bounds;
    }

    public int getCount() {
        return this.count;
    }

    private void setCount(int count) {
        this.count = count;
    }

    public boolean insert(T item) {
        if (!item.getBounds().isInside(this.bounds)) {
            return false;
        }
        int depth = 1;
        Node current = this.root;
        while (current.getChildren() != null) {
            int quadrant = getQuadrant(current, item.getBounds());
            if (quadrant == -1) {
                break;
            }
            current = current.getChildren()[quadrant];
            depth++;
        }
        current.getItems().add(item);
        this.split(current, depth);
        this.count++;
        return true;
    }

    public List<T> report(Rectangle bounds) {
        List<T> collisionCandidates = new ArrayList<>();
        getCollisionCandidates(this.root, bounds, collisionCandidates);
        return collisionCandidates;
    }

    private void getCollisionCandidates(Node<T> node, Rectangle bounds, List<T> collisionCandidates) {
        int quadrant = getQuadrant(node, bounds);
        if (quadrant == -1) {
            getSubtreeContents(node, bounds, collisionCandidates);
        } else {
            if (node.getChildren() != null) {
                getCollisionCandidates(node.getChildren()[quadrant], bounds, collisionCandidates);
            }
            collisionCandidates.addAll(node.getItems());
        }
    }

    private void getSubtreeContents(Node<T> node, Rectangle bounds, List<T> collisionCandidates) {
        if (node.getChildren() != null) {
            for (Node child : node.getChildren()) {
                if (child.getBounds().intersects(bounds)) {
                    getSubtreeContents(child, bounds, collisionCandidates);
                }
            }
        }
        collisionCandidates.addAll(node.getItems());
    }


    private int getQuadrant(Node<T> node, Rectangle bounds) {
        int verticalMidPoint = node.getBounds().getMidX();
        int horizontalMidPoint = node.getBounds().getMidY();

        boolean inTopQuadrant = node.getBounds().getY1() <= bounds.getY1() && bounds.getY2() <= horizontalMidPoint;
        boolean inBottomQuadrant = horizontalMidPoint <= bounds.getY1() && bounds.getY2() <= node.getBounds().getY2();
        boolean inLeftQuadrant = node.getBounds().getX1() <= bounds.getX1() && bounds.getX2() <= verticalMidPoint;
        boolean inRightQuadrant = verticalMidPoint <= bounds.getX1() && bounds.getX2() <= node.getBounds().getX2();

        if (inLeftQuadrant) {
            if (inTopQuadrant) {
                return 1;
            } else if (inBottomQuadrant) {
                return 2;
            }
        } else if (inRightQuadrant) {
            if (inTopQuadrant) {
                return 0;
            } else if (inBottomQuadrant) {
                return 3;
            }
        }
        return -1;
    }

    private void split(Node node, int depth) {
        if (!(node.shouldSplit() && depth < maxDepth)) {
            return;
        }

        int leftWidth = node.getBounds().getWidth() / 2;
        int rightWidth = node.getBounds().getWidth() - leftWidth;
        int topHeight = node.getBounds().getHeight() / 2;
        int bottomHeight = node.getBounds().getHeight() - topHeight;

        Node[] children = new Node[4];
        children[0] = new Node(node.getBounds().getMidX(), node.getBounds().getY1(), rightWidth, topHeight);
        children[1] = new Node(node.getBounds().getX1(), node.getBounds().getY1(), leftWidth, topHeight);
        children[2] = new Node(node.getBounds().getX1(), node.getBounds().getMidY(), leftWidth, bottomHeight);
        children[3] = new Node(node.getBounds().getMidX(), node.getBounds().getMidY(), rightWidth, bottomHeight);
        node.setChildren(children);

        for (int i = 0; i < node.getItems().size(); i++) {
            T item = (T) node.getItems().get(i);
            int quadrant = getQuadrant(node, item.getBounds());
            if (quadrant != -1) {
                node.getItems().remove(item);
                node.getChildren()[quadrant].getItems().add(item);
                i--;
            }
        }

        for (Node child : node.getChildren()) {
            split(child, depth + 1);
        }
    }
}
