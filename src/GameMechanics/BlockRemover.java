package GameMechanics;

import Interfaces.HitListener;
import Sprites.Ball;
import Sprites.Block;

//Omri Cohen 318673407

/**
 * The type Block remover.
 */
public class BlockRemover implements HitListener {
    private GameLevel gameLevel;
    private Counter remainingBlocks;

    /**
     * Instantiates a new Block remover.
     *
     * @param gameLevel            the gameLevel
     * @param remainingBlocks the remaining blocks
     */
    public BlockRemover(GameLevel gameLevel, Counter remainingBlocks) {
        this.gameLevel = gameLevel;
        this.remainingBlocks = remainingBlocks;
    }

    /**
     * Removes a block that was hit.
     *
     * @param beingHit the block that was hit
     * @param hitter the ball that hit the block
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        beingHit.removeFromGame(this.gameLevel);
        beingHit.removeHitListener(this);
        this.remainingBlocks.decrease(1);
    }
}
