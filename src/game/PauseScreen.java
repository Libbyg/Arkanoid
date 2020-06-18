package game;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

import java.awt.*;

public class PauseScreen implements Animation {
    private static final Color PINK = new Color(231, 98, 129);
    private static final int RECT_ANIMATION_X = 100;
    private static final int RECT_ANIMATION_Y = 150;
    private static final int RECT_ANIMATION_WIDTH = 600;
    private static final int RECT_ANIMATION_HEIGHT = 300;

    private KeyboardSensor keyboard;

    private boolean stop;

    public PauseScreen(KeyboardSensor k) {
        this.keyboard = k;
        this.stop = false;
    }
    public void doOneFrame(DrawSurface d) {
        d.setColor(PINK);
        d.drawRectangle(RECT_ANIMATION_X, RECT_ANIMATION_Y, RECT_ANIMATION_WIDTH, RECT_ANIMATION_HEIGHT);
        d.fillRectangle(RECT_ANIMATION_X, RECT_ANIMATION_Y, RECT_ANIMATION_WIDTH, RECT_ANIMATION_HEIGHT);
        d.setColor(Color.WHITE);
        d.drawText(150,  d.getHeight() / 2, "paused -- press space to continue", 32);
        if (this.keyboard.isPressed(KeyboardSensor.SPACE_KEY)) { this.stop = true; }
    }
    public boolean shouldStop() {
        return this.stop; }
}