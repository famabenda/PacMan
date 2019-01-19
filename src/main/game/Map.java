package game;

import lombok.Data;

@Data
public class Map {

    private String[][] map;
    public Map(String[][] mapAsString){
        setMap(mapAsString);
    }
}
