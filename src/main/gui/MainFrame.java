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
                if(Player.direction == Direction.NORTH) break;
                Player.prevDirection = Player.direction;
                Player.direction = Direction.NORTH;
                break;
            case KeyEvent.VK_DOWN:
                Logger.log("KEY-DOWN");
                if(Player.direction == Direction.SOUTH) break;
                Player.direction = Direction.SOUTH;
                break;
            case KeyEvent.VK_LEFT:
                Logger.log("KEY-LEFT");
                if(Player.direction == Direction.WEST) break;
                Player.direction = Direction.WEST;
                break;
            case KeyEvent.VK_RIGHT:
                Logger.log("KEY-RIGHT");
                if(Player.direction == Direction.EAST) break;
                Player.direction = Direction.EAST;
                break;

        }
    }

    @Override
    public void keyReleased(KeyEvent keyEvent) {

    }
}
