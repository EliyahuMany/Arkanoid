package arkanoid;

import core.Counter;
import core.HitListener;

/**
 * ScoreTrackingListener Class.
 *
 * @author Alihom
 *
 */
public class ScoreTrackingListener implements HitListener {
    private Counter currentScore;

    /**
     * ScoreTrackingListener Constructor.
     *
     * @param scoreCounter
     *            the score counter.
     */
    public ScoreTrackingListener(Counter scoreCounter) {
        this.currentScore = scoreCounter;
    }

    /**
     * getCurrentScore, return the score counter.
     *
     * @return Counter the score counter.
     */
    public Counter getCurrentScore() {
        return currentScore;
    }

    /**
     * This method is called whenever the beingHit object is hit. The hitter
     * parameter is the Ball that's doing the hitting.
     *
     * @param beingHit
     *            the block being hit.
     * @param hitter
     *            the hitter ball.
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        this.currentScore.increase(5);
        if (beingHit.getCount().getValue() == 0) {
            this.currentScore.increase(10);
        }
    }
}