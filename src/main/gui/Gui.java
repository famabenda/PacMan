package gui;

import game.GameOrchestrator;
import game.Map;
import lombok.Data;
import utils.Logger;


@Data
public class Gui {

    public static int RESOLUTION = 40;
    public static final int SCREEN_WIDTH = 25 * RESOLUTION;
    public static final int SCREEN_HEIGHT = 25 * RESOLUTION;
    public static int fps = 200;

    private MainPanel mainPanel;
    private MainFrame mainFrame;
    private Map currentMap;

    public Gui(Map map) {

        mainFrame = new MainFrame();
        mainPanel = new MainPanel();
        mainFrame.add(mainPanel);
        mainFrame.pack();
        mainFrame.setBounds(400, 50, SCREEN_WIDTH, SCREEN_HEIGHT);
        setCurrentMap(map);
        paintGame();
    }

    public void paintGame() {
        if (currentMap == null) return;
        new Thread(new Runnable() {

            @Override
            public void run() {
                synchronized (this) {

                    while (GameOrchestrator.running) {
                        try {

                            mainPanel.paintMap(currentMap);
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

}
