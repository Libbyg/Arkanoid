
/**
 * @author Libby Goldin 204566236
 * @version 1.0
 * @since 21/6/2020
 */
package game.animations;
import biuoop.GUI;
import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import biuoop.Sleeper;

/**
 * AnimationRunner class.
 * this class object will actually run the animation.
 */
public class AnimationRunner {
    /**
     * Fields.
     */
    private GUI gui;
    private int framesPerSecond;

    /**
     * Constructor.
     * @param gui of the game
     * @param framesPerSecond of the animation in the game
     */
    public AnimationRunner(GUI gui, int framesPerSecond) {
        this.gui = gui;
        this.framesPerSecond = framesPerSecond;
    }

    /**
     * run method runs the animation.
     * @param animation of the game.
     */
    public void run(Animation animation) {
        int millisecondsPerFrame = 1000 / this.framesPerSecond;
        Sleeper sleeper = new Sleeper();
        while (!animation.shouldStop()) {
            long startTime = System.currentTimeMillis(); // timing
            DrawSurface d = gui.getDrawSurface();
            animation.doOneFrame(d);
            gui.show(d);
            long usedTime = System.currentTimeMillis() - startTime;
            long milliSecondLeftToSleep = millisecondsPerFrame - usedTime;
            if (milliSecondLeftToSleep > 0) {
                sleeper.sleepFor(milliSecondLeftToSleep);
            }
        }
    }

    /**
     * getKeyboardSensor returns the keyboard sensor of the gui.
     * @return keyboard sensor of game's gui.
     */
    public KeyboardSensor getKeyboardSensor() {
        return gui.getKeyboardSensor();
    }
}