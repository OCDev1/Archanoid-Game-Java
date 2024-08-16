package GameMechanics;

//Omri Cohen 318673407

import Interfaces.Animation;
import biuoop.DrawSurface;

/**
 * The type End screen.
 */
public class EndScreen implements Animation {
    private static final int TEXT_SIZE = 32;
    private static final int TEXT_X = 250;
    private static final int TEXT_Y = 250;

    private String message;

    /**
     * Instantiates a new End screen.
     *
     * @param message the message
     */
    public EndScreen(String message) {
        this.message = message;
    }

    /**
     * Do one frame.
     *
     * @param d the d
     */
    @Override
    public void doOneFrame(DrawSurface d) {
        d.drawText(TEXT_X, TEXT_Y, message, TEXT_SIZE);
    }

    /**
     * Should stop boolean.
     *
     * @return the boolean
     */
    @Override
    public boolean shouldStop() {
        return false; // Keep the end screen displayed until space key is pressed
    }
}