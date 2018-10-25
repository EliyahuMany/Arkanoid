package arkanoid;

import animation.Animation;
import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

/**
 * PauseScreen Class.
 *
 * @author Alihom
 *
 */
public class PauseScreen implements Animation {
    private KeyboardSensor keyboard;
    private boolean stop;

    /**
     * PauseScreen Constructor.
     *
     * @param k
     *            the keyboard sensor.
     */
    public PauseScreen(KeyboardSensor k) {
        this.keyboard = k;
        this.stop = false;
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
        d.drawText(200, d.getHeight() / 2, "paused -- press space to continue", 32);
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