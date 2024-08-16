//Omri Cohen 318673407

import GameMechanics.AnimationRunner;
import GameMechanics.Counter;
import GameMechanics.GameFlow;
import Interfaces.LevelInformation;
import Levels.Level1;
import Levels.Level2;
import Levels.Level3;
import biuoop.GUI;
import biuoop.KeyboardSensor;

import java.util.ArrayList;
import java.util.List;

/**
 * The type Ass 6 game.
 */
public class Ass6Game {
    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        GUI gui = new GUI("Arkanoid", 800, 600);
        AnimationRunner animationRunner = new AnimationRunner(gui, 60);
        KeyboardSensor keyboardSensor = animationRunner.getGui().getKeyboardSensor();
        List<LevelInformation> levels = new ArrayList<>();
        Counter score = new Counter();
        if (args.length > 0) {
            for (String arg : args) {
                try {
                    int levelNumber = Integer.parseInt(arg);
                    if (levelNumber == 1) {
                        levels.add(new Level1());
                    } else if (levelNumber == 2) {
                        levels.add(new Level2());
                    } else if (levelNumber == 3) {
                        levels.add(new Level3());
                    }
                } catch (NumberFormatException e) {
                    //invalid argument
                }
            }
            if (!levels.isEmpty()) {
                GameFlow game = new GameFlow(animationRunner, keyboardSensor);
                game.runLevels(levels);
            }
        }
        if (args.length >= 0) {
            levels.add(new Level1());
            levels.add(new Level2());
            levels.add(new Level3());
            GameFlow game = new GameFlow(animationRunner, keyboardSensor);
            game.runLevels(levels);
        }
    }
}
