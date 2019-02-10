package game;

import game.enums.GameState;
import game.gameObjects.Player;
import game.gameObjects.ghosts.Ghost;
import lombok.Data;

@Data
public class Game {


    public static int collectedPacDots;
    private Map map;
    private Player player;


    public Game(Map map) {
        setMap(map);
    }

    public void move() {
        map.move();
    }


    public GameState evaluateGameState() {
        player = map.getSpieler();
        if (!map.pacDotsAvailable()) return GameState.WON;
        else if (Player.lives == 0) return GameState.LOST;
        return GameState.RUNNING;

    }


    public boolean playerAndGhostCollision() {
        Player spieler = map.getSpieler();
        return getMap().getSpielMap()[spieler.getXPosition()][spieler.getYPosition()][1] instanceof Ghost;
    }
}
