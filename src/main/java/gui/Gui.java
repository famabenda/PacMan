package gui;

import game.GameOrchestrator;
import game.Map;
import lombok.Data;
import utils.Logger;

import javax.swing.*;


@Data
/**
 * The java.gui holds some attributes
 */
public class Gui {

    public static final int RESOLUTION = 40;
    public static final int SCREEN_WIDTH = 25 * RESOLUTION;
    public static final int SCREEN_HEIGHT = 25 * RESOLUTION;
    public static int fps = 10;

    private MainFrame mainFrame;
    private Map currentMap;
    private JTabbedPane tabbedPane;
    private Thread paintThread;

    public Gui() {
        mainFrame = new MainFrame();
        mainFrame.pack();
        mainFrame.setBounds(400, 50, SCREEN_WIDTH, SCREEN_HEIGHT);
    }

    public void paintGame() {
        if (currentMap == null) return;
        paintThread = new Thread(new Runnable() {

            @Override
            public void run() {
                synchronized (this) {

                    while (GameOrchestrator.running) {
                        try {
                            mainFrame.getCardLayoutPanel().getMainPanel().paintMap(currentMap);
                            this.wait(fps);
                        } catch (InterruptedException e) {
                            Logger.error("Error in Thread which is responsible for drawing the java.game (paintGame())");
                            e.printStackTrace();
                        }
                    }
                }
            }
        });
        paintThread.start();
    }

    public void showGameView(){
        mainFrame.showGameView();
    }
    public void showMenuView(){
        mainFrame.showMenuView();
    }

}