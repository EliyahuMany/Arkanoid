package arkanoid;

import java.awt.Color;
import java.awt.Image;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;

import animation.Menu;
import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import core.Selection;

/**
 * Menu Animation class.
 *
 * @author Alihom
 *
 * @param <T>
 */
public class MenuAnimation<T> implements Menu<T> {
    private String st;
    private String lastSelect;
    private String imageString = null;
    private ArrayList<Selection> selections;
    private ArrayList<String> keys = new ArrayList<String>();
    private T status = null;
    private boolean stop = false;
    private KeyboardSensor sensor;
    private Map<String, Group<T>> subMenu = new HashMap<String, Group<T>>();

    /**
     * MenuAnimation constructor.
     *
     * @param str
     *            the name of the menu.
     * @param sensor
     *            keyboard.
     */
    public MenuAnimation(String str, KeyboardSensor sensor) {
        this.st = str;
        this.sensor = sensor;
        this.selections = new ArrayList<Selection>();
    }

    /**
     * MenuAnimation constructor.
     *
     * @param str
     *            the name of the menu.
     * @param sensor
     *            keyboard.
     * @param image
     *            the image.
     */
    public MenuAnimation(String str, KeyboardSensor sensor, String image) {
        this.st = str;
        this.sensor = sensor;
        this.selections = new ArrayList<Selection>();
        this.imageString = image;
    }

    /**
     * add selection.
     *
     * @param key
     *            the key to press.
     * @param name
     *            the name of the selection.
     * @param option
     *            the operate.
     */
    public void addSelection(String key, String name, T option) {
        this.selections.add(new Selection(key, name, option));
    }

    @Override
    public void doOneFrame(DrawSurface d, double dt) {
        if (this.imageString == null) {
            d.setColor(new Color(251, 24, 132));
            d.fillRectangle(0, 0, 800, 600);
            d.setColor(new Color(250, 133, 38));
            d.fillCircle(900, 620, 750);
            d.setColor(new Color(254, 228, 30));
            d.fillCircle(1450, 500, 950);
            for (int j = 0; j < this.selections.size(); j++) {
                d.setColor(new Color(251, 24, 132));
                d.fillRectangle(300, 250 + j * 70, 200, 40);

            }
            d.setColor(Color.white);
            d.drawText(340, 280, "s - Start", 30);
            d.drawText(310, 350, "h - High Scores", 25);
            d.drawText(340, 420, "q - Quit", 30);
            d.drawText(200, 170, "Arkanoid", 100);
            d.setColor(Color.black);
            d.drawText(205, 165, "Arkanoid", 100);
        } else {
            InputStream is = ClassLoader.getSystemClassLoader().getResourceAsStream(this.imageString);
            Image image;
            try {
                image = ImageIO.read(is);
                d.drawImage(0, 0, image);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        for (int i = 0; i < this.selections.size(); i++) {
            if (this.sensor.isPressed(this.selections.get(i).getKey())) {
                this.lastSelect = this.selections.get(i).getKey();
                this.status = (T) this.selections.get(i).getOption();
                ((Task<Void>) (this.status)).run();
                this.stop = true;
                break;
            }
        }
        if (!this.subMenu.isEmpty()) {
            this.stop = false;
        }
    }

    /**
     * set stop to the sub menues.
     *
     * @param s
     *            change the stop;
     */
    public void setStop(boolean s) {
        this.stop = s;
    }

    /**
     * return the last selected pressed.
     *
     * @return last selected press.
     */
    public String getLastSelect() {
        return lastSelect;
    }

    /**
     * get sub menu list.
     *
     * @return map of sub menues.
     */
    public Map<String, Group<T>> getSubMenu() {
        return this.subMenu;
    }

    @Override
    public boolean shouldStop() {
        return this.stop;
    }

    @Override
    public T getStatus() {
        return (T) this.status;
    }

    @Override
    public ArrayList<Selection> getSelections() {
        return this.selections;
    }

    @Override
    public void addSubMenu(String key, String message, Menu<T> sub) {
        this.subMenu.put(key, new Group(message, sub));
        this.keys.add(key);
    }

}
