package arkanoid;

import core.Counter;
import core.HitListener;

/**
 * BallRemover class.
 *
 * @author Alihom
 *
 */
public class BallRemover implements HitListener {
    private GameLevel gameLevel;
    private Counter remainingBalls;

    /**
     * BallRemover Constructor.
     *
     * @param gameLevel
     *            the game level.
     * @param count
     *            the counter of the life.
     */
    public BallRemover(GameLevel gameLevel, Counter count) {
        this.gameLevel = gameLevel;
        this.remainingBalls = count;
    }

    /**
     * hit Event, when the ball hit the block this method notify that.
     *
     * @param beingHit
     *            the block have been hit.
     * @param hitter
     *            the ball that hit.
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        this.gameLevel.getBallsCounter().decrease(1);
        this.gameLevel.removeSprite(hitter);
        if (this.remainingBalls.getValue() <= 0) {
            this.gameLevel.setRunning(false);
        }
    }

}
