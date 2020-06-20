
/**
 * @author Libby Goldin 204566236
 * @version 1.0
 * @since 21/6/2020
 */
package game;
import biuoop.GUI;
import biuoop.KeyboardSensor;
import game.animations.AnimationRunner;
import game.animations.EndScreen;
import game.animations.KeyPressStoppableAnimation;
import game.animations.WinScreen;
import game.levels.GameLevel;
import game.levels.LevelInformation;
import game.listeners.Counter;
import utilities.LevelEndedStatus;
import java.util.List;

/**
 * GameFlow class.
 */
public class GameFlow {
    /**
     * Fields.
     */
    private AnimationRunner animationRunner;
    private Counter score;

    /**
     * Constructor.
     * @param gui of the game, dynamically changing when the game is running.
     */
    public GameFlow(GUI gui) {
        this.animationRunner = new AnimationRunner(gui, 60);
        this.score = new Counter(0);
    }

    /**
     * GameFlow will run the levels of the game using the provided info of each level.
     * @param levels contains the information of each level that will run.
     */
    public void runLevels(List<LevelInformation> levels) {
        this.score = new Counter(0);
        for (LevelInformation levelInfo : levels) {
            GameLevel level = new GameLevel(this.animationRunner, this.score);
            level.initialize(levelInfo);
            level.run();
            if (level.getStatus() == LevelEndedStatus.LOST) {
                this.animationRunner.run(new KeyPressStoppableAnimation(this.animationRunner.getKeyboardSensor(),
                        KeyboardSensor.SPACE_KEY, new EndScreen(score.getValue())));
                break;
            }
            this.score.increase(100);
        }
        this.animationRunner.run(new KeyPressStoppableAnimation(this.animationRunner.getKeyboardSensor(),
                KeyboardSensor.SPACE_KEY, new WinScreen(score.getValue())));
        //todo as part of ass7 insert high scores
    }
}
