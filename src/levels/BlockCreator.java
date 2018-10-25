package levels;

import arkanoid.Block;

/**
 * block creator interface.
 *
 * @author Alihom
 *
 */
public interface BlockCreator {
    /**
     * Create a block at the specified location.
     *
     * @param xpos
     *            the x.
     * @param ypos
     *            the y.
     * @return block.
     */
    Block create(int xpos, int ypos);
}
