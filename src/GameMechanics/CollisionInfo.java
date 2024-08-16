package GameMechanics;
import Interfaces.Collidable;
import Geometry.Point;

//Omri Cohen 318673407

/**
 * The type Collision info.
 */
public class CollisionInfo {
    private Point collisionPoint;
    private Collidable collisionObject;

    /**
     * Instantiates a new Collision info.
     *
     * @param collisionPoint  the collision point
     * @param collisionObject the collision object
     */
    public CollisionInfo(Point collisionPoint, Collidable collisionObject) {
        this.collisionPoint = collisionPoint;
        this.collisionObject = collisionObject;
    }

    /**
     * collisionPoint.
     *
     * @return the point at which the collision occurs.
     */
    public Point collisionPoint() {
        return collisionPoint;
    }

    /**
     * collisionObject.
     *
     * @return the collidable object involved in the collision.
     */
    public Collidable collisionObject() {
        return collisionObject;
    }
}