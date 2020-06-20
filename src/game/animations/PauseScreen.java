
/**
 * @author Libby Goldin 204566236
 * @version 1.0
 * @since 21/6/2020
 */
package game.animations;
import biuoop.DrawSurface;
import java.awt.Color;

/**
 * PauseScreen Class. will create screen animation of pause in the game.
 */
public class PauseScreen implements Animation {
    /**
     * static final variables.
     */
    private static final Color PINK = new Color(231, 98, 129);
    private static final int RECT_ANIMATION_X = 100;
    private static final int RECT_ANIMATION_Y = 150;
    private static final int RECT_ANIMATION_WIDTH = 600;
    private static final int RECT_ANIMATION_HEIGHT = 300;

    /**
     * doOneFrame will draw the pause screen on draw surface.
     * @param d the DrawSurface will be actually drawn i.e. updated
     */
    public void doOneFrame(DrawSurface d) {
        d.setColor(PINK);
        d.drawRectangle(RECT_ANIMATION_X, RECT_ANIMATION_Y, RECT_ANIMATION_WIDTH, RECT_ANIMATION_HEIGHT);
        d.fillRectangle(RECT_ANIMATION_X, RECT_ANIMATION_Y, RECT_ANIMATION_WIDTH, RECT_ANIMATION_HEIGHT);
        d.setColor(Color.WHITE);
        d.drawText(150, d.getHeight() / 2, "paused -- press space to continue", 32);
    }

    /**
     * shouldStop pause screen animation method.
     * @return boolean false since keyPressStoppable will be responsible for stopping the pause screen.
     */
    public boolean shouldStop() {
        return false;
    }
}