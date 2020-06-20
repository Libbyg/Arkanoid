
/**
 * @author Libby Goldin 204566236
 * @version 1.0
 * @since 21/6/2020
 */
package game.animations;
import biuoop.DrawSurface;
import java.awt.Color;

/**
 * EndScreen class will be represented as animation when game ended.
 */
public class EndScreen implements Animation {
    private static final Color PINK = new Color(231, 98, 129);
    private static final int RECT_ANIMATION_X = 100;
    private static final int RECT_ANIMATION_Y = 150;
    private static final int RECT_ANIMATION_WIDTH = 600;
    private static final int RECT_ANIMATION_HEIGHT = 300;
    /**
     * Fields.
     */
    private int score;

    /**
     * Constructor.
     * @param score that will be passed to endScreen from gameLevel
     */
    public EndScreen(int score) {
        this.score = score;
    }

    /**
     * doOneFrame draws the endScreen on DrawSurface.
     * @param d the DrawSurface will be actually drawn i.e. updated
     */
    public void doOneFrame(DrawSurface d) {
        d.setColor(PINK);
        d.drawRectangle(RECT_ANIMATION_X, RECT_ANIMATION_Y, RECT_ANIMATION_WIDTH, RECT_ANIMATION_HEIGHT);
        d.fillRectangle(RECT_ANIMATION_X, RECT_ANIMATION_Y, RECT_ANIMATION_WIDTH, RECT_ANIMATION_HEIGHT);
        d.setColor(Color.WHITE);
        d.drawText(150, d.getHeight() / 2, "Game Over. Your score is " + this.score, 32);
    }

    /**
     * returns if endScreen suppose to stop.
     * @return false because keyPressStoppable will be in charge to stop the animation.
     */
    public boolean shouldStop() {
        return false;
    }
}
