package game;

import game.Enums.Direction;
import game.GameObjects.*;
import lombok.Data;
import utils.Logger;

@Data
public class Map {


    private SpielElement[][] spielMap;
    private Player spieler;

    public Map(SpielElement[][] map) {
        setSpielMap(map);
    }


    public Player getSpieler() {
        for (SpielElement[] reihe : spielMap) {
            for (SpielElement element : reihe) {
                if (element instanceof Player) return (Player) element;
            }
        }
        Logger.error("No player-object has been found on the map");
        return null;
    }


    public void move() {
        spielMap = getSpieler().move(spielMap);
    }
}
