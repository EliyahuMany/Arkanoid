package arkanoid;

import animation.Animation;
import animation.AnimationRunner;

/**
 * show hiScore class.
 *
 * @author Alihom
 *
 */
public class ShowHiScoresTask implements Task<Void> {
    private AnimationRunner runner;
    private Animation highScoresAnimation;

    /**
     * score high score constructor.
     *
     * @param runner
     *            the animation runner.
     * @param highScoresAnimation
     *            the animation.
     */
    public ShowHiScoresTask(AnimationRunner runner, Animation highScoresAnimation) {
        this.runner = runner;
        this.highScoresAnimation = highScoresAnimation;
    }

    /**
     * run method.
     *
     * @return return null.
     */
    public Void run() {
        this.runner.run(this.highScoresAnimation);
        return null;
    }
}