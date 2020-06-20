
/**
 * @author Libby Goldin 204566236
 * @version 1.0
 * @since 21/6/2020
 */
package shapes;
import biuoop.DrawSurface;
import geometry.Point;
import java.awt.Color;

/**
 * Circle shape that will be used for designing the background.
 */
public class Circle implements Shape {
    /**
     * Fields.
     */
    private Shape circle;
    private Color color;
    private Point center;
    private int radius;
    private Color perimeterColor;
    private Color fillColor;
    private boolean fill;

    /**
     * Constructor.
     * @param circle shape
     * @param color of circle
     */
    public Circle(Shape circle, Color color) {
        this.circle = circle;
        this.color = color;
    }

    /**
     * Constructor.
     * @param center point of circle
     * @param radius of circle
     * @param perimeterColor of circle
     * @param fillColor of circle
     */
    public Circle(Point center, int radius, Color perimeterColor, Color fillColor) {
        this.center = center;
        this.radius = radius;
        this.fillColor = fillColor;
        this.perimeterColor = perimeterColor;
        this.fill = true;
    }

    /**
     * Constructor.
     * @param center point of circle
     * @param radius of circle
     * @param perimeterColor of circle
     */
    public Circle(Point center, int radius, Color perimeterColor) {
        this.center = center;
        this.radius = radius;
        this.perimeterColor = perimeterColor;
        this.fill = false;
    }

    /**
     * Constructor.
     * @param circleShape to be drawn later
     * @param color of circle
     */
    public Circle(Circle circleShape, Color color) {

    }

    /**
     * get filling color of circle.
     * @return filling color of circle
     */
    @Override
    public Color getFillColor() {
        return fillColor;
    }

    /**
     * set if the circle is going to be filled or not.
     * @param value if circle filled with color or not.
     */
    @Override
    public void setFill(boolean value) {
        this.fill = value;
    }

    /**
     * drawing the circle with perimeter and filling if needed.
     * @param d surface on which we draw the sprites.
     */
    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(this.perimeterColor);
        d.drawCircle(((int) this.center.getX()), ((int) this.center.getY()), this.radius);
        if (this.fill) {
            d.setColor(this.fillColor);
            d.fillCircle(((int) this.center.getX()), ((int) this.center.getY()), this.radius);
        }
    }

    @Override
    public void timePassed() {
        //nothing
    }

    /**
     * get the color of circle's perimeter.
     * @return perimeter color of circle
     */
    @Override
    public Color getPerimeterColor() {
        return perimeterColor;
    }

    /**
     * set the fill color of circle
     * set the perimeter color of circle.
     * @param perimeter color of circle.
     * @param fill color of circle.
     */
    @Override
    public void setColors(Color perimeter, Color fill) {
        this.fillColor = fill;
        this.perimeterColor = perimeter;
    }
}
