package geometry;

/**
 * this class define the object Point and his methods.
 */

public class Point {
    private double x;
    private double y;

    /**
     * this method is contractor for new point.
     *
     * @param x value of x.
     * @param y value of y.
     */

    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }
    /**
     * this method calculate the distance.
     *
     * @param other the second point for the distance.
     *
     * @return double distance between two points.
     */

    public double distance(Point other) {
        return Math.sqrt(
                ((this.x) - (other.x)) * ((this.x) - (other.x)) + ((this.y) - (other.y)) * ((this.y) - (other.y)));
    }
    /**
     * this method check if the points are equals.
     *
     * @param other the second point to check if equals.
     *
     * @return boolean true if equals and false otherwise.
     */

    public boolean equals(Point other) {
        return (this.x == other.getX() && this.y == other.getY());
    }
    /**
     * this method return the x value.
     *
     * @return double value of x.
     */

    public double getX() {
        return this.x;
    }
    /**
     * this method return the y value.
     *
     * @return double value of y.
     */

    public double getY() {
        return this.y;
    }

}
