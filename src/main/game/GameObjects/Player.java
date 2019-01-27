package game.GameObjects;

import game.Enums.Direction;
import game.Map;
import gui.Gui;
import javafx.scene.input.KeyCode;
import lombok.Data;
import utils.Logger;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

@Data
public class Player extends SpielElement implements Movable {
    private Direction prevDirection;
    public static Direction direction;
    public static boolean tranistion = false;
    public static int pacDotCount = 0;
    private double speed = 1;

    @Override
    public SpielElement[][] move(SpielElement[][] spielMap) {
        int spielerX = getXPosition();
        int spielerY = getYPosition();
        Direction spielerDir = Player.direction;
        int newXPos = 0, newYPos = 0;
        switch (spielerDir) {
            case NORTH:
                newXPos = spielerX;
                newYPos = spielerY - 1;
                break;
            case SOUTH:
                newXPos = spielerX;
                newYPos = spielerY + 1;
                break;
            case WEST:
                newXPos = spielerX - 1;
                newYPos = spielerY;
                break;
            case EAST:
                newXPos = spielerX + 1;
                newYPos = spielerY;
        }
        Logger.log("Spieler X: " + spielerX + " SpielerY: " + spielerY + " | newX: " + newXPos + " newY: " + newYPos + " x: " + getXPosition() + " y: " + getYPosition());


        if (newXPos < 0) newXPos = spielMap.length - 1;
        if (newXPos > spielMap.length - 1) newXPos = 0;
        if (spielMap[newXPos][newYPos] instanceof Wall) return spielMap;

        if (spielMap[newXPos][newYPos] instanceof Ghost) {

        }
        if (spielMap[newXPos][newYPos] instanceof PacDot || spielMap[newXPos][newYPos] instanceof EmptySpace) {
        prevDirection = direction;
            spielMap[spielerX][spielerY] = new EmptySpace(spielerX, spielerY);
            spielMap[newXPos][newYPos] = this;
            setXPosition(newXPos);
            setYPosition(newYPos);
            if (spielMap[newXPos][newYPos] instanceof PacDot) {
                pacDotCount++;
            }
        }
        return spielMap;
    }


    public Player(int xPos, int yPos, Direction direction) {
        super(xPos, yPos);
        this.direction = direction;
    }

}
