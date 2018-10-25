package levels;

import java.awt.Color;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import arkanoid.Block;
import core.Sprite;
import core.Velocity;

/**
 * Level creator class.
 *
 * @author Alihom
 *
 */
public class LevelCreator implements LevelInformation {
    private List<Block> l;
    private List<Velocity> v;
    private int numOfBalls;
    private int padSpeed;
    private int padWidth;
    private String st;
    private Sprite bG;
    private int numToRemove;
    private BlocksFromSymbolsFactory factory;
    private int blockStartX;
    private int blockStartY;
    private int rowHeight;

    /**
     * Level creator constructor.
     *
     * @param levelName
     *            the name of the level.
     * @param velocity
     *            the list of the velocity.
     * @param background
     *            the background.
     * @param ints
     *            all the ints parameters.
     * @param blocks
     *            list of blocks.
     * @param blocksDef
     *            definitions of the blocks.
     */
    public LevelCreator(String levelName, List<Velocity> velocity, String background, int[] ints, List<String> blocks,
            String blocksDef) {
        this.st = levelName;
        this.v = velocity;
        this.bG = new BackgroundCreator(background);
        this.padSpeed = ints[0];
        this.padWidth = ints[1];
        this.blockStartX = ints[2];
        this.blockStartY = ints[3];
        this.rowHeight = ints[4];
        this.numToRemove = ints[5];
        this.numOfBalls = velocity.size();
        InputStream is = ClassLoader.getSystemClassLoader().getResourceAsStream(blocksDef);
        this.factory = BlocksDefinitionReader.fromReader(new InputStreamReader(is));
        this.l = blocksCreator(blocks, blockStartX, blockStartY, rowHeight, this.numToRemove);
    }

    /**
     * Level creator constructor.
     *
     * @param levelName
     *            the name of the level.
     * @param velocity
     *            the list of the velocity.
     * @param color
     *            the color.
     * @param ints
     *            all the ints parameters.
     * @param blocks
     *            list of blocks.
     * @param blocksDef
     *            definitions of the blocks.
     */
    public LevelCreator(String levelName, List<Velocity> velocity, Color color, int[] ints, List<String> blocks,
            String blocksDef) {
        this.st = levelName;
        this.v = velocity;
        this.bG = new BackgroundCreator(color);
        this.padSpeed = ints[0];
        this.padWidth = ints[1];
        this.blockStartX = ints[2];
        this.blockStartY = ints[3];
        this.rowHeight = ints[4];
        this.numToRemove = ints[5];
        this.numOfBalls = velocity.size();
        InputStream is = ClassLoader.getSystemClassLoader().getResourceAsStream(blocksDef);
        this.factory = BlocksDefinitionReader.fromReader(new InputStreamReader(is));
        this.l = blocksCreator(blocks, blockStartX, blockStartY, rowHeight, this.numToRemove);
    }

    /**
     * this method create list of blocks.
     *
     * @param blocks
     *            list of blocks.
     * @param x
     *            the start x val.
     * @param y
     *            the start y val.
     * @param row
     *            the row height.
     * @param numOfBlocks
     *            the number of blocks.
     * @return list of blocks.
     */
    public List<Block> blocksCreator(List<String> blocks, int x, int y, int row, int numOfBlocks) {
        String[] line;
        List<Block> list = new ArrayList<Block>();
        for (String b : blocks) {
            line = b.split("\n");
            for (String str : line) {
                int xpos = this.blockStartX;
                char[] chars = str.toCharArray();
                for (char c : chars) {
                    if (this.factory.isBlockSymbol(String.valueOf(c))) {
                        list.add(this.factory.getBlock(String.valueOf(c), xpos, this.blockStartY));
                        xpos += list.get(list.size() - 1).getCollisionRectangle().getWidth();
                    } else if (this.factory.isSpaceSymbol(String.valueOf(c))) {
                        xpos += this.factory.getSpaceWidth(String.valueOf(c));
                    }
                }
                this.blockStartY += this.rowHeight;
            }
        }
        return list;
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