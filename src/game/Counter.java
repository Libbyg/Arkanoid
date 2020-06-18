package game;

/**
 * @author Libby Goldin id:204566236
 * @version 1
 * @since 3/6/2020
 */
public class Counter {
    /**
     * Fields.
     */
    private int count;

    /**
     * Constructor.
     * @param count number of counter
     */
    public Counter(int count) {
        this.count = count;
    }

    /**
     * add number to current count.
     * @param number to be increased
     */
    void increase(int number) {
        this.count += number;
    }

    /**
     * subtract number from current count.
     * @param number to be increased
     */
    void decrease(int number) {
        this.count -= number;
    }

    /**
     * get current count.
     * @return the numeric value
     */
    int getValue() {
        return this.count;
    }
}