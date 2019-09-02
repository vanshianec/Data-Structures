public class GalaxyArea {

    private int x1;
    private int y1;
    private int x2;
    private int y2;

    public GalaxyArea(int x1, int y1, int width, int height) {
        this.x1 = x1;
        this.y1 = y1;
        setX2(width);
        setY2(height);
    }

    public int getX1() {
        return x1;
    }

    public void setX1(int x1) {
        this.x1 = x1;
    }

    public int getY1() {
        return y1;
    }

    public void setY1(int y1) {
        this.y1 = y1;
    }

    public int getX2() {
        return x2;
    }

    public void setX2(int width) {
        this.x2 = this.x1 + width;
    }

    public int getY2() {
        return y2;
    }

    public void setY2(int height) {
        this.y2 = this.y1 + height;
    }

    public boolean intersects(GalaxyArea other) {
        return this.getX1() <= other.getX2()
                && this.getX2() >= other.getX1()
                && this.getY1() <= other.getY2()
                && this.getY2() >= other.getY1();
    }

    @Override
    public String toString() {
        return "GalaxyArea{" +
                "x1=" + x1 +
                ", y1=" + y1 +
                ", x2=" + x2 +
                ", y2=" + y2 +
                '}';
    }
}
