package levels;

import java.awt.Color;

import biuoop.DrawSurface;
import core.Sprite;

/**
 * WideEasyBG Class.
 *
 * @author Alihom
 *
 */
public class WideEasyBG implements Sprite {
    /**
     * draw the sprite to the screen.
     *
     * @param d
     *            surface.
     */
    public void drawOn(DrawSurface d) {
        d.setColor(java.awt.Color.white);
        d.fillRectangle(20, 20, 760, 580);
        d.setColor(new Color(247, 247, 150));
        for (int i = 0; i < 100; i++) {
            d.drawLine(150, 150, 0 + (i * 7), 240);
        }
        d.fillCircle(150, 150, 60);
        d.setColor(new Color(242, 242, 79));
        d.fillCircle(150, 150, 48);
        d.setColor(java.awt.Color.yellow);
        d.fillCircle(150, 150, 36);
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
