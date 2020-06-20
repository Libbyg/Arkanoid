
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

public class ColorChangingLineShape extends LineShape {
    private static final Color BRIGHT_BROWN1 = new Color(165, 113, 78, 255);
    private static final Color BRIGHT_BROWN2 = new Color(175, 158, 107, 255);
    private static final Color DARK_GREEN1 = new Color(82, 128, 80, 255);
    private static final Color DARK_GREEN2 = new Color(26, 148, 59, 255);

    private static int CHANCE = 10;
    private int chance = CHANCE;
    private Color[] colors;
    private int currentColor = 0;
    private static final Random rand = new Random();

    public ColorChangingLineShape(Line shape, Color color) {
        super(shape, color);
        this.colors = new Color[]{BRIGHT_BROWN1,DARK_GREEN1, BRIGHT_BROWN2, DARK_GREEN2};
    }

    public ColorChangingLineShape(Point start, Point end, Color color) {
        super(start, end, color);
        this.colors = new Color[]{BRIGHT_BROWN1,DARK_GREEN1, BRIGHT_BROWN2, DARK_GREEN2};
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
        ColorChangingLineShape.CHANCE = Math.abs(chance);
    }
}
