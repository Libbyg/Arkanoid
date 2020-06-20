
/**
 * @author Libby Goldin 204566236
 * @version 1.0
 * @since 21/6/2020
 */
package shapes;

import game.objects.Sprite;

import java.awt.Color;

public interface Shape extends Sprite {
    Color getFillColor();
    Color getPerimeterColor();
    void setFill(boolean value);
    void setColors(Color perimeter, Color fill);
}
