package game;

import gui.Gui;
import lombok.Data;
import utils.Logger;
import utils.MapLoader;

@Data
public class GameOrchestrator {


    public static boolean running;

    private static Gui gui;
    private static Game game;
    private static MapLoader mapLoader;
    private static int gameSpeed = 500;

    public static void main(String[] args) {
        run();

    }

    private static void run() {
        running = true;
        initialize();


        new Thread(new Runnable() {

            @Override
            public void run() {
                synchronized (this) {
                    while (running) {
                        try {
                            game.move();
                            this.wait(gameSpeed);
                        } catch (InterruptedException e) {
                            Logger.error("Error in Thread which is responsible for drawing the game (paintGame())");
                            e.printStackTrace();
                        }
                    }
                }
            }
        }).start();


    }


    private static void initialize() {
        mapLoader = new MapLoader();
        game = new Game(mapLoader.loadMap("map/pacmanMap.txt"));
        gui = new Gui(game.getMap());
    }


}
