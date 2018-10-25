package core;

/**
 * Selection class.
 *
 * @author Alihom
 *
 * @param <T>
 */
public class Selection<T> {
    private String key;
    private String name;
    private T option;

    /**
     * Selection constructor.
     *
     * @param key
     *            the key of the selection.
     * @param name
     *            the name of the selection.
     * @param option
     *            the task of the selection.
     */
    public Selection(String key, String name, T option) {
        this.key = key;
        this.name = name;
        this.option = (T) option;
    }

    /**
     * get key method.
     *
     * @return String of the key.
     */
    public String getKey() {
        return this.key;
    }

    /**
     * get name method.
     *
     * @return String of the selection name.
     */
    public String getName() {
        return this.name;
    }

    /**
     * return task method.
     *
     * @return the task.
     */
    public T getOption() {
        return this.option;
    }

}
