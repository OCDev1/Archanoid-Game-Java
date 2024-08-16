package Sprites;

import GameMechanics.GameLevel;
import Interfaces.HitListener;
import Interfaces.HitNotifier;
import Interfaces.Sprite;
import Interfaces.Collidable;
import Geometry.Point;
import Geometry.Rectangle;
import Geometry.Velocity;
import biuoop.DrawSurface;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

//Omri Cohen 318673407

/**
 * The Block class to create Block objects.
 */
public class Block implements Collidable, Sprite, HitNotifier {
    private Rectangle rect;
    private Color color;
    private static final double EPSILON = 0.00001;
    /**
     * The Hit listeners.
     */
    private List<HitListener> hitListeners = new ArrayList<>();


    /**
     * Instantiates a new Block.
     *
     * @param rect  the rectangle
     * @param color the color
     */
    public Block(Rectangle rect, Color color) {
        this.rect = rect;
        this.color = color;
    }

    /**
     * Return the "collision shape" of the object.
     *
     * @return the collision shape
     */
    public Rectangle getCollisionRectangle() {
        return rect;
    }

    /**
     * Gets hit listeners.
     *
     * @return the hit listeners
     */
    public List<HitListener> getHitListeners() {
        return this.hitListeners;
    }

    /**
     * Notify the object that we collided with it at collisionPoint with
     * a given velocity.
     *
     * @param collisionPoint  the collision point
     * @param hitter          the hitting object
     * @param currentVelocity the current velocity
     * @return the new velocity expected after the hit (based on the force the object inflicted on us).
     */
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        double x = collisionPoint.getX();
        double y = collisionPoint.getY();
        double dx = currentVelocity.getVelocityDx();
        double dy = currentVelocity.getVelocityDy();

        // Check for collisions with block
        if (Math.abs(y - this.rect.getUpperLeft().getY() - this.rect.getHeight()) <= EPSILON) { // bottom side
            dy = -dy;
        } else if (Math.abs(y - this.rect.getUpperLeft().getY()) <= EPSILON) { // top side
            dy = -dy;
        } else if (Math.abs(x - this.rect.getUpperLeft().getX() - this.rect.getWidth()) <= EPSILON) { // right side
            dx = -dx;
        } else if (Math.abs(x - this.rect.getUpperLeft().getX()) <= EPSILON) { // left side
            dx = -dx;
        }

        //returning new velocity based on which side was hit
        this.notifyHit(hitter);
        return new Velocity(dx, dy);
    }

    /**
     * Draw on.
     *
     * @param surface the surface
     */
    public void drawOn(DrawSurface surface) {
        surface.setColor(color);
        surface.fillRectangle((int) rect.getUpperLeft().getX(), (int) rect.getUpperLeft().getY(),
                (int) rect.getWidth(), (int) rect.getHeight());
        surface.setColor(Color.black);
        surface.drawRectangle((int) rect.getUpperLeft().getX(), (int) rect.getUpperLeft().getY(),
                (int) rect.getWidth(), (int) rect.getHeight());
    }

    /**
     * time passed.
     */
    public void timePassed() {
    }

    /**
     * Adds the block to the game.
     *
     * @param g the game
     */
    public void addToGame(GameLevel g) {
        g.addCollidable(this);
        g.addSprite(this);
    }

    /**
     * Removes the block from the gameLevel.
     *
     * @param gameLevel the gameLevel
     */
    public void removeFromGame(GameLevel gameLevel) {
        gameLevel.removeCollidable(this);
        gameLevel.removeSprite(this);
    }

    /**
     * Notify hit - for all listeners.
     *
     * @param hitter the hitter
     */
    public void notifyHit(Ball hitter) {
        // Make a copy of the hitListeners before going over them.
        List<HitListener> listeners = new ArrayList<>(this.hitListeners);
        // Notify listeners about a hit event:
        for (HitListener hl : listeners) {
            hl.hitEvent(this, hitter);
        }
    }

    @Override
    public void addHitListener(HitListener hl) {
        hitListeners.add(hl);
    }

    @Override
    public void removeHitListener(HitListener hl) {
        hitListeners.remove(hl);

    }
}
