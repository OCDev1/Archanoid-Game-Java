package Levels;

//Omri Cohen 318673407

import GameMechanics.BackgroundSetter;
import Geometry.Point;
import Geometry.Rectangle;
import Geometry.Velocity;
import Interfaces.LevelInformation;
import Interfaces.Sprite;
import Sprites.Block;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * The type Level 1 (direct hit).
 */
public class Level1 implements LevelInformation {
    /**
     * a single Block width.
     */
    static final int BLOCK_WIDTH = 40;
    /**
     * The Paddle's speed.
     */
    static final int PADDLE_SPEED = 10;
    /**
     * a single Block height.
     */
    static final int BLOCK_HEIGHT = 40;
    /**
     * The width of the Paddle.
     */
    static final int PADDLE_WIDTH = 160;
    /**
     * Number of balls in play.
     */
    static final int NUM_BALLS = 1;
    private List<Block> blockList = new ArrayList<>();
    private List<Velocity> velocityList = new ArrayList<>();


    @Override
    public int numberOfBalls() {
        return NUM_BALLS;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        Velocity v = new Velocity(8, -8);
        velocityList.add(v);
        return velocityList;
    }

    @Override
    public int paddleSpeed() {
        return PADDLE_SPEED;
    }

    @Override
    public int paddleWidth() {
        return PADDLE_WIDTH;
    }

    /**
     * Ball start points list.
     *
     * @return the list
     */
    public List<Point> getBallStartPs() {
        List<Point> ballStartPs = new ArrayList<>();
        Point p = new Point(400, 550);
        ballStartPs.add(p);
        return ballStartPs;
    }

    @Override
    public String levelName() {
        return "Direct Hit";
    }

    @Override
    public Sprite getBackground() {
        return new BackgroundSetter(Color.black, levelName());
    }

    @Override
    public boolean blocksLeft() {
        return !blockList.isEmpty();
    }

    @Override
    public List<Block> blocks() {
        Rectangle rec = new Rectangle(new Point(380, 200), BLOCK_WIDTH, BLOCK_HEIGHT);
        Block b = new Block(rec, Color.red);
        blockList.add(b);
        return blockList;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return blockList.size();
    }
}
