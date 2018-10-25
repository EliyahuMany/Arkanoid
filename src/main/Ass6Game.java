package main;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import animation.AnimationRunner;
import animation.Menu;
import arkanoid.GameFlow;
import arkanoid.HighScoresTable;
import arkanoid.MenuAnimation;
import arkanoid.ShowHiScoresTask;
import arkanoid.Task;
import arkanoid.HighScoresAnimation;
import biuoop.GUI;
import levels.LevelSpecificationReader;

/**
 * Ass5Game class.
 */
public class Ass6Game {
    /**
     * main for game.
     *
     * @param args
     *            none.
     */
    public static void main(String[] args) {
        GUI gui = new GUI("Harry Blocker", 800, 600);
        Map<String, String> levelTasks = new HashMap<String, String>();
        Menu<Task<Void>> menu = new MenuAnimation<Task<Void>>("Harry Blocker", gui.getKeyboardSensor(),
                "background_images/main.jpg");
        AnimationRunner ar = new AnimationRunner(gui, 60);
        String line = "";
        String[] newStr = null;
        InputStream inputS;

        HighScoresTable table = HighScoresTable.loadFromFile(new File("highscores"));
        if (table.getHighScores().isEmpty()) {
            try {
                table.save(new File("highscores"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        GameFlow gF = new GameFlow(ar, 7, gui, table);

        Task<Void> subMenuEasy = new Task<Void>() {

            public Void run() {
                InputStream inputS = ClassLoader.getSystemClassLoader()
                        .getResourceAsStream("definitions/easy_level_definitions.txt");
                java.io.Reader reader = new InputStreamReader(inputS);
                LevelSpecificationReader l = new LevelSpecificationReader();
                gF.runLevels(l.fromReader(reader));
                return null;
            }
        };
        Task<Void> subMenuLevel = new Task<Void>() {

            public Void run() {
                InputStream inputS = ClassLoader.getSystemClassLoader().getResourceAsStream(
                        levelTasks.get(menu.getSubMenu().get(menu.getLastSelect()).getMenu().getLastSelect()));
                java.io.Reader reader = new InputStreamReader(inputS);
                LevelSpecificationReader l = new LevelSpecificationReader();
                gF.runLevels(l.fromReader(reader));
                return null;
            }
        };
        Task<Void> start = new Task<Void>() {

            public Void run() {
                menu.getSubMenu().get(menu.getLastSelect()).getMenu().setStop(false);
                ar.run(menu.getSubMenu().get(menu.getLastSelect()).getMenu());
                return null;
            }
        };
        menu.addSelection("s", "Play", start);
        Menu<Task<Void>> subMenu = new MenuAnimation<Task<Void>>("Levels", gui.getKeyboardSensor(),
                "background_images/submain.jpg");
        menu.addSubMenu("s", "Levels", subMenu);
        if (args.length > 0) {
            inputS = ClassLoader.getSystemClassLoader().getResourceAsStream(args[0]);
        } else {
            inputS = ClassLoader.getSystemClassLoader().getResourceAsStream("level_sets.txt");
        }
        java.io.Reader reader = new InputStreamReader(inputS);
        BufferedReader r = new BufferedReader(reader);
        while (true) {
            try {
                line = r.readLine();
            } catch (IOException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
            if (line != null) {
                if (line.contains(":")) {
                    newStr = line.split(":");
                } else if (line.contains(".txt")) {
                    levelTasks.put(newStr[0], line);
                    subMenu.addSelection(newStr[0], newStr[1], subMenuLevel);
                    newStr = null;
                }
            } else {
                break;
            }
        }
        menu.addSelection("h", "High Scores",
                (Task<Void>) new ShowHiScoresTask(ar, new HighScoresAnimation(table, "space", gui)));
        Task<Void> quit = new Task<Void>() {

            public Void run() {
                System.exit(0);
                return null;
            }
        };
        menu.addSelection("q", "Quit", quit);
        ar.run(menu);
    }
}
/*
 * List<LevelInformation> list = new
 * ArrayList<LevelInformation>();if(args.length==0) { list.add(new DirectHit());
 * list.add(new WideEasy()); list.add(new Green3()); list.add(new FinalFour());
 * }else { for (int i = 0; i < args.length; i++) { try { switch
 * (Integer.parseInt(args[i])) { case (1): list.add(new DirectHit()); break;
 * case (2): list.add(new WideEasy()); break; case (3): list.add(new Green3());
 * break; case (4): list.add(new FinalFour()); break; default: break; } } catch
 * (Exception e) { System.out.println(args[i] + " is not a legal level."); } } }
 */
