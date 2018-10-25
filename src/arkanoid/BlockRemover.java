package arkanoid;

import core.Counter;
import core.HitListener;

/**
 * a BlockRemover is in charge of removing blocks from the game, as well as
 * keeping count of the number of blocks that remain.
 *
 * @author Alihom
 *
 */
public class BlockRemover implements HitListener {
    private GameLevel gameLevel;
    private Counter remainingBlocks;

    /**
     * BlockRemover Constructor.
     *
     * @param gameLevel
     *            the game level.
     * @param remainingBlocks
     *            the number of block remain to win.
     */
    public BlockRemover(GameLevel gameLevel, Counter remainingBlocks) {
        this.gameLevel = gameLevel;
        this.remainingBlocks = remainingBlocks;
    }

    // Blocks that are hit and reach 0 hit-points should be removed
    // from the game. Remember to remove this listener from the block
    // that is being removed from the game.
    /**
     * Blocks that are hit and reach 0 hit-points should be removed from the
     * game. Remember to remove this listener from the block that is being
     * removed from the game.
     *
     * @param beingHit
     *            the block being hit.
     * @param hitter
     *            the hitter ball.
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        beingHit.getCount().decrease(1);
        if (beingHit.getCount().getValue() == 0) {
            this.gameLevel.getCount().decrease(1);
            beingHit.removeHitListener(this);
            this.gameLevel.removeCollidable(beingHit);
            this.gameLevel.removeSprite(beingHit);
        }
        if (this.gameLevel.getCount().getValue() <= 0) {
            this.gameLevel.setRunning(false);
        }

    }
}
