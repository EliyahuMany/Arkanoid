package core;
import geometry.Point;

/**
 * Collision info. Holds the information about the collision, what kind of
 * object and collision point.
 */
public class CollisionInfo {
    private Point collisionPoint;
    private Collidable collisionObject;

    /**
     * CollisionInfo constructor.
     *
     * @param p
     *            collision point.
     * @param c
     *            collidable object.
     */
    public CollisionInfo(Point p, Collidable c) {
        this.collisionPoint = new Point(p.getX(), p.getY());
        this.collisionObject = c;
    }

    /**
     * collisionPoint.
     *
     * @return Point at which the collision occurs.
     */
    public Point collisionPoint() {
        return this.collisionPoint;
    }

    /**
     * collisionObject.
     *
     * @return Collidable object involved in the collision.
     */
    public Collidable collisionObject() {
        return this.collisionObject;
    }

}
