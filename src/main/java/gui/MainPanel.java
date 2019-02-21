package gui;

import game.Game;
import game.Map;
import game.enums.Direction;
import game.gameObjects.EmptySpace;
import game.gameObjects.PacDot;
import game.gameObjects.Player;
import game.gameObjects.Wall;
import game.gameObjects.ghosts.Ghost;
import lombok.Data;
import org.imgscalr.Scalr;
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


    private static final int PAC_DOT_SIZE = Gui.RESOLUTION / 2;

    private static final Color backgroundColor = Color.black;
    private static final Color wallColor = Color.blue;


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
            pacDotImage = ImageIO.read(new File(getClass().getClassLoader().getResource("images/PacDot.png").getFile()));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public BufferedImage scaleImage(BufferedImage image, int resolution) {

        return Scalr.resize(image, resolution);
    }


    public void paintMap(Map map) {
        setMap(map);
        repaint();
    }

    public void paint(Graphics g) {
        setBackground(backgroundColor);
        Graphics2D g2 = (Graphics2D) g;
        removeAll();

        int res = Gui.RESOLUTION;
        for (int i = 0; i < map.getSpielMap().length; i++) {
            for (int j = 0; j < map.getSpielMap()[0].length; j++) {
                double x = 3;
                try {
                    x = map.getSpielMap()[i][j][0].getXPosition();
                } catch (NullPointerException npe) {
                    Logger.debug("x: " + i + " y: " + j);
                }
                double y = map.getSpielMap()[i][j][0].getYPosition();
                if (map.getSpielMap()[i][j][0] instanceof Wall) {
                    g2.setColor(wallColor);
                    g2.fillRect((int) (x * res), (int) (y * res), res, res);
                }
                if (map.getSpielMap()[i][j][0] instanceof PacDot) {
                    g.setColor(backgroundColor);
                    g.fillRect((int) x * res, (int) y * res, res, res);
                    g.drawImage(scaleImage(pacDotImage, res / 4), (int) ((x * res)) + (res / 2) - res / 8, (int) ((y * res) + (res / 2) - res / 8), null);
                }
                if (map.getSpielMap()[i][j][0] instanceof EmptySpace) {
                    g2.setColor(backgroundColor);
                    g2.fillRect((int) x * res, (int) y * res, res, res);
                }
                if (map.getSpielMap()[i][j][0] instanceof Player) {
                    g.setColor(backgroundColor);
                    g.fillRect((int) x * res, (int) y * res, res, res);
//                    g2.setColor(playerColor);
//                    g2.fillRect((int) ((x * res) + res / 4), (int) ((y * res) + res / 4), PAC_DOT_SIZE, PAC_DOT_SIZE);

                    switch (Player.direction) {
                        case EAST:
                            paintImage(((Player) map.getSpielMap()[i][j][0]).getImageRight(), x, y, res, g);
                            break;
                        case SOUTH:
                            paintImage(((Player) map.getSpielMap()[i][j][0]).getImageDown(), x, y, res, g);
                            break;
                        case NORTH:
                            paintImage(((Player) map.getSpielMap()[i][j][0]).getImageUp(), x, y, res, g);
                            break;
                        case WEST:
                            paintImage(((Player) map.getSpielMap()[i][j][0]).getImageLeft(), x, y, res, g);
                            break;
                    }

                }
                if (map.getSpielMap()[i][j][1] instanceof Ghost) {
//                    g.setColor(backgroundColor);
//                    g.fillRect((int) x * res, (int) y * res, res, res);
                    switch (((Ghost) map.getSpielMap()[i][j][1]).getDirection()) {
                        case EAST:
                            paintImage(((Ghost) map.getSpielMap()[i][j][1]).getImageRight(), x, y, res, g);
                            break;
                        case SOUTH:
                            paintImage(((Ghost) map.getSpielMap()[i][j][1]).getImageDown(), x, y, res, g);
                            break;
                        case NORTH:
                            paintImage(((Ghost) map.getSpielMap()[i][j][1]).getImageUp(), x, y, res, g);
                            break;
                        case WEST:
                            paintImage(((Ghost) map.getSpielMap()[i][j][1]).getImageLeft(), x, y, res, g);
                            break;
                    }

                }
            }
        }
        g.setColor(Color.yellow);
        g.setFont(new Font("impact", Font.BOLD, 30));
        g.drawString("Score: " + Game.calcPlayerScore(), 10, 30);
    }

    private void paintImage(BufferedImage image, double x, double y, int res, Graphics g) {
        g.drawImage(scaleImage(image, res / 2), (int) ((x * res)) + (res / 2) - res / 4, (int) ((y * res) + (res / 2) - res / 4), null);
    }


    @Override
    public void keyTyped(KeyEvent keyEvent) {

    }

    @Override
    public void keyPressed(KeyEvent keyEvent) {
        int keyCode = keyEvent.getKeyCode();
        switch (keyCode) {

            case KeyEvent.VK_UP:
                Logger.log("KEY-UP");
                if (Player.direction == Direction.NORTH) break;
                Player.lastPressedDirection = Direction.NORTH;
                break;
            case KeyEvent.VK_DOWN:
                Logger.log("KEY-DOWN");
                if (Player.direction == Direction.SOUTH) break;
                Player.lastPressedDirection = Direction.SOUTH;
                break;
            case KeyEvent.VK_LEFT:
                Logger.log("KEY-LEFT");
                if (Player.direction == Direction.WEST) break;
                Player.lastPressedDirection = Direction.WEST;
                break;
            case KeyEvent.VK_RIGHT:
                Logger.log("KEY-RIGHT");
                if (Player.direction == Direction.EAST) break;
                Player.lastPressedDirection = Direction.EAST;
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent keyEvent) {

    }
}