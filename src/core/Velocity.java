package core;

import geometry.Point;

/**
 * this class define the object Velocity and his methods.
 */

public class Velocity {
    private double dx;
    private double dy;

    /**
     * this method is contractor of new velocity.
     *
     * @param dx
     *            the difference in the x.
     * @param dy
     *            the difference in the y.
     */
    public Velocity(double dx, double dy) {
        this.dx = dx;
        this.dy = dy;
    }

    /**
     * Velocity Constructor.
     *
     * @param angle
     *            the angle of the velocity.
     * @param speed
     *            the speed of the velocity.
     * @param stat
     *            the constructor with the angle.
     */
    public Velocity(double angle, double speed, double stat) {
        this.dx = speed * Math.cos(Math.toRadians(angle - 90));
        this.dy = speed * Math.sin(Math.toRadians(angle - 90));
    }

    /**
     * this method move the point to the new point.
     *
     * @param p
     *            the old point.
     *
     * @return Point the new point.
     */

    public Point applyToPoint(Point p) {
        return new Point(p.getX() + dx, p.getY() + dy);
    }

    /**
     * this method return the value of the dx.
     *
     * @return dx the difference in the x.
     */

    public double getDx() {
        return this.dx;
    }

    /**
     * this method return the value of the dy.
     *
     * @return dy the difference in the y.
     */

    public double getDy() {
        return this.dy;
    }

    /**
     * this method set the velocity of the ball by speed and angle.
     *
     * @param angle
     *            the angle of the movement.
     * @param speed
     *            the speed of the movement.
     *
     * @return Velocity the velocity of the ball.
     */

    public static Velocity fromAngleAndSpeed(double angle, double speed) {
        double dx = speed * Math.cos(Math.toRadians(angle - 90));
        double dy = speed * Math.sin(Math.toRadians(angle - 90));
        return new Velocity(dx, dy);
    }
}
