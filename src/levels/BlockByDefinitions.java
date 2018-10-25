package levels;

import java.util.Map;

import arkanoid.Block;
import geometry.Point;
import geometry.Rectangle;

/**
 * block by definitions class.
 *
 * @author Alihom
 *
 */
public class BlockByDefinitions implements BlockCreator {
    private int width;
    private int height;
    private int hitPoints;
    private Map<String, String> backgrounds;
    private java.awt.Color stroke;

    /**
     * blocks by definitions constructor.
     *
     * @param width
     *            the block width.
     * @param height
     *            the block height.
     * @param hitPoints
     *            the number of hit points.
     * @param backgrounds
     *            the background string.
     * @param stroke
     *            the stroke color.
     */
    public BlockByDefinitions(int width, int height, int hitPoints, Map<String, String> backgrounds,
            java.awt.Color stroke) {
        this.width = width;
        this.height = height;
        this.hitPoints = hitPoints;
        this.backgrounds = backgrounds;
        this.stroke = stroke;
    }

    @Override
    public Block create(int xpos, int ypos) {
        return new Block(new Rectangle(new Point(xpos, ypos), width, height), this.hitPoints, this.stroke,
                this.backgrounds);
    }

}
