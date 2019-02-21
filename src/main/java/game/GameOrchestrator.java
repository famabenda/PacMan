package game;

import game.enums.Direction;
import game.enums.GameState;
import game.gameObjects.Player;
import gui.Gui;
import lombok.Data;
import score.HighscoreTable;
import score.Score;
import utils.MapLoader;
import utils.SoundPlayer;

import javax.swing.*;

@Data
/**
 * The game.GameOrchestrator is the starting-point of the java.game, it instantiates the java.gui and all necessary
 * parts which are essential for playing the java.game.
 * It also holds a bunch of parameter which can be used to make some adjustments on the behaviour of
 * the java.game.
 */
public class GameOrchestrator {

    public static final int PLAYER_LIVES = 3;
    public static final int MULTIPLIER = 100;
    public static boolean running = false;
    public static Thread gameThread;
    public static HighscoreTable highscoreTable;
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

    /**
     * checks in which state the current java.game is. At first it checks whether the player and one of the ghosts have collided.
     * if they had a collision, the movable objects gets reseted and the java.game goes on.
     * If they have not collided, the gamestate gets evaluated and after that depending on the result, the java.game is won, lost or still running.
     */
    public static void checkGameState() {
        if (game.playerAndGhostCollision()) {
            Player.lives--;
            SoundPlayer.playPlayerDeathSound();
            gameWait(2000);
            game.setMap(mapLoader.resetMovableObjects(game.getMap().getSpielMap(), mapPath));
            gui.setCurrentMap(game.getMap());
            Player.lastPressedDirection = Direction.NORTH;
            return;
        }

        GameState state = game.evaluateGameState();
        switch (state) {
            case RUNNING:
                break;
            case LOST:
                Score score = new Score(askForUserInput("Verloren, gebe bitte deinen Namen für die Highscore-Tabelle ein."), Game.calcPlayerScore());
                highscoreTable.add(score);
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

    /**
     * waits a certain amount of time (gameThread)
     * @param millis the amount of millis which the java.game should wait
     */
    private synchronized static void gameWait(long millis) {
        try {
            synchronized (gameThread) {
                gameThread.wait(millis);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * starts a new java.game
     */
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
        highscoreTable = new HighscoreTable();
        mapLoader = new MapLoader();
        gui = new Gui();
        gui.getMainFrame().showMenuView();
    }

    /**
     * shows a new inputdialog with the given message
     * @param message the message which the user should see
     * @return the userinput
     */
    private static String askForUserInput(String message) {
        return JOptionPane.showInputDialog(null, message);

    }


}
