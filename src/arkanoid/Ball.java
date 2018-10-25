package arkanoid;

import biuoop.DrawSurface;
import core.Sprite;
import core.Velocity;
import geometry.Line;
import geometry.Point;
import geometry.Rectangle;

/**
 * this class define the object Ball and his methods.
 */

public class Ball implements Sprite {
    private Point center;
    private int radius;
    private java.awt.Color color;
    private Velocity velocity;
    private Bounds bounds;
    private GameEnvironment environment;

    /**
     * contractor of new Ball (with one point).
     *
     * @param center
     *            the center point of the ball.
     * @param r
     *            the radius of the ball.
     * @param color
     *            the color of the ball.
     * @param environment
     *            the game environment.
     */
    public Ball(Point center, int r, java.awt.Color color, GameEnvironment environment) {
        if (r <= 0) {
            System.out.println("Wrong radius!");
            System.exit(1);
        }
        this.center = new Point(center.getX(), center.getY());
        radius = r;
        this.color = color;
        this.environment = environment;
    }

    /**
     * this method is contractor of new Ball (with 2 values).
     *
     * @param x1
     *            the x value of the point.
     * @param y1
     *            the y value of the point.
     * @param r
     *            the radius of the ball.
     * @param color
     *            the color of the ball.
     */

    public Ball(int x1, int y1, int r, java.awt.Color color) {
        this.center = new Point(x1, y1);
        radius = r;
        this.color = color;
    }

    /**
     * this method return the x value of the center point.
     *
     * @return int the x value of the center point.
     */

    public int getX() {
        return (int) this.center.getX();
    }

    /**
     * this method return the y value of the center point.
     *
     * @return int the y value of the center point.
     */

    public int getY() {
        return (int) this.center.getY();
    }

    /**
     * this method return the size of the ball.
     *
     * @return int the radius of the ball.
     */

    public int getSize() {
        return this.radius;
    }

    /**
     * this method return the color of the ball.
     *
     * @return color the color of the ball.
     */

    public java.awt.Color getColor() {
        return this.color;
    }

    /**
     * this method draw the ball on the draw surface.
     *
     * @param surface
     *            the surface of the draw.
     */

    public void drawOn(DrawSurface surface) {
        surface.setColor(this.color);
        surface.fillCircle(this.getX(), this.getY(), this.getSize());
        surface.setColor(java.awt.Color.black);
        surface.drawCircle(this.getX(), this.getY(), this.getSize());
    }

    /**
     * this method set the velocity of the ball (with velocity).
     *
     * @param v
     *            the required velocity of the ball.
     */

    public void setVelocity(Velocity v) {
        this.velocity = new Velocity(v.getDx(), v.getDy());
    }

    /**
     * this method set the velocity of the ball (with dx and dy).
     *
     * @param dx
     *            the difference in the x.
     * @param dy
     *            the difference in the y.
     */

    public void setVelocity(double dx, double dy) {
        this.velocity = new Velocity(dx, dy);
    }

    /**
     * this method return the velocity of the ball.
     *
     * @return Velocity the velocity of the ball.
     */

    public Velocity getVelocity() {
        return this.velocity;
    }

    /**
     * this method set bounds the ball can not leave.
     *
     * @param p1
     *            first point of the bound.
     * @param p2
     *            second point of the bound.
     */

    public void setBounds(Point p1, Point p2) {
        this.bounds = new Bounds(p1, p2);
    }

    /**
     * this method set bound the ball can not leave.
     *
     * @param x1
     *            one of the x bounds.
     * @param y1
     *            one of the y bounds.
     * @param x2
     *            one of the x bounds.
     * @param y2
     *            one of the y bounds.
     */

    public void setBounds(double x1, double y1, double x2, double y2) {
        this.bounds = new Bounds(x1, y1, x2, y2);
    }

    /**
     * this method return the bounds of the ball.
     *
     * @return Bounds the bounds of the ball.
     */

    public Bounds getBounds() {
        return this.bounds;
    }

    /**
     * getEnvironment this method return the environment.
     *
     * @return Bounds the bounds of the ball.
     */
    public GameEnvironment getEnvironment() {
        return environment;
    }

    /**
     * setEnvironment. this method set the environment.
     *
     * @param en
     *            where to create the new environment.
     */
    public void setEnvironment(GameEnvironment en) {
        this.environment = new GameEnvironment(en.getCollidables(), en.getCollision(), en.getWidth(), en.getHeight());
    }

    /**
     * this method moving the ball one step a head.
     *
     * @param dt
     *            the dt.
     */
    public void moveOneStep(double dt) {
        Velocity v = new Velocity(this.velocity.getDx() * dt, this.velocity.getDy() * dt);
        this.environment.setCollision(this.environment.getClosestCollision(
                new Line(this.center, new Point(this.center.getX() + v.getDx(), this.center.getY() + v.getDy()))));

        if (this.environment.getCollision() != null) {
            Rectangle r = this.environment.getCollision().collisionObject().getCollisionRectangle();
            Point p = this.environment.getCollision().collisionPoint();

            if (r.getLeft().pointOnLine(p)) {
                this.center = new Point(p.getX() - this.radius, p.getY());
            } else if (r.getTop().pointOnLine(p)) {
                this.center = new Point(p.getX(), p.getY() - this.radius);
            } else if (r.getRigth().pointOnLine(p)) {
                this.center = new Point(p.getX() + this.radius, p.getY());
            } else if (r.getBottom().pointOnLine(p)) {
                this.center = new Point(p.getX(), p.getY() + this.radius);
            }
            this.velocity = this.environment.getCollision().collisionObject().hit(this,
                    this.environment.getCollision().collisionPoint(), this.getVelocity());
        } else {
            this.center = v.applyToPoint(this.center);
        }

    }

    /**
     * TimePassed. Implementation of Sprite interface method. Calls to
     * moveOneStep.
     *
     * @param dt
     *            the dt.
     */
    public void timePassed(double dt) {
        this.moveOneStep(dt);
    }

    /**
     * addToGame. add the ball to the game.
     *
     * @param g
     *            the game.
     */
    public void addToGame(GameLevel g) {
        g.addSprite(this);
    }

}
