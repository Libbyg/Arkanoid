package game; /**
 * @author Libby Goldin 204566236
 * @version 1.0
 * @since 2020-05-06
 */
import biuoop.DrawSurface;

/**
 * sprite interface.
 */
public interface Sprite {
    /**
     * draw the sprite to the screen.
     * @param d surface on which we draw the sprites
     */
    void drawOn(DrawSurface d);

    /**
     * notify the sprite that time has passed.
     */
    void timePassed();
}