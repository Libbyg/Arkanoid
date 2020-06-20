
/**
 * @author Libby Goldin 204566236
 * @version 1.0
 * @since 21/6/2020
 */
package game.levels;
import game.objects.Background;
import game.objects.Block;
import game.objects.Sprite;
import game.objects.Velocity;
import geometry.Point;
import shapes.Circle;
import shapes.ColorChangingLineShape;
import shapes.Rectangle;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import static utilities.Axis.X;
import static utilities.Axis.XY;
import static utilities.Axis.Y;

/**
 * class of first level.
 */
public class Level1 extends AbstractLevelInformation {
    /**
     * Fields.
     */
    private Point topLeft;
    private Point bottomRight;

    /**
     * Constructor.
     * @param topLeft point of screen gui.
     * @param bottomRight point of screen gui.
     */
    public Level1(Point topLeft, Point bottomRight) {
        super("Direct Hit", 1, 100, 5, Color.MAGENTA);
        this.topLeft = topLeft;
        this.bottomRight = bottomRight;
    }

    /**
     *  initialBallVelocities method initializes the velocities of balls in this level.
     * @return list of velocities of balls in the level.
     */
    @Override
    public List<Velocity> initialBallVelocities() {
        List<Velocity> velocities = new ArrayList<>();
        velocities.add(Velocity.fromAngleAndSpeed(0, 2));
        return velocities;
    }

    /**
     * Creating the background of the level.
     * @return background of first level.
     */
    @Override
    public Sprite getBackground() {
        //Background background = new Background(new Block(topLeft, bottomRight, Color.BLACK));
        Background background = new Background(new Rectangle(topLeft, bottomRight, Color.BLACK, Color.BLACK));
        Point center = topLeft.shiftPoint(bottomRight.getX() / 2, X).shiftPoint(bottomRight.getY() / 4, Y);
        background.addSprite(new Circle(center, 100, Color.BLUE));
        background.addSprite(new Circle(center, 75, Color.BLUE));
        background.addSprite(new Circle(center, 50, Color.BLUE));
        ColorChangingLineShape.setChance(10);
        background.addSprite(new ColorChangingLineShape(center.shiftPoint(-110, X),
                center.shiftPoint(110, X), Color.BLUE));
        background.addSprite(new ColorChangingLineShape(center.shiftPoint(-110, Y),
                center.shiftPoint(110, Y), Color.BLUE));
        return background;
    }

    /**
     * Blocks method builds the blocks of first level.
     * @return list of blocks to be built in the game gui.
     */
    @Override
    public List<Block> blocks() {
        List<Block> blocks = new ArrayList<>();
        Point center = topLeft.shiftPoint(bottomRight.getX() / 2, X).shiftPoint(bottomRight.getY() / 4, Y);
        blocks.add(new Block(center.shiftPoint(-25, XY), center.shiftPoint(25, XY), Color.RED));
        return blocks;
    }

}