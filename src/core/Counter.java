package core;

/**
 * Counter Class.
 *
 * @author Alihom
 *
 */
public class Counter {
    private int count;

    /**
     * Counter Constructor.
     */
    public Counter() {
        this.count = 0;
    }

    /**
     * Counter Constructor.
     *
     * @param number
     *            value for the counter.
     */
    public Counter(int number) {
        this.count = number;
    }

    /**
     * add number to current count.
     *
     * @param number
     *            the value to increase.
     */
    public void increase(int number) {
        this.count += number;
    }

    /**
     * subtract number from current count.
     *
     * @param number
     *            the value to decrease.
     */
    public void decrease(int number) {
        this.count -= number;
    }

    /**
     * get current count.
     *
     * @return int value of the counter.
     */
    public int getValue() {
        return this.count;
    }
}
