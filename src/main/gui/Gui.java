package gui;

import lombok.Data;


@Data
public class Gui {

    public static final int SCREEN_WIDTH = 1000;
    public static final int SCREEN_HEIGHT = 800;

    private MainFrame mainFrame;

    public Gui() {
        mainFrame = new MainFrame(300, 300, SCREEN_WIDTH, SCREEN_HEIGHT);
    }


}
