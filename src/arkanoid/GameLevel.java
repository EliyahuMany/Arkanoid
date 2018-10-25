package arkanoid;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import animation.Animation;
import animation.AnimationRunner;
import biuoop.DrawSurface;
import biuoop.GUI;
import core.Collidable;
import core.Counter;
import core.Sprite;
import geometry.Point;
import geometry.Rectangle;
import levels.LevelInformation;

/**
 * Game class, initialize the game and runs it.
 *
 * @author Alihom
 *
 */
public class GameLevel implements Animation {
    private SpriteCollection sprites;
    private GameEnvironment environment;
    private GUI gui;
    private Paddle paddle;
    private Counter count;
    private Counter ballsCounter;
    private Counter score;
    private Counter numOfLife;
    private List<Ball> l;
    private AnimationRunner runner;
    private boolean running;
    private LevelInformation levelInfo;
    private boolean isAlreadyPressed;

    /**
     * getBallsCounter , return the number of balls counter.
     *
     * @return Counter counter of the number of balls.
     */
    public Counter getBallsCounter() {
        return ballsCounter;
    }

    /**
     * isRunning.
     *
     * @return true if this level running and false otherwise.
     */
    public boolean isRunning() {
        return running;
    }

    /**
     * setRunning.
     *
     * @param run
     *            the member.
     */
    public void setRunning(boolean run) {
        this.running = run;
    }

    /**
     * getGui.
     *
     * @return GUI the gui.
     */
    public GUI getGui() {
        return gui;
    }

    /**
     * addCollidable. adds an Collidable object to the list.
     *
     * @param c
     *            colliadable object.
     */
    public void addCollidable(Collidable c) {
        this.environment.addCollidable(c);
    }

    /**
     * getCount, return the count member.
     *
     * @return Counter of the number of life.
     */
    public Counter getCount() {
        return count;
    }

    /**
     * removeCollidable, remove the collidable from the list.
     *
     * @param c
     *            the collidable to remove.
     */
    public void removeCollidable(Collidable c) {
        this.environment.removeCollidable(c);
    }

    /**
     * addSprite. adds a Sprite object to the list.
     *
     * @param s
     *            sprite object.
     */
    public void addSprite(Sprite s) {
        this.sprites.addSprite(s);
    }

    /**
     * removeSprite, remove the sprite from the list.
     *
     * @param s
     *            the sprite to remove.
     */
    public void removeSprite(Sprite s) {
        this.sprites.removeSprite(s);
    }

    /**
     * getSprites.
     *
     * @return SpriteCollection the sprite collection.
     */
    public SpriteCollection getSprites() {
        return sprites;
    }

    /**
     * getEnvironment.
     *
     * @return GameEnvironment the game environment.
     */
    public GameEnvironment getEnvironment() {
        return environment;
    }

    /**
     * Game constructor.
     *
     * @param levelInfo
     *            the level.
     * @param gui
     *            the GUI.
     * @param ar
     *            the animation runner.
     * @param numOfLife
     *            the number of the lives.
     * @param score
     *            the score.
     */
    public GameLevel(LevelInformation levelInfo, GUI gui, AnimationRunner ar, Counter numOfLife, Counter score) {
        this.environment = new GameEnvironment();
        this.gui = gui;
        this.count = new Counter();
        this.ballsCounter = new Counter();
        this.score = score;
        this.numOfLife = numOfLife;
        this.runner = ar;
        this.sprites = new SpriteCollection((double) (1.0 / this.runner.getFramesPerSecond()));
        this.levelInfo = levelInfo;
        this.isAlreadyPressed = true;
    }

    /**
     * initialize. Initialize a new game: create the Blocks and Ball (and
     * Paddle) and add them to the game.
     */
    public void initialize() {
        this.addSprite(this.levelInfo.getBackground());
        BlockRemover pHL = new BlockRemover(this, this.count);
        BallRemover bHL = new BallRemover(this, this.ballsCounter);
        ScoreTrackingListener sHL = new ScoreTrackingListener(this.score);
        Block ind = new Block(new Rectangle(new Point(0, 0), 800, 20), 1, java.awt.Color.white);
        ind.addToGame(this);
        LivesIndicator lives = new LivesIndicator(this.numOfLife);
        lives.addToGame(this);
        LevelName lN = new LevelName(this.levelInfo.levelName());
        lN.addToGame(this);

        ScoreIndicator scoreIndicator = new ScoreIndicator(this.score);
        scoreIndicator.addToGame(this);

        for (int i = 0; i < 3; i++) {
            if (i < 1) {
                Block block = new Block(new Rectangle(new Point(0, 20), 800, 20), 1, java.awt.Color.gray);
                block.addToGame(this);
            } else {
                Block block = new Block(new Rectangle(new Point(775 * (i - 1), 40), 25, 580), 1, java.awt.Color.gray);
                block.addToGame(this);
            }
            for (int j = 0; j < 2; j++) {
                Block block = new Block(new Rectangle(new Point(0 + j * 800, 0), 0, 600), 1, java.awt.Color.white);
                block.addToGame(this);
                block.addHitListener(bHL);
            }
            Block block = new Block(new Rectangle(new Point(0, 620), 800, 0), 1, java.awt.Color.white);
            block.addToGame(this);
            block.addHitListener(bHL);
        }
        for (int j = 0; j < this.levelInfo.blocks().size(); j++) {
            this.levelInfo.blocks().get(j).addToGame(this);
            this.levelInfo.blocks().get(j).addHitListener(pHL);
            this.levelInfo.blocks().get(j).addHitListener(sHL);
        }
        this.count.increase(this.levelInfo.numberOfBlocksToRemove());
    }

    /**
     * run, run the level.
     */
    public void run() {
        while (this.numOfLife.getValue() > 0) {
            this.playOneTurn();
            if (this.count.getValue() <= 0) {
                this.score.increase(100);
            }
            this.numOfLife.decrease(1);
        }
        return;
    }

    /**
     * Run the game , start the animation loop.
     */
    public void playOneTurn() {
        if (this.count.getValue() > 0) {
            this.createBallsOnTopOfPaddle();
            CountdownAnimation cA = new CountdownAnimation(2, 3, this.sprites);
            this.runner.run(cA);
            this.running = true;
        }

        this.runner.run(this);
        return;
    }

    /**
     * this method make a random color.
     *
     * @return Color a random color.
     */

    public Color randomColor() {
        Random rand = new Random();
        float r = rand.nextFloat();
        float g = rand.nextFloat();
        float b = rand.nextFloat();
        return new Color(r, g, b);
    }

    /**
     * doOneFrame, running one frame of the animation.
     *
     * @param d
     *            the drawsurface.
     * @param dt
     *            the dt.
     */
    public void doOneFrame(DrawSurface d, double dt) {
        this.sprites.notifyAllTimePassed();
        this.sprites.drawAllOn(d);
        if (!this.gui.getKeyboardSensor().isPressed("p") && this.isAlreadyPressed) {
            this.isAlreadyPressed = false;
        } else if (this.gui.getKeyboardSensor().isPressed("p") && (!this.isAlreadyPressed)) {
            this.isAlreadyPressed = true;
            this.runner.run(new KeyPressStoppableAnimation(this.gui.getKeyboardSensor(), "space",
                    new PauseScreen(this.getGui().getKeyboardSensor())));
        }
    }

    /**
     * should stop method.
     *
     * @return true if the function should stop and false otherwise.
     */
    public boolean shouldStop() {
        return !this.running;
    }

    /**
     * createBallsOnTopOfPaddle, creating paddle and balls.
     */
    public void createBallsOnTopOfPaddle() {
        if (this.paddle != null) {
            this.sprites.removeSprite(this.paddle);
            this.removeCollidable(this.paddle);
        }
        this.paddle = new Paddle(this, this.levelInfo.paddleSpeed(), this.levelInfo.paddleWidth());
        this.paddle.addToGame(this);
        this.l = new ArrayList<Ball>();
        double x = (this.paddle.getCollisionRectangle().getUpperLeft().getX()
                + this.paddle.getCollisionRectangle().getUpperLeft().getX()
                + this.paddle.getCollisionRectangle().getWidth()) / 2;
        double y = this.paddle.getCollisionRectangle().getUpperLeft().getY() - 1;
        for (int i = 0; i < this.levelInfo.numberOfBalls(); i++) {
            this.l.add(new Ball(new Point(x, y), 5, java.awt.Color.yellow, this.environment));
            this.l.get(i).setVelocity(this.levelInfo.initialBallVelocities().get(i));
            this.l.get(i).addToGame(this);
            this.ballsCounter.increase(1);
        }
    }

}
