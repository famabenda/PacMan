package gui;

import javax.swing.*;

public class MainFrame extends JFrame {

    public MainFrame(int xPos, int yPos, int width, int heigth){
        this.setBounds(xPos, yPos, width, heigth);
        this.setVisible(true);
    }
}
