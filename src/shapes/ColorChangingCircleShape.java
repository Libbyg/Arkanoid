
/**
 * @author Libby Goldin 204566236
 * @version 1.0
 * @since 21/6/2020
 */
package shapes;
import geometry.Point;
import java.awt.Color;
import java.util.Random;

/**
 * ColorChangingCircleShape 
 */
public class ColorChangingCircleShape extends Circle {
    private static final Color PURPLE = new Color(160, 101, 227);
    private static int CHANCE = 100;
    private int chance = CHANCE;
    private Color[] colors;
    private int currentColor = 0;
    private static final Random rand = new Random();

    public ColorChangingCircleShape(Circle circleShape, Color color) {
        super(circleShape, color);
        this.colors = new Color[]{color,Color.WHITE, Color.red, Color.pink};
    }

    public ColorChangingCircleShape(Point center, int radius, Color perimeterColor, Color fillColor) {
        super(center, radius, perimeterColor, fillColor);
        this.colors = new Color[]{fillColor,PURPLE};
    }

    @Override
    public void timePassed() {
        this.currentColor = this.currentColor % colors.length;
        this.setColors(colors[this.currentColor], colors[this.currentColor]);
        if (rand.nextInt(this.chance)==0){
            this.currentColor++;
        }
    }

    public static void setChance(int chance) {
        ColorChangingCircleShape.CHANCE = Math.abs(chance);
    }
}

