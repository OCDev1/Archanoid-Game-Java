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
import java.util.Random;

/**
 * The type Level 3.
 */
public class Level3 implements LevelInformation {
    /**
     * The constant NUMBER_OF_BALLS.
     */
//fields
    static final int NUMBER_OF_BALLS = 2;
    /**
     * The Block width.
     */
    static final int BLOCK_WIDTH = 50;
    /**
     * The Block height.
     */
    static final int BLOCK_HEIGHT = 25;
    /**
     * The Paddle width.
     */
    static final int PADDLE_WIDTH = 120;
    /**
     * The Window width.
     */
    static final int WINDOW_WIDTH = 800;
    /**
     * The Window height.
     */
    static final int WINDOW_HEIGHT = 600;
    /**
     * The Paddle speed.
     */
    static final int PADDLE_SPEED = 7;
    private List<Velocity> velocityList = new ArrayList<>();
    private List<Block> blockList = new ArrayList<>();

    /**
     * Number of balls int.
     *
     * @return the int
     */
    @Override
    public int numberOfBalls() {
        return NUMBER_OF_BALLS;
    }

    /**
     * The ball Start Points.
     *
     * @return List of points where the balls starts
     */
    public List<Point> getBallStartPs() {
        List<Point> ballStartPs = new ArrayList<>();
        for (int i = 0; i < NUMBER_OF_BALLS; i++) {
            Point p = new Point(WINDOW_WIDTH / 2 - 100 + i * 200, WINDOW_WIDTH / 2);
            ballStartPs.add(p);
        }
        return ballStartPs;
    }

    /**
     * Initial ball velocities list.
     *
     * @return the list
     */
    @Override
    public List<Velocity> initialBallVelocities() {
        for (int i = 0; i < NUMBER_OF_BALLS; i++) {
            Velocity v = new Velocity(-6, -6);
            velocityList.add(v);
        }
        return velocityList;
    }

    /**
     * Paddle speed int.
     *
     * @return the int
     */
    @Override
    public int paddleSpeed() {
        return PADDLE_SPEED;
    }

    /**
     * Paddle width int.
     *
     * @return the int
     */
    @Override
    public int paddleWidth() {
        return PADDLE_WIDTH;
    }

    /**
     * Level name string.
     *
     * @return the string
     */
    @Override
    public String levelName() {
        return "Green 3";
    }

    /**
     * Gets background.
     *
     * @return the background
     */
    @Override
    public Sprite getBackground() {
        Color c = new Color(0, 102, 0);
        BackgroundSetter background = new BackgroundSetter(c, levelName());
        return background;
    }

    /**
     * Blocks list.
     *
     * @return the list
     */
    @Override
    public List<Block> blocks() {
        // create a random-number generator
        Random rand = new Random();
        Color oldColor;
        Color newBlockColor = null;
        //setting the blocks in the game
        //setting the rows
        for (int i = 1; i <= 6; ++i) {
            Point upperLeft = new Point(780 - (i * 50), 250);
            Block block = new Block(new Rectangle(upperLeft, 50, 25), Color.white);
            blockList.add(block);
        }
        for (int i = 1; i <= 7; ++i) {
            Point upperLeft = new Point(780 - (i * 50), 225);
            Block block = new Block(new Rectangle(upperLeft, 50, 25), Color.blue);
            blockList.add(block);
        }
        for (int i = 1; i <= 8; ++i) {
            Point upperLeft = new Point(780 - (i * 50), 200);
            Block block = new Block(new Rectangle(upperLeft, 50, 25), Color.yellow);
            blockList.add(block);
        }
        for (int i = 1; i <= 9; ++i) {
            Point upperLeft = new Point(780 - (i * 50), 175);
            Block block = new Block(new Rectangle(upperLeft, 50, 25), Color.red);
            blockList.add(block);
        }
        for (int i = 1; i <= 10; ++i) {
            Point upperLeft = new Point(780 - (i * 50), 150);
            Block block = new Block(new Rectangle(upperLeft, 50, 25), Color.darkGray);
            blockList.add(block);
        }
        return blockList;
    }

    /**
     * Number of blocks to remove int.
     *
     * @return the int
     */
    @Override
    public int numberOfBlocksToRemove() {
        return blockList.size();
    }

    @Override
    public boolean blocksLeft() {
        return !blockList.isEmpty();
    }
}