package geometry;
import java.util.List;
import java.util.ArrayList;

/**
 * Class for Rectangle.
 */
public class Rectangle {
    private Point upperLeft;
    private double width;
    private double height;
    private Line top;
    private Line rigth;
    private Line bottom;
    private Line left;

    /**
     * Constructor of the rectangle.
     *
     * @param upperLeft
     *            upper left point.
     * @param width
     *            width.
     * @param height
     *            height.
     */
    public Rectangle(Point upperLeft, double width, double height) {
        this.upperLeft = new Point(upperLeft.getX(), upperLeft.getY());
        this.width = width;
        this.height = height;
        this.top = new Line(this.upperLeft, new Point(upperLeft.getX() + this.getWidth(), upperLeft.getY()));
        this.rigth = new Line(new Point(upperLeft.getX() + this.getWidth(), upperLeft.getY()),
                new Point(upperLeft.getX() + this.getWidth(), upperLeft.getY() + this.getHeight()));
        this.bottom = new Line(new Point(upperLeft.getX() + this.getWidth(), upperLeft.getY() + this.getHeight()),
                new Point(upperLeft.getX(), upperLeft.getY() + this.getHeight()));
        this.left = new Line(new Point(upperLeft.getX(), upperLeft.getY() + this.getHeight()), this.upperLeft);
    }

    /**
     * intersectionPoints. Return a (possibly empty) List of intersection points
     * with the specified line.
     *
     * @param line
     *            to check if intersect with this rectangle.
     * @return list of intersection points.
     */
    public List<Point> intersectionPoints(Line line) {
        List<Point> points = new ArrayList<Point>();

        Point t1 = line.intersectionWith(this.top);
        Point t2 = line.intersectionWith(this.rigth);
        Point t3 = line.intersectionWith(this.bottom);
        Point t4 = line.intersectionWith(this.left);

        if (line.isIntersecting(this.top)) {
            points.add(line.intersectionWith(this.top));
        }
        if (line.isIntersecting(this.rigth)) {
            points.add(line.intersectionWith(this.rigth));
        }
        if (line.isIntersecting(this.bottom)) {
            points.add(line.intersectionWith(this.bottom));
        }
        if (line.isIntersecting(this.left)) {
            points.add(line.intersectionWith(this.left));
        }
        if (points.isEmpty()) {
            return null;
        } else {
            return points;
        }
    }

    /**
     * getWidth.
     *
     * @return rectangle's width.
     */
    public double getWidth() {
        return this.width;
    }

    /**
     * getHeight.
     *
     * @return rectangle's height.
     */
    public double getHeight() {
        return this.height;
    }

    /**
     * getUpperLeft.
     *
     * @return upper left point of rectangle.
     */
    public Point getUpperLeft() {
        return this.upperLeft;
    }

    /**
     * getTop.
     *
     * @return the top line of the rectangle.
     */
    public Line getTop() {
        return top;
    }

    /**
     * getRigth.
     *
     * @return the right line of the rectangle.
     */
    public Line getRigth() {
        return rigth;
    }

    /**
     * getBottom.
     *
     * @return the bottom line of the rectangle.
     */
    public Line getBottom() {
        return bottom;
    }

    /**
     * getLeft.
     *
     * @return the left line of the rectangle.
     */
    public Line getLeft() {
        return left;
    }
}
