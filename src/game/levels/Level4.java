
/**
 * @author Libby Goldin 204566236
 * @version 1.0
 * @since 21/6/2020
 */
package game.levels;
import game.objects.Background;
import game.objects.Block;
import game.objects.Sprite;
import geometry.Point;
import shapes.ColorChangingLineShape;
import shapes.Rectangle;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import static utilities.Axis.X;
import static utilities.Axis.Y;

/**
 * Class of fourth level.
 */
public class Level4 extends AbstractLevelInformation {
    /**
     * static final variables.
     */
    private static final Color RED = new Color(255, 123, 123);
    private static final Color YELLOW = new Color(255, 224, 127, 255);
    private static final Color GREEN = new Color(95, 232, 130, 255);
    private static final Color BLUE = new Color(84, 149, 246, 255);
    private static final Color PURPLE = new Color(169, 119, 229, 255);
    private static final Color PINK = new Color(237, 126, 255, 255);
    private static final Color ORANGE = new Color(250, 179, 125, 255);
    private static final Color LIGHT_BLUE = new Color(135, 212, 255, 255);
    private static final Color[] COLORS = {RED, YELLOW, GREEN, BLUE, PINK, ORANGE, PURPLE, LIGHT_BLUE};
    /**
     * Fields.
     */
    private final Point topLeft;
    private final Point bottomRight;

    /**
     * Constructor.
     * @param topLeft point of screen gui.
     * @param bottomRight point of screen gui.
     */
    public Level4(Point topLeft, Point bottomRight) {
        super("Colorful Mountains", 20, 135, 10, Color.ORANGE);
        this.topLeft = topLeft;
        this.bottomRight = bottomRight;
    }

    @Override
    public Sprite getBackground() {
        ColorChangingLineShape.setChance(1000);
        Background background = new Background(new Rectangle(topLeft, bottomRight, Color.WHITE, Color.WHITE));
        Point centerOne = topLeft.shiftPoint(bottomRight.getX() / 2, X).shiftPoint(bottomRight.getY() / 4, Y);
        for (int i = 0; i <= 800; i += 10) {
            Point endPoint = new Point(i, this.bottomRight.getY());
            background.addSprite(new ColorChangingLineShape(centerOne, endPoint, COLORS[i % COLORS.length]));
        }
        Point centerTwo = topLeft.shiftPoint(bottomRight.getX() / 6, X).shiftPoint(bottomRight.getY() / 4, Y);
        for (int i = 0; i <= 800; i += 10) {
            Point endPoint = new Point(i, this.bottomRight.getY());
            background.addSprite(new ColorChangingLineShape(centerTwo, endPoint, COLORS[i % COLORS.length]));
        }
        Point centerThree = topLeft.shiftPoint(bottomRight.getX() / 3, X).shiftPoint(bottomRight.getY() / 4, Y);
        for (int i = 0; i <= 800; i += 10) {
            Point endPoint = new Point(i, this.bottomRight.getY());
            background.addSprite(new ColorChangingLineShape(centerThree, endPoint, COLORS[i % COLORS.length]));
        }
        Point centerFour = topLeft.shiftPoint(bottomRight.getX() / 1.5, X).shiftPoint(bottomRight.getY() / 4, Y);
        for (int i = 0; i <= 800; i += 10) {
            Point endPoint = new Point(i, this.bottomRight.getY());
            background.addSprite(new ColorChangingLineShape(centerFour, endPoint, COLORS[i % COLORS.length]));
        }
        Point centerFive = topLeft.shiftPoint(bottomRight.getX() / 1.2, X).shiftPoint(bottomRight.getY() / 4, Y);
        for (int i = 0; i <= 800; i += 10) {
            Point endPoint = new Point(i, this.bottomRight.getY());
            background.addSprite(new ColorChangingLineShape(centerFive, endPoint, COLORS[i % COLORS.length]));
        }
        Point start = new Point(0, 5);
        Point end = new Point(800, 5);
        background.addSprite(new ColorChangingLineShape(start, end, COLORS[0]));
        return background;
    }


    /**
     * previous method helped to create blocks starting from specific point.
     * this method creates full well detailed blocks list with additional info.
     * @return list of blocks.
     */
    @Override
    public List<Block> blocks() {
        int blockDiff = 1;
        int numFirstLineBlocks = 8;
        int numLines = 8;
        double widthFill = 0.75;
        double startY = BACKGROUND_HEIGHT * 0.4;
        Point startPoint = new Point(BACKGROUND_WIDTH / 4, BACKGROUND_HEIGHT / 4);
        //background width subtract the two border blocks
        int blockWidth = ((int) ((BACKGROUND_WIDTH - (2 * MARGINS_WIDTH)) * widthFill) / numFirstLineBlocks);
        double startX = BACKGROUND_WIDTH - MARGINS_WIDTH;
        //create list of blocks
        ArrayList<Block> blocks = new ArrayList<>();
        //loop for each line index and add blocks to arrayList
        for (int i = 0; i < numLines; i++, startY += BLOCK_HEIGHT, numFirstLineBlocks -= blockDiff) {
            //colors mod operator- circular rotation
            blocks.addAll(blocksHelper(numFirstLineBlocks, new Point(startX, startY),
                    blockWidth, BLOCK_HEIGHT, COLORS[i % COLORS.length]));
        }
        return blocks;
    }

    /**
     * blocksHelper method creates the list of blocks and
     * the information regarding the way they'll be built.
     * @param numFirstLineBlocks in third level
     * @param startPoint where the blocks starts
     * @param width of each block
     * @param height of each block
     * @param color of each block
     * @return list of blocks.
     */
    public List<Block> blocksHelper(int numFirstLineBlocks, Point startPoint, int width, int height, Color color) {
        List<Block> blocks = new ArrayList<>();
        double x = startPoint.getX() - width;
        double y = startPoint.getY();
        for (int i = 0; i < numFirstLineBlocks; i++, x -= width) {
            Block b = new Block(new Point(x, y), width, height, color);
            blocks.add(b);
            //b.addHitListener(blockRemoverListener);
            //b.addHitListener(scoreTrackListener);
        }
        return blocks;
    }

}
