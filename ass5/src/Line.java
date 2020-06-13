/**
 * @author Libby Goldin 204566236
 *
 * @version 1.0
 *
 * @since 2020-04-22
 */
import java.util.List;

/**
 * class of Line object.
 */
public class Line {
    /**
     * Class fields.
     */
    private Point start;
    private Point end;
    private Double a, b; //y=ax+b
    private boolean isVertical;

    /**
     * Constructor that receives two Point objects.
     * @param x1 double
     * @param y1 double
     * @param x2 double
     * @param y2 double
     *
     */
    public Line(double x1, double y1, double x2, double y2) {
        this(new Point(x1, y1), new Point(x2, y2));
    }
    /**
     * Constructor that receives two Point objects.
     *
     * @param start Point.
     * @param end Point.
     */
    public Line(Point start, Point end) {
        this.start = start;
        this.end = end;
        this.initializeLine();
    }

    /**
     * private function calculates values of linear equation.
     */
    private void initializeLine() {
        this.calcSlope();
        this.calcB();
    }

    /**
     * public method length()
     * claculates length between two points.
     * @return length double
     */
    //Return the length of the line
    public double length() {
        return (this.start.distance(this.end));
    }

    /**
     * public method middle()
     * which calculates the middle of a line.
     * @return middle Point
     */
    //Returns the middle point of the line
    public Point middle() {
        return this.start.middle(this.end);
    }

    /**
     * public method start point of line.
     * @return start Point
     */
    //Returns the start point of the line
    public Point start() {
        return (this.start);
    }

    /**
     * public method end point of line.
     * @return end Point
     */
    //Returns the end point of the line
    public Point end() {
        return (this.end);
    }

    /**
     * Returns true if the lines intersect, false otherwise.
     * @param other line to check with the mutual position
     * @return boolean true if lines intersect and false if not
     */
    public boolean isIntersecting(Line other) {
        return this.intersectionWith(other) != null;

    }

    /**
     * public function intersetionWith()
     * checks if two lines intersect and returns true,
     * otherwise returns false.
     * @param other Line
     * @return isIntersects boolean
     */
    public Point intersectionWith(Line other) {
        double x, y;
        Point intersection;
        if (this.isVertical && other.isVertical) {
            return null;
        } else if (this.isVertical) {
            x = this.start.getX();
            y = other.a * x + other.b;
            intersection = new Point(x, y);
        } else if (other.isVertical) {
            x = other.start.getX();
            y = this.a * x + this.b;
            intersection = new Point(x, y);
        } else {
            x = (this.b - other.b) / (other.a - this.a);
            y = this.a * x + this.b;
            intersection = new Point(x, y);
        }
        return (this.pointOnLine(intersection) && other.pointOnLine(intersection)) ? intersection : null;
    }

    /**
     * public function pointOnLine() checks if
     * some point is on the line and returns true
     * otherwise returns false- using Triangle inequality method.
     * @param p some Point.
     * @return boolean true if point on the line and false otherwise.
     */
    public boolean pointOnLine(Point p) {
        double length = this.start.distance(p) + p.distance(this.end);
        return Utilities.doublesEqual(this.length(), length);
    }

    /**
     * public method equals if lines equal to each other.
     * @param other Line
     * @return boolean true if lines equal false otherwise
     */
    //equals -- return true is the lines are equal, false otherwise
    public boolean equals(Line other) {
        if (this.start.equals(other.start) && this.end.equals(other.end)) {
            return true;
        }
        return false;
    }

    /**
     * public method getSlope returns slope of a line if exists.
     * @return slope Double
     */
    public Double getSlope() {
        return this.a;
    }

    /**
     * private method calcSlope, which calculates the slope according to formula.
     */
    private void calcSlope() {
        if (Utilities.doublesEqual(this.start.getX(), this.end.getX())) {
            this.a = null;
            this.isVertical = true;
        } else {
            //find horizontal and vertical difference in distance for the line
            double diffY = this.start.getY() - this.end.getY();
            double diffX = this.start.getX() - this.end.getX();
            //return calculated slope
            this.a = (diffY / diffX);
            this.isVertical = false;
        }
    }

    /**
     * public method getB returns the intersection of line with y axis.
     * @return this.b Double
     */
    public Double getB() {
        return this.b;
    }

    /**
     * private method calcB calculates the intersection of a line with y axis
     * according to formula y= mx+b.
     * needed for linear equation
     */
    private void calcB() {
        if (!this.isVertical) {
            //linear equation y=mx+n. We'll find n
            this.b = this.start.getY() - (this.a * this.start.getX());
        } else {
            this.b = null;
        }

    }

    /**
     * public method isVertical checks if line is vertical.
     * checks if the line is vertical to x axis
     * @return boolean true if vertical false if not
     */
    public boolean isVertical() {
        return this.isVertical;
    }

    /**
     * If this line does not intersect with the rectangle, return null.
     * Otherwise, return the closest intersection point to the
     * start of the line
     * @param rect for rectangle object the line goes through.
     * @return colsest point for intersetion.
     */
    public Point closestIntersectionToStartOfLine(Rectangle rect) {
        List<Point> intersections = rect.intersectionPoints(this);
        if (intersections.isEmpty()) {
            return null;
        } else {
            Point closest = intersections.get(0);
            intersections.remove(0);
            for (Point p:intersections) {
                if (p.distance(this.start) < closest.distance(this.start)) {
                    closest = p;
                }
            }
            return closest;
        }
    }
}

