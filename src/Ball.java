import biuoop.DrawSurface;

import java.awt.*;


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


    public Ball(Point center, int radius, Color color) {
        this.center = center;
        this.radius = radius;
        this.color = color;
    }

    public Ball(int x, int y, int radius, Color color) {
        this(new Point(x, y), radius, color);
    }

    // accessors
    public int getX() {
        return (int) this.center.getX();
    }

    public int getY() {
        return (int) this.center.getY();
    }

    public int getSize() {
        return this.radius;
    }

    public Color getColor() {
        return this.color;
    }

    // draw the ball on the given DrawSurface
    public void drawOn(DrawSurface surface) {
        surface.setColor(this.color);
        surface.fillCircle(this.getX(), this.getY(), this.getSize());
        surface.setColor(Color.BLACK);
        surface.drawCircle(this.getX(), this.getY(), this.getSize());
    }

    public void setVelocity(double dx, double dy) {
        this.setVelocity(new Velocity(dx, dy));
    }

    public Velocity getVelocity() {
        return this.velocity;
    }

    public void setVelocity(Velocity v) {
        this.velocity = v;
    }

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

    public void setEnvironment(GameEnvironment environment) {
        this.environment = environment;
    }

    @Override
    public void timePassed() {
        this.moveOneStep();
    }
}
