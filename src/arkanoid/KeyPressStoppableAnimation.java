package arkanoid;

import animation.Animation;
import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

/**
 * KeyPressStoppableAnimation class.
 *
 * @author Alihom
 *
 */
public class KeyPressStoppableAnimation implements Animation {
    private KeyboardSensor sensor;
    private String key;
    private Animation animation;
    private boolean stop;
    private boolean isAlreadyPressed;

    /**
     * KeyPressStoppableAnimation constructor.
     *
     * @param sensor
     *            the keyboard.
     * @param key
     *            the key to stop.
     * @param animation
     *            the animation.
     */
    public KeyPressStoppableAnimation(KeyboardSensor sensor, String key, Animation animation) {
        this.sensor = sensor;
        this.key = key;
        this.animation = animation;
        this.stop = false;
        this.isAlreadyPressed = true;
    }

    @Override
    public void doOneFrame(DrawSurface d, double dt) {
        if (!this.sensor.isPressed(key) && this.isAlreadyPressed) {
            this.isAlreadyPressed = false;
        } else if (this.sensor.isPressed(key) && (!this.isAlreadyPressed)) {
            this.stop = true;
        }
        this.animation.doOneFrame(d, dt);
    }

    @Override
    public boolean shouldStop() {
        return this.stop;
    }
}