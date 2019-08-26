import java.util.*;

public class AStar {

    private char[][] map;

    public AStar(char[][] map) {
        this.map = map;
    }

    public static int getH(Node current, Node goal) {
        int deltaX = Math.abs(current.getCol() - goal.getCol());
        int deltaY = Math.abs(current.getRow() - goal.getRow());

        return deltaX + deltaY;
    }

    public Iterable<Node> getPath(Node start, Node goal) {
        PriorityQueue<Node> queue = new PriorityQueue<>();
        Map<Node, Node> parent = new LinkedHashMap<>();
        Map<Node, Integer> distance = new LinkedHashMap<>();
        queue.add(start);
        parent.put(start, null);
        distance.put(start, 0);

        while (!queue.isEmpty()) {
            Node current = queue.poll();
            if (current.equals(goal)) {
                break;
            }
            if (current.getRow() - 1 >= 0) {
                Node up = new Node(current.getRow() - 1, current.getCol());
                if (map[up.getRow()][up.getCol()] != 'W') {
                    addNeighbor(goal, queue, parent, distance, current, up);
                }
            }
            if (current.getCol() + 1 < map[0].length) {
                Node right = new Node(current.getRow(), current.getCol() + 1);
                if (map[right.getRow()][right.getCol()] != 'W') {
                    addNeighbor(goal, queue, parent, distance, current, right);
                }
            }
            if (current.getRow() + 1 < map.length) {
                Node down = new Node(current.getRow() + 1, current.getCol());
                if (map[down.getRow()][down.getCol()] != 'W') {
                    addNeighbor(goal, queue, parent, distance, current, down);
                }
            }
            if (current.getCol() - 1 >= 0) {
                Node left = new Node(current.getRow(), current.getCol() - 1);
                if (map[left.getRow()][left.getCol()] != 'W') {
                    addNeighbor(goal, queue, parent, distance, current, left);
                }
            }
        }


        return getPath(parent, goal);
    }


    private void addNeighbor(Node goal, PriorityQueue<Node> queue, Map<Node, Node> parent, Map<Node, Integer> distance, Node current, Node neighbor) {
        int newCost = distance.get(current) + 1;
        if (!distance.containsKey(neighbor) || newCost < distance.get(neighbor)) {
            distance.put(neighbor, newCost);
            neighbor.setF(newCost + getH(neighbor, goal));
            queue.add(neighbor);
            parent.put(neighbor, current);
        }
    }

    private Iterable<Node> getPath(Map<Node, Node> parent, Node goal) {
        LinkedList<Node> path = new LinkedList<>();

        if (!parent.containsKey(goal)) {
            path.addFirst(null);
            return path;
        }

        Node current = goal;

        while (current != null) {
            path.addFirst(current);
            current = parent.get(current);
        }

        return path;
    }
}
