
/**
 * @author Libby Goldin 204566236
 * @version 1.0
 * @since 21/6/2020
 */
package game.objects;
import biuoop.DrawSurface;
import game.collections.SpriteCollection;

/**
 * Background class.
 */
public class Background implements Sprite {
    /**
     * Fields.
     */
    private Sprite background;
    private SpriteCollection objects = new SpriteCollection();

    /**
     * Constructor.
     * @param background of the level in the game.
     */
    public Background(Sprite background) {
        this.background = background;
    }

    /**
     * background to be added to game sprite's.
     * @param s sprite object
     */
    public void addSprite(Sprite s) {
        objects.addSprite(s);
    }

    public void removeSprite(Sprite s) {
        objects.removeSprite(s);
    }

    /**
     * drawOn methos draws the background of the game on surface.
     * @param d surface on which we draw the sprites
     */
    @Override
    public void drawOn(DrawSurface d) {
        this.background.drawOn(d);
        this.objects.drawAllOn(d);
    }

    /**
     * passing time of the background when the game is running.
     */
    @Override
    public void timePassed() {
        this.background.timePassed();
        this.objects.notifyAllTimePassed();
    }
}
