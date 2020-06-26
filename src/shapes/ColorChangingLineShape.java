
/**
 * @author Libby Goldin 204566236
 * @version 1.0
 * @since 21/6/2020
 */
package shapes;
import geometry.Line;
import geometry.Point;
import java.awt.Color;
import java.util.Random;

/**
 * ColorChangingLineShape class.
 */
public class ColorChangingLineShape extends LineShape {
    /**
     * static final variables.
     */
    private static final Color BRIGHT_BROWN1 = new Color(165, 113, 78, 255);
    private static final Color BRIGHT_BROWN2 = new Color(175, 158, 107, 255);
    private static final Color DARK_GREEN1 = new Color(82, 128, 80, 255);
    private static final Color DARK_GREEN2 = new Color(26, 148, 59, 255);
    private static final Random RAND = new Random();
    private static int CHANCE = 10;
    /**
     * Fields.
     */
    private int chance = CHANCE;
    private Color[] colors;
    private int currentColor = 0;

    /**
     * Construcor.
     * @param shape line
     * @param color of line
     */
    public ColorChangingLineShape(Line shape, Color color) {
        super(shape, color);
        this.colors = new Color[]{BRIGHT_BROWN1, DARK_GREEN1, BRIGHT_BROWN2, DARK_GREEN2};
    }

    /**
     * Constructor.
     * @param start Point of line shape
     * @param end point of line shape
     * @param color of line shape
     */
    public ColorChangingLineShape(Point start, Point end, Color color) {
        super(start, end, color);
        this.colors = new Color[]{BRIGHT_BROWN1, DARK_GREEN1, BRIGHT_BROWN2, DARK_GREEN2};
    }

    /**
     * each time that passing a color of line shape will be changed.
     */
    @Override
    public void timePassed() {
        this.currentColor = this.currentColor % colors.length;
        this.setColors(colors[this.currentColor], colors[this.currentColor]);
        if (RAND.nextInt(this.chance) == 0) {
            this.currentColor++;
        }
    }

    /**
     * set chance method will control the randomness of color changing episodes.
     * @param chance int number of changing color frequency.
     */
    public static void setChance(int chance) {
        ColorChangingLineShape.CHANCE = Math.abs(chance);
    }
}
