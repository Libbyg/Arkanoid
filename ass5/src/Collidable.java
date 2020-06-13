/**
 * @author Libby Goldin id:204566236
 * @since 3/6/2020
 * @version 1
 */

/**
 * Collidable interface i.e. of objects that can bbe collided.
 */
public interface Collidable {
    // Return the "collision shape" of the object.

    /**
     * getCollisionRectangle returns rectangle object of collision.
     * @return rectangle object of collision
     */
    Rectangle getCollisionRectangle();

    // Notify the object that we collided with it at collisionPoint with
    // a given velocity.
    // The return is the new velocity expected after the hit (based on
    // the force the object inflicted on us).

    /**
     * hit method returns the velocity after hit.
     * @param hitter ball that hit the collidable object
     * @param collisionPoint of the colldable object with the other object
     * @param currentVelocity of the object
     * @return new velocity after hit (if happened)
     */
    Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity);

    /**
     * getCollisionInfo returns info about the collision.
     * @param trajectory of the object before the collision moment
     * @return collision information
     */
    CollisionInfo getCollisionInfo(Line trajectory);
}