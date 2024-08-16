package GameMechanics;

//Omri Cohen 318673407

import Interfaces.Animation;
import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

/**
 * The type Key press stoppable animation.
 */
public class KeyPressStoppableAnimation implements Animation {
    //fields
    private KeyboardSensor keyboard;
    private String keyPressed;
    private Animation anim;
    private boolean stop;
    private boolean isAlreadyPressed;

    /**
     * Instantiates a new Key press stoppable animation.
     *
     * @param sensor    the KeyboardSensor
     * @param key       the key that need to be press
     * @param animation the animation
     */
    public KeyPressStoppableAnimation(KeyboardSensor sensor, String key, Animation animation) {
        this.keyboard = sensor;
        this.keyPressed = key;
        this.anim = animation;
        this.stop = false;
        this.isAlreadyPressed = true;
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        anim.doOneFrame(d);
        if (this.keyboard.isPressed(keyPressed)) {
            this.stop = true;
        } else {
            this.isAlreadyPressed = false;
        }
    }

    @Override
    public boolean shouldStop() {
        return this.stop;
    }
}
