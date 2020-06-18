package game;

/**
 * @author Libby Goldin id 204566236
 * @version 1
 * @since 3/6/2020
 */
public class BallRemover implements HitListener {
    private GameLevel game;
    private Counter remainingBalls;
    /**
     * constructor.
     * @param game from which the ball is going to be removed
     * @param remainingBalls number of the remained balls in the game
     */
    public BallRemover(GameLevel game, Counter remainingBalls) {
        this.game = game;
        this.remainingBalls = remainingBalls;
    }

    /**
     * hitEvent method removes the ball remover listener.
     * @param beingHit
     * @param hitter
     */
    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        hitter.removeFromGame(this.game);
        this.remainingBalls.decrease(1);
    }
}
