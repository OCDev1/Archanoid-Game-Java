package Interfaces;
import biuoop.DrawSurface;

//Omri Cohen 318673407

/**
 * The Sprite interface.
 */
public interface Sprite {
    /**
     * draw the sprite to the screen.
     *
     * @param d the draw surface
     */
    void drawOn(DrawSurface d);

    /**
     * notify the sprite that time has passed.
     */
    void timePassed();
}