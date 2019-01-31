package game;

import game.Enums.GameState;
import gui.Gui;
import lombok.Data;
import utils.Logger;
import utils.MapLoader;
import utils.SoundPlayer;

import javax.swing.*;

@Data
public class GameOrchestrator {


    public static boolean running;

    private static Gui gui;
    private static Game game;
    private static MapLoader mapLoader;
    private static int gameSpeed = 300;

    public static void main(String[] args) {
        run();

    }

    private static void run() {
        running = true;
        initialize();
    }

    private static void runGame() {
        gui.showGameView();

        new Thread(new Runnable() {

            @Override
            public void run() {
                synchronized (this) {
                    while (running) {
                        try {
                            game.move();
                            checkGameState();
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


    private static void checkGameState() {
        GameState state = game.evaluateGameState();
        switch (state) {
            case RUNNING:
                break;
            case LOST:
                JOptionPane.showMessageDialog(null, "Verloren");
                gui.showMenuView();
                break;
            case WON:
                JOptionPane.showMessageDialog(null, "Gewonnen");
                break;
        }
    }

    public static void startGame() {
        gui.showGameView();
        runGame();
        gui.paintGame();
        SoundPlayer.playStartMusic();
    }


    private static void initialize() {
        mapLoader = new MapLoader();
        game = new Game(mapLoader.loadMap("map/pacmanMap.txt"));
        gui = new Gui();
        gui.setCurrentMap(game.getMap());
        gui.getMainFrame().showMenuView();
    }


}
