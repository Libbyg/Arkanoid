package game;

import geometry.Point;
import shapes.LineShape;
import shapes.Rectangle;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import static utilities.Axis.X;
import static utilities.Axis.Y;

public class Level4 implements LevelInformation {

    private Point topLeft = new Point(0,0);
    private Point bottomRight = new Point(800, 600);

    private static final Color RED = new Color(255, 123, 123);
    private static final Color YELLOW = new Color(255, 224, 127, 255);
    private static final Color GREEN = new Color(95, 232, 130, 255);
    private static final Color BLUE = new Color(84, 149, 246, 255);
    private static final Color PURPLE = new Color(169, 119, 229, 255);
    private static final Color PINK = new Color(237, 126, 255, 255);
    private static final Color ORANGE = new Color(250, 179, 125, 255);
    private static final Color LIGHT_BLUE = new Color(135, 212, 255, 255);
    private static final Color[] COLORS = {RED, YELLOW, GREEN, BLUE, PINK, ORANGE, PURPLE, LIGHT_BLUE};
    private static final int BACKGROUND_WIDTH = 800;
    private static final int BACKGROUND_HEIGHT = 600;
    private static final int BLOCK_HEIGHT = 20;
    private static final int MARGINS_WIDTH = 20;

    public Level4(){
    }

    public Level4(Point topLeft, Point bottomRight) {
        this.topLeft = topLeft;
        this.bottomRight = bottomRight;
    }
    public int numberOfBalls() {
        return initialBallVelocities().size();
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        List <Velocity> velocities = new ArrayList<>();
        velocities.add(Velocity.fromAngleAndSpeed(0, 7));
        velocities.add(Velocity.fromAngleAndSpeed(1, 7));
        velocities.add(Velocity.fromAngleAndSpeed(2, 7));
        velocities.add(Velocity.fromAngleAndSpeed(3, 7));
        return velocities;
    }

    @Override
    public int paddleSpeed() {
        return 9;
    }

    @Override
    public int paddleWidth() {
        return 135;
    }

    @Override
    public String levelName() {
        return "Colorful Mountains";
    }

    @Override
    public Sprite getBackground() {
        Background background = new Background(new Rectangle(topLeft, bottomRight, Color.WHITE, Color.WHITE));
        Point centerOne = topLeft.shiftPoint(bottomRight.getX() / 2, X).shiftPoint(bottomRight.getY() / 4, Y);
        for (int i = 0; i <= 800; i+=10) {
            Point endPoint = new Point (i,this.bottomRight.getY());
            background.addSprite(new LineShape(centerOne,endPoint,COLORS[i % COLORS.length]));
        }

        Point centerTwo = topLeft.shiftPoint(bottomRight.getX()/ 6, X).shiftPoint(bottomRight.getY() /4, Y);
        for (int i = 0; i <= 800; i+=10) {
            Point endPoint = new Point (i,this.bottomRight.getY());
            background.addSprite(new LineShape(centerTwo,endPoint,COLORS[i % COLORS.length]));
        }

        Point centerThree = topLeft.shiftPoint(bottomRight.getX() /3 , X).shiftPoint(bottomRight.getY() /4, Y);
        for (int i = 0; i <= 800; i+=10) {
            Point endPoint = new Point (i,this.bottomRight.getY());
            background.addSprite(new LineShape(centerThree,endPoint,COLORS[i % COLORS.length]));
        }

        Point centerFour = topLeft.shiftPoint(bottomRight.getX() /1.5 , X).shiftPoint(bottomRight.getY() /4, Y);
        for (int i = 0; i <= 800; i+=10) {
            Point endPoint = new Point (i,this.bottomRight.getY());
            background.addSprite(new LineShape(centerFour,endPoint,COLORS[i % COLORS.length]));
        }


        Point centerFive = topLeft.shiftPoint(bottomRight.getX() /1.2 , X).shiftPoint(bottomRight.getY() /4, Y);
        for (int i = 0; i <= 800; i+=10) {
            Point endPoint = new Point (i,this.bottomRight.getY());
            background.addSprite(new LineShape(centerFive,endPoint,COLORS[i % COLORS.length]));
        }

        int startDefaultX = 0;
        int endDefaultX = 800;
        for (int k = 0; k <= 600; k += 5) {
            Point start = new Point(startDefaultX, k);
            Point end = new Point(endDefaultX, k);
            background.addSprite(new LineShape(start, end, COLORS[k % COLORS.length]));
        }


        Point start = new Point (0,5);
        Point end = new Point (800,5);
        background.addSprite(new LineShape(start,end,COLORS[0]));

        return background;
    }


    @Override
    public List<Block> blocks() {

        int blockDiff =1;
        int numFirstLineBlocks = 8;
        int numLines = 8;
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
