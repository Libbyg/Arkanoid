
/**
 * @author Libby Goldin 204566236
 * @version 1.0
 * @since 21/6/2020
 */
package shapes;
import game.objects.Sprite;
import java.awt.Color;

/**
 * geometric shapes interface.
 */
public interface Shape extends Sprite {
    /**
     * gets the filling color of shape.
     * @return filling color.
     */
    Color getFillColor();

    /**
     * get the perimeter color of shape.
     * @return perimeter color of a shape.
     */
    Color getPerimeterColor();

    /**
     * setFill defines whether the shape will be filled with color or not.
     * @param value true or false if the shape has a filling.
     */
    void setFill(boolean value);

    /**
     * set the colors os shape: perimeter color and filling color.
     * @param perimeter color of shape.
     * @param fill color of shape.
     */
    void setColors(Color perimeter, Color fill);
}
