package game; /**
 * @author Libby Goldin 204566236
 * @version 1.0
 * @since 2020-05-06
 */
import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.KeyboardSensor;
import geometry.Point;
import utilities.Axis;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * Game Class sets the object named game.
 */
public class GameLevel implements Animation {
    /**
     * static final variables.
     */
    private static final int BACKGROUND_WIDTH = 800;
    private static final int BACKGROUND_HEIGHT = 600;
    private static final int BLOCK_HEIGHT = 20;
    private static final int MARGINS_WIDTH = 20;
    //colors for blocks
    private static final Color RED = new Color(255, 123, 123);
    private static final Color YELLOW = new Color(255, 224, 127, 255);
    private static final Color GREEN = new Color(95, 232, 130, 255);
    private static final Color BLUE = new Color(84, 149, 246, 255);
    private static final Color PURPLE = new Color(169, 119, 229, 255);
    private static final Color PINK = new Color(237, 126, 255, 255);
    private static final Color ORANGE = new Color(250, 179, 125, 255);
    private static final Color LIGHT_BLUE = new Color(135, 212, 255, 255);
    //color for paddle
    private static final Color LIGHT_GRAY = new Color(177, 169, 169, 255);
    //color for borders
    private static final Color DARK_GRAY = new Color(78, 74, 74, 255);
    //color for balls
    private static final Color BALL_COLOR = new Color(245, 245, 245, 255);
    private static final int BALL_RADIUS_SIZE = 6;
    private static final Color[] COLORS = {RED, YELLOW, GREEN, BLUE, PINK, ORANGE, PURPLE, LIGHT_BLUE};

    //{Color.MAGENTA, Color.YELLOW, Color.CYAN, Color.BLUE, Color.GREEN,
    //Color.RED, Color.PINK, Color.ORANGE};
    /**
     * Fields
     */
    private AnimationRunner runner;
    private boolean running;
    private ScoreTrackingListener scoreTrackListener;
    private Counter scoreCount;
    private Counter remainedBalls;
    private Counter remainedBlocks;
    private SpriteCollection sprites;
    private GameEnvironment environment;
    private GUI gui;
    private Paddle paddle;
    private KeyboardSensor keyboard;
    private LevelInformation levelInfo;

    public GameLevel(GUI gui, KeyboardSensor keyboard, LevelInformation levelInfo) {
        this.gui = gui;
        this.keyboard = keyboard;
        this.levelInfo = levelInfo;
        //this.runner = runner;
        //this.scoreCount = scoreCount;
    }

    /**
     * add collidable object to collidables collection of the game.
     *
     * @param c collidable object will e added to to environment.
     */
    public void addCollidable(Collidable c) {
        this.environment.addCollidable(c);
    }

    /**
     * add sprite object to sprite collection objects of the game.
     *
     * @param s sprite the will be added to sprites list
     */
    public void addSprite(Sprite s) {
        this.sprites.addSprite(s);
    }

    // Initialize a new game: create the Blocks and Ball (and Paddle)
    // and add them to the game.

    /**
     * new game initializing with all necessary attributes:
     * ball, blocks orders, blocks of the game, paddle etc.
     *
     */
    public void initialize(LevelInformation levelInformation){
        this.scoreCount = new Counter(0);
        this.scoreTrackListener = new ScoreTrackingListener(this.scoreCount);
        this.remainedBlocks = new Counter(levelInformation.numberOfBlocksToRemove());
        this.remainedBalls = new Counter(levelInformation.numberOfBalls());
        this.sprites = new SpriteCollection();
        this.sprites.addSprite(levelInformation.getBackground());
        this.environment = new GameEnvironment();
        //this.gui = new GUI("Arkanoid", 800, 600);
        this.runner = new AnimationRunner(this.gui, 60);

        //create block remover listener object
        BlockRemover blockRemoverListener = new BlockRemover(this, this.remainedBlocks);

        //create ball remover listener object
        BallRemover ballRemover = new BallRemover(this, this.remainedBalls);

        //creating score indicator
        ScoreIndicator scoreIndicator = new ScoreIndicator(this.scoreCount);

        //creating blocks
        List<Block> blockList = levelInformation.blocks();
        for (Block block : blockList) {
            block.addHitListener(blockRemoverListener);
            block.addToGame(this);
        }
        List<Block> borders = this.createBorders(ballRemover, BACKGROUND_WIDTH, BACKGROUND_HEIGHT, MARGINS_WIDTH);
        for (Block border : borders) {
            border.addToGame(this);
        }
        //create borders and add to game
        this.paddle = new Paddle(new Block(new Point(350, 550), levelInformation.paddleWidth(), 15, LIGHT_GRAY),
                this.gui.getKeyboardSensor(), levelInformation.paddleSpeed());
        this.paddle.addToGame(this);
        scoreIndicator.addToGame(this);
        for (Velocity v : levelInformation.initialBallVelocities()) {
            Ball ball = new Ball(this.paddle.getPaddleCenter().shiftPoint(-5.1, Axis.Y),5,Color.WHITE);
            ball.setVelocity(v);
            ball.addToGame(this);
        }
    }


    public boolean shouldStop() {
        return !this.running;
    }

    /**
     * running the game i.e. putting the game into action
     * where things are actually happen - they are drawn on gui surface and
     * respond according to defined methods
     */
    public void run() {
        this.runner.run(new CountDownAnimation(3, 3, this.sprites)); // countdown before turn starts.
        // use our runner to run the current animation -- which is one turn of
        // the game.
        this.running = true;
        this.runner.run(this);
    }

    private void createBallsOnTopOfPaddle() {
        //this.sprites.addSprite(s);
        //creating ball
        Point center = paddle.getPaddleCenter();
        Ball ball1 = new Ball(center, BALL_RADIUS_SIZE, BALL_COLOR);
        ball1.setVelocity(Velocity.fromAngleAndSpeed(120, 3));
        Ball ball2 = new Ball(center, BALL_RADIUS_SIZE, BALL_COLOR);
        ball2.setVelocity(Velocity.fromAngleAndSpeed(180, 4));
        Ball ball3 = new Ball(center, BALL_RADIUS_SIZE, BALL_COLOR);
        ball3.setVelocity(Velocity.fromAngleAndSpeed(125, 5));
        ball1.addToGame(this);
        ball2.addToGame(this);
        ball3.addToGame(this);
    }

    public void doOneFrame(DrawSurface d) {
        // the logic from the previous run method goes here.
        // the `return` or `break` statements should be replaced with
        // this.running = false;
        this.sprites.drawAllOn(d);
        this.sprites.notifyAllTimePassed();
       if (this.keyboard.isPressed("p")) {
           this.runner.run(new PauseScreen(this.keyboard));
        }
        if (this.remainedBlocks.getValue() == 0) {
            this.scoreCount.increase(100);
            System.out.println(this.scoreCount.getValue());
            this.running = false;
        }
        if (this.remainedBalls.getValue() == 0) {
            this.running = false;
        }
    }

    /**
     * getEnvironment method returns the game's environment.
     *
     * @return new Game environment (immutable and dynamic in the game)
     */
    public GameEnvironment getEnvironment() {
        return new GameEnvironment(this.environment.getCollidables());
    }

    /**
     * assignEnv method assigns ball object into the game environment if it's in the sprite.
     *
     * @param ball to load to the game
     */
    public void assignEnv(Ball ball) {
        if (this.sprites.contains(ball)) {
            ball.setEnvironment(this.environment);
        }
    }

    /**
     * creating the blocks of the game which the ball suppose to break.
     *
     * @param numFirstLineBlocks   according to this number all other blocks will be created
     *                             (each line one block less)
     * @param widthFill            blocks' width size to bo be colored
     * @param numLines             number of blocks' lines.
     * @param startY               y coordinate from where blocks building starts
     * @param blockDiff            difference of blocks between lines
     * @param blockRemoverListener listener for the blocks
     * @return blocks ArrayList
     */
    private ArrayList<Block> createBlocks(HitListener blockRemoverListener, int numFirstLineBlocks, double widthFill,
                                          int numLines, double startY, int blockDiff) {
        //background width subtract the two border blocks
        int blockWidth = ((int) ((BACKGROUND_WIDTH - (2 * MARGINS_WIDTH)) * widthFill) / numFirstLineBlocks);
        double startX = BACKGROUND_WIDTH - MARGINS_WIDTH;
        //create list of blocks
        ArrayList<Block> blocks = new ArrayList<>();
        //loop for each line index and add blocks to arrayList
        for (int i = 0; i < numLines; i++, startY += BLOCK_HEIGHT, numFirstLineBlocks -= blockDiff) {
            //colors mod operator- circular rotation
            blocks.addAll(buildBlockLine(blockRemoverListener, numFirstLineBlocks, new Point(startX, startY),
                    blockWidth, BLOCK_HEIGHT, COLORS[i % COLORS.length]));
        }
        return blocks;
    }

    /**
     * creating the block borders of the game to prevent from the ball to roll of the screen.
     *
     * @param widthGui    gui window width
     * @param heightGui   gui window height
     * @param widthBorder game borders' width
     * @param ballRemover listener object
     * @return ArrayList of blocks
     */
    public ArrayList<Block> createBorders(BallRemover ballRemover, int widthGui, int heightGui, int widthBorder) {
        ArrayList<Block> borders = new ArrayList<>();
        //left border
        Block leftBorder = new Block(new Point(0, 0), new Point(widthBorder, heightGui), DARK_GRAY);
        borders.add(leftBorder);
        //right border
        Block rightBorder = new Block(new Point(widthGui - widthBorder, 0), new Point(widthGui, heightGui), DARK_GRAY);
        borders.add(rightBorder);
        //upper border
        Block upperBorder = new Block(new Point(0, 0), new Point(widthGui, widthBorder), DARK_GRAY);
        borders.add(upperBorder);
        //bottom border
        Block bottomBorder = new Block(new Point(0, heightGui - widthBorder),
                new Point(widthGui, heightGui), DARK_GRAY);
        borders.add(bottomBorder);
        bottomBorder.addHitListener(ballRemover);
        return (borders);
    }

    /**
     * creating an arrayList of blocks for each line (in method above all lists will be unified)
     * and building it on gui surface starting on right side, going to left side of the screen.
     *
     * @param numFirstLineBlocks   number of blocks in the first line
     * @param startPoint           point where blocks building starts
     * @param width                of each block
     * @param height               of each block
     * @param color                of each lock
     * @param blockRemoverListener of the block to transfer information regarding block that was hit
     * @return ArrayList of Block objects
     */
    public ArrayList<Block> buildBlockLine(HitListener blockRemoverListener, int numFirstLineBlocks, Point startPoint,
                                           int width, int height, Color color) {

        ArrayList<Block> blocks = new ArrayList<>();
        double x = startPoint.getX() - width;
        double y = startPoint.getY();
        for (int i = 0; i < numFirstLineBlocks; i++, x -= width) {
            Block b = new Block(new Point(x, y), width, height, color);
            blocks.add(b);
            b.addHitListener(blockRemoverListener);
            b.addHitListener(scoreTrackListener);
        }
        return blocks;
    }

    /**
     * removeCollidable removes the collidable object from collidable object list.
     *
     * @param c collidale object
     */
    public void removeCollidable(Collidable c) {
        this.environment.removeCollidable(c);
    }

    /**
     * removeSprite method removes the sprite from the list.
     *
     * @param s sprite to removed from the list
     */
    public void removeSprite(Sprite s) {
        this.sprites.removeSprite(s);
    }

}