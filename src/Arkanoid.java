import java.awt.Color;

public class Arkanoid {
    public static void main(String[] args) {
        Game game = new Game();
        Point startPoint = new Point(50,50);
        game.initialize(startPoint,10, Color.GREEN);
        game.run();
    }
}