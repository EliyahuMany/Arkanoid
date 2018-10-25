package arkanoid;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * High scores table class.
 *
 * @author Alihom
 *
 */
public class HighScoresTable implements Serializable {
    private static final long serialVersionUID = 1L;
    private int size;
    private List<ScoreInfo> list;

    /**
     * Create an empty high-scores table with the specified size. The size means
     * that the table holds up to size top scores.
     *
     * @param size
     *            the size of the highscores table.
     */
    public HighScoresTable(int size) {
        this.size = size;
        this.list = new ArrayList<ScoreInfo>(this.size);
    }

    /**
     * Add a high-score.
     *
     * @param score
     *            the score to add.
     */
    public void add(ScoreInfo score) {
        if (this.list.size() < this.size) {
            this.list.add(this.list.size(), score);
        } else {
            this.list.remove(this.list.size() - 1);
            this.list.add(this.list.size() - 1, score);
        }
        for (int i = 0; i < this.list.size() - 1; i++) {
            for (int j = 0; j < this.list.size() - 1; j++) {
                if (this.list.get(j).getScore() < this.list.get(j + 1).getScore()) {
                    ScoreInfo x = this.list.get(j + 1);
                    this.list.remove(j + 1);
                    this.list.add(j + 1, this.list.get(j));
                    this.list.remove(j);
                    this.list.add(j, x);
                }
            }
        }
    }

    /**
     * Return table size.
     *
     * @return size of the table.
     */
    public int size() {
        return this.size;
    }

    /**
     * Return the current high scores. The list is sorted such that the highest
     * scores come first.
     *
     * @return highscores table list.
     */
    public List<ScoreInfo> getHighScores() {
        return this.list;
    }

    /**
     * return the rank of the current score: where will it be on the list if
     * added? Rank 1 means the score will be highest on the list. Rank `size`
     * means the score will be lowest. Rank > `size` means the score is too low
     * and will not be added to the list.
     *
     * @param score
     *            the score to check.
     * @return the result of the check.
     */
    public int getRank(int score) {
        if (this.list.isEmpty()) {
            return 1;
        }
        for (int i = 0; i < this.size; i++) {
            if (this.list.size() < this.size || (this.list.get(i)).getScore() < score) {
                return i + 1;
            }
        }
        return -1;
    }

    /**
     * Clears the table.
     */
    public void clear() {
        this.list.removeAll(list);
    }

    /**
     * Load table data from file. Current table data is cleared.
     *
     * @param filename
     *            the file.
     * @throws IOException
     *             exeption.
     */
    public void load(File filename) throws IOException {
        ObjectInputStream in = null;
        ScoreInfo score;
        try {
            in = new ObjectInputStream(new FileInputStream(filename.getAbsolutePath()));
            while ((score = (ScoreInfo) in.readObject()) != null) {
                this.list.add(score);
            }
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
        } finally {
            // Exception might have happened at constructor
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                }
            }
        }
    }

    /**
     * Save table data to the specified file.
     *
     * @param filename
     *            the file.
     * @throws IOException
     *             exeption.
     */
    public void save(File filename) throws IOException {
        ObjectOutputStream out = null;
        try {
            out = new ObjectOutputStream(new FileOutputStream(filename.getAbsolutePath()));
            for (int i = 0; i < this.list.size(); i++) {
                out.writeObject(this.list.get(i));
            }
            out.writeObject(null);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        } finally {
            // Exception might have happened at constructor
            if (out != null) {
                try {
                    out.close();
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                }
            }
        }
    }

    /**
     * Read a table from file and return it. If the file does not exist, or
     * there is a problem with reading it, an empty table is returned.
     *
     * @param filename
     *            the file.
     * @return high score table.
     */
    public static HighScoresTable loadFromFile(File filename) {
        HighScoresTable table = new HighScoresTable(5);
        try {
            table.load(filename);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return table;
    }
}