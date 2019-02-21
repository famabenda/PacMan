package game;

import game.gameObjects.PacDot;
import game.gameObjects.Player;
import game.gameObjects.SpielElement;
import game.gameObjects.Wall;
import game.gameObjects.ghosts.Ghost;
import lombok.Data;
import utils.Logger;

@Data
public class Map {


    public static int ghostCount;
    private SpielElement[][][] spielMap;
    private Player spieler;

    public Map(SpielElement[][][] map) {
        setSpielMap(map);
        spieler = getSpielerFromMap();
    }

    /**
     * checks whether the given tile is a wall or a ghost
     * @param x x-parameter of the given tile
     * @param y y-parameter of the given tile
     * @param spielMap the current gamemap
     * @return whether the tile is a ghost or a wall
     */
    public static boolean isWallOrGhost(int x, int y, SpielElement[][][] spielMap) {
        try {
            return spielMap[x][y][0] instanceof Wall || spielMap[x][y][1] instanceof Ghost;
        } catch (IndexOutOfBoundsException ioobe) {
            return true;
        }
    }

    /**
     * retrieves the player from the map
     * @return the player-object fromthe current map
     */
    private Player getSpielerFromMap() {
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

    /**
     * retrieves all ghosts from the current map
     * @return an array of all ghosts on the map
     */
    private Ghost[] getGhostsFromMap() {
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
        return ghosts;
    }

    /**
     * Checks whether there are pacdots left on the map
     * @return the boolean if there are pacdots left
     */
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

    /**
     * calles the move method on the player and the ghosts
     */
    public void moveElements() {
        if (getSpieler() == null) return;
        spielMap = getSpieler().move(spielMap);
        GameOrchestrator.checkGameState();
        Ghost[] ghosts = getGhostsFromMap();

        if (ghosts.length < ghostCount) return;
        for (Ghost ghost : ghosts) {
            ghost.move(spielMap);
        }
        GameOrchestrator.checkGameState();

    }
}
