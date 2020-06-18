package game;

import biuoop.DrawSurface;
import biuoop.Sleeper;
import java.awt.Color;

public class CountDownAnimation implements Animation {
    private double numOfSeconds;
    private int startingCount;
    private int currentCount;
    private SpriteCollection sprites;
    private boolean stop;


    public CountDownAnimation(double numOfSeconds, int startingCount, SpriteCollection sprites) {
        this.numOfSeconds = numOfSeconds;
        this.startingCount = startingCount;
        this.sprites = sprites;
        this.currentCount = startingCount;
        this.stop = false;
    }

    @Override
    public boolean shouldStop() {
        return this.stop;
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        Sleeper sleeper = new Sleeper();
        if (this.currentCount < this.startingCount) {
            sleeper.sleepFor((long) ((this.numOfSeconds / this.startingCount) * 1000));
        }
        sprites.drawAllOn(d);
        if (this.currentCount != 0) {
            d.setColor(Color.RED);
            d.drawText(d.getWidth() / 2, d.getHeight() / 5, Integer.toString(this.currentCount), 32);
        }
        this.currentCount--;
        if (currentCount < 0){
            d.setColor(Color.RED);
            d.drawText(d.getWidth() / 2, d.getHeight() / 5,"GO!", 32);
        }

        if (currentCount < -1) {
            this.stop = true;
        }

    }

}
