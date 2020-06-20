
/**
 * @author Libby Goldin 204566236
 * @version 1.0
 * @since 21/6/2020
 */
package game.animations;
import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

/**
 * KeyPressStoppable class.
 */
public class KeyPressStoppableAnimation implements Animation {
    /**
     * Fields.
     */
    private Animation animation;
    private KeyboardSensor keyboard;
    private String key;
    private boolean stop = false;
    private boolean isAlreadyPressed = true;

    /**
     * Constructor.
     * @param sensor of keyboard of gui
     * @param key string i.e. the key that will be pressed and animation will respond to.
     * @param animation of the game
     */
    public KeyPressStoppableAnimation(KeyboardSensor sensor, String key, Animation animation) {
        this.keyboard = sensor;
        this.key = key;
        this.animation = animation;
    }

    /**
     * souldStop animation decides if animation will stop or not.
     * @return boolean true or false if animation suppose to stop.
     */
    @Override
    public boolean shouldStop() {
        return this.stop;
    }

    /**
     * doOneFrame method will draw the animation and
     * check if key is pressed and respond accordingly.
     * @param d the DrawSurface will be actually drawn i.e. updated
     */
    @Override
    public void doOneFrame(DrawSurface d) {
        this.animation.doOneFrame(d);
        if (this.keyboard.isPressed(this.key.toLowerCase()) || this.keyboard.isPressed(this.key.toUpperCase())) {
            if (!this.isAlreadyPressed) {
                this.stop = true;
            }
        } else {
            this.isAlreadyPressed = false;
        }
    }
}
