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


    public Game(Map map){
        setMap(map);
    }

    public void move(){
        map.move();
    }


    public GameState evaluateGameState(){
        player = map.getSpieler();
        if(!map.pacDotsAvailable()) return GameState.WON;
        else if(player.getLives()==0) return GameState.LOST;
        return GameState.RUNNING;

    }
}
