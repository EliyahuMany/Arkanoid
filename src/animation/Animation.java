package animation;

import biuoop.DrawSurface;

/**
 * Animation interface.
 *
 * @author Alihom
 *
 */
public interface Animation {
    /**
     * doOneFrame, running one frame of the animation.
     *
     * @param d
     *            the drawsurface.
     * @param dt
     *            time diffrent.
     */
    void doOneFrame(DrawSurface d, double dt);

    /**
     * should stop method.
     *
     * @return true if the function should stop and false otherwise.
     */
    boolean shouldStop();
}
