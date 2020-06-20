package game.objects; /**
 * @author Libby Goldin 204566236
 * @version 1.0
 * @since 2020-05-06
 */
import biuoop.DrawSurface;
import game.levels.GameLevel;
import game.listeners.HitListener;
import game.listeners.HitNotifier;
import geometry.Line;
import geometry.Point;
import geometry.Rectangle;
import utilities.Side;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * block class implements the interfaces Sprite and Collidable.
 */
public class Block implements Collidable, Sprite, HitNotifier {
    /**
     * Fields.
     */
    private Rectangle rectangle;
    private Color color;
    private List<HitListener> hitListeners = new ArrayList<HitListener>();

    /**
     * Constructor.
     * @param rectangle block's shape
     * @param color of the block
     */
    public Block(Rectangle rectangle, Color color) {
        this.rectangle = rectangle;
        this.color = color;
    }

    /**
     * Constructor.
     * @param topLeft upper left point of block
     * @param bottomRight bottom right point of the block
     * @param color color of the block
     */
    public Block(Point topLeft, Point bottomRight, Color color) {
        this(new Rectangle(topLeft, bottomRight), color);
    }

    /**
     * Constructor.
     * @param topLeft point of the block
     * @param width of the block
     * @param height of the block
     * @param color of the block
     */
    public Block(Point topLeft, double width, double height, Color color) {
        this(new Rectangle(topLeft, width, height), color);
    }

    /**
     * get geometry.Rectangle returns rectangle of the block.
     * @return rectangle
     */
    public Rectangle getRectangle() {
        return this.rectangle;
    }

    /**
     * get color returns color of the block.
     * @return color of the block
     */
    public Color getColor() {
        return this.color;
    }

    /**
     * width method returns rectangle's width.
     * @return double size of width
     */
    public double width() {
        return this.rectangle.width();
    }

    /**
     *  method height returns height size of block.
     * @return double size of height
     */
    public double height() {
        return this.rectangle.height();
    }

    /**
     * topLeft method returns the top left point of the block.
     * @return topLeft geometry.Point of block.
     */
    public Point topLeft() {
        return this.rectangle.topLeft();
    }

    /**
     * the method returns the bottom Right point pf the block.
     * @return bottomRight point of block
     */
    public Point bottomRight() {
        return this.rectangle.bottomRight();
    }

    /**
     * getEdge returns the edge of block's rectangle's.
     * @param edge returns the side name of block's rectangle's edge.
     * @return line object
     */
    public Line getEdge(Side edge) {
        return this.rectangle.getEdge(edge);
    }

    /**
     *
     * @return
     */
    @Override
    public Rectangle getCollisionRectangle() {
        return this.rectangle;
    }

    /**
     * hit function checks whether there was a hit between the hitting object and the block.
     * if there was the hit the velocity will be changed
     * @param hitter ball that hits the block
     * @param collisionPoint collision point of the all and the block
     * @param currentVelocity the velocity of the ball at the hit moment
     * @return velocity changed after the hit if the hit happened.
     */
    @Override
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        Side hitSide = Side.NONE;
        for (Side edge : this.rectangle.getEdges().keySet()) {
            if (this.getEdge(edge).pointOnLine(collisionPoint)) {
                if (hitSide != Side.NONE) {
                    hitSide = Side.CORNER;
                    break;
                }
                hitSide = edge;
            }
        }
        this.notifyHit(hitter);
        return currentVelocity.changeSigns(hitSide);
    }

    /**
     * getCollision info return all the needed details
     * regarding the collision between block and other object.
     * @param trajectory the path made before the collision happened.
     * @return
     */
    @Override
    public CollisionInfo getCollisionInfo(Line trajectory) {
        Point collision = trajectory.closestIntersectionToStartOfLine(this.rectangle);
        return collision != null ? new CollisionInfo(collision, this) : null;

    }

    /**
     * the drawOnn omethod draws the lock on the drawing surface.
     * @param surface where block will be drawn
     */
    public void drawOn(DrawSurface surface) {
        surface.setColor(this.color);
        surface.fillRectangle(((int) this.topLeft().getX()), ((int) this.topLeft().getY()), ((int) this.width()),
                ((int) this.height()));
        surface.setColor(Color.BLACK);
        surface.drawRectangle(((int) this.topLeft().getX()), ((int) this.topLeft().getY()), ((int) this.width()),
                ((int) this.height()));
    }

    /**
     * timePassed method does nothing.
     */
    @Override
    public void timePassed() {
    }

    /**
     * addToGame method adds the block to game.
     * @param game passed to method to be changed - block will be added to it.
     */
    public void addToGame(GameLevel game) {
        game.addSprite(this);
        game.addCollidable(this);
    }

    /**
     *removeFromGame method removes the block from the game.
     * @param game passed to method to be changed
     * remove the block from it and it sprite list.
     */
    public void removeFromGame(GameLevel game) {
        game.removeSprite(this);
        game.removeCollidable(this);
    }

    /**
     * addHitListener method adds listener to the block.
     * @param hl listener is added after block is hit
     */
    @Override
    public void addHitListener(HitListener hl) {
        this.hitListeners.add(hl);

    }

    /**
     * removeHitListener method removes the listener from the lock.
     * @param hl listener is removed after block is hit
     */
    @Override
    public void removeHitListener(HitListener hl) {
        this.hitListeners.remove(hl);
    }

    /**
     * is called whenever a hit() occurs
     * and will notify all of the registered HitListener.
     * @param hitter Ball object that hit the block.
     */
    private void notifyHit(Ball hitter) {
        // Make a copy of the hitListeners before iterating over them.
        List<HitListener> listeners = new ArrayList<HitListener>(this.hitListeners);
        // Notify all listeners about a hit event:
        for (HitListener hl : listeners) {
            hl.hitEvent(this, hitter);
        }
    }
}
