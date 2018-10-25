package arkanoid;
import java.util.ArrayList;
import java.util.List;

import core.Collidable;
import core.CollisionInfo;
import geometry.Line;

/**
 * GameEnvironment class. Holds all the collidables objects in game and can give
 * the closest collision point with a line.
 */
public class GameEnvironment {
    private List<Collidable> collidables;
    private CollisionInfo collision;
    private int width = 1400;
    private int height = 1000;

    /**
     * getCollidables.
     *
     * @return List of Collidable objects.
     */
    public List<Collidable> getCollidables() {
        return this.collidables;
    }

    /**
     * getCollision.
     *
     * @return CollisionInfo.
     */
    public CollisionInfo getCollision() {
        return this.collision;
    }

    /**
     * setCollision.this method set the Collision.
     *
     * @param c
     *            collision info.
     */
    public void setCollision(CollisionInfo c) {
        this.collision = c;
    }

    /**
     * Constructor for GameEnvironment.
     */
    public GameEnvironment() {
        this.collidables = new ArrayList<Collidable>();
    }

    /**
     * Constructor for GameEnvironment.
     *
     * @param collidables
     *            list of collidables.
     * @param collision
     *            collision point.
     * @param width
     *            the width.
     * @param height
     *            the height.
     */
    public GameEnvironment(List<Collidable> collidables, CollisionInfo collision, int width, int height) {
        this.collidables = collidables;
        this.collision = collision;
        this.width = width;
        this.height = height;
    }

    /**
     * getWidth.
     *
     * @return int Width.
     */
    public int getWidth() {
        return width;
    }

    /**
     * getHeight.
     *
     * @return int Height.
     */
    public int getHeight() {
        return height;
    }

    /**
     * add the given collidable to the environment.
     *
     * @param c
     *            collidable object.
     */
    public void addCollidable(Collidable c) {
        collidables.add(c);
    }

    /**
     * remove the given collidable to the environment.
     *
     * @param c
     *            collidable object.
     */
    public void removeCollidable(Collidable c) {
        collidables.remove(c);
    }

    /**
     * this method line and check who is the closest point the line intersection
     * with.
     *
     * @param trajectory
     *            the line to check.
     * @return CollisionInfo.
     */
    public CollisionInfo getClosestCollision(Line trajectory) {
        int index = -1;

        for (int i = 0; i < collidables.size(); i++) {
            if (trajectory.closestIntersectionToStartOfLine(collidables.get(i).getCollisionRectangle()) != null) {
                if ((index == -1) || (trajectory.start()
                        .distance(trajectory.closestIntersectionToStartOfLine(
                                collidables.get(i).getCollisionRectangle())) < trajectory.start()
                                        .distance(trajectory.closestIntersectionToStartOfLine(
                                                collidables.get(index).getCollisionRectangle())))) {
                    index = i;
                }
            }
        }
        if (index != -1) {
            return new CollisionInfo(
                    trajectory.closestIntersectionToStartOfLine(collidables.get(index).getCollisionRectangle()),
                    collidables.get(index));
        } else {
            return null;
        }
    }
}