package levels;

import java.awt.Image;
import java.io.IOException;
import java.io.InputStream;
import javax.imageio.ImageIO;
import biuoop.DrawSurface;
import core.Sprite;
import geometry.Point;

/**
 * the background creator class.
 *
 * @author Alihom
 *
 */
public class BackgroundCreator implements Sprite {
    private String background = null;
    private java.awt.Color color = null;
    private Point point = null;
    private int width;
    private int height;
    private Image image;

    /**
     * background creator constructor.
     *
     * @param background
     *            the background
     */
    public BackgroundCreator(String background) {
        this.background = background;
    }

    /**
     * background creator constructor.
     *
     * @param color
     *            the color.
     */
    public BackgroundCreator(java.awt.Color color) {
        this.color = color;
    }

    /**
     * background creator constructor.
     *
     * @param background
     *            the background string.
     * @param point
     *            the point of the start.
     * @param width
     *            the width of the background.
     * @param height
     *            the height of the image.
     */
    public BackgroundCreator(String background, Point point, int width, int height) {
        this.background = background;
        this.point = point;
        this.width = width;
        this.height = height;
    }

    /**
     * background creator constructor.
     *
     * @param color
     *            the color.
     * @param point
     *            the point of the start.
     * @param width
     *            the width of the background.
     * @param height
     *            the height of the image.
     */
    public BackgroundCreator(java.awt.Color color, Point point, int width, int height) {
        this.color = color;
        this.point = point;
        this.width = width;
        this.height = height;
    }

    @Override
    public void drawOn(DrawSurface d) {
        if (this.color == null) {
            if (image == null) {
                InputStream is = ClassLoader.getSystemClassLoader().getResourceAsStream(this.background);
                try {
                    this.image = ImageIO.read(is);
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
            if (point == null) {
                d.drawImage(0, 0, this.image);
            } else {
                d.drawImage((int) this.point.getX(), (int) this.point.getY(), this.image);
            }
        } else {
            d.setColor(color);
            if (point == null) {
                d.fillRectangle(0, 0, 800, 600);
            } else {
                d.fillRectangle((int) this.point.getX(), (int) this.point.getY(), this.width, this.height);
            }
        }

    }

    @Override
    public void timePassed(double dt) {
    }

}
