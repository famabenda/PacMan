package game.GameObjects;

import game.Enums.Direction;
import game.GameObjects.Movable;
import game.GameObjects.SpielElement;
import gui.MainPanel;
import lombok.Data;
import utils.Logger;

import java.util.ArrayList;

@Data
public class Ghost extends SpielElement implements Movable {
    private Direction direction;
    private double speed = 1;


    public Ghost(int xPos, int yPos, Direction direction) {
        super(xPos, yPos);
        setDirection(direction);
    }


    private ArrayList<Direction> getPossibleDirections(SpielElement[][] spielMap) {

        Direction geistDir = getDirection();
        int ghostX = getXPosition();
        int ghostY = getYPosition();
        int newXPos = 0, newYPos = 0;
        ArrayList<Direction> possibleDirections = new ArrayList<>();

        if (geistDir == Direction.NORTH) {
            newXPos = ghostX;
            newYPos = ghostY - 1;
            if (!(spielMap[newXPos][newYPos] instanceof Wall)) possibleDirections.add(Direction.NORTH);
        } else if (geistDir == Direction.SOUTH) {
            newXPos = ghostX;
            newYPos = ghostY + 1;
            if (!(spielMap[newXPos][newYPos] instanceof Wall)) possibleDirections.add(Direction.SOUTH);
        } else if (geistDir == Direction.WEST) {
            newXPos = ghostX - 1;
            newYPos = ghostY;
            if (!(spielMap[newXPos][newYPos] instanceof Wall)) possibleDirections.add(Direction.WEST);
        } else if (geistDir == Direction.EAST) {
            newXPos = ghostX + 1;
            newYPos = ghostY;
            if (!(spielMap[newXPos][newYPos] instanceof Wall)) possibleDirections.add(Direction.EAST);
        }
        return possibleDirections;

    }

    @Override
    public SpielElement[][] move(SpielElement[][] spielMap) {
        ArrayList<Direction> possibleDirections = getPossibleDirections(spielMap);
        Direction newDirection = Direction.NONE;
        if (possibleDirections.size() > 2 || !possibleDirections.contains(direction)) {
            newDirection = possibleDirections.get((int) (Math.random() * possibleDirections.size()));
        }

        int newXPos = 0;
        int newYPos= 0;

        if (newDirection == Direction.NORTH) {
            newXPos = xPosition;
            newYPos = yPosition - 1;
            if (!(spielMap[newXPos][newYPos] instanceof Wall)) possibleDirections.add(Direction.NORTH);
        } else if (newDirection == Direction.SOUTH) {
            newXPos = xPosition;
            newYPos = yPosition + 1;
            if (!(spielMap[newXPos][newYPos] instanceof Wall)) possibleDirections.add(Direction.SOUTH);
        } else if (newDirection == Direction.WEST) {
            newXPos = xPosition - 1;
            newYPos = yPosition;
            if (!(spielMap[newXPos][newYPos] instanceof Wall)) possibleDirections.add(Direction.WEST);
        } else if (newDirection == Direction.EAST) {
            newXPos = xPosition + 1;
            newYPos = yPosition;
            if (!(spielMap[newXPos][newYPos] instanceof Wall)) possibleDirections.add(Direction.EAST);
        }


        spielMap[newXPos][newYPos] = spielMap[xPosition][yPosition];
        spielMap[xPosition][yPosition] = new PacDot(xPosition,yPosition);


        return spielMap;
//        if (newXPos < 0) newXPos = spielMap.length - 1;
//        if (newXPos > spielMap.length - 1) newXPos = 0;
////        hasWallRigth = spielMap[spielerX + 1][spielerY] instanceof Wall ? true : false;
////        hasWallAbove = spielMap[spielerX][spielerY - 1] instanceof Wall ? true : false;
////        hasWallLeft = spielMap[spielerX - 1][spielerY] instanceof Wall ? true : false;
////        hasWallUnder = spielMap[spielerX][spielerY + 1] instanceof Wall ? true : false;
//
//        if (spielMap[newXPos][newYPos] instanceof Wall) {
//            direction = prevDirection;
//            return spielMap;
//        }
//
//        if (spielMap[newXPos][newYPos] instanceof Ghost) {
//
//        }
//        if (spielMap[newXPos][newYPos] instanceof PacDot || spielMap[newXPos][newYPos] instanceof EmptySpace) {
//            prevDirection = direction;
//            spielMap[spielerX][spielerY] = new EmptySpace(spielerX, spielerY);
//            spielMap[newXPos][newYPos] = this;
//            setXPosition(newXPos);
//            setYPosition(newYPos);
//            if (spielMap[newXPos][newYPos] instanceof PacDot) {
//                pacDotCount++;
//            }
//        }
//        return spielMap;
//
//
//        if (newXPos < 0) newXPos = spielMap.length - 1;
//        if (newXPos > spielMap.length - 1) newXPos = 0;


    }

}
