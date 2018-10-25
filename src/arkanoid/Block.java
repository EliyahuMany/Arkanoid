package arkanoid;

import java.awt.Color;
import java.awt.Image;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;

import biuoop.DrawSurface;
import core.Collidable;
import core.Counter;
import core.HitListener;
import core.HitNotifier;
import core.Sprite;
import core.Velocity;
import geometry.Point;
import geometry.Rectangle;

/**
 * The class implements Collidable and Sprite interfaces. In this class the user
 * able to set a Block - size, color, upperLeft point and more.
 */
public class Block implements Collidable, Sprite, HitNotifier {
    private Rectangle block;
    private java.awt.Color color;
    private java.awt.Color stroke;
    private Map<Integer, Image> image = new HashMap<Integer, Image>();
    private Counter count;
    private List<HitListener> hitListeners;
    private Map<String, String> background = null;

    /**
     * Constructor to the block.
     *
     * @param r
     *            rectangle.
     * @param life
     *            life of the block.
     * @param color
     *            color of the block.
     */
    public Block(Rectangle r, int life, java.awt.Color color) {
        this.block = new Rectangle(r.getUpperLeft(), r.getWidth(), r.getHeight());
        this.count = new Counter(life);
        this.color = color;
        this.hitListeners = new ArrayList<HitListener>();
    }

    /**
     * Constructor to the block.
     *
     * @param r
     *            rectangle.
     * @param hitPoints
     *            life of the block.
     * @param stroke
     *            the stroke of the block.
     * @param backgrounds
     *            the background of the block.
     */
    public Block(Rectangle r, int hitPoints, java.awt.Color stroke, Map<String, String> backgrounds) {
        this.block = new Rectangle(r.getUpperLeft(), r.getWidth(), r.getHeight());
        this.count = new Counter(hitPoints);
        this.hitListeners = new ArrayList<HitListener>();
        this.stroke = stroke;
        this.background = backgrounds;
    }

    /**
     * drawOn. Implementation of Sprite interface method. Draws the Block, uses
     * fillRectangle and then drawRectangle so the block has a frame. Draws the
     * number of hits on block.
     *
     * @param surface
     *            draw surface.
     */
    public void drawOn(DrawSurface surface) {
        if (this.background == null) {
            surface.setColor(this.color);
            surface.fillRectangle((int) this.block.getUpperLeft().getX(), (int) this.block.getUpperLeft().getY(),
                    (int) this.block.getWidth(), (int) this.block.getHeight());
            surface.setColor(java.awt.Color.black);
            surface.drawRectangle((int) this.block.getUpperLeft().getX(), (int) this.block.getUpperLeft().getY(),
                    (int) this.block.getWidth(), (int) this.block.getHeight());
        } else if (this.background.containsKey("fill-" + String.valueOf(this.count.getValue()))) {
            if (this.background.get("fill-" + String.valueOf(this.count.getValue())).contains("image")) {
                if (!this.image.containsKey(this.count.getValue())) {
                    this.image.put(this.count.getValue(),
                            stringToImage(this.background.get("fill-" + String.valueOf(this.count.getValue()))));
                }
                surface.drawImage((int) this.block.getUpperLeft().getX(), (int) this.block.getUpperLeft().getY(),
                        this.image.get(this.count.getValue()));
            } else {
                surface.setColor(stringToColor(this.background.get("fill-" + String.valueOf(this.count.getValue()))));
                surface.fillRectangle((int) this.block.getUpperLeft().getX(), (int) this.block.getUpperLeft().getY(),
                        (int) this.block.getWidth(), (int) this.block.getHeight());
            }
        } else if (this.background.containsKey("fill")) {
            if (this.background.get("fill").contains("image")) {
                if (!this.image.containsKey(this.count.getValue())) {
                    this.image.put(this.count.getValue(), stringToImage(this.background.get("fill")));
                }
                surface.drawImage((int) this.block.getUpperLeft().getX(), (int) this.block.getUpperLeft().getY(),
                        this.image.get(this.count.getValue()));
            } else {
                surface.setColor(stringToColor(this.background.get("fill")));
                surface.fillRectangle((int) this.block.getUpperLeft().getX(), (int) this.block.getUpperLeft().getY(),
                        (int) this.block.getWidth(), (int) this.block.getHeight());
            }
        }
        if (stroke != null) {
            surface.setColor(this.stroke);
        }
        surface.drawRectangle((int) this.block.getUpperLeft().getX(), (int) this.block.getUpperLeft().getY(),
                (int) this.block.getWidth(), (int) this.block.getHeight());
    }

    /**
     * getCollisionRectangle. Implementation of collidable interface method.
     * returns the shape of the block.
     *
     * @return the shape of the block.
     */
    public Rectangle getCollisionRectangle() {
        return this.block;
    }

    /**
     * this method get the collision point and the velocity and return the new
     * velocity of the ball.
     *
     * @param hitter
     *            the ball have been hit the block.
     * @param collisionPoint
     *            the collision point of the object with the block.
     * @param currentVelocity
     *            the velocity of the object that hit the block.
     * @return new velocity to the object.
     */
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        this.notifyHit(hitter);
        if (this.getCollisionRectangle().getTop().pointOnLine(collisionPoint)
                || this.getCollisionRectangle().getBottom().pointOnLine(collisionPoint)) {
            if (this.getCollisionRectangle().getLeft().pointOnLine(collisionPoint)
                    || this.getCollisionRectangle().getRigth().pointOnLine(collisionPoint)) {
                return new Velocity(-currentVelocity.getDx(), -currentVelocity.getDy());
            } else {
                return new Velocity(currentVelocity.getDx(), -currentVelocity.getDy());
            }
        } else {
            return new Velocity(-currentVelocity.getDx(), currentVelocity.getDy());
        }
    }

    /**
     * nitifyHit.
     *
     * @param hitter
     *            the hitter ball.
     */
    private void notifyHit(Ball hitter) {
        // Make a copy of the hitListeners before iterating over them.
        List<HitListener> listeners = new ArrayList<HitListener>(this.hitListeners);
        // Notify all listeners about a hit event:
        for (HitListener hl : listeners) {
            hl.hitEvent(this, hitter);
        }
    }

    /*
     * public Velocity hit2(Point collisionPoint, Velocity currentVelocity) { if
     * (this.life > 0) { this.life--; } if ((collisionPoint.getX() ==
     * block.getUpperLeft().getX() || collisionPoint.getX() ==
     * block.getUpperLeft().getX() + block.getWidth()) && (collisionPoint.getY()
     * == block.getUpperLeft().getY() || collisionPoint.getY() ==
     * block.getUpperLeft().getY() + block.getHeight())) { return new
     * Velocity(-currentVelocity.getDx(), -currentVelocity.getDy()); } else if
     * (collisionPoint.getX() == block.getUpperLeft().getX() ||
     * collisionPoint.getX() == block.getUpperLeft().getX() + block.getWidth())
     * { return new Velocity(-currentVelocity.getDx(), currentVelocity.getDy());
     * } else { return new Velocity(currentVelocity.getDx(),
     * -currentVelocity.getDy()); } }
     */
    /**
     * TimePassed. Implementation of Sprite interface method.
     *
     * @param dt
     *            the dt.
     */
    public void timePassed(double dt) {
    }

    /**
     * addToGame. adds the block to game's collidable list and sprite list.
     *
     * @param g
     *            game.
     */
    public void addToGame(GameLevel g) {
        g.addCollidable(this);
        g.addSprite(this);
    }

    /**
     * getCount return the count member.
     *
     * @return Counter the life counter.
     */
    public Counter getCount() {
        return count;
    }

    /**
     * removeFromGame this method remove the block from the game.
     *
     * @param gameLevel
     *            the game level.
     */
    public void removeFromGame(GameLevel gameLevel) {
        gameLevel.removeCollidable(this);
        gameLevel.removeSprite(this);
    }

    /**
     * addHitListener, this method add a hit listener to the block.
     *
     * @param hl
     *            the hit listener.
     */
    public void addHitListener(HitListener hl) {
        this.hitListeners.add(hl);
    }

    /**
     * removeHitListener, this method remove a hit listener to the block.
     *
     * @param hl
     *            the hit listener.
     */
    public void removeHitListener(HitListener hl) {
        this.hitListeners.remove(hl);
    }

    /**
     * change string to color.
     *
     * @param str
     *            the name of the color.
     * @return Color.
     */
    public static java.awt.Color stringToColor(String str) {
        String[] backgroundStr;
        String[] newString;

        if (str.contains("RGB")) {
            backgroundStr = str.split("\\(");
            newString = null;
            newString = backgroundStr[2].split("\\)");
            backgroundStr = null;
            backgroundStr = newString[0].split(",");
            return new java.awt.Color(Integer.parseInt(backgroundStr[0]), Integer.parseInt(backgroundStr[1]),
                    Integer.parseInt(backgroundStr[2]));
        } else {
            backgroundStr = str.split("\\(");
            newString = null;
            newString = backgroundStr[1].split("\\)");
            Field f;
            try {
                f = Color.class.getField(newString[0]);
                return (Color) f.get(null);
            } catch (Exception ce) {
                // if we can't get any color return black
                return Color.LIGHT_GRAY;
            }
        }
    }

    /**
     * change string to image.
     *
     * @param str
     *            the name of the image.
     * @return image.
     */
    public static Image stringToImage(String str) {
        String[] background;
        String[] im;
        background = str.split("\\(");
        im = background[1].split("\\)");
        InputStream is = ClassLoader.getSystemClassLoader().getResourceAsStream(im[0]);
        try {
            return ImageIO.read(is);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
