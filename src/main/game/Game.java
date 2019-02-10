package game;

import game.enums.GameState;
import game.gameObjects.Player;
import game.gameObjects.ghosts.Ghost;
import lombok.Data;
import utils.Logger;

@Data
public class Game {


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
        Logger.log("Player and Ghost had a collision");
        return getMap().getSpielMap()[spieler.getXPosition()][spieler.getYPosition()][1] instanceof Ghost;
    }
}
