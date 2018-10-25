package levels;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import arkanoid.Block;
import core.Sprite;
import core.Velocity;
import geometry.Point;
import geometry.Rectangle;

/**
 * FinalFour Class.
 *
 * @author Alihom
 *
 */
public class FinalFour implements LevelInformation {
    private List<Block> l;
    private List<Velocity> v;
    private int numOfBalls;
    private int padSpeed;
    private int padWidth;
    private String st;
    private Sprite bG;
    private int numToRemove;

    /**
     * FinalFour Constructor.
     */
    public FinalFour() {
        this.l = new ArrayList<Block>();
        this.v = new ArrayList<Velocity>();
        for (int j = 0; j < 7; j++) {
            java.awt.Color color = this.randomColor();
            for (int i = 0; i < 15; i++) {
                this.l.add(new Block(new Rectangle(new Point(20 + (50.67 * i), 120 + 20 * j), 50.67, 20), 1, color));
            }
        }
        for (int k = 0; k < 3; k++) {
            this.v.add(new Velocity(-20 + (k * 20), 240, 2));
        }
        this.numOfBalls = 3;
        this.padSpeed = 600;
        this.padWidth = 100;
        this.st = "Final Four";
        this.bG = new FinalFourBG();
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

    /**
     * rendomColor.
     *
     * @return Color rendom color.
     */
    public Color randomColor() {
        Random rand = new Random();
        float r = rand.nextFloat();
        float g = rand.nextFloat();
        float b = rand.nextFloat();
        return new Color(r, g, b);
    }

}
