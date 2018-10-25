package arkanoid;
import geometry.Point;

/**
 * this class define the object Boubds and his methods are made
 * for the ball.
 */

public class Bounds {
    private double lowerX;
    private double higerX;
    private double lowerY;
    private double higerY;

    /**
     * this method define the bounds of the ball.
     *
     * @param x1 one of the x bounds.
     * @param y1 one of the y bounds.
     * @param x2 one of the x bounds.
     * @param y2 one of the y bounds.
     */
    public Bounds(double x1, double y1, double x2, double y2) {
        if (x1 < x2) {
            this.lowerX = x1;
            this.higerX = x2;
        } else {
            this.lowerX = x2;
            this.higerX = x1;
        }

        if (y1 < y2) {
            this.lowerY = y1;
            this.higerY = y2;
        } else {
            this.lowerY = y2;
            this.higerY = y1;
        }
    }
    /**
     * this method define the bounds of the ball.
     *
     * @param p1 one of the points bounds.
     * @param p2 one of the points bounds.
     */

    public Bounds(Point p1, Point p2) {
        if (p1.getX() < p2.getX()) {
            this.lowerX = p1.getX();
            this.higerX = p2.getX();
        } else {
            this.lowerX = p2.getX();
            this.higerX = p1.getX();
        }

        if (p1.getY() < p2.getY()) {
            this.lowerY = p1.getY();
            this.higerY = p2.getY();
        } else {
            this.lowerY = p2.getY();
            this.higerY = p1.getY();
        }
    }
    /**
     * this method return the lower x bound.
     *
     * @return double lower x bound.
     */

    public double getLowerX() {
        return this.lowerX;
    }
    /**
     * this method return the lower y bound.
     *
     * @return double lower y bound.
     */

    public double getLowerY() {
        return this.lowerY;
    }
    /**
     * this method return the higher x bound.
     *
     * @return double higher x bound.
     */

    public double getHiggerX() {
        return this.higerX;
    }

    /**
     * this method return the higher y bound.
     *
     * @return double higher y bound.
     */
    public double getHiggerY() {
        return this.higerY;
    }

}
