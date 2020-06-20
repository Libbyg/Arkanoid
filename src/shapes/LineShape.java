
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

public class LineShape implements Shape {
    private Color color;
    private Line shape;

    public LineShape(Line shape, Color color) {
        this.shape = shape;
        this.color = color;
    }

    public LineShape(Point start, Point end, Color color) {
        this(new Line(start, end), color);
    }

    @Override
    public Color getFillColor() {
        return color;
    }

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

    @Override
    public void setFill(boolean value) {
        //no fill in line
    }

    @Override
    public Color getPerimeterColor() {
        return color;
    }

    @Override
    public void setColors(Color perimeter, Color fill) {
        this.color = fill;
    }
}
