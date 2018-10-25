package levels;

import java.util.ArrayList;
import java.util.List;

import arkanoid.Block;
import core.Sprite;
import core.Velocity;
import geometry.Point;
import geometry.Rectangle;

/**
 * WideEasy Class.
 *
 * @author Alihom
 *
 */
public class WideEasy implements LevelInformation {
    private List<Block> l;
    private List<Velocity> v;
    private int numOfBalls;
    private int padSpeed;
    private int padWidth;
    private String st;
    private Sprite bG;
    private int numToRemove;

    /**
     * WideEasy Construcor.
     */
    public WideEasy() {
        java.awt.Color color = java.awt.Color.red;
        this.l = new ArrayList<Block>();
        this.v = new ArrayList<Velocity>();
        for (int i = 0; i < 15; i++) {
            if (i == 2) {
                color = java.awt.Color.orange;
            } else if (i == 4) {
                color = java.awt.Color.yellow;
            } else if (i == 6) {
                color = java.awt.Color.green;
            } else if (i == 9) {
                color = java.awt.Color.blue;
            } else if (i == 11) {
                color = java.awt.Color.pink;
            } else if (i == 13) {
                color = java.awt.Color.cyan;
            }
            this.l.add(new Block(new Rectangle(new Point(20 + (50.67 * i), 240), 50.67, 20), 1, color));
        }
        for (int j = 0; j < 10; j++) {
            this.v.add(new Velocity(-45 + (j * 10), 240, 2));
        }
        this.numOfBalls = 10;
        this.padSpeed = 300;
        this.padWidth = 500;
        this.st = "Wide Easy";
        this.bG = new WideEasyBG();
        this.numToRemove = this.l.size();
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
