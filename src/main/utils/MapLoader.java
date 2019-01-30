package utils;

import game.Enums.Direction;
import game.GameObjects.*;
import game.Map;

import java.io.Console;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class MapLoader {


    public Map loadMap(String path) {
        String[][] mapAsString = loadMapAsString(path);
        SpielElement[][] translatedMap = new SpielElement[mapAsString[0].length][mapAsString.length];
        for (int i = 0; i < mapAsString.length; i++) {
            for (int j = 0; j < mapAsString[0].length; j++) {

                if (mapAsString[i][j].equals("#")) translatedMap[j][i] = new Wall(j,i);
                else if (mapAsString[i][j].equals("*")) translatedMap[j][i] = new PacDot(j,i);
                else if (mapAsString[i][j].equals(" ")) translatedMap[j][i] = new EmptySpace(j,i);
                else if (mapAsString[i][j].equals("M")) translatedMap[j][i] = new Ghost(j,i, Direction.SOUTH);
                else if (mapAsString[i][j].equals("P")) translatedMap[j][i] = new Player(j,i, Direction.NONE);
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
