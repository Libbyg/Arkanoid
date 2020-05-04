import java.util.*;

public class Rectangle {
    //Fields
    private Point topLeft, bottomRight;
    private Map<Side, Line> edges;

    //constructor
    public Rectangle(Point topLeft, Point bottomRight) {
        this.topLeft = topLeft;
        this.bottomRight = bottomRight;
        this.edges = translatePointsToEdges(topLeft, bottomRight);
    }

    // Create a new rectangle with location and width/height.
    public Rectangle(Point topLeft, double width, double height) {
        this(topLeft, new Point(topLeft.getX() + Math.abs(width), topLeft.getY() + Math.abs(height)));
    }

    // Return a (possibly empty) List of intersection points
    // with the specified line.
    public List<Point> intersectionPoints(Line line) {
        List<Point> intersections = new ArrayList<>();
        for (Line edge : this.edges.values()){
            if (edge.isIntersecting(line)){
                intersections.add(edge.intersectionWith(line));
            }
        }
        return intersections;
    }

    // Return the width and height of the rectangle
    public double width() {
        return this.edges.get(Side.TOP).length();
    }

    public double height() {
        return this.edges.get(Side.RIGHT).length();
    }

    public Point topLeft() {
        return this.topLeft;
    }

    public Point bottomRight() {
        return this.bottomRight;
    }

    public Map<Side, Line> getEdges() {
        return new TreeMap<>(this.edges);
    }

    public Line getEdge(Side edge){
        return this.edges.get(edge);
    }

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
