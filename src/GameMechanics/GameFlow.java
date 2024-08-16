package GameMechanics;

//Omri Cohen 318673407

import Interfaces.Animation;
import Interfaces.LevelInformation;
import biuoop.GUI;
import biuoop.KeyboardSensor;

import java.util.List;

/**
 * The type Game flow.
 */
public class GameFlow {
    //fields
    private Counter score;
    private AnimationRunner animationRunner;
    private KeyboardSensor keyboard;
    private GUI gui;
    private boolean gameOver;

    /**
     * Instantiates a new Game flow.
     *
     * @param ar the AnimationRunner
     * @param ks the KeyboardSensor
     */
    public GameFlow(AnimationRunner ar, KeyboardSensor ks) {
        this.score = new Counter();
        this.animationRunner = ar;
        this.keyboard = ks;
    }

    /**
     * Run levels - according to the List levels.
     *
     * @param levels the levels
     */
    public void runLevels(List<LevelInformation> levels) {
        score.setValue(0);
        //running every level in the list
        for (LevelInformation levelInfo : levels) {
            GameLevel level = new GameLevel(levelInfo, keyboard, animationRunner, score);
            if (levels.indexOf(levelInfo) != 0) {
                score.setValue(score.getValue() + 100);
            }
            level.initialize();
            level.run();
            if (level.getNumOfBalls().getValue() == 0) {
                gameOver = true;
                break;
            }
            score.setValue(score.getValue());
        }
        if (gameOver) {
            showEndScreen("Game Over. Your score is " + score.getValue());
        } else {
            boolean blocksLeft = false;
            for (LevelInformation levelInfo : levels) {
                if (levelInfo.blocksLeft()) {
                    blocksLeft = true;
                    break;
                }
            }

            if (!blocksLeft) {
                score.setValue(score.getValue() + 100);
                showEndScreen("You Win! Your score is " + score.getValue());
            }
        }
        waitForSpaceKey();
        animationRunner.getGui().close();
    }

    private void showEndScreen(String message) {
        Animation endScreen = new EndScreen(message);
        Animation keyPressStoppableAnimation =
                new KeyPressStoppableAnimation(keyboard, KeyboardSensor.SPACE_KEY, endScreen);
        animationRunner.run(keyPressStoppableAnimation);
    }

    private void waitForSpaceKey() {
        Animation endScreen;
        if (gameOver) {
            endScreen = new EndScreen("Game Over. Your score is " + score.getValue());
        } else {
            score.setValue(score.getValue() + 100);
            endScreen = new EndScreen("You Win! Your score is " + score.getValue());
        }

        Animation keyPressStoppableAnimation =
                new KeyPressStoppableAnimation(keyboard, KeyboardSensor.SPACE_KEY, endScreen);
        animationRunner.run(keyPressStoppableAnimation);
    }

}
