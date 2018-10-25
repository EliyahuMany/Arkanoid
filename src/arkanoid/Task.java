package arkanoid;

/**
 * Task<T> class.
 *
 * @author Alihom
 *
 * @param <T>
 */
public interface Task<T> {
    /**
     * run method.
     *
     * @return T.
     */
    T run();
}