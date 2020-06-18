/**
 * @author Libby Goldin 204566236
 * @version 1.0
 * @since 2020-05-06
 */
public class CollisionInfo {
    /**
     * Fields
     */
    private Point collisionPoint;
    private Collidable collisionObject;

    /**
     * constructor
     * @param collisionPoint
     * @param collisionObject
     */
    public CollisionInfo(Point collisionPoint, Collidable collisionObject) {
        this.collisionPoint = collisionPoint;
        this.collisionObject = collisionObject;
    }

    /**
     * returning collision point
     * @return geometry.Point
     */
    public Point collisionPoint() {
        return this.collisionPoint;
    }

    public Collidable collisionObject() {
        return this.collisionObject;
    }
}
