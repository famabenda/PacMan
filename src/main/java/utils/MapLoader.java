package utils;

import game.Map;
import game.enums.Direction;
import game.enums.MapElements;
import game.gameObjects.*;
import game.gameObjects.ghosts.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class MapLoader {


    public Map resetMovableObjects(SpielElement[][][] spielMap, String mapPath) {
        String[][] mapAsString = loadMapAsString(mapPath);
        for (int i = 0; i < mapAsString.length; i++) {
            for (int j = 0; j < mapAsString[0].length; j++) {
                if (spielMap[j][i][1] instanceof Ghost) spielMap[j][i][1] = null;
                if (spielMap[j][i][0] instanceof Player) spielMap[j][i][0] = new EmptySpace(j, i);
                if (mapAsString[i][j].equals(MapElements.BLINKY_SYMBOL.getMapCode())) {
                    spielMap[j][i][1] = new Blinky(j, i, Direction.SOUTH);
                    spielMap[j][i][0] = new EmptySpace(j, i);
                }
                if (mapAsString[i][j].equals(MapElements.INKY_SYMBOL.getMapCode())) {
                    spielMap[j][i][1] = new Inky(j, i, Direction.SOUTH);
                    spielMap[j][i][0] = new EmptySpace(j, i);
                }
                if (mapAsString[i][j].equals(MapElements.PINKY_SYMBOL.getMapCode())) {
                    spielMap[j][i][1] = new Pinky(j, i, Direction.SOUTH);
                    spielMap[j][i][0] = new EmptySpace(j, i);
                }
                if (mapAsString[i][j].equals(MapElements.CLYDE_SYMBOL.getMapCode())) {
                    spielMap[j][i][1] = new Clyde(j, i, Direction.SOUTH);
                    spielMap[j][i][0] = new EmptySpace(j, i);
                }
                if (mapAsString[i][j].equals(MapElements.USER_SYMBOL.getMapCode())) {
                    spielMap[j][i][0] = new Player(j, i, Direction.NORTH);
                }
            }
        }


        Logger.log("Map successfully reloaded after player-death");
        return new Map(spielMap);
    }


    public Map loadMap(String path) {
        Map.ghostCount = 0;
        String[][] mapAsString = loadMapAsString(path);
        SpielElement[][][] translatedMap = new SpielElement[mapAsString[0].length][mapAsString.length][2];
        for (int i = 0; i < mapAsString.length; i++) {
            for (int j = 0; j < mapAsString[0].length; j++) {

                if (mapAsString[i][j].equals(MapElements.WALL_SYMBOL.getMapCode()))
                    translatedMap[j][i][0] = new Wall(j, i);
                else if (mapAsString[i][j].equals(MapElements.PACDOT_SYMBOL.getMapCode()))
                    translatedMap[j][i][0] = new PacDot(j, i);
                else if (mapAsString[i][j].equals(MapElements.EMPTY_SPACE_SYMBOL.getMapCode()))
                    translatedMap[j][i][0] = new EmptySpace(j, i);
                else if (mapAsString[i][j].equals(MapElements.BLINKY_SYMBOL.getMapCode())) {
                    translatedMap[j][i][1] = new Blinky(j, i, Direction.SOUTH);
                    Map.ghostCount++;
                    translatedMap[j][i][0] = new EmptySpace(j, i);
                } else if (mapAsString[i][j].equals(MapElements.PINKY_SYMBOL.getMapCode())) {
                    translatedMap[j][i][1] = new Pinky(j, i, Direction.SOUTH);
                    Map.ghostCount++;
                    translatedMap[j][i][0] = new EmptySpace(j, i);
                } else if (mapAsString[i][j].equals(MapElements.INKY_SYMBOL.getMapCode())) {
                    translatedMap[j][i][1] = new Inky(j, i, Direction.SOUTH);
                    Map.ghostCount++;
                    translatedMap[j][i][0] = new EmptySpace(j, i);
                } else if (mapAsString[i][j].equals(MapElements.CLYDE_SYMBOL.getMapCode())) {
                    translatedMap[j][i][1] = new Clyde(j, i, Direction.SOUTH);
                    Map.ghostCount++;
                    translatedMap[j][i][0] = new EmptySpace(j, i);
                } else if (mapAsString[i][j].equals(MapElements.USER_SYMBOL.getMapCode()))
                    translatedMap[j][i][0] = new Player(j, i, Direction.NORTH);
            }
        }
        return new Map(translatedMap);
    }


    private String[][] loadMapAsString(String path) {
        try {
            Scanner reader = new Scanner(new File(getClass().getClassLoader().getResource(path).getFile()));
            ArrayList<String> lines = new ArrayList<>();
            do {
                lines.add(reader.nextLine());
            } while (reader.hasNextLine());
            String[][] stringMap = new String[lines.size()][lines.get(0).length()];
            for (int i = 0; i < lines.size(); i++) {
                stringMap[i] = lines.get(i).split(",");
            }
            Logger.log("Map succesfully loaded from " + path);
            return stringMap;

        } catch (FileNotFoundException fnfe) {


            Logger.error("FileNotFoundException in MapLoader (loadMap) with InputPath: " + path);
            fnfe.printStackTrace();
        }
        return null;
    }


}
