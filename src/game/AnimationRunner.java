package game;

import biuoop.GUI;
import biuoop.DrawSurface;
import biuoop.Sleeper;

public class AnimationRunner {
    private GUI gui;
    private int framesPerSecond = 60;

    public AnimationRunner(GUI gui, int framesPerSecond) {
        this.gui = gui;
        this.framesPerSecond = framesPerSecond;
    }
    public void run(Animation animation) {
        int millisecondsPerFrame = 1000 / this.framesPerSecond;
        while (!animation.shouldStop()) {
            long startTime = System.currentTimeMillis(); // timing
            DrawSurface d = gui.getDrawSurface();

            animation.doOneFrame(d);

            gui.show(d);
            Sleeper sleeper = new Sleeper();
            long usedTime = System.currentTimeMillis() - startTime;
            long milliSecondLeftToSleep = millisecondsPerFrame - usedTime;
            if (milliSecondLeftToSleep > 0) {
                sleeper.sleepFor(milliSecondLeftToSleep);
            }
        }
    }

    public GUI getGui(){
        return this.gui;
    }
}