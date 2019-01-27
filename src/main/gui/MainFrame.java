package gui;

import game.Enums.Direction;
import game.GameObjects.Player;
import lombok.Data;
import utils.Logger;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

@Data
public class MainFrame extends JFrame implements KeyListener {


    public MainFrame() {
        pack();
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        addKeyListener(this);
    }


    @Override
    public void keyTyped(KeyEvent keyEvent) {

    }

    @Override
    public void keyPressed(KeyEvent keyEvent) {
//        if (Player.tranistion) return;
        int keyCode = keyEvent.getKeyCode();
        switch (keyCode) {

            case KeyEvent.VK_UP:
                Logger.log("KEY-UP");
                Player.direction = Direction.NORTH;
                Player.tranistion = true;
                break;
            case KeyEvent.VK_DOWN:
                Logger.log("KEY-DOWN");
                Player.direction = Direction.SOUTH;
                Player.tranistion = true;
                break;
            case KeyEvent.VK_LEFT:
                Logger.log("KEY-LEFT");
                Player.direction = Direction.WEST;
                Player.tranistion = true;
                break;
            case KeyEvent.VK_RIGHT:
                Logger.log("KEY-RIGHT");
                Player.direction = Direction.EAST;
                Player.tranistion = true;
                break;

        }
    }

    @Override
    public void keyReleased(KeyEvent keyEvent) {

    }
}
