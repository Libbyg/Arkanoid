/**
 * @author Libby Goldin 204566236
 * @version 1.0
 * @since 2020-04-22
 */
// Velocity specifies the change in position on the `x` and the `y` axes.
public class Velocity {
    /**
     * Fields of class Velocity.
     */
    private double dx;
    private double dy;

    /**
     * Constructor that receives doubles.
     *
     * @param dx double.
     * @param dy double.
     */
    public Velocity(double dx, double dy) {
        this.dx = dx;
        this.dy = dy;
    }

    /**
     * Constructor that receives integers.
     *
     * @param dx int.
     * @param dy int.
     */
    public Velocity(int dx, int dy) {
        this((double) dx, (double) dy);
    }

    /**
     * the public method getDx() returns the value of dx.
     *
     * @return this.dx double.
     */
    public double getDx() {
        return this.dx;
    }


    /**
     * the public method getDy() returns the value of dy.
     *
     * @return this.dy double.
     */
    public double getDy() {
        return this.dy;
    }


    /**
     * Take a point with position (x,y) and return a new point with position (x+dx, y+dy)
     *
     * @return newPoint
     */
    public Point applyToPoint(Point p) {
        double newX = p.getX() + this.dx;
        double newY = p.getY() + this.dy;
        return new Point(newX, newY);
    }

    public double speed() {
        /* dx = sin(angle) * speed
           dy = cos(angle-180) * speed = -cos(angle)
           sqrt((dx)^2 + (-dy)^2 = sin(angle)^2*speed^2 + cos(angle)^2*speed^2)=
           sqrt(speed^2 *(cos(angle)^2+sin(angle)^2) = sqrt(speed^2*1)=speed
         */
        return Math.hypot(this.dx, this.dy);
    }

    public double angle() {
        double angle = Math.toDegrees(Utilities.calculateAngle(this.dx, -this.dy));
        return angle;
    }

    public Velocity changeDirection(double x, double y) {
        if (Double.isNaN(x) || x == 0) {
            x = 1;
        }
        if (Double.isNaN(y) || y == 0) {
            y = 1;
        }
        return new Velocity(this.dx * Math.signum(x), this.dy * Math.signum(y));
    }

    public Velocity changeSigns(Side side) {
        Velocity v;
        switch (side) {
            case TOP:
            case BOTTOM:
                v = this.changeDirection(1, -1);
                break;
            case LEFT:
            case RIGHT:
                v = this.changeDirection(-1, 1);
                break;
            case CORNER:
                v = this.changeDirection(-1, -1);
                break;
            case NONE:
            default:
                v = this.changeDirection(1, 1);
                break;
        }
        return v;
    }

    /**
     * the public method fromAngleAndSpeed() returns the values of dx
     * and dy as an ordered set.
     *
     * @param angle double.
     * @param speed double.
     * @return Velocity(dx, dy) Velocity.
     */
    public static Velocity fromAngleAndSpeed(double angle, double speed) {
        double dx = Math.sin(Math.toRadians(angle)) * Math.abs(speed);
        double dy = Math.cos(Math.toRadians(angle - 180)) * Math.abs(speed);
        return new Velocity(dx, dy);
    }
}