package game;

import game.GameObjects.*;
import lombok.Data;
import utils.Logger;

@Data
public class Map {


    private SpielElement[][][] spielMap;
    public static int ghostCount;

    public Map(SpielElement[][][] map) {
        setSpielMap(map);
    }


    public Player getSpieler() {


        for (SpielElement[][] ebene : spielMap) {
            for (SpielElement[] reihe : ebene) {
                if (reihe[0] instanceof Ghost) {
                    return (Player) reihe[0];
                }
            }
        }


        for (SpielElement[][] ebene : spielMap) {
            for (SpielElement[] reihe : ebene) {
                for (SpielElement element : reihe) {
                    if (element instanceof Player) return (Player) element;
                }
            }
        }
        Logger.error("No player-object has been found on the map");
        return null;

    }

    public Ghost[] getGhosts() {
        Ghost[] ghosts = new Ghost[ghostCount];
        int index = 0;


        for (SpielElement[][] ebene : spielMap) {
            for (SpielElement[] reihe : ebene) {
                if (reihe[1] instanceof Ghost) {
                    ghosts[index] = (Ghost) reihe[1];
                    Logger.log("Ghost found");
                    index++;
                }
            }
        }
        System.out.println("");
        return ghosts;
    }

    public boolean pacDotsAvailable() {
        for (SpielElement[][] ebene : spielMap) {
            for (SpielElement[] reihe : ebene) {
                for (SpielElement element : reihe) {
                    if (element instanceof PacDot) return true;
                }
            }
        }
        Logger.log("All pacdots have been collected");
        return false;
    }


    public void move() {
        spielMap = getSpieler().move(spielMap);
        Ghost[] ghosts = getGhosts();

        for (Ghost ghost : ghosts) {
            spielMap = ghost.move(spielMap);
        }
    }

    public static boolean isWallOrGhost(int x, int y, SpielElement[][][] spielMap) {
        try {
            return spielMap[x][y][0] instanceof Wall || spielMap[x][y][1] instanceof Ghost;
        } catch (IndexOutOfBoundsException ioobe) {
            return true;
        }
    }
}
