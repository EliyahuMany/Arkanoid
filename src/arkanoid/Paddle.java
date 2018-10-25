package arkanoid;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import core.Collidable;
import core.Sprite;
import core.Velocity;
import geometry.Point;
import geometry.Rectangle;

/**
 * Class for Paddle. Paddle class implements Sprite and Collidable interfaces.
 */
public class Paddle implements Sprite, Collidable {
    private biuoop.KeyboardSensor keyboard;
    private Rectangle block;
    private java.awt.Color color;
    private int speed;

    /**
     * Cunstroctor for Paddle.
     *
     * @param g
     *            game.
     * @param speed
     *            the speed of the paddle.
     * @param width
     *            the width of the paddle.
     */
    public Paddle(GameLevel g, int speed, int width) {
        this.block = new Rectangle(new Point(400 - (width / 2), 550), width, 20);
        this.color = java.awt.Color.orange;
        this.keyboard = g.getGui().getKeyboardSensor();
        this.speed = speed;
    }

    /**
     * moveLeft. moves the paddle to the left.
     *
     * @param dt
     *            the dt.
     */
    public void moveLeft(double dt) {
        this.block = new Rectangle(
                new Point(this.block.getUpperLeft().getX() - dt * speed, this.block.getUpperLeft().getY()),
                this.block.getWidth(), this.block.getHeight());
    }

    /**
     * moveRight. moves the paddle to the right.
     *
     * @param dt
     *            the dt.
     */
    public void moveRight(double dt) {
        this.block = new Rectangle(
                new Point(this.block.getUpperLeft().getX() + dt * speed, this.block.getUpperLeft().getY()),
                this.block.getWidth(), this.block.getHeight());
    }

    /**
     * TimePassed. Implementation of Sprite interface method. Calls to moveLeft
     * or moveRight.
     *
     * @param dt
     *            the dt.
     */
    public void timePassed(double dt) {
        if (keyboard.isPressed(KeyboardSensor.LEFT_KEY)) {
            if (this.block.getUpperLeft().getX() - dt * speed >= 25) {
                this.moveLeft(dt);
            }
        }
        if (keyboard.isPressed(KeyboardSensor.RIGHT_KEY)) {
            if (this.block.getUpperLeft().getX() + this.block.getWidth() + dt * speed <= 775) {
                this.moveRight(dt);
            }
        }
    }

    /**
     * getCollisionRectangle.
     *
     * @return Rectangle of the paddle.
     */
    public Rectangle getCollisionRectangle() {
        return this.block;
    }

    /**
     * this method receive Velocity and Point and return the new velocity.
     *
     * @param collisionPoint
     *            the collision point of the object with the block.
     * @param currentVelocity
     *            the velocity of the object that hit the block.
     * @param hitter
     *            the hitter ball.
     * @return Velocity new velocity.
     */
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        int region = (int) ((collisionPoint.getX() - this.block.getUpperLeft().getX()) / (this.block.getWidth() / 5))
                + 1;
        if (this.getCollisionRectangle().getTop().pointOnLine(collisionPoint)) {
            switch (region) {
            case (1):
                return Velocity.fromAngleAndSpeed(300,
                        Math.sqrt(Math.pow(currentVelocity.getDx(), 2) + Math.pow(currentVelocity.getDy(), 2)));
            case (2):
                return Velocity.fromAngleAndSpeed(330,
                        Math.sqrt(Math.pow(currentVelocity.getDx(), 2) + Math.pow(currentVelocity.getDy(), 2)));
            case (3):
                return new Velocity(currentVelocity.getDx(), -currentVelocity.getDy());
            case (4):
                return Velocity.fromAngleAndSpeed(30,
                        Math.sqrt(Math.pow(currentVelocity.getDx(), 2) + Math.pow(currentVelocity.getDy(), 2)));
            case (5):
                return Velocity.fromAngleAndSpeed(60,
                        Math.sqrt(Math.pow(currentVelocity.getDx(), 2) + Math.pow(currentVelocity.getDy(), 2)));
            default:
                return new Velocity(currentVelocity.getDx(), -currentVelocity.getDy());
            }
        } else if (this.getCollisionRectangle().getLeft().pointOnLine(collisionPoint)
                || this.getCollisionRectangle().getRigth().pointOnLine(collisionPoint)) {
            return new Velocity(-currentVelocity.getDx(), currentVelocity.getDy());
        } else {
            return new Velocity(currentVelocity.getDx(), -currentVelocity.getDy());
        }

    }

    /**
     * addToGame. Add this paddle to the game.
     *
     * @param g
     *            game
     */
    public void addToGame(GameLevel g) {
        g.addCollidable(this);
        g.addSprite(this);
    }

    /**
     * Draws the paddle on the given surface. Implementation of Sprite interface
     * method.
     *
     * @param d
     *            where to draw the ball.
     */
    public void drawOn(DrawSurface d) {
        d.setColor(this.color);
        d.fillRectangle((int) this.block.getUpperLeft().getX(), (int) this.block.getUpperLeft().getY(),
                (int) this.block.getWidth(), (int) this.block.getHeight());
        d.setColor(java.awt.Color.gray);
        d.drawRectangle((int) this.block.getUpperLeft().getX(), (int) this.block.getUpperLeft().getY(),
                (int) this.block.getWidth(), (int) this.block.getHeight());
    }
}
