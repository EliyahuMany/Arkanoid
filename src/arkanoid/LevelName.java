package arkanoid;

import biuoop.DrawSurface;
import core.Sprite;

/**
 * LevelName Class.
 *
 * @author Alihom
 *
 */
public class LevelName implements Sprite {
    private String str;

    /**
     * LevelName Constructor.
     *
     * @param str
     *            the name of the level.
     */
    public LevelName(String str) {
        this.str = str;
    }

    /**
     * draw the sprite to the screen.
     *
     * @param d
     *            surface.
     */
    public void drawOn(DrawSurface d) {
        d.setColor(java.awt.Color.blue);
        d.drawText(400, 18, "Level Name: " + this.str, 20);
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
     * addToGame, add the level to the game.
     *
     * @param g
     *            the game.
     */
    public void addToGame(GameLevel g) {
        g.addSprite(this);
    }
}
