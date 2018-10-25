package levels;

import java.awt.Color;
import java.io.BufferedReader;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import core.Velocity;

/**
 * Level specification reader class.
 *
 * @author Alihom
 *
 */
public class LevelSpecificationReader {
    /**
     * from reader, read the file.
     *
     * @param reader
     *            file name;
     * @return list of level information.
     */
    public List<LevelInformation> fromReader(java.io.Reader reader) {
        BufferedReader r = new BufferedReader(reader);
        String line = null;
        try {
            line = r.readLine();
        } catch (IOException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        List<LevelInformation> l = new ArrayList<LevelInformation>();

        while (line != null) {
            String levelName = null;
            String blocksDef = null;
            List<String> blocks = new ArrayList<String>();
            List<Velocity> velocity = new ArrayList<Velocity>();
            String background = null;
            java.awt.Color color = null;
            int paddleSpeed = 0;
            int paddleWidth = 0;
            int blockStartX = 0;
            int blockStartY = 0;
            int rowHeight = 0;
            int numOfBlocks = 0;
            boolean timeForBlocks = false;
            if (line.contains("START_LEVEL")) {
                while (!line.contains("END_LEVEL")) {

                    String[] newString = null;
                    if (line.contains("END_BLOCKS")) {
                        timeForBlocks = false;
                    } else if (timeForBlocks) {
                        blocks.add(line);
                    } else if (line.contains("START_BLOCKS")) {
                        timeForBlocks = true;
                    } else if (line.contains("level_name")) {
                        newString = line.split(":");
                        levelName = newString[1];
                    } else if (line.contains("ball_velocities")) {
                        newString = line.split(":");
                        String v = newString[1];
                        newString = null;
                        newString = v.split(" ");
                        for (String velo : newString) {
                            String[] velocities = null;
                            velocities = velo.split(",");
                            velocity.add(
                                    new Velocity(Integer.parseInt(velocities[0]), Integer.parseInt(velocities[1]), 2));
                        }
                    } else if (line.contains("background")) {
                        newString = line.split(":");
                        String[] backgroundStr = null;
                        if (newString[1].contains("image")) {
                            backgroundStr = newString[1].split("\\(");
                            newString = null;
                            newString = backgroundStr[1].split("\\)");
                            background = newString[0];
                        } else if (newString[1].contains("color")) {
                            if (newString[1].contains("RGB")) {
                                backgroundStr = newString[1].split("\\(");
                                newString = null;
                                newString = backgroundStr[2].split("\\)");
                                backgroundStr = null;
                                backgroundStr = newString[0].split(",");
                                color = new java.awt.Color(Integer.parseInt(backgroundStr[0]),
                                        Integer.parseInt(backgroundStr[1]), Integer.parseInt(backgroundStr[2]));
                                backgroundStr = null;
                            } else {
                                backgroundStr = newString[1].split("\\(");
                                newString = null;
                                newString = backgroundStr[1].split("\\)");
                                Field f;
                                try {
                                    f = Color.class.getField(newString[0]);
                                    color = (Color) f.get(null);
                                } catch (Exception ce) {
                                    // if we can't get any color return black
                                    color = Color.LIGHT_GRAY;
                                }
                                backgroundStr = null;
                            }

                        }
                    } else if (line.contains("paddle_speed")) {
                        newString = line.split(":");
                        paddleSpeed = Integer.parseInt(newString[1]);
                    } else if (line.contains("paddle_width")) {
                        newString = line.split(":");
                        paddleWidth = Integer.parseInt(newString[1]);
                    } else if (line.contains("block_definitions")) {
                        newString = line.split(":");
                        blocksDef = newString[1];
                    } else if (line.contains("blocks_start_x")) {
                        newString = line.split(":");
                        blockStartX = Integer.parseInt(newString[1]);
                    } else if (line.contains("blocks_start_y")) {
                        newString = line.split(":");
                        blockStartY = Integer.parseInt(newString[1]);
                    } else if (line.contains("row_height")) {
                        newString = line.split(":");
                        rowHeight = Integer.parseInt(newString[1]);
                    } else if (line.contains("num_blocks")) {
                        newString = line.split(":");
                        numOfBlocks = Integer.parseInt(newString[1]);
                    }
                    try {
                        line = r.readLine();
                    } catch (IOException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
                int[] ints = {paddleSpeed, paddleWidth, blockStartX, blockStartY, rowHeight, numOfBlocks };
                if (color != null) {
                    l.add(new LevelCreator(levelName, velocity, color, ints, blocks, blocksDef));
                } else {
                    l.add(new LevelCreator(levelName, velocity, background, ints, blocks, blocksDef));
                }
            } else {
                try {
                    line = r.readLine();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }
        return l;
    }
}
