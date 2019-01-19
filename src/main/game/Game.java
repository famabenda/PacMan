package game;

import lombok.Data;

@Data
public class Game {

    private Map map;
    private Player player;
    private Ghost[] ghosts;

    public Game(Map map){
        setMap(map);
    }

    public void tick(){

    }
}
