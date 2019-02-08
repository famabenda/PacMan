package gui;

import game.GameOrchestrator;
import game.Map;
import lombok.Data;
import utils.Logger;

import javax.swing.*;


@Data
public class Gui {

    public static int RESOLUTION = 45;
    public static final int SCREEN_WIDTH = 25 * RESOLUTION;
    public static final int SCREEN_HEIGHT = 25 * RESOLUTION;
    public static int fps = 20;

    private MainFrame mainFrame;
    private Map currentMap;
    private JTabbedPane tabbedPane;

    public Gui() {
        mainFrame = new MainFrame();
        mainFrame.pack();
        mainFrame.setBounds(400, 50, SCREEN_WIDTH, SCREEN_HEIGHT);
    }

    public void paintGame() {
        if (currentMap == null) return;
        new Thread(new Runnable() {

            @Override
            public void run() {
                synchronized (this) {

                    while (GameOrchestrator.running) {
                        try {
                            mainFrame.getCardLayoutPanel().getMainPanel().paintMap(currentMap);
                            this.wait(fps);
                        } catch (InterruptedException e) {
                            Logger.error("Error in Thread which is responsible for drawing the game (paintGame())");
                            e.printStackTrace();
                        }
                    }
                }
            }
        }).start();
    }

    public void showGameView(){
        mainFrame.showGameView();
    }
    public void showMenuView(){
        mainFrame.showMenuView();
    }

}
