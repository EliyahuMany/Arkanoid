package levels;

import java.awt.Color;

import biuoop.DrawSurface;
import core.Sprite;

/**
 * FinaFourBG Class.
 *
 * @author Alihom
 *
 */
public class FinalFourBG implements Sprite {
    /**
     * draw the sprite to the screen.
     *
     * @param d
     *            surface.
     */
    public void drawOn(DrawSurface d) {
        d.setColor(new Color(114, 217, 255));
        d.fillRectangle(20, 20, 760, 580);
        d.setColor(new Color(224, 246, 255));
        for (int i = 0; i < 10; i++) {
            d.drawLine(90 + 6 * i, 420, 70 + 6 * i, 600);
            d.drawLine(620 + 6 * i, 480 + 2 * i, 590 + 6 * i, 600);
        }
        d.setColor(new Color(226, 226, 226));
        d.fillCircle(100, 400, 20);
        d.fillCircle(110, 420, 20);
        d.fillCircle(630, 480, 20);
        d.fillCircle(640, 500, 20);
        d.setColor(new Color(209, 209, 209));
        d.fillCircle(650, 485, 20);
        d.fillCircle(120, 395, 20);
        d.setColor(new Color(193, 193, 193));
        d.fillCircle(670, 495, 20);
        d.fillCircle(657, 513, 13);
        d.fillCircle(145, 400, 20);
        d.fillCircle(133, 420, 13);
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
