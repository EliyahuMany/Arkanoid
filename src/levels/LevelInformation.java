package levels;

import java.util.List;

import arkanoid.Block;
import core.Sprite;
import core.Velocity;

/**
 * LevelInformation.
 *
 * @author Alihom
 *
 */
public interface LevelInformation {
    /**
     * numberOfBalls.
     *
     * @return int return the number of balls.
     */
    int numberOfBalls();

    /**
     * initialBallVelocities.
     *
     * @return list of the balls velocity.
     */
    List<Velocity> initialBallVelocities();

    /**
     * paddleSpeed.
     *
     * @return the paddle speed.
     */
    int paddleSpeed();

    /**
     * paddleWidth.
     *
     * @return int the paddle width.
     */
    int paddleWidth();

    /**
     * levelName.
     *
     * @return String the level name.
     */
    String levelName();

    /**
     * getBackground.
     *
     * @return Sprite the background sprite.
     */
    Sprite getBackground();

    /**
     * block.
     *
     * @return list<Block> list of the block.
     */
    List<Block> blocks();

    /**
     * numberOfBlocksToRemove.
     *
     * @return int number of blocks to remove until win the game.
     */
    int numberOfBlocksToRemove();
}