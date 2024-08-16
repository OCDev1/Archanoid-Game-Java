package Sprites;
import GameMechanics.GameLevel;
import Interfaces.Sprite;
import Interfaces.Collidable;
import GameMechanics.GameEnvironment;
import GameMechanics.CollisionInfo;
import Geometry.Point;
import Geometry.Line;
import Geometry.Velocity;
import biuoop.DrawSurface;

//Omri Cohen 318673407

/**
 * class for a ball.
 * the ball has a radius, a center point,color, velocity etc.
 * the class can return all fields and also perform actions on the ball object
 */
public class Ball implements Sprite {
    private static final int DEFAULT_RADIUS = 5;
    private static final int MIN_RADIUS = 0;
    private static final int MAX_RADIUS = 99;
    private static final double EPSILON = 0.00001;
    private Point center;
    private double radius;
    private final java.awt.Color color;
    private Velocity velocity;
    private int topBorder;
    private int bottomBorder;
    private int rightBorder;
    private int leftBorder;
    private GameEnvironment environment;

    /**
     * Instantiates a new Ball.
     *
     * @param center the center
     * @param r      the r
     * @param color  the color
     */
    public Ball(Point center, int r, java.awt.Color color) {
        //checking input and giving default value
        if (r < MIN_RADIUS || r > MAX_RADIUS) {
            r = DEFAULT_RADIUS;
        }
        if ((center.getX() < this.radius + leftBorder
                || center.getX() + this.radius > rightBorder)
                || (center.getY() < this.radius + bottomBorder || center.getY()
                + this.radius > topBorder)) {
            center = new Point(radius + leftBorder, radius + bottomBorder);
        }
        this.center = center;
        this.radius = r;
        this.color = color;
    }

    /**
     * Sets borders.
     *
     * @param left   the left border
     * @param right  the right border
     * @param top    the top border
     * @param bottom the bottom border
     */
    public void setBorders(int left, int right, int top, int bottom) {
        this.topBorder = top;
        this.bottomBorder = bottom;
        this.leftBorder = left;
        this.rightBorder = right;
    }

    /**
     * Instantiates a new Ball.
     *
     * @param x     the x
     * @param y     the y
     * @param r     the r
     * @param color the color
     */
    public Ball(double x, double y, int r, java.awt.Color color) {
        if (r < MIN_RADIUS || r > MAX_RADIUS) {
            r = DEFAULT_RADIUS;
        }

        if (x < this.radius + leftBorder || x > rightBorder - this.radius) {
            x = radius + leftBorder;
        }
        if (y < this.radius + bottomBorder || y > topBorder - this.radius) {
            y = radius + bottomBorder;
        }
        this.center = new Point(x, y);
        this.radius = r;
        this.color = color;
    }

    /**
     * Gets x.
     *
     * @return the x
     */
    public int getX() {
        return (int) center.getX();
    }

    /**
     * Gets y.
     *
     * @return the y
     */
    public int getY() {
        return (int) center.getY();
    }

    /**
     * Gets size of radius.
     *
     * @return the radius
     */
    public int getSize() {
        return (int) radius;
    }

    /**
     * draw the ball on the given DrawSurface.
     *
     * @param surface the surface
     */
    public void drawOn(DrawSurface surface) {
        // set the color of the ball
        surface.setColor(this.color);

        // draw the ball via center and radius
        surface.fillCircle((int) this.center.getX(),
                (int) this.center.getY(), (int) this.radius);
    }

    /**
     * Sets game environment.
     *
     * @param environment the environment
     */
    public void setGameEnvironment(GameEnvironment environment) {
        this.environment = environment;
    }

    /**
     * Set velocity.
     *
     * @param v the v
     */
    public void setVelocity(Velocity v) {
        this.velocity = v;
    }

    /**
     * Adds ball to game.
     *
     * @param g the game
     */
    public void addToGame(GameLevel g) {
        g.addSprite(this);
    }

    /**
     * Sets center.
     *
     * @param center the center
     */
    public void setCenter(Point center) {
        this.center = center;
    }

    /**
     * instantiates velocity object.
     *
     * @param dx the dx
     * @param dy the dy
     */
    public void setVelocity(double dx, double dy) {
        this.velocity = new Velocity(dx, dy);
    }

    /**
     * return the velocity.
     *
     * @return the velocity
     */
    public Velocity getVelocity() {
        return this.velocity;
    }

    /**
     * Moves the ball according to velocity, if it gets stuck in a wall
     * changes direction, also takes into calculations the collidable objects in the environment.
     */
    public void moveOneStep() {
        // Compute the ball trajectory
        Line trajectory = new Line(this.center, this.getVelocity().applyToPoint(this.center));

        // Check if the trajectory collides with any collidables in the game environment
        CollisionInfo closestCollision = this.environment.getClosestCollision(trajectory);

        // If there is no collision, move the ball to the end of the trajectory
        if (closestCollision == null) {
            this.center = trajectory.end();
        } else {
            // Otherwise, move the ball to just before the collision point
            Point collisionPoint = closestCollision.collisionPoint();
            double dx = this.getVelocity().getVelocityDx();
            double dy = this.getVelocity().getVelocityDy();

            // Change x direction if ball is very close to collision on x-axis
            if (Math.abs(collisionPoint.getX() - this.center.getX()) <= EPSILON) {
                dx = -dx;
            }
            // Change y direction if ball is very close to collision on y-axis
            if (Math.abs(collisionPoint.getY() - this.center.getY()) <= EPSILON) {
                dy = -dy;
            }
            // Move the ball to just before the collision point
            this.center = new Point(collisionPoint.getX() - 1.0001 * dx, collisionPoint.getY() - 1.0001 * dy);

            // Notify the collidable object that a collision occurred and changing direction
            Collidable collisionObject = closestCollision.collisionObject();
            Velocity newVelocity = collisionObject.hit(this, collisionPoint, this.velocity);
            this.setVelocity(newVelocity);

            // Move the ball one step using the new velocity
            this.center = this.getVelocity().applyToPoint(this.center);
        }
    }


    /**
     * Returns a Line object representing the trajectory of the ball's movement.
     * The trajectory is defined by the current position of the ball (center point)
     * and its velocity.
     *
     * @return the trajectory line of the ball
     */
    public Line getTrajectory() {
        Point start = new Point(center.getX(), center.getY());
        Point end = velocity.applyToPoint(center);
        return new Line(start, end);
    }

    /**
     * time passed calls the moveOneStep method.
     */
    public void timePassed() {
        moveOneStep();
    }

    /**
     * Removes the ball from the gameLevel.
     * @param gameLevel The gameLevel from which to remove the ball.
     */
    public void removeFromGame(GameLevel gameLevel) {
        gameLevel.removeSprite(this);
    }
}