import org.w3c.dom.css.Rect;

import java.util.List;

/**
 * @author Libby Goldin 204566236
 *
 * @version 1.0
 *
 * @since 2020-04-22
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
     * Constructor that recieves two Point objects.
     *
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
     * private function calculates values of linear equation
     */
    private void initializeLine() {
        this.calcSlope();
        this.calcB();
    }

    /**
     * public method length()
     * claculates length between two points
     * @return length double
     */
    //Return the length of the line
    public double length() {
        return (this.start.distance(this.end));
    }

    /**
     * public method middle()
     * which calculates the middle of a line
     * @return middle Point
     */
    //Returns the middle point of the line
    public Point middle() {
        return this.start.middle(this.end);
    }

    /**
     * public method start()
     * @return start Point
     */
    //Returns the start point of the line
    public Point start() {
        return (this.start);
    }

    /**
     * public method end()
     * @return end Point
     */
    //Returns the end point of the line
    public Point end() {
        return (this.end);
    }

    //Returns true if the lines intersect, false otherwise
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
     * otherwise returns false- using Triangle inequality method
     * @param p Point
     * @return boolean
     */
    public boolean pointOnLine(Point p) {
        double length = this.start.distance(p) + p.distance(this.end);
        return Utilities.doublesEqual(this.length(), length);
    }

    /**
     * public method equals()
     * checks if twolines are equal
     * @param other Line
     * @return boolean
     */
    //equals -- return true is the lines are equal, false otherwise
    public boolean equals(Line other) {
        if (this.start.equals(other.start) && this.end.equals(other.end)) {
            return true;
        }
        return false;
    }

    /**
     * public method getSlope()
     * @return slope Double
     */
    public Double getSlope() {
        return this.a;
    }

    /**
     * private method calcSlope
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
     * public method getB()
     * @return this.b Double
     */
    public Double getB() {
        return this.b;
    }

    /**
     * private method calcB()
     * calculates intersection of line with axis y
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
     * public method isVertical()
     * checks if the line is vertical to x axis
     * @return boolean
     */
    public boolean isVertical() {
        return this.isVertical;
    }

    // If this line does not intersect with the rectangle, return null.
    // Otherwise, return the closest intersection point to the
    // start of the line.
    public Point closestIntersectionToStartOfLine(Rectangle rect){
        List<Point> intersections = rect.intersectionPoints(this);
        if (intersections.isEmpty()){
            return null;
        } else {
            Point closest = intersections.get(0);
            intersections.remove(0);
            for (Point p:intersections){
                if (p.distance(this.start)<closest.distance(this.start)){
                    closest = p;
                }
            }
            return closest;
        }
    }
}

