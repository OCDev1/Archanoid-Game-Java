package GameMechanics;

//Omri Cohen 318673407

import Interfaces.HitListener;
import Sprites.Ball;
import Sprites.Block;

/**
 * The type Score tracking listener.
 */
public class ScoreTrackingListener implements HitListener {
    private Counter currentScore;

    /**
     * Instantiates a new Score tracking listener.
     *
     * @param scoreCounter the score counter
     */
    public ScoreTrackingListener(Counter scoreCounter) {
        this.currentScore = scoreCounter;
    }

    /**
     * Instantiates a new Score tracking listener.
     *
     * @param beingHit the block being hit
     * @param hitter the ball that hit the block
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        // Update score by 5 points for hitting a block
        currentScore.increase(5);
    }
}