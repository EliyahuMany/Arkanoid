package arkanoid;

import animation.Animation;
import biuoop.DrawSurface;
import biuoop.GUI;

/**
 * High scores animation class.
 *
 * @author Alihom
 *
 */
public class HighScoresAnimation implements Animation {
    private HighScoresTable h;
    private String endKey;
    private GUI gui;
    private boolean stop;

    /**
     * high scores animation constructor.
     *
     * @param scores
     *            the high scores table.
     * @param endKey
     *            the key to exit the table.
     * @param gui
     *            the gui.
     */
    public HighScoresAnimation(HighScoresTable scores, String endKey, GUI gui) {
        this.h = scores;
        this.endKey = endKey;
        this.gui = gui;
        this.stop = false;
    }

    @Override
    public void doOneFrame(DrawSurface d, double dt) {
        if (this.h.getHighScores().isEmpty()) {
            d.setColor(java.awt.Color.blue);
            d.drawRectangle(0, 0, 800, 600);
        }
        d.setColor(java.awt.Color.blue);
        d.fillRectangle(0, 0, 800, 600);
        d.setColor(java.awt.Color.white);
        d.drawText(195, 70, "High Scores Table", 50);
        for (int i = 0; i < this.h.getHighScores().size(); i++) {
            d.setColor(java.awt.Color.white);
            d.fillRectangle(200, 100 + i * 70, 400, 50);
            d.setColor(java.awt.Color.black);
            d.drawText(220, 140 + i * 70, (i + 1) + ". " + this.h.getHighScores().get(i).getName(), 40);
            d.drawText(470, 140 + i * 70, "" + this.h.getHighScores().get(i).getScore(), 40);
        }
        if (this.gui.getKeyboardSensor().isPressed(endKey)) {
            this.stop = true;
        }

    }

    @Override
    public boolean shouldStop() {
        if (this.stop) {
            this.stop = false;
            return true;
        } else {
            return this.stop;
        }
    }

}
