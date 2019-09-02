public class Point2D implements Comparable<Point2D> {

    private int X;
    private int Y;

    public Point2D(int x, int y) {
        this.setX(x);
        this.setY(y);
    }

    public int getX() {
        return this.X;
    }

    public void setX(int x) {
        X = x;
    }

    public int getY() {
        return this.Y;
    }

    public void setY(int y) {
        Y = y;
    }

    @Override
    public int compareTo(Point2D that) {
        if (this.Y < that.Y) return -1;
        if (this.Y > that.Y) return +1;
        if (this.X < that.X) return -1;
        if (this.X > that.X) return +1;

        return 0;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }

        if (obj == null) {
            return false;
        }

        Point2D that = (Point2D) obj;
        return this.X == that.X && this.Y == that.Y;
    }

    @Override
    public int hashCode() {
        int hashX = Double.valueOf(this.X).hashCode();
        int hashY = Double.valueOf(this.Y).hashCode();
        return 31 * hashX + hashY;
    }

    public boolean isInside(GalaxyArea area) {
        return (this.X >= area.getX1() && this.X <= area.getX2()) && (this.Y >= area.getY1() && this.Y <= area.getY2());
    }

    @Override
    public String toString() {
        return String.format("(%d, %d)", this.X, this.Y);
    }
}
