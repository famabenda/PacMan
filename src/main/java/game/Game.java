package game;

import game.enums.GameState;
import game.gameObjects.Player;
import game.gameObjects.ghosts.Ghost;
import lombok.Data;

@Data
/**
 * The java.game class holds the current map and the player. It is created when the user clicks on the start button an gets destroyed
 * when when the user  has lost
 */
public class Game {


    public static int collectedPacDots;
    private Map map;
    private Player player;


    public Game(Map map) {
        setMap(map);
    }


    /**
     * calls the move method on the map
     */
    public void move() {
        map.moveElements();
    }

    /**
     * checks in which GameState the java.game is
     * @return the current GameState of the java.game
     */
    public GameState evaluateGameState() {
        player = map.getSpieler();
        if (!map.pacDotsAvailable()) return GameState.WON;
        else if (Player.lives == 0) return GameState.LOST;
        return GameState.RUNNING;

    }

    /**
     * checks whether the player and the ghost have a collision
     * @return the boolean whether the player and any ghost have a collision
     */
    public boolean playerAndGhostCollision() {
        Player spieler = map.getSpieler();
        return getMap().getSpielMap()[spieler.getXPosition()][spieler.getYPosition()][1] instanceof Ghost;
    }

    /**
     * calculates the current user-java.score based on the GameO
     * @return the java.score of the player
     */
    public static int calcPlayerScore(){
        return collectedPacDots * GameOrchestrator.MULTIPLIER;
    }
}
