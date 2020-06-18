package geometry;

import utilities.Axis;
import utilities.Utilities;

import static utilities.Axis.*;

/**
 * @author Libby Goldin 204566236
 * @version 1.0
 * @since 2020-04-22
 */
public class Point {
    /**
     * Class fields.
     */
    private double x;
    private double y;

    /**
     * Constructor.
     * @param x double
     *@param y  double
     */
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * public method distance
     * Distance-- returns the distance of this point to other point.
     * @param other other point
     * @return distance between points
     */
    public double distance(Point other) {
        double squaredDeltaX = Math.pow((this.x - other.x), 2);
        double squaredDeltaY = Math.pow((this.y - other.y), 2);
        return (Math.sqrt(squaredDeltaX + squaredDeltaY));
    }

    /**
     * public method middle()
     * returns the middle point between two points.
     * @param other point
     * @return middle point between two points.
     */
    public Point middle(Point other) {
        return new Point((this.x + other.x) / 2, (this.y + other.y) / 2);
    }

    /**
     * public method equals()
     * checking two points if they're equal- returns true, false otherwise.
     * @param other point
     * @return boolean if two points are the same one
     */
    //Equals-- returns true if the points are equal, false otherwise
    public boolean equals(Point other) {
        return Utilities.doublesEqual(this.x, other.x) && Utilities.doublesEqual(this.y, other.y);
    }

    /**
     * public method getX
     * the method returns coordinate x of point.
     * @return x double
     */
    //Return the x and y values of this point
    public double getX() {
        return this.x;
    }

    /**
     * public method getX()
     * the method returns coordinate y of point.
     * @return y double
     */
    public double getY() {
        return this.y;
    }

    /**
     * the new point calculated when paddle's move is done by the key sensor.
     * @param shiftSize the size of point's change.
     * @param axis whether it's x axis ir y or both
     * @return new point after changing location
     */
    public Point shiftPoint(double shiftSize, Axis axis) {
        Point shiftedPoint;
        switch (axis) {
            case X:
                shiftedPoint = new Point(this.x + shiftSize, this.y);
                break;
            case Y:
                shiftedPoint = new Point(this.x, this.y + shiftSize);
                break;
            case YX:
            case XY:
            default:
                shiftedPoint = new Point(this.x + shiftSize, this.y + shiftSize);
                break;
        }
        return shiftedPoint;
    }

}