package game;

import game.enums.GameState;
import game.gameObjects.Player;
import gui.Gui;
import lombok.Data;
import utils.Logger;
import utils.MapLoader;
import utils.SoundPlayer;

import javax.swing.*;

@Data
public class GameOrchestrator {

    public static int PLAYER_LIVES = 3;
    public static int MULTIPLIER = 100;
    public static boolean running = false;
    public static Thread gameThread;
    private static String mapPath = "map/pacmanMap.txt";
    private static Gui gui;
    private static Game game;
    private static MapLoader mapLoader;
    private static int gameSpeed = 200;

    public static void main(String[] args) {
        run();

    }

    private static void run() {
        initialize();
    }

    private static void runGame() {
        gui.showGameView();
        gameThread = new Thread(() -> {
            synchronized (gameThread) {
                while (running) {
                    game.move();
                    gameWait(gameSpeed);
                }
            }
        });
        gameThread.start();
    }

    public static void checkGameState() {
        if (game.playerAndGhostCollision()) {
            Player.lives--;
            SoundPlayer.playPlayerDeathSound();
            gameWait(2000);
            game.setMap(mapLoader.resetMovableObjects(game.getMap().getSpielMap(), mapPath));
            gui.setCurrentMap(game.getMap());
            return;
        }

        GameState state = game.evaluateGameState();
        switch (state) {
            case RUNNING:
                break;
            case LOST:
                JOptionPane.showMessageDialog(null, "Verloren");
                gui.showMenuView();
                running = false;
                Player.reset();
                Game.collectedPacDots = 0;
                break;
            case WON:
                game.setMap(mapLoader.loadMap(mapPath));
                gui.setCurrentMap(game.getMap());
                gui.getMainFrame().showGameView();
                gameWait(2000);
                break;
        }
    }

    private synchronized static void gameWait(long millis) {
        try {
            synchronized (gameThread) {
                gameThread.wait(millis);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }



    public static void startGame() {
        game = new Game(mapLoader.loadMap(mapPath));
        gui.setCurrentMap(game.getMap());
        gui.showGameView();
        running = true;
        runGame();
        gui.paintGame();
        new SoundPlayer().playStartMusic();
    }

    private static void initialize() {
        mapLoader = new MapLoader();
        gui = new Gui();
        gui.getMainFrame().showMenuView();
    }


}
