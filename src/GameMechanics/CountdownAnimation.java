package GameMechanics;

//Omri Cohen 318673407

import Interfaces.Animation;
import biuoop.DrawSurface;
import biuoop.Sleeper;

import java.awt.Color;

/**
 * The type Countdown animation.
 */
public class CountdownAnimation implements Animation {
    private double durationInSeconds;
    private int countFrom;
    private SpriteCollection gameScreen;
    private boolean stop;

    /**
     * Instantiates a new Countdown animation.
     *
     * @param duration   the duration
     * @param countFrom  the count from
     * @param gameScreen the game screen
     */
    public CountdownAnimation(double duration, int countFrom, SpriteCollection gameScreen) {
        this.durationInSeconds = duration;
        this.countFrom = countFrom;
        this.gameScreen = gameScreen;
        this.stop = false;
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        Sleeper sleeper = new Sleeper();
        // Display the game screen
        gameScreen.drawAllOn(d);
        d.setColor(Color.white);

        // Display the countdown number
        if (countFrom >= 0) {
            d.drawText(d.getWidth() / 2, d.getHeight() / 2, String.valueOf(countFrom), 60);
            long durationPerNumber = (long) (durationInSeconds * 1000 / (countFrom + 1.5));
            sleeper.sleepFor(durationPerNumber);
            countFrom--;
        } else {
            // Countdown finished, stop the animation
            stop = true;
        }
    }


    @Override
    public boolean shouldStop() {
        return stop;
    }
}

