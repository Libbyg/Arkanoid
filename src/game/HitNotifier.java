package game;

/**
 * HitNotifier listener transfers the information of object being hit.
 */
public interface HitNotifier {
    /**
     *Add hl as a listener to hit events.
     * @param hl to be added to objects to be hit
     */
    void addHitListener(HitListener hl);

    /**
     * Remove hl from the list of listeners to hit events.
     * @param hl to be removed from hit objects before removing the hit objects themselves.
     */
    void removeHitListener(HitListener hl);
}