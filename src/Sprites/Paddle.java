package Sprites;
import GameMechanics.GameLevel;
import Interfaces.Sprite;
import Interfaces.Collidable;
import Geometry.Point;
import Geometry.Rectangle;
import Geometry.Velocity;
import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import java.awt.Color;

//Omri Cohen 318673407

/**
 * The type Paddle.
 */
public class Paddle implements Sprite, Collidable {
    private biuoop.KeyboardSensor keyboard;
    private int paddleWidth;
    private int paddleHeight;
    private Rectangle rect;
    private Color color;
    private int velocity;

    //constants
    private int leftBoundary;
    private int rightBoundary;
    private static final double EPSILON = 0.00001;


    /**
     * Instantiates a new paddle.
     *
     * @param rect     the rectangle
     * @param color    the color
     * @param keyboard the keyboard
     * @param velocity the velocity
     */
    public Paddle(Rectangle rect, Color color, biuoop.KeyboardSensor keyboard, int velocity) {
        this.rect = rect;
        this.color = color;
        this.paddleWidth = (int) rect.getWidth();
        this.keyboard = keyboard;
        this.paddleHeight = (int) rect.getHeight();
        this.velocity = velocity;
    }

    /**
     * sets L/R boundaries.
     *
     * @param leftBoundary  the left boundary
     * @param rightBoundary the right boundary
     */
    public void setBoundaries(int leftBoundary, int rightBoundary) {
        this.leftBoundary = leftBoundary;
        this.rightBoundary = rightBoundary;
    }

    /**
     * Move left.
     */
    public void moveLeft() {
        double dx = this.rect.getUpperLeft().getX() - this.velocity;
        if (dx >= leftBoundary) {
            this.rect = new Rectangle(new Point(dx, this.rect.getUpperLeft().getY()), paddleWidth, paddleHeight);
        } else {
            this.rect =
                    new Rectangle(new Point(leftBoundary, this.rect.getUpperLeft().getY()), paddleWidth, paddleHeight);
        }
    }

    /**
     * Move right.
     */
    public void moveRight() {
        double dx = this.rect.getUpperLeft().getX() + this.velocity;
        if (dx <= rightBoundary) {
            this.rect = new Rectangle(new Point(dx, this.rect.getUpperLeft().getY()), paddleWidth, paddleHeight);
        } else {
            this.rect = new Rectangle(new Point(rightBoundary, this.rect.getUpperLeft().getY()), paddleWidth,
                    paddleHeight);
        }
    }

    /**
     * get paddle width.
     *
     * @return the width
     */
    public int getWidth() {
        return paddleWidth;
    }

    // Sprite

    /**
     * time passed.
     */
    public void timePassed() {
        // Move the paddle left if the left key is pressed
        if (keyboard.isPressed(KeyboardSensor.LEFT_KEY)
                && getCollisionRectangle().getUpperLeft().getX() > leftBoundary) {
            moveLeft();
        }

        // Move the paddle right if the right key is pressed
        if (keyboard.isPressed(KeyboardSensor.RIGHT_KEY)
                && getCollisionRectangle().getUpperLeft().getX() + getWidth() < rightBoundary) {
            moveRight();
        }
    }

    /**
     * draws to draw surface.
     *
     * @param d the draw surface
     */
    public void drawOn(DrawSurface d) {
        d.setColor(color);
        d.fillRectangle((int) rect.getUpperLeft().getX(), (int) rect.getUpperLeft().getY(),
                (int) rect.getWidth(), (int) rect.getHeight());
    }

    /**
     * gets the rectangle involved in the collision.
     *
     * @return the rectangle involved in the collision
     */
    public Rectangle getCollisionRectangle() {
        return rect;
    }

    /**
     * Notify the object that we collided with it at collisionPoint with
     * a given velocity.
     *
     * @param collisionPoint  the collision point
     * @param currentVelocity the current velocity
     * @return the new velocity expected after the hit (based on the force the object inflicted on us).
     */
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        double x = collisionPoint.getX();
        //dividing paddle to regions
        double regionSize = this.rect.getWidth() / 5;
        double positionOnPaddle = collisionPoint.getX() - this.rect.getUpperLeft().getX();

        //finding the speed by using the formula for speed via velocity
        double speed = Math.sqrt(Math.pow(currentVelocity.getVelocityDx(), 2)
                + (Math.pow(currentVelocity.getVelocityDy(), 2)));

        //collisions with top of paddle
        if (collisionPoint.getY() == this.rect.getUpperLeft().getY()) {
            if (positionOnPaddle < regionSize) { //leftmost hit
                return Velocity.fromAngleAndSpeed(300, speed);
            } else if (positionOnPaddle < 2 * regionSize) { //left-mid hit
                return Velocity.fromAngleAndSpeed(330, speed);
            } else if (positionOnPaddle < 3 * regionSize) { //middle hit
                return new Velocity(currentVelocity.getVelocityDx(), -currentVelocity.getVelocityDy());
            } else if (positionOnPaddle < 4 * regionSize) { //mid-right hit
                return Velocity.fromAngleAndSpeed(30, speed);
            } else { //rightmost hit
                return Velocity.fromAngleAndSpeed(60, speed);
            }
        }
        //collisions with left side of paddle
        if (Math.abs(x - this.rect.getUpperLeft().getX()) <= EPSILON) { // left side
            return Velocity.fromAngleAndSpeed(330, speed);
        }
        //collisions with right side of paddle
        if (Math.abs(x - this.rect.getUpperLeft().getX() - this.rect.getWidth()) <= EPSILON) { // right side
            return Velocity.fromAngleAndSpeed(30, speed);
        }
        return currentVelocity;
    }

    /**
     * Add this paddle to the game.
     *
     * @param g the game
     */
    public void addToGame(GameLevel g) {
        g.addCollidable(this);
        g.addSprite(this);
    }
}