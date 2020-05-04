import biuoop.DrawSurface;
import java.util.ArrayList;
import biuoop.DrawSurface;

public class SpriteCollection {
    private ArrayList<Sprite> sprites;

    //creates sprite collection
    public SpriteCollection(){
        sprites = new ArrayList<Sprite>();
    }

    public void addSprite(Sprite s){
        sprites.add(s);
    }

    public void removeSprite(Sprite s){
        sprites.remove(s);
    }


    // call timePassed() on all sprites.
    public void notifyAllTimePassed(double time){
        //to prevent exceptions we'll copy the sprites collection
        ArrayList<Sprite> spritesCopy = new ArrayList<Sprite>(sprites);
        for (Sprite sprite: spritesCopy){
            sprite.timePassed(time);
        }

    }

    // call drawOn(d) on all sprites.
    public void drawAllOn(DrawSurface d){
        ArrayList<Sprite> spritesCopy = new ArrayList<Sprite>(sprites);
        for (Sprite sprite: spritesCopy){
            sprite.drawOn(d);
        }
    }
}