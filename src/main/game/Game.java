package game;

import game.Enums.GameState;
import game.GameObjects.Ghost;
import game.GameObjects.Player;
import lombok.Data;

@Data
public class Game {


    private Map map;
    private Player player;
    private Ghost[] ghosts;
    private GameState gameState;


    public Game(Map map){
        setMap(map);
        gameState = GameState.RUNNING;
    }

    public void move(){
        map.move();
    }
}
