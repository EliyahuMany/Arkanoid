package arkanoid;

import java.io.File;
import java.io.IOException;
import java.util.List;

import animation.AnimationRunner;
import biuoop.DialogManager;
import biuoop.GUI;
import core.Counter;
import levels.LevelInformation;

/**
 * GameFlow class.
 *
 * @author Alihom
 *
 */
public class GameFlow {
    private AnimationRunner ar;
    private GUI gui;
    private Counter score;
    private Counter numOfLife;
    private boolean win = true;
    private HighScoresTable table;
    private int num;

    /**
     * GameFlow Constructor.
     *
     * @param ar
     *            the animation runner.
     * @param numOfLife
     *            the number of life.
     * @param gui
     *            the GUI.
     * @param table
     *            the high scores table.
     */
    public GameFlow(AnimationRunner ar, int numOfLife, GUI gui, HighScoresTable table) {
        this.ar = ar;
        this.gui = gui;
        this.score = new Counter();
        this.num = numOfLife;
        this.numOfLife = new Counter(numOfLife);
        this.table = table;
    }

    /**
     * runLevels, this method run all the levels the user ask for. if the user
     * dosent ask for levels it will run the game from 1 to 4.
     *
     * @param levels
     *            the list of the levels.
     */
    public void runLevels(List<LevelInformation> levels) {
        for (LevelInformation levelInfo : levels) {

            GameLevel level = new GameLevel(levelInfo, this.gui, this.ar, this.numOfLife, this.score);

            level.initialize();

            while (level.getCount().getValue() > 0 && this.win) {
                if (this.numOfLife.getValue() <= 0) {
                    this.win = false;
                    break;
                }
                level.playOneTurn();
                if (level.getBallsCounter().getValue() <= 0) {
                    this.numOfLife.decrease(1);
                }
            }
            if (this.win) {
                this.score.increase(100);
            }
        }
        if (this.numOfLife.getValue() > 0) {
            this.win = true;
        }
        this.ar.run(new KeyPressStoppableAnimation(this.gui.getKeyboardSensor(), "space",
                new EndGame(this.gui.getKeyboardSensor(), this.score, win)));
        if (table.getRank(this.score.getValue()) != -1) {
            DialogManager dialog = gui.getDialogManager();
            this.table.add(
                    new ScoreInfo(dialog.showQuestionDialog("Name", "What is your name?", ""), this.score.getValue()));
            try {
                table.save(new File("highscores"));
            } catch (

            IOException e) {
                e.printStackTrace();
            }
        }
        this.ar.run(new KeyPressStoppableAnimation(this.gui.getKeyboardSensor(), "space",
                new HighScoresAnimation(table, "space", gui)));
        this.numOfLife = new Counter(num);
        this.score = new Counter();
        this.win = true;
    }
}