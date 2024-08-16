package Interfaces;

//Omri Cohen 318673407

import Geometry.Point;
import Geometry.Velocity;
import Sprites.Block;
import java.util.List;

/**
 * The interface Level information.
 */
public interface LevelInformation {
    /**
     * return Number of balls.
     *
     * @return the Number of balls
     */
    int numberOfBalls();

    /**
     * // The initial velocity of each ball.
     *
     * @return the list
     */
// Note that initialBallVelocities().size() == numberOfBalls()
    List<Velocity> initialBallVelocities();

    /**
     * Paddle speed.
     *
     * @return the Paddle speed
     */
    int paddleSpeed();

    /**
     * Paddle width.
     *
     * @return the paddle width
     */
    int paddleWidth();

    /**
     * the level name will be displayed at the top of the screen.
     *
     * @return the string
     */
    String levelName();

    /**
     * Returns a sprite with the background of the level.
     *
     * @return the background
     */
    Sprite getBackground();

    /**
     * Blocks left boolean.
     *
     * @return the boolean
     */
    boolean blocksLeft();

    /**
     * Ball start points list.
     * The initial velocity of each ball
     * Note that initialBallVelocities().size() == numberOfBalls()
     *
     * @return the list
     */
    List<Point> getBallStartPs();

    /**
     * The Blocks that make up this level, each block contains its size, color and location.
     *
     * @return the list
     */
    List<Block> blocks();

    /**
     * Number of blocks that should be removed before the level is considered to be "cleared".
     *
     * @return the int
     */
// This number should be <= blocks.size();
    int numberOfBlocksToRemove();
}
