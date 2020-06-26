
/**
 * @author Libby Goldin 204566236
 * @version 1.0
 * @since 21/6/2020
 */
package shapes;
import geometry.Point;
import biuoop.DrawSurface;
import java.awt.Color;

/**
 * Rectangle shape class.
 */
public class Rectangle implements Shape {
    /**
     * fields.
     */
    private Point topLeft;
    private Point bottomRight;
    private int width;
    private int height;
    private Color perimeterColor;
    private Color fillColor;
    private boolean fill;

    /**
     * Constructor. (without fill)
     * @param topLeft point of rectangle.
     * @param bottomRight point of rectangle.
     * @param perimeterColor of rectangle.
     */
    public Rectangle(Point topLeft, Point bottomRight, Color perimeterColor) {
        this.topLeft = topLeft;
        this.bottomRight = bottomRight;
        this.width = (int) (bottomRight.getX() - topLeft.getX());
        this.height = (int) (bottomRight.getY() - topLeft.getY());
        this.perimeterColor = perimeterColor;
        this.fill = false;
    }

    /**
     * Constructor. (with fill)
     * @param topLeft point of rectangle.
     * @param bottomRight point of rectangle.
     * @param perimeterColor of rectangle.
     * @param fillColor of rectangle.
     */
    public Rectangle(Point topLeft, Point bottomRight, Color perimeterColor, Color fillColor) {
        this.topLeft = topLeft;
        this.bottomRight = bottomRight;
        this.width = (int) (bottomRight.getX() - topLeft.getX());
        this.height = (int) (bottomRight.getY() - topLeft.getY());
        this.perimeterColor = perimeterColor;
        this.fillColor = fillColor;
        this.fill = true;
    }

    /**
     * get the filling color of a rectangle.
     * @return color of rectangle's fill.
     */
        @Override
        public Color getFillColor() {
            return this.fillColor;
        }

    /**
     * get the perimeter color of a rectangle.
     * @return color of rectangle's perimeter.
     */
        @Override
        public Color getPerimeterColor() {
            return this.perimeterColor;
        }

    /**
     * the method defines if the rectangle will be filled or not.
     * @param value boolean true or false if the rectangle will have a filling color.
     */
    @Override
        public void setFill(boolean value) {
            this.fill = value;
        }

    /**
     * setting colors for a rectangle.
     * @param perimeterColor of rectangle.
     * @param fillColor of rectangle.
     */
        @Override
        public void setColors(Color perimeterColor, Color fillColor) {
            this.perimeterColor = perimeterColor;
            this.fillColor = fillColor;
        }

    /**
     * drawing the rectangle.
     * @param d surface on which we draw the sprites
     */
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
        public void timePassed() {
            //nothing
        }
    }

