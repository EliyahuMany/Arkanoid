package core;

import arkanoid.Ball;
import arkanoid.Block;

/**
 * HitListener interface Class.
 *
 * @author Alihom
 *
 */
public interface HitListener {
    /**
     * This method is called whenever the beingHit object is hit. The hitter
     * parameter is the Ball that's doing the hitting.
     *
     * @param beingHit
     *            the block being hit.
     * @param hitter
     *            the hitter ball.
     */
    void hitEvent(Block beingHit, Ball hitter);
}
