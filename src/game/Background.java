package game;

import biuoop.DrawSurface;

public class Background implements Sprite{
    private Sprite background;
    private SpriteCollection objects = new SpriteCollection();

    public Background(Sprite background) {
        this.background = background;
    }

    public void addSprite(Sprite s) {
        objects.addSprite(s);
    }

    public void removeSprite(Sprite s) {
        objects.removeSprite(s);
    }

    @Override
    public void drawOn(DrawSurface d) {
        this.background.drawOn(d);
        this.objects.drawAllOn(d);
    }

    @Override
    public void timePassed() {
        this.background.timePassed();
        this.objects.notifyAllTimePassed();
    }
}
