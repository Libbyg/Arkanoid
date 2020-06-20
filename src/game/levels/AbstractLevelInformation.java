
/**
 * @author Libby Goldin 204566236
 * @version 1.0
 * @since 21/6/2020
 */
package game.levels;
import game.objects.Velocity;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * AbstractLevelInformation class for all levels.
 * prevent from common code to be repeated.
 */
public abstract class AbstractLevelInformation implements LevelInformation {
    /**
     * Fields.
     */
    private String levelName;
    private int numOfBalls;
    private int paddleWidth;
    private int paddleSpeed;
    private Color paddleColor;

    /**
     * Constructor.
     * @param levelName
     * @param numOfBalls
     * @param paddleWidth
     * @param paddleSpeed
     * @param paddleColor
     */
    public AbstractLevelInformation(String levelName, int numOfBalls, int paddleWidth, int paddleSpeed, Color paddleColor) {
        this.levelName = levelName;
        this.numOfBalls = numOfBalls;
        this.paddleWidth = paddleWidth;
        this.paddleSpeed = paddleSpeed;
        this.paddleColor = paddleColor;
    }

    @Override
    public int numberOfBalls() {
        return this.numOfBalls;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        List <Velocity> velocities = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < this.numberOfBalls(); i++) {
            velocities.add(Velocity.fromAngleAndSpeed((270+random.nextInt(180))%360,7));
        }
        return velocities;
    }

    @Override
    public int paddleSpeed() {
        return this.paddleSpeed;
    }

    @Override
    public int paddleWidth() {
        return this.paddleWidth;
    }

    @Override
    public String levelName() {
        return this.levelName;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return this.blocks().size();
    }

    @Override
    public Color getPaddleColor() {
        return this.paddleColor;
    }
}
