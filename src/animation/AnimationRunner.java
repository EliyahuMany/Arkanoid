package animation;

import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;

/**
 * AnimationRunner class.
 *
 * @author Alihom
 *
 */
public class AnimationRunner {
    private GUI gui;
    private int framesPerSecond;
    private Sleeper sleeper;

    /**
     * AnimationRunner Constructor.
     *
     * @param gui
     *            the GUI.
     * @param fps
     *            the number of frames per second.
     */
    public AnimationRunner(GUI gui, int fps) {
        this.gui = gui;
        this.sleeper = new Sleeper();
        this.framesPerSecond = fps;
    }

    /**
     * run, this method run the animation.
     *
     * @param animation
     *            the animation to run.
     */
    public void run(Animation animation) {
        int millisecondsPerFrame = 1000 / this.framesPerSecond;
        while (!animation.shouldStop()) {
            long startTime = System.currentTimeMillis(); // timing

            DrawSurface d = this.gui.getDrawSurface();
            animation.doOneFrame(d, 1 / this.framesPerSecond);
            this.gui.show(d);

            // timing
            long usedTime = System.currentTimeMillis() - startTime;
            long milliSecondLeftToSleep = millisecondsPerFrame - usedTime;
            if (milliSecondLeftToSleep > 0) {
                sleeper.sleepFor(milliSecondLeftToSleep);
            }
        }
    }

    /**
     * getFramesPerSecond, this method return the fps.
     *
     * @return framesPerSecond
     */
    public int getFramesPerSecond() {
        return framesPerSecond;
    }
}
