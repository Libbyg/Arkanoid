package game.objects; /**
 * @author Libby Goldin 204566236
 * @version 1.0
 * @since 2020-05-06
 */
import biuoop.DrawSurface;
import game.collections.GameEnvironment;
import game.levels.GameLevel;
import geometry.Line;
import geometry.Point;

import java.awt.Color;

/**
 * class all implements ball.
 */
public class Ball implements Sprite {
    /**
     * Class fields.
     */
    //the ball stands still by default
    public static final Velocity DEFAULT_VEL = new Velocity(1, 1);
    private Point center;
    private int radius;
    private Color color;
    private Velocity velocity;
    private GameEnvironment environment;

    /**
     * constructor.
     * @param center of the ball.
     * @param radius of the all
     * @param color of the ball
     */
    public Ball(Point center, int radius, Color color) {
        this.center = center;
        this.radius = radius;
        this.color = color;
    }

    /**
     * constructor.
     * @param x of ball's center
     * @param y of ball's center
     * @param radius of the ball
     * @param color of the ball
     */
    public Ball(int x, int y, int radius, Color color) {
        this(new Point(x, y), radius, color);
    }

    /**
     * "getters" - get x coordinate of center point of the ball.
     * @return int x of ball center point
     */
    public int getX() {
        return (int) this.center.getX();
    }

    /**
     * "getters" - get y coordinate of center point of the ball.
     * @return int y of ball center point.
     */

    public int getY() {
        return (int) this.center.getY();
    }

    /**
     * method returns ball's size- its radius.
     * @return int size i.e. radius
     */
    public int getSize() {
        return this.radius;
    }

    /**
     * method returns ball's color.
     * @return color of the ball
     */
    public Color getColor() {
        return this.color;
    }

    /**
     * method draws the ball on surface gui.
     * @param surface to draw the ball on it.
     */
    // draw the ball on the given DrawSurface
    public void drawOn(DrawSurface surface) {
        surface.setColor(this.color);
        surface.fillCircle(this.getX(), this.getY(), this.getSize());
        surface.setColor(Color.BLACK);
        surface.drawCircle(this.getX(), this.getY(), this.getSize());
    }

    /**
     * method sets ball's velocity.
     * @param dx change in x axis
     * @param dy change in y axis
     */
    public void setVelocity(double dx, double dy) {
        this.setVelocity(new Velocity(dx, dy));
    }

    /**
     * method returns  ball's velocity.
     * @return velocity of the ball
     */
    public Velocity getVelocity() {
        return this.velocity;
    }

    /**
     * method sets ball's velocity (this time different arguments).
     * @param v velocity of the ball
     */
    public void setVelocity(Velocity v) {
        this.velocity = v;
    }

    /**
     * the method activates ball's move.
     */
    public void moveOneStep() {
        Line trajectory = this.calcTrajectory();
        CollisionInfo info = this.environment.getClosestCollision(trajectory);
        if (info != null) {
            Velocity v = Velocity.fromAngleAndSpeed(this.velocity.angle(), this.radius).changeDirection(-1, -1);
            this.center = v.applyToPoint(info.collisionPoint());
            this.velocity = info.collisionObject().hit(this, info.collisionPoint(), this.velocity);
        } else {
            this.center = this.velocity.applyToPoint(this.center);
        }
    }

    /**
     * method calculates the ball's trajectory line of movement and returns it.
     * @return geometry.Line
     */
    private Line calcTrajectory() {
        Point nextPoint;
        if (this.velocity.speed() < this.radius) {
            Velocity v = Velocity.fromAngleAndSpeed(this.velocity.angle(), this.radius);
            nextPoint = v.applyToPoint(this.center);
        } else {
            nextPoint = this.velocity.applyToPoint(this.center);
        }
        return new Line(this.center, nextPoint);
    }

    /**
     * method sets the Environment in which the ball will be in use.
     * @param environment of the game.
     */
    public void setEnvironment(GameEnvironment environment) {
        this.environment = environment;
    }

    /**
     *making the ball move each time one step more.
     */
    @Override
    public void timePassed() {
        this.moveOneStep();
    }

    /**
     * adding the ball to the game.
     * @param game with ball added to it.
     */
    public void addToGame(GameLevel game) {
        game.addSprite(this);
        game.assignEnv(this);
    }

    /**
     * removeFromGame method removes the ball from the game.
     * @param game after it's changed and the ball removed from it.
     */
    public void removeFromGame(GameLevel game) {
        game.removeSprite(this);
        game.assignEnv(this);
    }

}
