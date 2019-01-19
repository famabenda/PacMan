package utils;

import game.Map;

import java.io.Console;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class MapLoader {


    public Map loadMap(String path) {
        try {
            Scanner reader = new Scanner(new File(path));
            ArrayList<String> lines = new ArrayList<>();
            while (reader.hasNextLine()) {
                lines.add(reader.nextLine());
            }
            String[][] stringMap = new String[lines.size()][lines.get(0).length()];
            for (int i = 0; i < lines.size(); i++) {
                stringMap[i] = lines.get(i).split(",");
            }
            Logger.log("Map succesfully loaded from "+path);
            return new Map(stringMap);

        } catch (FileNotFoundException fnfe) {


            Logger.error("FileNotFoundException in MapLoader (loadMap) with InputPath: " + path);
        }
        return null;
    }


}
