package game; /**
 * @author Libby Goldin id:204566236
 * @version 1
 * @since 3/6/2020
 */

import biuoop.DrawSurface;

import java.awt.Color;

/**
 * class of scoreIndicator object, kind of a sprite.
 */
public class ScoreIndicator implements Sprite {
    /**
     * Field.
     */
    private Counter score;
    private static final String TXT_DISPLAY = "Score : ";

    /**
     * Constructor.
     * @param score player's points in the game.
     */
    public ScoreIndicator(Counter score) {
        this.score = score;
    }

    /**
     * drawing the score od surface.
     * @param d surface on which we draw the sprites
     */
    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(Color.WHITE);
        d.drawText(400, 18, TXT_DISPLAY + this.score.getValue(), 20);
    }

    /**
     * time passed for sprites.
     */
    @Override
    public void timePassed() {
    }

    /**
     * timeIndicator which is kind of a sprite will
     * be added to sprites collection and loaded to the game.
     * @param game to add the sprite to its sprite collection.
     */
    public void addToGame(GameLevel game) {
        game.addSprite(this);
    }

}
