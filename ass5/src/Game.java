/**
 * @author Libby Goldin 204566236
 * @version 1.0
 * @since 2020-05-06
 */
import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;
import java.awt.Color;
import java.util.ArrayList;

/**
 * Game Class sets the object named game.
 */
public class Game {
    /**
     * static final variables.
     */
    private static final int BACKGROUND_WIDTH = 800;
    private static final int BACKGROUND_HEIGHT = 600;
    private static final int BLOCK_HEIGHT = 20;
    private static final int MARGINS_WIDTH = 20;
    private static final Color[] COLORS = {Color.MAGENTA, Color.YELLOW, Color.CYAN, Color.BLUE, Color.GREEN,
            Color.RED, Color.PINK, Color.ORANGE};

    private ScoreTrackingListener scoreTrackListener;
    private Counter scoreCount;
    private Counter remainedBalls;
    private Counter remainedBlocks;
    private PrintingHitListener printListener;
    private SpriteCollection sprites;
    private GameEnvironment environment;
    private GUI gui;

    /**
     * add collidable object to collidables collection of the game.
     * @param c collidable object will e added to to environment.
     */
    public void addCollidable(Collidable c) {
        this.environment.addCollidable(c);
    }

    /**
     * add sprite object to sprite collection objects of the game.
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
     * @param center point of balls
     * @param radius of the balls
     * @param color of balls
     */
    public void initialize(Point center, int radius, Color color) {

        this.scoreCount = new Counter(0);
        this.scoreTrackListener = new ScoreTrackingListener(this.scoreCount);
        this.remainedBlocks = new Counter(36);
        this.remainedBalls = new Counter(3);
        this.printListener = new PrintingHitListener();
        this.sprites = new SpriteCollection();
        this.environment = new GameEnvironment();
        this.gui = new GUI("Arkanoid", 800, 600);

        //create block remover listener object
        BlockRemover blockRemoverListener = new BlockRemover(this, this.remainedBlocks);

        //create ball remover listener object
        BallRemover ballRemover = new BallRemover(this, this.remainedBalls);

        //create score listener
        ScoreTrackingListener scoreTrack = new ScoreTrackingListener(this.scoreCount);


        //this.sprites.addSprite(s);
        //creating ball
        Ball ball1 = new Ball(center, radius, color);
        ball1.setVelocity(Velocity.fromAngleAndSpeed(120, 3));
        Ball ball2 = new Ball(center, radius, color);
        ball2.setVelocity(Velocity.fromAngleAndSpeed(180, 3));
        Ball ball3 = new Ball(center, radius, color);
        ball3.setVelocity(Velocity.fromAngleAndSpeed(125, 3));
       //creating score indicator
        ScoreIndicator scoreIndicator = new ScoreIndicator(this.scoreCount);

        //creating blocks
        ArrayList<Block> blockList = this.createBlocks(blockRemoverListener, 8, 0.75, 8, BACKGROUND_HEIGHT * 0.4, 1);
        for (Block block : blockList) {
            block.addToGame(this);
        }
        ArrayList<Block> borders = this.createBorders(ballRemover, BACKGROUND_WIDTH, BACKGROUND_HEIGHT, MARGINS_WIDTH);
        for (Block border : borders) {
            border.addToGame(this);
        }
        //create borders and add to game
        Paddle paddle = new Paddle(new Block(new Point(350, 550), 100, 15, Color.BLACK),
                this.gui.getKeyboardSensor());
        paddle.addToGame(this);
        ball1.addToGame(this);
        ball2.addToGame(this);
        ball3.addToGame(this);
        scoreIndicator.addToGame(this);

    }

    /**
     * running the game i.e. putting the game into action
     * where things are actually happen - they are drawn on gui surface and
     * respond according to defined methods
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
            if (this.remainedBlocks.getValue() == 0) {
                this.scoreCount.increase(100);
                System.out.println(this.scoreCount.getValue());
                gui.close();
            }
            if (this.remainedBalls.getValue() == 0) {
                gui.close();
            }
        }
    }

    /**
     * getEnvironment method returns the game's environment.
     * @return new Game environment (immutable and dynamic in the game)
     */
    public GameEnvironment getEnvironment() {
        return new GameEnvironment(this.environment.getCollidables());
    }

    /**
     * assignEnv method assigns ball object into the game environment if it's in the sprite.
     * @param ball to load to the game
     */
    public void assignEnv(Ball ball) {
        if (this.sprites.contains(ball)) {
            ball.setEnvironment(this.environment);
        }
    }

    /**
     * creating the blocks of the game which the ball suppose to break.
     * @param numFirstLineBlocks according to this number all other blocks will be created
     * (each line one block less)
     * @param widthFill blocks' width size to bo be colored
     * @param numLines number of blocks' lines.
     * @param startY y coordinate from where blocks building starts
     * @param blockDiff difference of blocks between lines
     * @param blockRemoverListener listener for the blocks
     * @return blocks ArrayList
     */
    private ArrayList<Block> createBlocks(BlockRemover blockRemoverListener, int numFirstLineBlocks, double widthFill,
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
     * @param widthGui gui window width
     * @param heightGui gui window height
     * @param widthBorder game borders' width
     * @param ballRemover listener object
     * @return ArrayList of blocks
     */
    public ArrayList<Block> createBorders(BallRemover ballRemover, int widthGui, int heightGui, int widthBorder) {
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
        Block bottomBorder = new Block(new Point(0, heightGui - widthBorder),
                new Point(widthGui, heightGui), Color.GRAY);
        borders.add(bottomBorder);
        bottomBorder.addHitListener(ballRemover);
        return (borders);
    }

    /**
     * creating an arrayList of blocks for each line (in method above all lists will be unified)
     * and building it on gui surface starting on right side, going to left side of the screen.
     * @param numFirstLineBlocks number of blocks in the first line
     * @param startPoint point where blocks building starts
     * @param width of each block
     * @param height of each block
     * @param color of each lock
     * @param blockRemoverListener of the block to transfer information regarding block that was hit
     * @return ArrayList of Block objects
     */
    public ArrayList<Block> buildBlockLine(BlockRemover blockRemoverListener, int numFirstLineBlocks, Point startPoint,
                                           int width, int height, Color color) {

        ArrayList<Block> blocks = new ArrayList<>();
        double x = startPoint.getX() - width;
        double y = startPoint.getY();
        for (int i = 0; i < numFirstLineBlocks; i++, x -= width) {
            Block b = new Block(new Point(x, y), width, height, color);
            blocks.add(b);
            b.addHitListener(this.printListener);
            b.addHitListener(blockRemoverListener);
            b.addHitListener(scoreTrackListener);
        }
        return blocks;
    }

    /**
     * removeCollidable removes the collidable object from collidable object list.
     * @param c collidale object
     */
    public void removeCollidable(Collidable c) {
        this.environment.removeCollidable(c);
    }

    /**
     * removeSprite method removes the sprite from the list.
     * @param s sprite to removed from the list
     */
    public void removeSprite(Sprite s) {
        this.sprites.removeSprite(s);
    }

}