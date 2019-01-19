package game;

import gui.Gui;
import lombok.Data;

@Data
public class GameOrchestrator {

    private static Gui gui;
    public static void main(String[] args){
        run();
    }

    private static void tick(){

    }
    private static void run(){
        initialize();
    }

    private static void initialize(){
        gui = new Gui();
    }
}
