package geometry; /**
 * @author Libby Goldin 204566236
 * @version 1.0
 * @since 2020-05-06
 */

import utilities.Side;

import java.util.ArrayList;
import java.util.Map;
import java.util.List;
import java.util.TreeMap;

/**
 * class of rectangle object.
 */
public class Rectangle {
    /**
     * fields.
     */
    private Point topLeft, bottomRight;
    private Map<Side, Line> edges;

    /**
     * constructor.
     * @param topLeft point corner of rectangle
     * @param bottomRight point corner of rectangle
     */
    public Rectangle(Point topLeft, Point bottomRight) {
        this.topLeft = topLeft;
        this.bottomRight = bottomRight;
        this.edges = translatePointsToEdges(topLeft, bottomRight);
    }

    /**
     * constructor.
     * @param topLeft point corner of rectangle
     * @param width of rectangle
     * @param height of rectangle
     */
    // Create a new rectangle with location and width/height.
    public Rectangle(Point topLeft, double width, double height) {
        this(topLeft, new Point(topLeft.getX() + Math.abs(width), topLeft.getY() + Math.abs(height)));
    }

    /**
     * the method returns list of intersection points between line and rectangle.
     * @param line that crosses the rectangle
     * @return List of geometry.Point objects
     */
    // Return a (possibly empty) List of intersection points
    // with the specified line.
    public List<Point> intersectionPoints(Line line) {
        List<Point> intersections = new ArrayList<>();
        for (Line edge : this.edges.values()) {
            if (edge.isIntersecting(line)) {
                intersections.add(edge.intersectionWith(line));
            }
        }
        return intersections;
    }

    /**
     * the method width returns the width of the rectangle.
     * @return width
     */
    // Return the width and height of the rectangle
    public double width() {
        return this.edges.get(Side.TOP).length();
    }

    /**
     * The method height returns the height of the rectangle.
     * @return height
     */
    public double height() {
        return this.edges.get(Side.RIGHT).length();
    }

    /**
     * the method topLeft  returns the upper left point of the rectangle.
     * @return topLeft
     */
    public Point topLeft() {
        return this.topLeft;
    }

    /**
     *the method bottom right returns the bottom right point of the rectangle.
     * @return bottom right point of rectangle.
     */
    public Point bottomRight() {
        return this.bottomRight;
    }

    /**
     * getEdges method maps each side of the rectangle in a tree Map and returns this tree.
     * @return Map of each side name (enum) and its line object attached to it
     */
    public Map<Side, Line> getEdges() {
        return new TreeMap<>(this.edges);
    }

    /**
     * getEdge method returns the line of the desirable rectangle.
     * @param edge enum os one of rectangle's edges
     * @return geometry.Line of rectangle's specific edge
     */
    public Line getEdge(Side edge) {
        return this.edges.get(edge);
    }

    /**
     * translatePointsToEdges takes each two points of rectangle
     * corners and creates lines for each side than maps it (stores) in tree map.
     * @param topLeft point of rectangle
     * @param bottomRight point of rectangle
     * @return map of rectangle edges' lines
     */
    public static Map<Side, Line> translatePointsToEdges(Point topLeft, Point bottomRight) {
        Point topRight = new Point(bottomRight.getX(), topLeft.getY());
        Point bottomLeft = new Point(topLeft.getX(), bottomRight.getY());
        Map<Side, Line> sideLineMap = new TreeMap<>();
        sideLineMap.put(Side.LEFT, new Line(topLeft, bottomLeft));
        sideLineMap.put(Side.RIGHT, new Line(topRight, bottomRight));
        sideLineMap.put(Side.TOP, new Line(topLeft, topRight));
        sideLineMap.put(Side.BOTTOM, new Line(bottomLeft, bottomRight));
        return sideLineMap;
    }
}
