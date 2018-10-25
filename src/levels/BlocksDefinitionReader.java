package levels;

import java.awt.Color;
import java.io.BufferedReader;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Map;
import java.util.HashMap;

/**
 * block definition reader class.
 *
 * @author Alihom
 *
 */
public class BlocksDefinitionReader {
    /**
     * create block definition reader from reader.
     *
     * @param reader
     *            the reader file.
     * @return block definition reader
     */
    public static BlocksFromSymbolsFactory fromReader(java.io.Reader reader) {
        BufferedReader r = new BufferedReader(reader);
        String l = "";
        String text = null;
        String symbol = null;
        String[] lines;
        String[] definitions;
        String[] keyVal = null;
        Map<String, String> defaultMap = new HashMap<String, String>();
        Map<String, Integer> space = new HashMap<String, Integer>();
        Map<String, BlockCreator> blocksCreators = new HashMap<String, BlockCreator>();
        Map<String, String> blockDefs = new HashMap<String, String>();
        Map<String, String> backgrounds = new HashMap<String, String>();
        Map<String, String> defBackgrounds = new HashMap<String, String>();
        int width;
        int height;
        int hitPoints;
        java.awt.Color stroke = null;

        while (true) {
            try {
                l = r.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            symbol = null;
            if (l != null) {
                if (l.contains(":")) {
                    if (l.contains("default")) {
                        definitions = l.split(" ");
                        for (String def : definitions) {
                            if (def.contains("fill")) {
                                keyVal = def.split(":");
                                if (keyVal[0].contains("-")) {
                                    String[] a = keyVal[0].split("-");
                                    defBackgrounds.put(a[1], keyVal[1]);
                                    keyVal = null;
                                } else {
                                    defBackgrounds.put(keyVal[0], keyVal[1]);
                                    keyVal = null;
                                }
                            } else if (def.contains("stroke")) {
                                keyVal = def.split(":");
                                defaultMap.put(keyVal[0], keyVal[1]);
                                keyVal = null;
                            } else if (def.contains(":")) {
                                keyVal = def.split(":");
                                defaultMap.put(keyVal[0], keyVal[1]);
                                keyVal = null;
                            }
                        }
                        definitions = null;
                    } else if (l.contains("sdef")) {
                        definitions = l.split(" ");
                        for (String def : definitions) {
                            if (def.contains("symbol")) {
                                keyVal = def.split(":");
                                symbol = keyVal[1];
                                keyVal = null;
                            } else if (def.contains("width")) {
                                keyVal = def.split(":");
                                space.put(symbol, Integer.parseInt(keyVal[1]));
                                keyVal = null;
                                symbol = null;
                            }
                        }
                        definitions = null;
                    } else if (l.contains("bdef")) {
                        blockDefs = new HashMap<String, String>();
                        definitions = l.split(" ");
                        for (String def : definitions) {
                            if (def.contains(":")) {
                                if (def.contains("fill")) {
                                    keyVal = def.split(":");
                                    backgrounds.put(keyVal[0], keyVal[1]);
                                    keyVal = null;

                                } else if (def.contains("stroke")) {
                                    keyVal = def.split(":");
                                    blockDefs.put(keyVal[0], keyVal[1]);
                                    keyVal = null;
                                } else if (def.contains("symbol")) {
                                    keyVal = def.split(":");
                                    symbol = keyVal[1];
                                    keyVal = null;
                                } else {
                                    keyVal = def.split(":");
                                    blockDefs.put(keyVal[0], keyVal[1]);
                                    keyVal = null;
                                }
                            }
                        }
                    }
                    if (symbol != null) {
                        if (blockDefs.containsKey("width")) {
                            width = Integer.parseInt(blockDefs.get("width"));
                        } else {
                            width = Integer.parseInt(defaultMap.get("width"));
                        }
                        if (blockDefs.containsKey("height")) {
                            height = Integer.parseInt(blockDefs.get("height"));
                        } else {
                            height = Integer.parseInt(defaultMap.get("height"));
                        }
                        if (blockDefs.containsKey("hit_points")) {
                            hitPoints = Integer.parseInt(blockDefs.get("hit_points"));
                        } else {
                            hitPoints = Integer.parseInt(defaultMap.get("hit_points"));
                        }
                        if (blockDefs.containsKey("stroke")) {
                            stroke = stringToColor(blockDefs.get("stroke"));
                        } else if (defaultMap.containsKey("stroke")) {
                            stroke = stringToColor(defaultMap.get("stroke"));
                        }
                        if (backgrounds == null) {
                            blocksCreators.put(symbol,
                                    new BlockByDefinitions(width, height, hitPoints, defBackgrounds, stroke));
                        } else {
                            blocksCreators.put(symbol,
                                    new BlockByDefinitions(width, height, hitPoints, backgrounds, stroke));
                        }
                    }
                    symbol = null;
                    backgrounds = new HashMap<String, String>();
                }
            } else {
                break;
            }
        }

        return new BlocksFromSymbolsFactory(space, blocksCreators);

    }

    /**
     * String to color.
     *
     * @param str
     *            the color.
     * @return color.
     */
    public static java.awt.Color stringToColor(String str) {
        String[] backgroundStr;
        String[] newString;

        if (str.contains("RGB")) {
            backgroundStr = str.split("RGB(");
            newString = null;
            newString = backgroundStr[1].split("))");
            backgroundStr = null;
            backgroundStr = newString[0].split(",");
            return new java.awt.Color(Integer.parseInt(backgroundStr[0]), Integer.parseInt(backgroundStr[1]),
                    Integer.parseInt(backgroundStr[2]));
        } else {
            backgroundStr = str.split("\\(");
            newString = null;
            newString = backgroundStr[1].split("\\)");
            Field f;
            try {
                f = Color.class.getField(newString[0]);
                return (Color) f.get(null);
            } catch (Exception ce) {
                // if we can't get any color return black
                return Color.LIGHT_GRAY;
            }
        }
    }
}