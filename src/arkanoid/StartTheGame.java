package arkanoid;

import java.util.List;

import levels.LevelInformation;

/**
 * start the game class.
 *
 * @author Alihom
 *
 */
public class StartTheGame implements Task<Void> {
    private GameFlow gameFlow;
    private List<LevelInformation> list;

    /**
     * start the game constructor.
     *
     * @param gameFlow
     *            the game flow.
     * @param list
     *            the list of the level informations.
     */
    public StartTheGame(GameFlow gameFlow, List<LevelInformation> list) {
        this.gameFlow = gameFlow;
        this.list = list;
    }

    /**
     * run method.
     *
     * @return return null.
     */
    public Void run() {
        this.gameFlow.runLevels(this.list);
        return null;
    }
}