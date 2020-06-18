package shapes;

import game.Sprite;

import java.awt.Color;

public interface Shape extends Sprite {
    Color getFillColor();
    Color getPerimeterColor();
    void setFill(boolean value);
    void setColors(Color perimeter, Color fill);
}
