package game;

import game.Enums.GameState;
import gui.Gui;
import lombok.Data;
import utils.MapLoader;

@Data
public class GameOrchestrator {


    public static boolean running;

    private static Gui gui;
    private static Game game;
    private static MapLoader mapLoader;

    public static void main(String[] args){
        run();
    }

    private static void update(){

    }

    private static void run(){
        running = true;
        initialize();

        while(game.getGameState() == GameState.RUNNING){
            update();
        }

    }

    public void startGame(){

    }

    private static void initialize(){
        gui = new Gui();
        mapLoader = new MapLoader();
        game = new Game(mapLoader.loadMap("../res/map/pacmanMap.txt"));
    }


}
