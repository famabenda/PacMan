package game;

import game.GameObjects.Ghost;
import game.GameObjects.PacDot;
import game.GameObjects.Player;
import game.GameObjects.SpielElement;
import lombok.Data;
import utils.Logger;

import java.util.Arrays;

@Data
public class Map {


    private SpielElement[][] spielMap;
    private Player spieler;
    private Ghost[] ghosts;

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

    public Ghost[] getGhosts() {
        Ghost[] ghosts = new Ghost[4];
        int index = 0;
        for (SpielElement[] reihe : spielMap) {
            for (SpielElement element : reihe) {
                if (element instanceof Ghost) {
                    ghosts[index] = (Ghost) element;
                    index++;
                }
            }
        }
        return ghosts;
    }

    public boolean pacDotsAvailable() {
        for (SpielElement[] reihe : spielMap) {
            for (SpielElement element : reihe) {
                if (element instanceof PacDot) return true;
            }
        }
        Logger.log("All pacdots have been collected");
        return false;
    }


    public void move() {
        spielMap = getSpieler().move(spielMap);
        ghosts = getGhosts();

        for(Ghost ghost : ghosts){
            spielMap = ghost.move(spielMap);
        }
    }
}
