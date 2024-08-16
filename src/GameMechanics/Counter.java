package GameMechanics;

//Omri Cohen 318673407

/**
 * The type Counter.
 */
public class Counter {
    private int count;

    /**
     * Instantiates a new Counter.
     */
    public Counter() {
        this.count = 0;
    }

    /**
     * Increase.
     *
     * @param number the number
     */
    public void increase(int number) {
        this.count += number;
    }

    /**
     * Decrease.
     *
     * @param number the number
     */
    public void decrease(int number) {
        this.count -= number;
    }

    /**
     * Gets value.
     *
     * @return the value
     */
    public int getValue() {
        return this.count;
    }

    /**
     * Sets value.
     *
     * @param value the value
     */
    public void setValue(int value) {
        this.count = value;
    }
}