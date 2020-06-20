/**
 * @author Libby Goldin 204566236
 * @version 1.0
 * @since 21/6/2020
 */
import biuoop.GUI;
import game.GameFlow;
import game.levels.LevelInformation;
import game.levels.Level1;
import game.levels.Level2;
import game.levels.Level3;
import game.levels.Level4;
import geometry.Point;
import java.util.ArrayList;
import java.util.List;

/**
 * Sixth Assignment Game.
 */
public class Ass6Game {
    /**
     * Main class method.
     * @param args from user which level to run.
     * the options are: 1-4.
     * Or run all the levels chronologically.
     */
    public static void main(String[] args) {
        List<LevelInformation> levelInformationList = new ArrayList<>();
        GUI gui = new GUI("Arkanoid", 800, 600);
        GameFlow gameFlow = new GameFlow(gui);
        Point topLeft = new Point(0, 0);
        Point bottomRight = new Point(800, 600);
        if (args.length == 0) {
            levelInformationList.add(new Level1(topLeft, bottomRight));
            levelInformationList.add(new Level2(topLeft, bottomRight));
            levelInformationList.add(new Level3(topLeft, bottomRight));
            levelInformationList.add(new Level4(topLeft, bottomRight));
            gameFlow.runLevels(levelInformationList);
            gui.close();
        } else {
            for (String str : args) {
                try {
                    int levelNumber = Integer.parseInt(str);
                    switch (levelNumber) {
                        case 1:
                            levelInformationList.add(new Level1(topLeft, bottomRight));
                            break;
                        case 2:
                            levelInformationList.add(new Level2(topLeft, bottomRight));
                            break;
                        case 3:
                            levelInformationList.add(new Level3(topLeft, bottomRight));
                            break;
                        case 4:
                            levelInformationList.add(new Level4(topLeft, bottomRight));
                            break;
                        default:
                            break;
                    }
                } catch (Exception e) {
                    continue;
                }
            }
            gameFlow.runLevels(levelInformationList);
            gui.close();
        }
    }
}

