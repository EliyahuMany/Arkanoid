package geometry;

import java.util.List;

/**
 * this class define the object Line and his methods.
 */

public class Line {
    private Point start;
    private Point end;

    /**
     * this method is contractor for new line (with 2 points).
     *
     * @param start
     *            the first point of the line.
     * @param end
     *            the second point of the line.
     */
    public Line(Point start, Point end) {
        this.start = new Point(start.getX(), start.getY());
        this.end = new Point(end.getX(), end.getY());
    }

    /**
     * this method is contractor for new line (with 4 values).
     *
     * @param x1
     *            the first point value of x.
     * @param y1
     *            the first point value of y.
     * @param x2
     *            the second point value of x.
     * @param y2
     *            the second point value of y.
     */

    public Line(double x1, double y1, double x2, double y2) {
        this.start = new Point(x1, y1);
        this.end = new Point(x2, y2);
    }

    /**
     * this method return the length of two points.
     *
     * @return double length of the line.
     */

    public double length() {
        return this.start.distance(this.end);
    }

    /**
     * this method calculate the middle point of the line.
     *
     * @return Point of the middle.
     */

    public Point middle() {
        return new Point(((start.getX() + end.getX()) / 2), ((start.getY() + end.getY()) / 2));
    }

    /**
     * this method return the start point of the line.
     *
     * @return Point the start point.
     */

    public Point start() {
        return this.start;
    }

    /**
     * this method return the end point of the line.
     *
     * @return Point the end point.
     */

    public Point end() {
        return this.end;
    }

    /**
     * this method calculate the slope of the line.
     *
     * @param first
     *            the first point of the line.
     * @param second
     *            the second point of the line.
     *
     * @return Double slope of the line.
     */

    public Double slope(Point first, Point second) {
        return (second.getY() - first.getY()) / (second.getX() - first.getX());
    }

    /**
     * this method calculate the value of b.
     *
     * @param p
     *            one point of the line.
     * @param slope
     *            the slope of the line.
     *
     * @return double value of b.
     */

    public double bVal(Point p, double slope) {
        return p.getY() - slope * p.getX();
    }

    /**
     * this method check if two line are intersecting.
     *
     * @param other
     *            the second line.
     *
     * @return boolean true if they intersecting and false otherwise.
     */

    public boolean isIntersecting(Line other) {
        return this.intersectionWith(other) != null;
    }

    /**
     * this method return the point of the intersection between the two lines.
     *
     * @param other
     *            the second line.
     *
     * @return Point the intersection point.
     */

    public Point intersectionWith(Line other) {
        double m1, m2, b1, b2, x, y;

        m1 = slope(this.start, this.end);
        m2 = slope(other.start, other.end);
        b1 = bVal(this.start, m1);
        b2 = bVal(other.start, m2);
        x = (b2 - b1) / (m1 - m2);
        y = m1 * x + b1;

        if (m1 == m2) {
            return null;
        } else if ((m1 == Double.POSITIVE_INFINITY || m1 == Double.NEGATIVE_INFINITY)
                && (this.start.getX() <= Math.max(other.start.getX(), other.end.getX())
                        && this.start.getX() >= Math.min(other.start.getX(), other.end.getX()))
                && ((m2 * this.start.getX() + b2) <= Math.max(this.start.getY(), this.end.getY()))
                && ((m2 * this.start.getX() + b2) >= Math.min(this.start.getY(), this.end.getY()))) {
            x = this.start.getX();
            y = m2 * x + b2;
            return new Point(x, y);
        } else if ((m2 == Double.POSITIVE_INFINITY || m2 == Double.NEGATIVE_INFINITY)
                && (other.start.getX() <= Math.max(this.start.getX(), this.end.getX())
                        && (other.start.getX() >= Math.min(this.start.getX(), this.end.getX())))
                && ((m1 * other.start.getX() + b1) <= Math.max(other.start.getY(), other.end.getY()))
                && ((m1 * other.start.getX() + b1) >= Math.min(other.start.getY(), other.end.getY()))) {
            x = other.start.getX();
            y = m1 * x + b1;
            return new Point(x, y);
        } else if (m1 == 0 && this.start.getY() <= Math.max(other.start.getY(), other.end.getY())
                && this.start.getY() >= Math.min(other.start.getY(), other.end.getY())
                && ((this.start.getY() - b2) / m2) <= Math.max(this.start.getX(), this.end.getX())
                && ((this.start.getY() - b2) / m2) >= Math.min(this.start.getX(), this.end.getX())) {
            x = ((this.start.getY() - b2) / m2);
            y = this.start.getY();
            return new Point(x, y);
        } else if (m2 == 0 && other.start.getY() <= Math.max(this.start.getY(), this.end.getY())
                && other.start.getY() >= Math.min(this.start.getY(), this.end.getY())
                && ((other.start.getY() - b1) / m1) <= Math.max(other.start.getX(), other.end.getX())
                && ((other.start.getY() - b1) / m1) >= Math.min(other.start.getX(), other.end.getX())) {
            x = ((other.start.getY() - b1) / m1);
            y = other.start.getY();
            return new Point(x, y);
        } else if (x <= Math.max(this.start.getX(), this.end.getX())
                && x <= Math.max(other.start.getX(), other.end.getX())
                && x >= Math.min(this.start.getX(), this.end.getX())
                && x >= Math.min(other.start.getX(), other.end.getX())
                && y <= Math.max(this.start.getY(), this.end.getY())
                && y <= Math.max(other.start.getY(), other.end.getY())
                && y >= Math.min(this.start.getY(), this.end.getY())
                && y >= Math.min(other.start.getY(), other.end.getY())) {
            return new Point(x, y);
        } else {
            return null;
        }
    }

    /**
     * this method check if two line are equals.
     *
     * @param other
     *            the second line.
     *
     * @return boolean true if the lines are equals and false otherwise.
     */

    public boolean equals(Line other) {
        return ((this.start.getX() == other.start.getX() && this.end.getX() == other.end.getX()
                && this.start.getY() == other.start.getY() && this.end.getY() == this.end.getY())
                || (this.start.getX() == other.end.getX() && this.end.getX() == other.start.getX()
                        && this.start.getY() == other.end.getY() && this.end.getY() == this.start.getY()));
    }

    /**
     * this method check all the collision points and return the closest one to
     * the start point.
     *
     * @param rect
     *            the rectangle.
     *
     * @return Point of the closest intersection.
     */

    public Point closestIntersectionToStartOfLine(Rectangle rect) {
        List<Point> points = rect.intersectionPoints(this);
        int index = 0;

        if (rect.intersectionPoints(this) == null) {
            return null;
        } else {

            for (int i = 0; i < points.size(); i++) {
                if (this.start.distance((Point) points.get(i)) < this.start.distance((Point) points.get(index))) {
                    index = i;
                }
            }
            return new Point(points.get(index).getX(), points.get(index).getY());
        }
    }

    /**
     * this method check if two line are equals.
     *
     * @param p
     *            the point.
     *
     * @return boolean true if the point on the line and false otherwise.
     */

    public boolean pointOnLine(Point p) {
        return (p.getX() <= Math.max(this.start.getX(), this.end.getX())
                && p.getX() >= Math.min(this.start.getX(), this.end.getX())
                && p.getY() <= Math.max(this.start.getY(), this.end.getY())
                && p.getY() >= Math.min(this.start.getY(), this.end.getY()));
    }
}