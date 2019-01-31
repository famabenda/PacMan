package gui;

import game.Enums.Direction;
import game.GameObjects.*;
import game.Map;
import lombok.Data;
import utils.Logger;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

@Data
public class MainPanel extends JPanel implements KeyListener {


    private static final int pacDotSize = Gui.RESOLUTION / 2;

    private static final Color wallColor = Color.BLACK;
    private static final Color pacDotColor = Color.YELLOW;
    private static final Color emptySpaceColor = Color.LIGHT_GRAY;
    private static final Color ghostColor = Color.WHITE;
    private static final Color playerColor = Color.RED;


    private BufferedImage pacDotImage;

    private Map map;

    public MainPanel() {
        init();
        setDoubleBuffered(true);
        this.setLayout(null);
        addKeyListener(this);
    }

    private void init() {
        try {
            pacDotImage = ImageIO.read(new File(getClass().getClassLoader().getResource("images/pacdot.png").getFile()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void paintMap(Map map) {
        setMap(map);
        repaint();
    }

    public void paint(Graphics g) {

        Graphics2D g2 = (Graphics2D) g;
        removeAll();

        int res = Gui.RESOLUTION;
        for (int i = 0; i < map.getSpielMap().length; i++) {
            for (int j = 0; j < map.getSpielMap()[0].length; j++) {

                double x = map.getSpielMap()[i][j].getXPosition();

                double y = map.getSpielMap()[i][j].getYPosition();
                if (map.getSpielMap()[i][j] instanceof Wall) {
                    g2.setColor(wallColor);
                    g2.fillRect((int) (x * res), (int) (y * res), res, res);
                }
                if (map.getSpielMap()[i][j] instanceof PacDot) {
                    g2.setColor(pacDotColor);
//                    g2.drawImage(pacDotImage, x * Gui.RESOLUTION, y * Gui.RESOLUTION, null);
                    g2.fillRect((int) ((x * res)) + res / 4, (int) ((y * res) + res / 4), pacDotSize, pacDotSize);
                }
                if (map.getSpielMap()[i][j] instanceof EmptySpace) {
                    g2.setColor(emptySpaceColor);
                    g2.fillRect((int) x * res, (int) y * res, res, res);
                }
                if (map.getSpielMap()[i][j] instanceof Ghost) {
                    g2.setColor(ghostColor);
                    g2.drawString("G", (int) ((x * res) + res / 4), (int) ((y * res) + res / 2));
                }
                if (map.getSpielMap()[i][j] instanceof Player) {
                    g2.setColor(playerColor);
//                    g.drawString("P", (x * res)+res/4, (y * res)+res/4);
                    g2.fillRect((int) ((x * res) + res / 4), (int) ((y * res) + res / 4), pacDotSize, pacDotSize);
                }
            }
        }
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
                if (Player.direction == Direction.NORTH) break;
                Player.prevDirection = Player.direction;
                Player.direction = Direction.NORTH;
                break;
            case KeyEvent.VK_DOWN:
                Logger.log("KEY-DOWN");
                if (Player.direction == Direction.SOUTH) break;
                Player.direction = Direction.SOUTH;
                break;
            case KeyEvent.VK_LEFT:
                Logger.log("KEY-LEFT");
                if (Player.direction == Direction.WEST) break;
                Player.direction = Direction.WEST;
                break;
            case KeyEvent.VK_RIGHT:
                Logger.log("KEY-RIGHT");
                if (Player.direction == Direction.EAST) break;
                Player.direction = Direction.EAST;
                break;

        }
    }

    @Override
    public void keyReleased(KeyEvent keyEvent) {

    }
}
