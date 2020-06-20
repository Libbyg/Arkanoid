
/**
 * @author Libby Goldin 204566236
 * @version 1.0
 * @since 21/6/2020
 */
package game.levels;
import game.objects.Block;
import game.objects.Sprite;
import game.objects.Velocity;
import java.awt.Color;
import java.util.List;

/**
 * LevelInformation interface.
 */
public interface LevelInformation {
    /**
     * static final variables.
     */
    int BACKGROUND_WIDTH = 800;
    int BACKGROUND_HEIGHT = 600;
    int BLOCK_HEIGHT = 20;
    int MARGINS_WIDTH = 20;

    /**
     * number of balls in the level.
     * @return int number of balls.
     */
    int numberOfBalls();
    // The initial velocity of each ball
    // Note that initialBallVelocities().size() == numberOfBalls()

    /**
     * initializing velocities of each ball in the level.
     * @return list of velocities of level balls.
     */
    List<Velocity> initialBallVelocities();

    /**
     * paddle speed will be unique for each level.
     * @return int speed
     */
    int paddleSpeed();

    /**
     * paddle width size will be unique for each level.
     * @return int width size of paddle
     */
    int paddleWidth();
    // the level name will be displayed at the top of the screen.

    /**
     * level name will be unique for each level in the game.
     * @return string name of level.
     */
    String levelName();
    // Returns a sprite with the background of the level

    /**
     * getBackground returns the background that
     * was "tailor-made" for specific level.
     * @return the background of the level.
     */
    Sprite getBackground();
    // The Blocks that make up this level, each block contains
    // its size, color and location.

    /**
     * blocks of each level differs in quantity, colors, the way they built/organized etc.
     * @return List of blocks each list will be unique for each level.
     */
    List<Block> blocks();
    // Number of blocks that should be removed
    // before the level is considered to be "cleared".
    // This number should be <= blocks.size();

    /**
     * a unique number of blocks in each level.
     * @return integer number of blocks to be removed.
     */
    int numberOfBlocksToRemove();

    /**
     * Color of each paddle is going to be unique also.
     * @return paddle's color.
     */
    Color getPaddleColor();
}