package game;// a BlockRemover is in charge of removing blocks from the game, as well as keeping count
// of the number of blocks that remain.

/**
 * BlockRemover class is kind of HitListener.
 */
public class BlockRemover implements HitListener {
    /**
     * Fields.
     */
    private GameLevel game;
    private Counter remainingBlocks;

    /**
     * Constructor.
     * @param game from which the blocks will be removed.
     * @param removedBlocks number of blocks that were removed from the game.
     */
    public BlockRemover(GameLevel game, Counter removedBlocks) {
        this.game = game;
        this.remainingBlocks = removedBlocks;
    }

    /**
     * hitEvent method removes listeners from hit
     * block and then the hit block itself from the game.
     * @param beingHit block that was hit
     * @param hitter the ball that hit the block
     */
    // Blocks that are hit should be removed
    // from the game. Remember to remove this listener from the block
    // that is being removed from the game.
    public void hitEvent(Block beingHit, Ball hitter) {
        beingHit.removeHitListener(this);
        beingHit.removeFromGame(this.game);
        this.remainingBlocks.decrease(1);
    }
}