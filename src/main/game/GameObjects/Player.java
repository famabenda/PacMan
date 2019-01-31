package game.GameObjects;

import game.Enums.Direction;
import lombok.Data;
import utils.Logger;

@Data
public class Player extends SpielElement implements Movable {
    public static Direction prevDirection;
    public static Direction direction;
    public static boolean tranistion = false;
    public static int pacDotCount = 0;
    private double speed = 1;
    private int lives = 3;

    @Override
    public SpielElement[][] move(SpielElement[][] spielMap) {
        int spielerX = getXPosition();
        int spielerY = getYPosition();
        boolean hasWallRigth, hasWallAbove, hasWallLeft, hasWallUnder;
        Direction spielerDir = Player.direction;
        int newXPos = 0, newYPos = 0;

        Logger.log("Spieler X: " + spielerX + " SpielerY: " + spielerY + " | newX: " + newXPos + " newY: " + newYPos + " x: " + getXPosition() + " y: " + getYPosition());

        if (spielerDir == Direction.NORTH) {
            newXPos = spielerX;
            newYPos = spielerY - 1;
        } else if (spielerDir == Direction.SOUTH) {
            newXPos = spielerX;
            newYPos = spielerY + 1;
        } else if (spielerDir == Direction.WEST) {
            newXPos = spielerX - 1;
            newYPos = spielerY;
        } else if (spielerDir == Direction.EAST) {
            newXPos = spielerX + 1;
            newYPos = spielerY;
        }


        if (newXPos < 0) newXPos = spielMap.length - 1;
        if (newXPos > spielMap.length - 1) newXPos = 0;
//        hasWallRigth = spielMap[spielerX + 1][spielerY] instanceof Wall ? true : false;
//        hasWallAbove = spielMap[spielerX][spielerY - 1] instanceof Wall ? true : false;
//        hasWallLeft = spielMap[spielerX - 1][spielerY] instanceof Wall ? true : false;
//        hasWallUnder = spielMap[spielerX][spielerY + 1] instanceof Wall ? true : false;

        if (spielMap[newXPos][newYPos] instanceof Wall) {
            direction = prevDirection;
            return spielMap;
        }

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
