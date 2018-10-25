package levels;

import java.awt.Color;

import biuoop.DrawSurface;
import core.Sprite;

/**
 * Green3BG Class.
 *
 * @author Alihom
 *
 */
public class Green3BG implements Sprite {
    /**
     * draw the sprite to the screen.
     *
     * @param d
     *            surface.
     */
    public void drawOn(DrawSurface d) {
        d.setColor(new Color(101, 175, 3));
        d.fillRectangle(20, 20, 760, 580);
        d.setColor(java.awt.Color.black);
        d.fillRectangle(70, 420, 100, 180);
        d.setColor(java.awt.Color.white);
        for (int j = 0; j < 5; j++) {
            for (int i = 0; i < 5; i++) {
                d.fillRectangle((int) (80 + 17 * i), 430 + 36 * j, 13, 30);
            }
        }
        d.setColor(java.awt.Color.darkGray);
        d.fillRectangle(110, 360, 20, 60);
        d.setColor(java.awt.Color.gray);
        d.fillRectangle(115, 170, 10, 190);
        d.setColor(java.awt.Color.yellow);
        d.fillCircle(120, 155, 15);
        d.setColor(java.awt.Color.orange);
        d.fillCircle(120, 155, 10);
        d.setColor(java.awt.Color.white);
        d.fillCircle(120, 155, 5);
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
