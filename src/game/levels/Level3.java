
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
import shapes.ColorChangingCircleShape;
import shapes.Rectangle;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * Class of third level.
 */
public class Level3 extends AbstractLevelInformation {
    /**
     * static final variables.
     */
    private static final Color BACKGROUND = new Color(14, 98, 167);
    private static final Color CIRCLE_COLOR = new Color(33, 127, 215);
    private static final Color LIGHT_BLUE = new Color(182, 213, 241);
    private static final Color LIGHT_GREEN = new Color(182, 241, 197);
    private static final Color LIGHT_PURPLE = new Color(226, 189, 238);
    private static final Color LIGHT_TURKIZ = new Color(121, 198, 210);
    private static final Color[] COLORS = {LIGHT_BLUE, LIGHT_GREEN, LIGHT_PURPLE, LIGHT_TURKIZ};
    /**
     * Fields.
     */
    private Point topLeft = new Point(0, 0);
    private Point bottomRight = new Point(800, 600);

    /**
     * Constructor.
     * @param topLeft point of screen gui.
     * @param bottomRight point of screen gui.
     */
    public Level3(Point topLeft, Point bottomRight) {
        super("Disco", 40, 130, 8, Color.YELLOW);
        this.topLeft = topLeft;
        this.bottomRight = bottomRight;
    }

    /**
     * Creating the background of the level.
     * @return background of third level.
     */
    @Override
    public Sprite getBackground() {
        Background background = new Background(new Rectangle(topLeft, bottomRight, BACKGROUND, BACKGROUND));
        for (int i = 30; i <= 780; i += 30) {
            for (int j = 30; j <= 580; j += 30) {
                Point center = new Point(i, j);
                background.addSprite(new ColorChangingCircleShape(center, 10, CIRCLE_COLOR, CIRCLE_COLOR));
            }

        }
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
        int numLines = 4;
        double widthFill = 0.75;
        double startY = LevelInformation.BACKGROUND_HEIGHT * 0.4;
        Point startPoint = new Point(LevelInformation.BACKGROUND_WIDTH / 4,
                LevelInformation.BACKGROUND_HEIGHT / 4);
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
