
/**
 * @author Libby Goldin 204566236
 * @version 1.0
 * @since 21/6/2020
 */
package game.animations;
import biuoop.DrawSurface;
import java.awt.Color;

/**
 * Win screen .
 */
public class WinScreen implements Animation {
    /**
     * static final variables.
     */
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
     * @param score from game level to be displayed on win screen.
     */
    public WinScreen(int score) {
        this.score = score;
    }

    /**
     * doOneFrame method draws the win screen.
     * @param d the DrawSurface will be actually drawn i.e. updated
     */
    public void doOneFrame(DrawSurface d) {
        d.setColor(PINK);
        d.drawRectangle(RECT_ANIMATION_X, RECT_ANIMATION_Y, RECT_ANIMATION_WIDTH, RECT_ANIMATION_HEIGHT);
        d.fillRectangle(RECT_ANIMATION_X, RECT_ANIMATION_Y, RECT_ANIMATION_WIDTH, RECT_ANIMATION_HEIGHT);
        d.setColor(Color.WHITE);
        d.drawText(150, d.getHeight() / 2, "You Win! Your score is " + this.score, 32);
    }

    /**
     * shouldStop method decides when the screen animation will stop..
     * @return false since keyPressStoppable will be responsible for stopping animation of win screen
     */
    public boolean shouldStop() {
        return false;
    }
}
