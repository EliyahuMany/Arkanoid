package arkanoid;

import java.util.ArrayList;
import java.util.List;

import biuoop.DrawSurface;
import core.Sprite;

/**
 * Class for SpriteCollection. Holds the all the objects who implements sprite.
 */
public class SpriteCollection {
    private List<Sprite> list;
    private double dt;

    /**
     * Constructor for SpriteCollection.
     *
     * @param dt
     *            the dt.
     */
    public SpriteCollection(double dt) {
        this.list = new ArrayList<Sprite>();
        this.dt = dt;
    }

    /**
     * add the given sprite to the list.
     *
     * @param s
     *            sprite object.
     */
    public void addSprite(Sprite s) {
        this.list.add(s);
    }

    /**
     * remove the given sprite to the list.
     *
     * @param s
     *            sprite object.
     */
    public void removeSprite(Sprite s) {
        this.list.remove(s);
    }

    /**
     * call timePassed() on all sprites.
     */
    public void notifyAllTimePassed() {
        for (int i = 0; i < list.size(); i++) {
            list.get(i).timePassed(this.dt);
        }
    }

    /**
     * call drawOn() on all sprites.
     *
     * @param d
     *            surface.
     */
    public void drawAllOn(DrawSurface d) {
        for (int i = 0; i < list.size(); i++) {
            list.get(i).drawOn(d);
        }
    }
}