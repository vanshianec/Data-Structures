public class CollisionObject implements Comparable {

    private final int WIDTH = 10;
    private final int HEIGHT = 10;

    private String name;
    private int x1;
    private int y1;
    private int x2;
    private int y2;

    public CollisionObject(String name, int x1, int y1) {
        this.name = name;
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x1 + WIDTH;
        this.y2 = y1 + HEIGHT;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getX1() {
        return x1;
    }

    public void setX1(int x1) {
        this.x1 = x1;
        this.x2 = this.x1 + WIDTH;
    }

    public int getY1() {
        return y1;
    }

    public void setY1(int y1) {
        this.y1 = y1;
        this.y2 = this.y1 + HEIGHT;
    }

    public int getX2() {
        return x2;
    }

    public void setX2(int x2) {
        this.x2 = x2;
    }

    public int getY2() {
        return y2;
    }

    public void setY2(int y2) {
        this.y2 = y2;
    }


    @Override
    public int compareTo(java.lang.Object o) {
        CollisionObject that = (CollisionObject) o;
        return Integer.compare(this.x1, that.x1);
    }
}
