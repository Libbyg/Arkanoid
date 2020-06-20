
/**
 * @author Libby Goldin 204566236
 * @version 1.0
 * @since 21/6/2020
 */
package game.objects;
import biuoop.DrawSurface;
import game.levels.GameLevel;
import java.awt.Color;

/**
 * LevelIndicator class which indicates the activated current level at the game.
 */
public class LevelIndicator implements Sprite {
    /**
     * Fields.
     */
    private String name;
    private static final String TXT_DISPLAY = "Level Name : ";

    /**
     * Constructor.
     * @param name of the level.
     */
    public LevelIndicator(String name) {
        this.name = name;
    }

    /**
     * drawOn method is drawing the level name on a surface and display it to user.
     * @param d surface on which we draw the sprites
     */
    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(Color.WHITE);
        d.drawText(400, 18, TXT_DISPLAY + this.name, 20);
    }

    @Override
    public void timePassed() {
        //nothing
    }

    /**
     * adding the level name to the game.
     * @param game
     */
    public void addToGame(GameLevel game) {
        game.addSprite(this);
    }

}
