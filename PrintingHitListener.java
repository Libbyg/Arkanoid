/**
 * printing when the ball hit block.
 */
public class PrintingHitListener implements HitListener {
    public void hitEvent(Block beingHit, Ball hitter) {
        System.out.println("A Block was hit.");
    }
}