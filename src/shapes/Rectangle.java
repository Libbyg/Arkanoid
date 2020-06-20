
/**
 * @author Libby Goldin 204566236
 * @version 1.0
 * @since 21/6/2020
 */
package shapes;
import geometry.Point;
import biuoop.DrawSurface;
import java.awt.Color;

public class Rectangle implements Shape {
    private Point topLeft;
    private Point bottomRight;
    private int width;
    private int height;
    private Color perimeterColor;
    private Color fillColor;
    private boolean fill;

    public Rectangle (Point topLeft, Point bottomRight, Color perimeterColor) {
        this.topLeft = topLeft;
        this.bottomRight = bottomRight;
        this.width = (int) (bottomRight.getX() - topLeft.getX());
        this.height = (int) (bottomRight.getY() - topLeft.getY());
        this.perimeterColor = perimeterColor;
        this.fill = false;
    }

    public Rectangle (Point topLeft, Point bottomRight, Color perimeterColor, Color fillColor) {
        this.topLeft = topLeft;
        this.bottomRight = bottomRight;
        this.width = (int) (bottomRight.getX() - topLeft.getX());
        this.height = (int) (bottomRight.getY() - topLeft.getY());
        this.perimeterColor = perimeterColor;
        this.fillColor = fillColor;
        this.fill = true;
    }

        @Override
        public Color getFillColor () {
            return this.fillColor;
        }

        @Override
        public Color getPerimeterColor () {
            return this.perimeterColor;
        }

        @Override
        public void setFill (boolean value) {
            this.fill = value;
        }

        @Override
        public void setColors (Color perimeterColor, Color fillColor) {
            this.perimeterColor = perimeterColor;
            this.fillColor = fillColor;
        }

        @Override
        public void drawOn(DrawSurface d) {
            d.setColor(this.perimeterColor);
            d.drawRectangle(((int) this.topLeft.getX()), ((int) this.topLeft.getY()), 800, 600);
            if (this.fill) {
                d.setColor(this.fillColor);
                d.fillRectangle(((int) this.topLeft.getX()), ((int) this.topLeft.getY()), 800, 600);
            }

        }

        @Override
        public void timePassed () {
            //nothing
        }
    }

