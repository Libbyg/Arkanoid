
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
 * ColorChangingCircleShape class.
 */
public class ColorChangingCircleShape extends Circle {
    /**
     * static final variables.
     */
    private static final Color PURPLE = new Color(160, 101, 227);
    private static int CHANCE = 100;
    private static final Random RANDOM = new Random();
    /**
     * Fields.
     */
    private int chance = CHANCE;
    private Color[] colors;
    private int currentColor = 0;

    /**
     * Constructor.
     * @param circleShape to be drawn
     * @param color of circle
     */
    public ColorChangingCircleShape(Circle circleShape, Color color) {
        super(circleShape, color);
        this.colors = new Color[]{color, Color.WHITE, Color.red, Color.pink};
    }

    /**
     * Constructor.
     * @param center of circle.
     * @param radius of circle.
     * @param perimeterColor of circle.
     * @param fillColor of circle.
     */
    public ColorChangingCircleShape(Point center, int radius, Color perimeterColor, Color fillColor) {
        super(center, radius, perimeterColor, fillColor);
        this.colors = new Color[]{fillColor, PURPLE};
    }

    /**
     * each time that passing a filling colors will be changed in a circle.
     */
    @Override
    public void timePassed() {
        this.currentColor = this.currentColor % colors.length;
        this.setColors(colors[this.currentColor], colors[this.currentColor]);
        if (RANDOM.nextInt(this.chance) == 0) {
            this.currentColor++;
        }
    }

    /**
     * set chance method will control the randomness of color changing episodes.
     * @param chance int number of changing color frequency.
     */
    public static void setChance(int chance) {
        ColorChangingCircleShape.CHANCE = Math.abs(chance);
    }
}

