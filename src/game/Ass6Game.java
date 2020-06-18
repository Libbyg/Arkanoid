package game; /**
 * @author Libby Goldin id 204566236
 * @version 1
 * @since 3/6/2020
 */

import biuoop.GUI;
/**
 * Ass5Game class includes main class
 * the game will run from here.
 */
public class Ass6Game {
    /**
     * main class method.
     * @param args default of main function.
     */
    public static void main(String[] args) {
        GUI gui = new GUI("Arkanoid", 800, 600);
        GameLevel game = new GameLevel(gui, gui.getKeyboardSensor(), new Level4());
        //game.initialize(new Level1());
        //game.initialize(new Level2());
        //game.initialize(new Level3());
        game.initialize(new Level4());

        //game.initialize();
        game.run();
        gui.close();
    }
}
