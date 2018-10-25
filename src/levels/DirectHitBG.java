package levels;

import biuoop.DrawSurface;
import core.Sprite;

/**
 * DirectHitBG Class.
 *
 * @author Alihom
 *
 */
public class DirectHitBG implements Sprite {
    /**
     * draw the sprite to the screen.
     *
     * @param d
     *            surface.
     */
    public void drawOn(DrawSurface d) {
        d.setColor(java.awt.Color.black);
        d.fillRectangle(20, 20, 760, 580);
        d.setColor(java.awt.Color.blue);
        d.drawCircle(400, 200, 50);
        d.drawCircle(400, 200, 100);
        d.drawCircle(400, 200, 150);
        d.drawLine(400, 30, 400, 400);
        d.drawLine(200, 200, 600, 200);
    }

    /**
     * notify the sprite that time has passed.
     *
     * @param dt
     *            the dt.
     */
    public void timePassed(double dt) {
    }

}
