package Interfaces;
import Geometry.Point;
import Geometry.Velocity;
import Geometry.Rectangle;
import Sprites.Ball;

//Omri Cohen 318673407

/**
 * The interface Collidable.
 */
public interface Collidable {
    /**
     * Return the "collision shape" of the object.
     *
     * @return the collision shape
     */
    Rectangle getCollisionRectangle();

    /**
     * Notify the object that we collided with it at collisionPoint with
     * a given velocity.
     *
     * @param collisionPoint  the collision point
     * @param currentVelocity the current velocity
     * @return the new velocity expected after the hit (based on the force the object inflicted on us).
     */
    Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity);
}

