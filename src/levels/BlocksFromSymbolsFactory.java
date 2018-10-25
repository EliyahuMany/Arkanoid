package levels;

import java.util.Map;

import arkanoid.Block;

/**
 * Blocks from symbols factory class.
 *
 * @author Alihom
 *
 */
public class BlocksFromSymbolsFactory {
    private Map<String, Integer> spacerWidths;
    private Map<String, BlockCreator> blockCreators;

    /**
     * blocks from symbols factory constructor.
     *
     * @param spacerWidths
     *            map of spaces.
     * @param blockCreators
     *            map of block creators.
     */
    public BlocksFromSymbolsFactory(Map<String, Integer> spacerWidths, Map<String, BlockCreator> blockCreators) {
        this.spacerWidths = spacerWidths;
        this.blockCreators = blockCreators;
    }

    /**
     * returns true if 's' is a valid space symbol.
     *
     * @param s
     *            String we want to check.
     * @return true if space and false otherwise.
     */
    public boolean isSpaceSymbol(String s) {
        return this.spacerWidths.containsKey(s);
    }

    /**
     * returns true if 's' is a valid block symbol.
     *
     * @param s
     *            String we want to check.
     * @return true if block and false otherwise.
     */
    public boolean isBlockSymbol(String s) {
        return this.blockCreators.containsKey(s);
    }

    /**
     * Return a block according to the definitions associated with symbol s. The
     * block will be located at position (xpos, ypos).
     *
     * @param s
     *            the string of the block.
     * @param xpos
     *            int value of x.
     * @param ypos
     *            int value of y.
     * @return Block by the string.
     */
    public Block getBlock(String s, int xpos, int ypos) {
        return this.blockCreators.get(s).create(xpos, ypos);
    }

    /**
     * Returns the width in pixels associated with the given spacer-symbol.
     *
     * @param s
     *            String to check.
     * @return the space width value.
     */
    public int getSpaceWidth(String s) {
        return this.spacerWidths.get(s);
    }
}