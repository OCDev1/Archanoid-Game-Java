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
 * The type Level 2.
 */
public class Level2 implements LevelInformation {
    /**
     * The constant NUMBER_OF_BALLS.
     */
//fields
    static final int NUMBER_OF_BALLS = 10;
    /**
     * The Block width.
     */
    static final int BLOCK_WIDTH = 51;
    /**
     * The Block height.
     */
    static final int BLOCK_HEIGHT = 30;
    /**
     * The Paddle width.
     */
    static final int PADDLE_WIDTH = 650;
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
    static final int PADDLE_SPEED = 10;
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
     * Ball start points list.
     *
     * @return the list
     */
    public List<Point> getBallStartPs() {
        List<Point> ballStartPs = new ArrayList<>();
        for (int i = 0; i < NUMBER_OF_BALLS; i++) {
            Point p = new Point(400, 550);
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
        int angle = 310;
        for (int i = 0; i < 10; i++) {
            if (i == 5) {
                angle = 10;
            }
            Velocity velocity = Velocity.fromAngleAndSpeed(angle, 8);
            angle += 10;
            velocityList.add(velocity);
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
        return "Wide Easy";
    }

    /**
     * Gets background.
     *
     * @return the background
     */
    @Override
    public Sprite getBackground() {
        BackgroundSetter background = new BackgroundSetter(Color.blue, levelName());
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
        Color prevColor;
        Color nextColor = null;
        for (int i = 0; i < 6;) {
            do {
                //setting random colors for the blocks
                float r = rand.nextFloat();
                float g = rand.nextFloat();
                float b = rand.nextFloat();
                prevColor = nextColor;
                nextColor = new Color(r, g, b);
            } while (prevColor == nextColor);
            //setting up a pair of blocks (of the same color)
            for (int j = 0; j < 2; j++, i++) {
                Rectangle r = new Rectangle(new Point(21 + i * BLOCK_WIDTH, 270), BLOCK_WIDTH,
                        BLOCK_HEIGHT);
                Block b = new Block(r, nextColor);
                blockList.add(b);
            }
        }
        for (int i = 0; i < 6;) {
            do {
                //setting a random color
                float r = rand.nextFloat();
                float g = rand.nextFloat();
                float b = rand.nextFloat();
                prevColor = nextColor;
                nextColor = new Color(r, g, b);
            } while (prevColor == nextColor);
            //setting up a pair of blocks (of the same color)
            for (int j = 0; j < 2; j++, i++) {
                Rectangle r = new Rectangle(new Point(479 + i * BLOCK_WIDTH, 270), BLOCK_WIDTH,
                        BLOCK_HEIGHT);
                Block b = new Block(r, nextColor);
                blockList.add(b);
            }
        }
        for (int i = 0; i < 3; i++) {
            Rectangle r = new Rectangle(new Point(326 + i * BLOCK_WIDTH, 270), BLOCK_WIDTH,
                    BLOCK_HEIGHT);
            Block b = new Block(r, nextColor);
            blockList.add(b);
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
