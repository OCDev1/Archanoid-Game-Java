package GameMechanics;
import Interfaces.Sprite;
import biuoop.DrawSurface;
import java.util.ArrayList;
import java.util.List;

//Omri Cohen 318673407

/**
 * The Sprite collection management class.
 */
public class SpriteCollection {
    private List<Sprite> sprites;

    /**
     * Instantiates a new interfaces.Sprite collection.
     * constructor
     */
    public SpriteCollection() {
        List<Sprite> s = new ArrayList<>();
        this.sprites = s;
    }

    /**
     * Instantiates a new interfaces.Sprite collection.
     * constructor
     *
     * @param s the List of sprites
     */
    public SpriteCollection(List<Sprite> s) {
        this.sprites = s;
    }

    /**
     * Instantiates a new Sprite collection.
     * then adds the sprite
     *
     * @param s      the List of sprites
     * @param sprite the Sprite
     */
    public SpriteCollection(List<Sprite> s, Sprite sprite) {
        this.sprites = s;
        sprites.add(sprite);
    }

    /**
     * Add sprites to the list.
     *
     * @param s the sprite
     */
    public void addSprite(Sprite s) {
        this.sprites.add(s);
    }

    /**
     * Remove sprite.
     *
     * @param s the s
     */
    public void removeSprite(Sprite s) {
        this.sprites.remove(s);
    }

    /**
     * call timePassed() on all sprites.
     */
    public void notifyAllTimePassed() {
        List<Sprite> spritesCopy = new ArrayList<>(this.sprites);
        for (Sprite s : spritesCopy) {
            s.timePassed();
        }
    }

    /**
     * call drawOn(d) on all sprites.
     *
     * @param d the draw surface
     */
    public void drawAllOn(DrawSurface d) {
        for (Sprite s : this.sprites) {
            s.drawOn(d);
        }
    }

    /**
     * Gets sprites.
     *
     * @return all sprites
     */
    public List<Sprite> getSprites() {
        return this.sprites;
    }
}