package arkanoid;

import animation.Menu;

/**
 * the Gruop class.
 *
 * @author Alihom
 *
 * @param <T>
 */
public class Group<T> {
    private String str;
    private Menu<T> menu;

    /**
     * get the string.
     *
     * @return Str the String.
     */
    public String getStr() {
        return str;
    }

    /**
     * get menu.
     *
     * @return Menu<T> the menu.
     */
    public Menu<T> getMenu() {
        return menu;
    }

    /**
     * Group Constructor.
     *
     * @param str
     *            String.
     * @param menu
     *            Menu<T>.
     */
    public Group(String str, Menu<T> menu) {
        this.str = str;
        this.menu = menu;
    }
}
