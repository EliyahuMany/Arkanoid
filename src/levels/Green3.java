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
 * Green3 Class.
 *
 * @author Alihom
 *
 */
public class Green3 implements LevelInformation {
    private List<Block> l;
    private List<Velocity> v;
    private int numOfBalls;
    private int padSpeed;
    private int padWidth;
    private String st;
    private Sprite bG;
    private int numToRemove;

    /**
     * Green3 Constructor.
     */
    public Green3() {
        this.l = new ArrayList<Block>();
        this.v = new ArrayList<Velocity>();
        for (int i = 0; i < 5; i++) {
            java.awt.Color color = this.randomColor();
            for (int j = 0; j < 10 - i; j++) {
                l.add(new Block(new Rectangle(new Point(725 - 55 * j, 140 + 35 * i), 55, 35), 1, color));
            }
        }
        this.v.add(new Velocity(-180, -300));
        this.v.add(new Velocity(180, -300));
        this.padSpeed = 400;
        this.padWidth = 160;
        this.numOfBalls = 2;
        this.st = "Green 3";
        this.bG = new Green3BG();
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
     * @return Color random color.
     */
    public Color randomColor() {
        Random rand = new Random();
        float r = rand.nextFloat();
        float g = rand.nextFloat();
        float b = rand.nextFloat();
        return new Color(r, g, b);
    }

}
