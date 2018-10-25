package levels;

import java.util.ArrayList;
import java.util.List;

import arkanoid.Block;
import core.Sprite;
import core.Velocity;
import geometry.Point;
import geometry.Rectangle;

/**
 * DirectHit Class.
 *
 * @author Alihom
 *
 */
public class DirectHit implements LevelInformation {
    private List<Block> l;
    private List<Velocity> v;
    private int numOfBalls;
    private int padSpeed;
    private int padWidth;
    private String st;
    private Sprite bG;
    private int numToRemove;

    /**
     * DirectHit Constructor.
     */
    public DirectHit() {
        this.l = new ArrayList<Block>();
        this.v = new ArrayList<Velocity>();
        this.l.add(new Block(new Rectangle(new Point(390, 190), 20, 20), 1, java.awt.Color.red));
        this.v.add(new Velocity(0, -300));
        this.numOfBalls = 1;
        this.padSpeed = 400;
        this.padWidth = 120;
        this.st = "Direct Hit";
        this.bG = new DirectHitBG();
        this.numToRemove = 1;
    }

    /**
     * numberOfBalls.
     *
     * @return int return the number of balls.
     */
    public int numberOfBalls() {
        return this.numOfBalls;
    }

    /**
     * initialBallVelocities.
     *
     * @return list of the balls velocity.
     */
    public List<Velocity> initialBallVelocities() {
        return this.v;
    }

    /**
     * paddleSpeed.
     *
     * @return the paddle speed.
     */
    public int paddleSpeed() {
        return this.padSpeed;
    }

    /**
     * paddleWidth.
     *
     * @return int the paddle width.
     */
    public int paddleWidth() {
        return this.padWidth;
    }

    /**
     * levelName.
     *
     * @return String the level name.
     */
    public String levelName() {
        return this.st;
    }

    /**
     * getBackground.
     *
     * @return Sprite the background sprite.
     */
    public Sprite getBackground() {
        return this.bG;
    }

    /**
     * block.
     *
     * @return list<Block> list of the block.
     */
    public List<Block> blocks() {
        return this.l;
    }

    /**
     * numberOfBlocksToRemove.
     *
     * @return int number of blocks to remove until win the game.
     */
    public int numberOfBlocksToRemove() {
        return this.numToRemove;
    }

}
