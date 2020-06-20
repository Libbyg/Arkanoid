package game.listeners; /**
 * @author Libby Goldin id:204566236
 * @version 1
 * @since 3/6/2020
 */

import game.objects.Ball;
import game.objects.Block;

/**
 * score tracking listener to track the score.
 */
public class ScoreTrackingListener implements HitListener {
    //private static final int BLOCK_HIT_SCORE = 5;
    private Counter currentScore;

    /**
     * Constructor.
     * @param scoreCounter counts points in the game.
     */
    public ScoreTrackingListener(Counter scoreCounter) {
        this.currentScore = scoreCounter;
    }

    /**
     * hitEvent method to increase score for every
     * block that was hit and than removing the score listener from it.
     * @param beingHit is the block that the ball hit.
     * @param hitter is the Ball that's doing the hitting.
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        this.currentScore.increase(5);
        beingHit.removeHitListener(this);
    }
}