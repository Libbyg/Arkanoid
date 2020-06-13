/**
 * @author Libby Goldin 204566236
 * @version 1.0
 * @since 2020-05-06
 */
import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;

import java.awt.*;
import java.util.ArrayList;


public class Game {
    /**
     * static final variables
     */
    private static final int BACKGROUND_WIDTH = 800;
    private static final int BACKGROUND_HEIGHT = 600;
    private static final int BLOCK_HEIGHT = 20;
    private static final int MARGINS_WIDTH = 20;
    private static final Color[] COLORS = {Color.MAGENTA, Color.YELLOW, Color.CYAN, Color.BLUE, Color.GREEN,
            Color.RED, Color.PINK, Color.ORANGE};

    private SpriteCollection sprites = new SpriteCollection();
    private GameEnvironment environment = new GameEnvironment();
    private GUI gui = new GUI("Arkanoid", 800, 600);

    /**
     * add collidable object to collidables collection of the game
     * @param c
     */
    public void addCollidable(Collidable c) {
        this.environment.addCollidable(c);
    }

    /**
     * add sprite object to sprite collection objects of the game
     * @param s
     */
    public void addSprite(Sprite s) {
        this.sprites.addSprite(s);
    }

    // Initialize a new game: create the Blocks and Ball (and Paddle)
    // and add them to the game.

    /**
     * new game initializing with all necessary tools:
     * ball, blocks orders, blocks of the game, paddle etc
     * @param center
     * @param radius
     * @param color
     */
    public void initialize(Point center, int radius, Color color) {
        //gui size 800*600

        //this.sprites.addSprite(s);
        //creating ball
        Ball ball1 = new Ball (center, radius, color);
        ball1.setVelocity(Velocity.fromAngleAndSpeed(180, 3));
        Ball ball2 = new Ball (center, radius, color);
        ball2.setVelocity(Velocity.fromAngleAndSpeed(120, 3));
        //creating blocks
        ArrayList<Block> blockList = this.createBlocks(8, 0.75, 8, BACKGROUND_HEIGHT * 0.4, 1);
        for (Block block : blockList) {
            block.addToGame(this);
        }
        ArrayList<Block> borders = this.createBorders(BACKGROUND_WIDTH, BACKGROUND_HEIGHT, MARGINS_WIDTH);
        for (Block border : borders) {
            border.addToGame(this);
        }
        //create borders and add to game
        Paddle paddle = new Paddle(new Block(new Point(350, 550), 100, 15, Color.BLACK),
                this.gui.getKeyboardSensor());
        paddle.addToGame(this);
        ball1.addToGame(this);
        ball2.addToGame(this);
    }

    /**
     * running the game i.e. putting the game into action
     * where things are actually happen - they are drawn on gui surface and
     * respond according to defind methods
     */
    // Run the game -- start the animation loop.
    public void run() {
        int framesPerSecond = 60;
        int millisecondsPerFrame = 1000 / framesPerSecond;
        while (true) {
            Sleeper sleeper = new Sleeper();
            long startTime = System.currentTimeMillis(); // timing
            DrawSurface d = this.gui.getDrawSurface();
            this.sprites.drawAllOn(d);
            gui.show(d);
            this.sprites.notifyAllTimePassed();

            // timing
            long usedTime = System.currentTimeMillis() - startTime;
            long milliSecondLeftToSleep = millisecondsPerFrame - usedTime;
            if (milliSecondLeftToSleep > 0) {
                sleeper.sleepFor(milliSecondLeftToSleep);
            }
        }
    }

    public GameEnvironment getEnvironment() {
        return new GameEnvironment(this.environment.getCollidables());
    }

    public void assignEnv(Ball ball) {
        if (this.sprites.contains(ball)) {
            ball.setEnvironment(this.environment);
        }
    }

    /**
     * creating the blocks of the game which the ball suppose to break
     * @param numFirstLineBlocks
     * @param widthFill
     * @param numLines
     * @param startY
     * @param blockDiff
     * @return
     */
    private ArrayList<Block> createBlocks(int numFirstLineBlocks, double widthFill,
                                          int numLines, double startY, int blockDiff) {
        //background width subtract the two border blocks
        int blockWidth = ((int) ((BACKGROUND_WIDTH - (2 * MARGINS_WIDTH)) * widthFill) / numFirstLineBlocks);
        double startX = BACKGROUND_WIDTH - MARGINS_WIDTH;
        //create list of blocks
        ArrayList<Block> blocks = new ArrayList<>();
        //loop for each line index and add blocks to arrayList
        for (int i = 0; i < numLines; i++, startY += BLOCK_HEIGHT, numFirstLineBlocks -= blockDiff) {
            //colors mod operator- circular rotation
            blocks.addAll(buildBlockLine(numFirstLineBlocks, new Point(startX, startY),
                    blockWidth, BLOCK_HEIGHT, COLORS[i % COLORS.length]));
        }
        return blocks;
    }

    /**
     * creating the block borders of the game to prevent from the ball to roll of the screen
     * @param widthGui
     * @param heightGui
     * @param widthBorder
     * @return
     */
    public ArrayList<Block> createBorders(int widthGui, int heightGui, int widthBorder) {
        ArrayList<Block> borders = new ArrayList<>();
        //left border
        Block leftBorder = new Block(new Point(0, 0), new Point(widthBorder, heightGui), Color.GRAY);
        borders.add(leftBorder);
        //right border
        Block rightBorder = new Block(new Point(widthGui - widthBorder, 0), new Point(widthGui, heightGui), Color.GRAY);
        borders.add(rightBorder);
        //upper border
        Block upperBorder = new Block(new Point(0, 0), new Point(widthGui, widthBorder), Color.GRAY);
        borders.add(upperBorder);
        //bottom border
        Block bottomBorder = new Block(new Point(0, heightGui - widthBorder), new Point(widthGui, heightGui), Color.GRAY);
        borders.add(bottomBorder);
        return (borders);
    }

    /**
     * creating an arrayList of blocks for each line (in method above all lists will be unified)
     * and building it on gui surface starting on right side, going to left side of the screen
     * @param numFirstLineBlocks
     * @param startPoint
     * @param width
     * @param height
     * @param color
     * @return ArrayList of Block objects
     */
    public static ArrayList<Block> buildBlockLine(int numFirstLineBlocks, Point startPoint,
                                                  int width, int height, Color color) {

        ArrayList<Block> blocks = new ArrayList<>();
        double x = startPoint.getX() - width;
        double y = startPoint.getY();
        for (int i = 0; i < numFirstLineBlocks; i++, x -= width) {
            blocks.add(new Block(new Point(x, y), width, height, color));
        }
        return blocks;
    }

}