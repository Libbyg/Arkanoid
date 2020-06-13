/**
 * @author Libby Goldin 204566236
 * @version 1.0
 * @since 2020-05-06
 */
import biuoop.DrawSurface;
import java.awt.Color;


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
     * constructor
     * @param center
     * @param radius
     * @param color
     */
    public Ball(Point center, int radius, Color color) {
        this.center = center;
        this.radius = radius;
        this.color = color;
    }

    /**
     * constructor
     * @param x
     * @param y
     * @param radius
     * @param color
     */
    public Ball(int x, int y, int radius, Color color) {
        this(new Point(x, y), radius, color);
    }

    /**
     * "getters" - get x coordinate of center point of the ball
     * @return int x
     */
    public int getX() {
        return (int) this.center.getX();
    }

    /**
     * "getters" - get y coordinate of center point of the ball
     * @return int y
     */

    public int getY() {
        return (int) this.center.getY();
    }

    /**
     * methos returns ball's size- its radius
     * @return int size i.e. radius
     */
    public int getSize() {
        return this.radius;
    }

    /**
     * methos returns ball's color
     * @return color
     */
    public Color getColor() {
        return this.color;
    }

    /**
     * methos draws the ball on surface gui
     * @param surface
     */
    // draw the ball on the given DrawSurface
    public void drawOn(DrawSurface surface) {
        surface.setColor(this.color);
        surface.fillCircle(this.getX(), this.getY(), this.getSize());
        surface.setColor(Color.BLACK);
        surface.drawCircle(this.getX(), this.getY(), this.getSize());
    }

    /**
     * methos sets ball's velocity
     * @param dx
     * @param dy
     */
    public void setVelocity(double dx, double dy) {
        this.setVelocity(new Velocity(dx, dy));
    }

    /**
     * methos returns  ball's velocity
     * @return
     */
    public Velocity getVelocity() {
        return this.velocity;
    }

    /**
     * method sets ball's velocity (this time different arguments)
     * @param v
     */
    public void setVelocity (Velocity v) {
        this.velocity = v;
    }

    /**
     * the method activates ball's move
     */
    public void moveOneStep() {
        Line trajectory = this.calcTrajectory();
        CollisionInfo info = this.environment.getClosestCollision(trajectory);
        if (info != null) {
            Velocity v = Velocity.fromAngleAndSpeed(this.velocity.angle(), this.radius).changeDirection(-1, -1);
            this.center = v.applyToPoint(info.collisionPoint());
            this.velocity = info.collisionObject().hit(info.collisionPoint(), this.velocity);
        } else {
            this.center = this.velocity.applyToPoint(this.center);
        }
    }

    /**
     * method calculates the ball's trajectory line of movement and returns it
     * @return Line
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
     * method sets the Environment in which the ball will be in use
     * @param environment
     */
    public void setEnvironment(GameEnvironment environment) {
        this.environment = environment;
    }

    /**
     *making the ball move each time one step more
     */
    @Override
    public void timePassed() {
        this.moveOneStep();
    }

    /**
     * adding the ball to the game
     * @param game
     */
    public void addToGame(Game game) {
        game.addSprite(this);
        game.assignEnv(this);
    }

}
