package arkanoid;

import biuoop.DrawSurface;
import core.Counter;
import core.Sprite;

/**
 * ScoreIndicator Class.
 *
 * @author Alihom
 *
 */
public class ScoreIndicator implements Sprite {
    private Counter score;

    /**
     * ScoreIndicator Constructor.
     *
     * @param s
     *            the counter of the score.
     */
    public ScoreIndicator(Counter s) {
        this.score = s;
    }

    /**
     * draw the sprite to the screen.
     *
     * @param d
     *            surface.
     */
    public void drawOn(DrawSurface d) {
        d.setColor(java.awt.Color.blue);
        d.drawText(220, 18, "Score: " + score.getValue(), 20);
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
     * addToGame, add the indicator the the game.
     *
     * @param g
     *            the game.
     */
    public void addToGame(GameLevel g) {
        g.addSprite(this);
    }
}
