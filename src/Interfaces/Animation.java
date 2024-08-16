package Interfaces;

//Omri Cohen 318673407

import biuoop.DrawSurface;

/**
 * The interface Animation.
 */
public interface Animation {
    /**
     * Do one frame.
     *
     * @param d the draw surface
     */
    void doOneFrame(DrawSurface d);

    /**
     * Should stop boolean.
     *
     * @return the boolean yes or no
     */
    boolean shouldStop();
}
