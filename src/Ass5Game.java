/**
 * @author Libby Goldin id 204566236
 * @version 1
 * @since 3/6/2020
 */

import java.awt.Color;
/**
 * Ass5Game class includes main class
 * the game will run from here.
 */
public class Ass5Game {
    /**
     * main class method.
     * @param args default of main function.
     */
    public static void main(String[] args) {
        Game game = new Game();
        Point startPoint = new Point(50, 50);
        game.initialize(startPoint, 10, Color.GREEN);
        game.run();
    }
}
