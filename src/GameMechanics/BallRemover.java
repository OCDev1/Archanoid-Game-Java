package GameMechanics;

//Omri Cohen 318673407

import Interfaces.HitListener;
import Sprites.Ball;
import Sprites.Block;

/**
 * The type Ball remover.
 */
public class BallRemover implements HitListener {
    private GameLevel gameLevel;
    private Counter remainingBalls;

    /**
     * Instantiates a new Ball remover.
     *
     * @param gameLevel           the gameLevel
     * @param remainingBalls the remaining balls
     */
    public BallRemover(GameLevel gameLevel, Counter remainingBalls) {
        this.gameLevel = gameLevel;
        this.remainingBalls = remainingBalls;
    }

    /**
     * Hit event.
     *
     * @param deathRegion the death region
     * @param ball        the ball
     */
    @Override
    public void hitEvent(Block deathRegion, Ball ball) {
        ball.removeFromGame(gameLevel);
        remainingBalls.decrease(1);
    }
}
