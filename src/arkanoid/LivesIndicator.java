package arkanoid;

import biuoop.DrawSurface;
import core.Counter;
import core.Sprite;

/**
 * LivesIndicator Class.
 *
 * @author Alihom
 *
 */
public class LivesIndicator implements Sprite {
    private Counter lives;

    /**
     * LiveIndicator Constructor.
     *
     * @param l
     *            counter of the lives.
     */
    public LivesIndicator(Counter l) {
        this.lives = l;
    }

    /**
     * draw the sprite to the screen.
     *
     * @param d
     *            surface.
     */
    public void drawOn(DrawSurface d) {
        d.setColor(java.awt.Color.blue);
        d.drawText(100, 18, "Lives: " + lives.getValue(), 20);
    }

    /**
     * notify the sprite that time has passed.
     *
     * @param dt
     *            the dt.
     */
    public void timePassed(double dt) {

    }

    /**
     * addToGame, add the indicator to the game.
     *
     * @param g
     *            the game.
     */
    public void addToGame(GameLevel g) {
        g.addSprite(this);
    }
}
