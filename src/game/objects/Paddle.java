
/**
 * @author Libby Goldin 204566236
 * @version 1.0
 * @since 21/6/2020
 */
package game.objects;
import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import game.collections.GameEnvironment;
import game.levels.GameLevel;
import geometry.Line;
import geometry.Point;
import geometry.Rectangle;
import utilities.Axis;
import utilities.Side;

import java.awt.Color;

/**
 * paddle class implements two interfaces: Collidable and Sprite.
 */
public class Paddle implements Sprite, Collidable {
    //private static final int PADDLE_MOVEMENT = 10;
    private static final Color FILL_COLOR = Color.LIGHT_GRAY;
    //private static final Color BORDER_COLOR = Color.BLACK;
    private Block block;
    private KeyboardSensor keyboard;
    private GameEnvironment environment;
    private int speed;
    /**
     * constructor.
     * @param block object of th paddle
     * @param keyboard sensor to make the paddle react to buttons press of the user
     * @param speed of paddle
     */
    public Paddle(Block block, KeyboardSensor keyboard, int speed) {
        this.block = block;
        this.keyboard = keyboard;
        this.speed = speed;
    }

    /**
     * constructor.
     * @param rect rectangle object of the paddle
     * @param keyboard sensor to make the paddle react to buttons press of the user
     * @param speed of paddle
     */
    public Paddle(Rectangle rect, KeyboardSensor keyboard, int speed) {
        this(new Block(rect, FILL_COLOR), keyboard, speed);
    }

    /**
     * constructor.
     * @param rect rectangle object of the paddle
     * @param color of the paddle
     * @param keyboard sensor to make the paddle react to buttons press of the user
     * @param speed of paddle
     */
    public Paddle(Rectangle rect, Color color, KeyboardSensor keyboard, int speed) {
        this(new Block(rect, color), keyboard, speed);
    }

    /**
     * moveLeft Method moves the paddle through
     * line trajectory and checks if it suppose to collide into an object
     * the movements is done by updating the rectangle points.
     */
    public void moveLeft() {
        Line trajectory = this.movementTrajectory(Side.LEFT);
        CollisionInfo info = this.environment.getClosestCollision(trajectory);
        if (info != null) {
            this.block = new Block(info.collisionPoint(), this.width(), this.height(), this.getColor());
        } else {
            this.block = new Block(trajectory.end(), this.width(), this.height(), this.getColor());
        }
    }

    /**
     * moveLeft Method moves the paddle through
     * line trajectory and checks if it suppose to collide into an object
     * the movements is done by updating the rectangle points.
     */
    public void moveRight() {
        //update the top left point
        Line trajectory = this.movementTrajectory(Side.RIGHT);
        CollisionInfo info = this.environment.getClosestCollision(trajectory);
        if (info != null) {
            this.block = new Block(info.collisionPoint().shiftPoint(-this.width(), Axis.X),
                    this.width(), this.height(), this.getColor());
        } else {
            this.block = new Block(trajectory.end().shiftPoint(-this.width(), Axis.X), this.width(),
                    this.height(), this.getColor());
        }
    }

    /**
     * movementTrajectory method calculates.
     * @param side name (enum) of rectangle
     * @return geometry.Line trajectory i.e. path
     */
    private Line movementTrajectory(Side side) {
        Point endPoint, startPoint;
        switch (side) {
            default:
            case LEFT:
                startPoint = this.block.topLeft();
                endPoint = startPoint.shiftPoint(-this.speed, Axis.X);
                break;
            case RIGHT:
                startPoint = this.block.topLeft().shiftPoint(this.width(), Axis.X);
                endPoint = startPoint.shiftPoint(this.speed, Axis.X);
                break;
        }
        return new Line(startPoint, endPoint);
    }

    /**
     * activating timePassed on right or left paddle moves.
     */
    // Sprite
    public void timePassed() {
        if (keyboard.isPressed(KeyboardSensor.RIGHT_KEY)) {
            this.moveRight();
        } else if (keyboard.isPressed(KeyboardSensor.LEFT_KEY)) {
            this.moveLeft();
        }
    }

    /**
     * drawing the paddle on the gui surface.
     * @param d surface on which the paddle will be drawn
     */
    public void drawOn(DrawSurface d) {
        this.block.drawOn(d);
    }

    /**
     * adding the paddle to sprites collection and to collidables collection.
     * @param g game to which the paddle will be added
     */
    // Add this paddle to the game.
    public void addToGame(GameLevel g) {
        g.addCollidable(this);
        g.addSprite(this);
        this.environment = g.getEnvironment();
        this.environment.removeCollidable(this);
    }

    /**
     *method getCollisionRectangle returns the rectangle object.
     * @return rectangle
     */
    @Override
    public Rectangle getCollisionRectangle() {
        return block.getCollisionRectangle();
    }

    /**
     * the method getCollisionInfo checks if there is going to be a collision.
     * Then returns collisionInfo object which stores collision point and collidable object.
     * @param trajectory
     * @return collisionInfo
     */
    @Override
    public CollisionInfo getCollisionInfo(Line trajectory) {
        CollisionInfo info = this.block.getCollisionInfo(trajectory);
        return info != null ? new CollisionInfo(info.collisionPoint(), this) : null;
    }

    @Override
    /**
     * hit method uses hitHelp method to calc the
     * new velocity of object after hit in the paddle (according to area)
     * if there wasn't a hit the velocity will not be changed.
     * @return velocity
     */
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        if (!this.block.getEdge(Side.TOP).pointOnLine(collisionPoint)) {
            return this.block.hit(hitter, collisionPoint, currentVelocity);
        } else {
            return this.topHitHelp(collisionPoint, currentVelocity);
        }
    }

    /**
     *topHitHelp method helps to calc the area of the paddle and the
     * angle the object will turn after the hit.
     * @param collisionPoint where ball hot the paddle
     * @param currentVelocity i.e. before the hit
     * @return new velocity after hit
     */
    private Velocity topHitHelp(Point collisionPoint, Velocity currentVelocity) {
        //divide the paddle to 5 equal parts
        double partSize = getCollisionRectangle().width() / 5;
        double mySector = Math.abs(this.block.topLeft().getX() - collisionPoint.getX());
        double topLeftX = getCollisionRectangle().topLeft().getX();
        Velocity v;
        //first area
        if (mySector >= 0 && mySector < partSize) {
            v = (Velocity.fromAngleAndSpeed(300, currentVelocity.speed()));
        } /*second area*/ else if (mySector >= partSize && mySector < partSize * 2) {
            v = (Velocity.fromAngleAndSpeed(300, currentVelocity.speed()));
        } /*third area*/ else if (mySector >= partSize * 2 && mySector < partSize * 3) {
            v = Velocity.fromAngleAndSpeed(0, currentVelocity.speed());
        } /*fourth area*/ else if (mySector >= partSize * 3 && mySector < partSize * 4) {
            v = (Velocity.fromAngleAndSpeed(30, currentVelocity.speed()));
        } /*fifth area*/ else {
            v = (Velocity.fromAngleAndSpeed(60, currentVelocity.speed()));
        }
        return v;
    }

    /**
     * method width return's paddle's width.
     * @return width
     */
    public double width() {
        return block.width();
    }

    /**
     * method height returns paddle's height.
     * @return height
     */
    public double height() {
        return block.height();
    }

    /**
     * method getFillColor returns paddle color.
     * @return color
     */
    public Color getColor() {
        return block.getColor();
    }

    /**
     * method returns the center point of the paddle (to locate the balls there).
     * @return center point of paddle.
     */
    public Point getPaddleCenter() {
        return this.block.getEdge(Side.TOP).middle();
    }

}