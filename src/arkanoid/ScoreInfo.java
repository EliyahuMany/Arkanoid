package arkanoid;

import java.io.Serializable;

/**
 * score info class.
 *
 * @author Alihom
 *
 */
public class ScoreInfo implements Serializable {
    private static final long serialVersionUID = 1L;
    private String name;
    private int score;

    /**
     * Score info constructor.
     *
     * @param name
     *            the name.
     * @param score
     *            the score.
     */
    public ScoreInfo(String name, int score) {
        this.name = name;
        this.score = score;
    }

    /**
     * get the name.
     *
     * @return the String of the name.
     */
    public String getName() {
        return this.name;
    }

    /**
     * get the score.
     *
     * @return int score.
     */
    public int getScore() {
        return this.score;
    }
}