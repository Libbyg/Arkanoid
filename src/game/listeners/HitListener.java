package game.listeners;

import game.objects.Ball;
import game.objects.Block;

/**
 * HitListener interface for the info of object that was hit to be transferred.
 */
public interface HitListener {
    /**
     * This method is called whenever the beingHit object is hit.
     * @param beingHit is the block that the ball hit.
     * @param hitter is the Ball that's doing the hitting.
     */
    void hitEvent(Block beingHit, Ball hitter);
}