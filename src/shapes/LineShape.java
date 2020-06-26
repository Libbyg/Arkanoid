
/**
 * @author Libby Goldin 204566236
 * @version 1.0
 * @since 21/6/2020
 */
package shapes;
import biuoop.DrawSurface;
import geometry.Line;
import geometry.Point;
import java.awt.Color;

/**
 * LineShape class.
 */
public class LineShape implements Shape {
    /**
     * Fields.
     */
    private Color color;
    private Line shape;

    /**
     * Constructor.
     * @param shape line.
     * @param color of line.
     */
    public LineShape(Line shape, Color color) {
        this.shape = shape;
        this.color = color;
    }

    /**
     * Constructor.
     * @param start point of line
     * @param end point of line
     * @param color of line
     */
    public LineShape(Point start, Point end, Color color) {
        this(new Line(start, end), color);
    }

    /**
     * get the color of a line.
     * @return color of line to be drawn.
     */
    @Override
    public Color getFillColor() {
        return color;
    }

    /**
     * drawing the line from start point to end point with chosen color.
     * @param d surface on which we draw the sprites.
     */
    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(this.color);
        d.drawLine(((int) this.shape.start().getX()), ((int) this.shape.start().getY()),
                ((int) this.shape.end().getX()), ((int) this.shape.end().getY()));
    }

    @Override
    public void timePassed() {
        //nothing
    }

    /**
     * line doesn't have a fill.
     * @param value -irrelevant.
     */
    @Override
    public void setFill(boolean value) {
        //no fill in line
    }

    /**
     * get the color of line shape.
     * @return color of a line.
     */
    @Override
    public Color getPerimeterColor() {
        return color;
    }

    /**
     * set color of line shape.
     * @param perimeter color same as fill
     * @param fill color same as perimeter
     */
    @Override
    public void setColors(Color perimeter, Color fill) {
        this.color = fill;
    }
}
