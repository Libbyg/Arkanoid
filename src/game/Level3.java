package game;

import geometry.Point;
import shapes.Circle;
import shapes.LineShape;
import shapes.Rectangle;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;


public class Level3 implements LevelInformation {

    private Point topLeft = new Point(0,0);
    private Point bottomRight = new Point(800, 600);
    //private static final Color BACKGROUND = new Color(196, 225, 245);
    private static final Color BACKGROUND = new Color(14, 98, 167);
    private static final Color CIRCLE_COLOR = new Color(33, 127, 215);
    private static final int BACKGROUND_WIDTH = 800;
    private static final int BACKGROUND_HEIGHT = 600;
    private static final int BLOCK_HEIGHT = 20;
    private static final int MARGINS_WIDTH = 20;
    private static final Color LIGHT_BLUE = new Color(182, 213, 241);
    private static final Color LIGHT_GREEN = new Color(182, 241, 197);
    private static final Color LIGHT_PURPLE = new Color(226, 189, 238);
    private static final Color LIGHT_TURKIZ = new Color(121, 198, 210);
    private static final Color[] COLORS = {LIGHT_BLUE,LIGHT_GREEN,LIGHT_PURPLE,LIGHT_TURKIZ};


    public Level3() {
    }

    public Level3(Point topLeft, Point bottomRight) {
        this.topLeft = topLeft;
        this.bottomRight = bottomRight;
    }

    @Override
    public int numberOfBalls() {
        return initialBallVelocities().size();
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        List <Velocity> velocities = new ArrayList<>();
        velocities.add(Velocity.fromAngleAndSpeed(0, 6));
        velocities.add(Velocity.fromAngleAndSpeed(1, 6));
        velocities.add(Velocity.fromAngleAndSpeed(2, 6));
        return velocities;
    }

    @Override
    public int paddleSpeed() {
        return 7;
    }

    @Override
    public int paddleWidth() {
        return 130;
    }

    @Override
    public String levelName() {
        return "Dotted";
    }

    @Override
    public Sprite getBackground() {
        Background background = new Background(new Rectangle(topLeft, bottomRight, BACKGROUND, BACKGROUND));
        for (int i = 30; i <= 780; i += 30){
            for (int j = 30; j <= 580; j += 30){
                Point center = new Point(i,j);
                background.addSprite(new Circle(center, 10, CIRCLE_COLOR, CIRCLE_COLOR));
            }

        }
        return background;
    }

    @Override
    public List<Block> blocks() {

        int blockDiff =1;
        int numFirstLineBlocks = 8;
        int numLines = 4;
        double widthFill = 0.75;
        double startY =BACKGROUND_HEIGHT *0.4;
        Point startPoint = new Point(BACKGROUND_WIDTH/4,BACKGROUND_HEIGHT/4);
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

    public List<Block> blocksHelper(int numFirstLineBlocks, Point startPoint, int width, int height, Color color) {
        List <Block> blocks = new ArrayList<>();
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


    @Override
    public int numberOfBlocksToRemove() {
        return this.blocks().size();
    }
}
