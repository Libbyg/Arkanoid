/**
 * @author Libby Goldin 204566236
 * @version 1.0
 * @since 2020-05-06
 */
import biuoop.DrawSurface;
import java.util.ArrayList;
import biuoop.DrawSurface;


public class SpriteCollection {
    /**
     * field ArrayLit of Sprite objects
     */
    private ArrayList<Sprite> sprites;

    /**
     * constructor
     */
    //creates sprite collection
    public SpriteCollection(){
        sprites = new ArrayList<Sprite>();
    }

    /**
     * addSprite method addSprite adds sprite to sprites collection
     * @param s
     */
    public void addSprite(Sprite s) {
        sprites.add(s);
    }

    /**
     * the method removeSprite removes the sprite from sprites collection
     * @param s
     */
    public void removeSprite(Sprite s) {
        sprites.remove(s);
    }

    /**
     * the method notifyAllTimePassed activate timePassed method on each sprite
     */
    // call timePassed() on all sprites.
    public void notifyAllTimePassed() {
        //to prevent exceptions we'll copy the sprites collection
        ArrayList<Sprite> spritesCopy = new ArrayList<Sprite>(sprites);
        for (Sprite sprite: spritesCopy){
            sprite.timePassed();
        }
    }

    /**
     *The method contains checks if the sprite is in sprites collections
     * @param s
     * @return boolean true/false
     */
    public boolean contains(Sprite s) {
        return this.sprites.contains(s);
    }

    /**
     * the method draws sprites from collection on draw surface (of the gui)
     * @param d
     */
    // call drawOn(d) on all sprites.
    public void drawAllOn(DrawSurface d){
        ArrayList<Sprite> spritesCopy = new ArrayList<Sprite>(sprites);
        for (Sprite sprite: spritesCopy){
            sprite.drawOn(d);
        }
    }
}