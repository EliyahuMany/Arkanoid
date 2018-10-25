package arkanoid;

import animation.Animation;
import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import core.Counter;

/**
 * EndGame Class.
 *
 * @author Alihom
 *
 */
public class EndGame implements Animation {
    private KeyboardSensor keyboard;
    private boolean stop;
    private Counter score;
    private boolean win;

    /**
     * EndGame Constructor.
     *
     * @param k
     *            the keyboard sensor.
     * @param score
     *            the score counter.
     * @param win
     *            true if we still can win and false otherwise.
     */
    public EndGame(KeyboardSensor k, Counter score, boolean win) {
        this.keyboard = k;
        this.stop = false;
        this.score = score;
        this.win = win;
    }

    /**
     * doOneFrame, running one frame of the animation.
     *
     * @param d
     *            the drawsurface.
     * @param dt
     *            the dt.
     */
    public void doOneFrame(DrawSurface d, double dt) {
        d.setColor(java.awt.Color.blue);
        d.fillRectangle(0, 0, 800, 600);
        d.setColor(java.awt.Color.white);
        d.fillRectangle(200, 350, 400, 100);
        d.setColor(java.awt.Color.black);
        d.drawText(280, 435, "" + this.score.getValue(), 100);
        d.setColor(java.awt.Color.red);
        if (this.win) {
            d.drawText(200, 200, "You Win!", 100);
            d.drawText(250, 300, "Your score is", 50);
        } else {
            d.drawText(140, 200, "Game Over!", 100);
            d.drawText(250, 300, "Your score is", 50);
        }
    }

    /**
     * should stop method.
     *
     * @return true if the function should stop and false otherwise.
     */
    public boolean shouldStop() {
        return this.stop;
    }
}