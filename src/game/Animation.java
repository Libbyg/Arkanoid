package game;

import biuoop.DrawSurface;

public interface Animation {
    boolean shouldStop();

    void doOneFrame(DrawSurface d);
}
