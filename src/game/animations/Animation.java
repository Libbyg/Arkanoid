
/**
 * @author Libby Goldin 204566236
 * @version 1.0
 * @since 21/6/2020
 */
package game.animations;
import biuoop.DrawSurface;

/**
 * Animation interface.
 */
public interface Animation {
    /**
     * shouldStop method indicates if animation suppose to run or to stop.
     * @return true or false if animation suppose to stop
     */
    boolean shouldStop();

    /**
     * doOneFrame method suppose to create an animation.
     * @param d the DrawSurface will be actually drawn i.e. updated
     */
    void doOneFrame(DrawSurface d);

}
