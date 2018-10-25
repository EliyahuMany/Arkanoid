package arkanoid;

import animation.Animation;
import biuoop.DrawSurface;
import biuoop.Sleeper;

/**
 * The CountdownAnimation will display the given gameScreen, for numOfSeconds
 * seconds, and on top of them it will show a count down from countFrom back to
 * 1, where each number will appear on the screen for (numOfSeconds / countFrom)
 * seconds, before it is replaced with the next one.
 *
 * @author Alihom
 *
 */
public class CountdownAnimation implements Animation {
    private double numOfSeconds;
    private int countFrom;
    private SpriteCollection gameScreen;
    private boolean stop;

    /**
     * CountdownAnimation Constructor.
     *
     * @param numOfSeconds
     *            the number of seconds.
     * @param countFrom
     *            the number to count down from.
     * @param gameScreen
     *            the sprite of the game screen.
     */
    public CountdownAnimation(double numOfSeconds, int countFrom, SpriteCollection gameScreen) {
        this.numOfSeconds = numOfSeconds;
        this.countFrom = countFrom;
        this.gameScreen = gameScreen;
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
        this.gameScreen.drawAllOn(d);
        Sleeper sleeper = new Sleeper();
        int millisecondsPerFrame = 1000 / (int) numOfSeconds;
        if (this.countFrom >= 0) {
            if (this.countFrom == 0) {
                d.setColor(java.awt.Color.cyan);
                d.drawText(d.getWidth() / 2, d.getHeight() / 2, "Go!", 53);
                d.setColor(java.awt.Color.blue);
                d.drawText(d.getWidth() / 2, d.getHeight() / 2, "Go!", 50);
            } else if (this.countFrom > 0) {
                d.setColor(java.awt.Color.cyan);
                d.drawText(d.getWidth() / 2, d.getHeight() / 2, "" + countFrom, 53);
                d.setColor(java.awt.Color.blue);
                d.drawText(d.getWidth() / 2, d.getHeight() / 2, "" + countFrom, 50);
            }
            this.countFrom--;
        } else {
            this.stop = true;
        }
        sleeper.sleepFor(millisecondsPerFrame);
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
