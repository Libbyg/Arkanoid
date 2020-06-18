package shapes;

import biuoop.DrawSurface;
import geometry.Point;
import java.awt.Color;

public class Circle implements Shape{
    private Point center;
    private int radius;
    private Color perimeterColor;
    private Color fillColor;
    private boolean fill;

    public Circle(Point center, int radius, Color perimeterColor, Color fillColor) {
        this.center = center;
        this.radius = radius;
        this.fillColor = fillColor;
        this.perimeterColor = perimeterColor;
        this.fill = true;
    }

    public Circle(Point center, int radius, Color perimeterColor) {
        this.center = center;
        this.radius = radius;
        this.perimeterColor = perimeterColor;
        this.fill = false;
    }

    @Override
    public Color getFillColor() {
        return fillColor;
    }

    @Override
    public void setFill(boolean value) {
        this.fill = value;
    }

    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(this.perimeterColor);
        d.drawCircle(((int) this.center.getX()), ((int) this.center.getY()),this.radius);
        if (this.fill){
            d.setColor(this.fillColor);
            d.fillCircle(((int) this.center.getX()), ((int) this.center.getY()),this.radius);
        }
    }

    @Override
    public void timePassed() {
        //nothing
    }

    @Override
    public Color getPerimeterColor() {
        return perimeterColor;
    }

    @Override
    public void setColors(Color perimeter, Color fill) {
        this.fillColor = fill;
        this.perimeterColor = perimeter;
    }
}
