package animation;

import java.util.ArrayList;
import java.util.Map;

import arkanoid.Group;
import core.Selection;

/**
 * Menu<T> interface.
 *
 * @author Alihom
 *
 * @param <T>
 */
public interface Menu<T> extends Animation {
    /**
     * addSelection, add the selection to the menu.
     *
     * @param choose
     *            what to press.
     * @param name
     *            the name of the selection.
     * @param option
     *            the operate.
     */
    void addSelection(String choose, String name, T option);

    /**
     * return the status of the pressed key.
     *
     * @return the press key operate.
     */
    T getStatus();

    /**
     * return the array list of the selections.
     *
     * @return the array list.
     */
    ArrayList<Selection> getSelections();

    /**
     * add sub menu to the menu.
     *
     * @param key
     *            the key of the sub menu.
     * @param message
     *            the name of the sub menu.
     * @param subMenu
     *            the menu of the sub menu.
     */
    void addSubMenu(String key, String message, Menu<T> subMenu);

    /**
     * return the map of the sub menus.
     *
     * @return map of the sub menus.
     */
    Map<String, Group<T>> getSubMenu();

    /**
     * return the last selection.
     *
     * @return String of the last selection pressed.
     */
    String getLastSelect();

    /**
     * set stop to the sub menues.
     *
     * @param stop
     *            change the stop;
     */
    void setStop(boolean stop);

}
