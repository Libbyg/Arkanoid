
/**
 * @author Libby Goldin 204566236
 * @version 1.0
 * @since 21/6/2020
 */
package game.animations;
import biuoop.DrawSurface;
import biuoop.Sleeper;
import game.collections.SpriteCollection;
import java.awt.Color;

/**
 * countDownAnimation class.
 */
public class CountDownAnimation implements Animation {
    /**
     * Fields.
     */
    private double numOfSeconds;
    private int startingCount;
    private int currentCount;
    private SpriteCollection sprites;
    private boolean stop;

    /**
     * Constructor.
     * @param numOfSeconds the countDown will count
     * @param startingCount number where count starts
     * @param sprites Collection that will be drawn on surface and loaded to game.
     */
    public CountDownAnimation(double numOfSeconds, int startingCount, SpriteCollection sprites) {
        this.numOfSeconds = numOfSeconds;
        this.startingCount = startingCount;
        this.sprites = sprites;
        this.currentCount = startingCount;
        this.stop = false;
    }

    /**
     * shouldStop method suppose to return true or false for count down to stop.
     * @return boolean for the countDown animation whether it suppose to stop
     */
    @Override
    public boolean shouldStop() {
        return this.stop;
    }

    /**
     * doOneFrame method responsible for drawing sprites and the countDown too.
     * @param d the DrawSurface will be actually drawn i.e. updated
     */
    @Override
    public void doOneFrame(DrawSurface d) {
        Sleeper sleeper = new Sleeper();
        if (this.currentCount < this.startingCount) {
            sleeper.sleepFor((long) ((this.numOfSeconds / this.startingCount) * 1000));
        }
        sprites.drawAllOn(d);
        if (this.currentCount != 0) {
            d.setColor(Color.RED);
            d.drawText(d.getWidth() / 2, d.getHeight() / 5, Integer.toString(this.currentCount), 32);
        }
        this.currentCount--;
        if (currentCount < 0) {
            d.setColor(Color.RED);
            d.drawText(d.getWidth() / 2, d.getHeight() / 5, "GO!", 32);
        }

        if (currentCount < -1) {
            this.stop = true;
        }

    }

}
