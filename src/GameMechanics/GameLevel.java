package GameMechanics;

//Omri Cohen 318673407

import Geometry.Velocity;
import Interfaces.Animation;
import Interfaces.Collidable;
import Interfaces.HitListener;
import Interfaces.LevelInformation;
import Interfaces.Sprite;
import Sprites.Block;
import Sprites.Ball;
import Sprites.Paddle;
import Geometry.Point;
import Geometry.Rectangle;
import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.KeyboardSensor;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * The GameLevel management class.
 */
public class GameLevel implements Animation {
    private SpriteCollection sprites;
    private GameEnvironment environment;
    private GUI gui;
    private KeyboardSensor keyboard;
    private Paddle paddle;
    private Counter remainingBlocks;
    private List<HitListener> hitListeners;
    private Counter ballsCounter;
    private int initialNumberOfBalls;
    private LevelInformation levelInformation;
    private Counter score;
    private AnimationRunner runner;
    private boolean running;

    /**
     * Instantiates a new GameLevel.
     *
     * @param levelInfo the level info
     * @param keyboard  the keyboard
     * @param runner    the runner
     * @param score     the score
     */
    public GameLevel(LevelInformation levelInfo, KeyboardSensor keyboard, AnimationRunner runner, Counter score) {
        this.sprites = new SpriteCollection();
        this.environment = new GameEnvironment();
        this.remainingBlocks = new Counter();
        this.hitListeners = new ArrayList<>();
        this.score = score;
        this.runner = runner;
        this.levelInformation = levelInfo;
        this.keyboard = keyboard;
        this.gui = runner.getGui();
    }

    /**
     * Adds a collidable to the game.
     *
     * @param c the collidable
     */
    public void addCollidable(Collidable c) {
        this.environment.addCollidable(c);
    }

    /**
     * Gets num of balls.
     *
     * @return the num of balls
     */
    public Counter getNumOfBalls() {
        return ballsCounter;
    }

    /**
     * Sets num of balls.
     *
     * @param numOfBalls the num of balls
     */
    public void setNumOfBalls(Counter numOfBalls) {
        this.ballsCounter = numOfBalls;
    }

    /**
     * Gets the initial number of balls.
     *
     * @return The initial number of balls.
     */
    public int getInitialNumberOfBalls() {
        return initialNumberOfBalls;
    }

    /**
     * Sets the initial number of balls.
     *
     * @param initialNumberOfBalls The new initial number of balls.
     */
    public void setInitialNumberOfBalls(int initialNumberOfBalls) {
        this.initialNumberOfBalls = initialNumberOfBalls;
    }

    /**
     * Gets the score counter.
     *
     * @return The score counter.
     */
    public Counter getScore() {
        return score;
    }

    /**
     * Adds a sprite to the game.
     *
     * @param s the sprite
     */
    public void addSprite(Sprite s) {
        this.sprites.addSprite(s);
    }

    /**
     * Remove collidable.
     *
     * @param c the collidable
     */
    public void removeCollidable(Collidable c) {
        this.environment.removeCollidable(c);
    }

    /**
     * Remove sprite.
     *
     * @param s the sprite
     */
    public void removeSprite(Sprite s) {
        this.sprites.removeSprite(s);
    }

    /**
     * Add hit listener.
     *
     * @param hl the hl
     */
    public void addHitListener(HitListener hl) {
        hitListeners.add(hl);
    }

    /**
     * Remove hit listener.
     *
     * @param hl the hl
     */
    public void removeHitListener(HitListener hl) {
        hitListeners.remove(hl);
    }

    /**
     * Add block row.
     *
     * @param numOfBlocks the num of blocks in the row
     * @param color       the color
     * @param upprLftX    the upper left x
     * @param upprLftY    the upper left y
     * @param width       the width
     * @param height      the height
     */
    public void addBlockRow(int numOfBlocks, Color color, int upprLftX, int upprLftY, int width, int height) {
        // create and add the blocks
        List<Block> blocks = new ArrayList<>();

        for (int j = 0; j < numOfBlocks; j++) {
            Point upperLeft = new Point(upprLftX + (j * 60), upprLftY);
            Block block = new Block(new Rectangle(upperLeft, width, height), color);
            blocks.add(block);
        }

        BlockRemover blockRemover = new BlockRemover(this, this.remainingBlocks);
        ScoreTrackingListener scoreListener = new ScoreTrackingListener(score);

        for (Block block : blocks) {
            //adding blocks to game
            block.addToGame(this);
            //increasing amount by 1
            remainingBlocks.increase(1);
            //adding block remover listener
            this.addHitListener(blockRemover);
            block.addHitListener(blockRemover);
            //adding score tracking listener
            block.addHitListener(scoreListener);
        }
    }

    /**
     * Add border blocks.
     *
     * @param borderWidth  the border width
     * @param screenHeight the screen height
     * @param screenWidth  the screen width
     */
    public void addBorderBlocks(int borderWidth, int screenHeight, int screenWidth) {
        Block rightBlock = new Block(new Rectangle(new Point(screenWidth - borderWidth, 0), borderWidth, screenHeight),
                Color.gray);
        Block topBlock = new Block(new Rectangle(new Point(0, 15), screenWidth, borderWidth), Color.gray);
        Block leftBlock = new Block(new Rectangle(new Point(0, 0), borderWidth, screenHeight), Color.gray);
        Block scoreBlock = new Block(new Rectangle(new Point(0, 0), screenWidth, borderWidth), Color.white);

        //adding border blocks to the environment and game
        rightBlock.addToGame(this);
        leftBlock.addToGame(this);
        topBlock.addToGame(this);
        scoreBlock.addToGame(this);
    }

    /**
     * Adds paddle, the screen height and width are needed to set the boundaries of the paddle.
     *
     * @param height       the height
     * @param width        the width
     * @param velocity     the velocity
     * @param color        the color
     * @param borderWidth  the border width
     * @param screenWidth  the screen width
     * @param screenHeight the screen height
     */
    public void addPaddle(int height, int width, int velocity, Color color, int borderWidth, int screenWidth,
                          int screenHeight) {
        this.paddle = new Paddle(new Rectangle(new Point(((double) 400 - (double) width / 2),
                screenHeight - height - borderWidth), width, height), color,
                keyboard, velocity);
        paddle.addToGame(this);
        paddle.setBoundaries(borderWidth + 1, screenWidth - borderWidth - paddle.getWidth());
    }

    /**
     * Add ball.
     *
     * @param radius the radius
     * @param color  the color
     * @param dx     the dx
     * @param dy     the dy
     * @param start  the start point of the ball
     */
    public void addBall(int radius, Color color, int dx, int dy, Point start) {
        Ball ball = new Ball(new Point(400, 500), radius, color);
        ball.setCenter(start);
        ball.setVelocity(dx, dy);
        ball.addToGame(this);
        ball.setGameEnvironment(this.environment);
    }

    /**
     * Initialize a new game: create the Blocks and Ball (and Paddle) and add them to the game.
     */
    public void initialize() {
        //creating and adding the background
        Sprite background = levelInformation.getBackground();
        addSprite(background);

        // create and add the block rows
        List<Block> blocks = levelInformation.blocks();
        for (Block block : blocks) {
            block.addToGame(this);
            remainingBlocks.increase(1);
            BlockRemover blockRemover = new BlockRemover(this, remainingBlocks);
            ScoreTrackingListener scoreListener = new ScoreTrackingListener(score);
            block.addHitListener(blockRemover);
            block.addHitListener(scoreListener);
        }

        //creating and adding border blocks
        addBorderBlocks(20, 600, 800);

        //creating and adding death region
        ballsCounter = new Counter();
        setInitialNumberOfBalls(levelInformation.numberOfBalls());
        ballsCounter.increase(initialNumberOfBalls);
        BallRemover ballRemover = new BallRemover(this, this.ballsCounter);
        Block deathRegion = new Block(new Rectangle(new Point(0, 600 - 1), 800, 20),
                Color.gray);
        this.addCollidable(deathRegion);
        deathRegion.addHitListener(ballRemover);

        //creating and adding the paddle
        addPaddle(15, levelInformation.paddleWidth(), levelInformation.paddleSpeed(), Color.orange, 20, 800, 600);

        List<Velocity> ballVelocities = levelInformation.initialBallVelocities();
        for (int i = 0; i < initialNumberOfBalls; i++) {
            Velocity velocity = ballVelocities.get(i);
            addBall(5, Color.white, (int) velocity.getVelocityDx(), (int) velocity.getVelocityDy(),
                    levelInformation.getBallStartPs().get(i));
        }
    }

    /**
     * Run the game -- start the animation loop.
     */
    public void run() {
        this.runner.run(new CountdownAnimation(2, 3, this.sprites));
        this.running = true;
        this.runner.run(this);
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        // Check for user input
        if (keyboard.isPressed(KeyboardSensor.LEFT_KEY)) {
            paddle.moveLeft();
        } else if (keyboard.isPressed(KeyboardSensor.RIGHT_KEY)) {
            paddle.moveRight();
        }

        // Draw all sprites on the screen
        sprites.drawAllOn(d);

        // Perform game logic
        sprites.notifyAllTimePassed();

        // Draw the paddle
        paddle.drawOn(d);

        // Draw the score
        d.setColor(Color.black);
        d.drawText(380, 15, "Score: " + score.getValue(), 15);

        // Draw the level name
        d.setColor(Color.black);
        d.drawText(500, 15, "Level Name: " + levelInformation.levelName(), 15);

        if (this.keyboard.isPressed("p")) {
            this.runner.run(new PauseScreen(this.keyboard));
        }

        // Check for game over conditions
        if (ballsCounter.getValue() == 0 || remainingBlocks.getValue() == 0) {
            running = false;
        }
    }

    @Override
    public boolean shouldStop() {
        return !this.running;
    }
}