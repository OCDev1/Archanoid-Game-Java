package GameMechanics;
import Interfaces.Collidable;
import Geometry.Point;
import Geometry.Line;
import Geometry.Rectangle;

import java.util.List;

//Omri Cohen 318673407

/**
 * The type GameLevel environment.
 */
public class GameEnvironment {
    private final java.util.ArrayList<Collidable> collidables;


    /**
     * Add the given collidable to the environment.
     *
     * @param c the collidable
     */
    public void addCollidable(Collidable c) {
        this.collidables.add(c);
    }

    /**
     * Remove collidable.
     *
     * @param c the c
     */
    public void removeCollidable(Collidable c) {
        this.collidables.remove(c);
    }

    /**
     * Instantiates a new GameLevel environment.
     */
    public GameEnvironment() {
        this.collidables = new java.util.ArrayList<>();
    }

    /**
     * Assume an object moving from line.start() to line.end().
     * If this object will not collide with any of the collidables
     * in this collection, return null. Else, return the information
     * about the closest collision that is going to occur.
     *
     * @param trajectory the trajectory
     * @return the Closest Collision
     */
    public CollisionInfo getClosestCollision(Line trajectory) {
        // Initialize closestCollision to null
        CollisionInfo closestCollision = null;

        // Iterate over all collidables and find the closest collision
        for (Collidable c : collidables) {
            Rectangle rect = c.getCollisionRectangle();
            Point intersection = trajectory.closestIntersectionToStart(rect);

            // If there is an intersection, and it is closer than the current closest collision
            if (intersection != null && (closestCollision == null || intersection.distance(trajectory.start())
                    < closestCollision.collisionPoint().distance(trajectory.start()))) {
                // Set closestCollision to the new collision
                closestCollision = new CollisionInfo(intersection, c);
            }
        }

        return closestCollision;
    }

    /**
     * Gets collidables.
     *
     * @return the collidables list
     */
    public List<Collidable> getCollidables() {
        return this.collidables;
    }
}
