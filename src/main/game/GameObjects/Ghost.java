package game.GameObjects;

import game.Enums.Direction;
import game.GameObjects.Movable;
import game.GameObjects.SpielElement;
import game.Map;
import gui.MainPanel;
import jdk.nashorn.internal.ir.annotations.Ignore;
import lombok.Data;
import utils.Logger;

import java.util.ArrayList;

@Data
public class Ghost extends SpielElement implements Movable {
    private Direction direction;
    private double speed = 1;
    private boolean standingOnPacDot = false;


    public Ghost(int xPos, int yPos, Direction direction) {
        super(xPos, yPos);
        setDirection(direction);
    }


    private ArrayList<Direction> getPossibleDirections(SpielElement[][] spielMap) {

        int ghostX = getXPosition();
        int ghostY = getYPosition();
        ArrayList<Direction> possibleDirections = new ArrayList<>();
        Logger.debug("x: " + xPosition + " y: " + yPosition);


        if (!Map.isWallOrGhost(ghostX, ghostY - 1, spielMap)) possibleDirections.add(Direction.NORTH);
        if (!Map.isWallOrGhost(ghostX, ghostY + 1, spielMap)) possibleDirections.add(Direction.SOUTH);
        if (!Map.isWallOrGhost(ghostX - 1, ghostY, spielMap)) possibleDirections.add(Direction.WEST);
        if (!Map.isWallOrGhost(ghostX + 1, ghostY, spielMap)) possibleDirections.add(Direction.EAST);

        Logger.debug("possible directions: " + possibleDirections.size() + " " + possibleDirections);

        return possibleDirections;

    }

    @Override
    public SpielElement[][] move(SpielElement[][] spielMap) {
        ArrayList<Direction> possibleDirections = getPossibleDirections(spielMap);
        if (possibleDirections.size() == 0) return spielMap;
        Direction newDirection = Direction.NONE;
        if (possibleDirections.size() > 1) {
            Direction originDirection = Direction.invertDirection(direction);
            for (int i = 0; i < possibleDirections.size(); i++) {
                if (possibleDirections.get(i) == originDirection) {
                    possibleDirections.remove(i);
                    Logger.debug("After removement: " + possibleDirections.size() + " " + possibleDirections);
                    break;
                }
            }
            int random = (int) (Math.random() * possibleDirections.size());
            Logger.debug("Random: " + random);
            newDirection = possibleDirections.get(random);
        } else newDirection = possibleDirections.get(0);

        Logger.log("New Direction = " + newDirection);
        if (newDirection == Direction.NORTH) {
            standingOnPacDot = standOnPacDot(direction, spielMap, xPosition, yPosition - 1);
            return moveNorth(spielMap);
        } else if (newDirection == Direction.SOUTH) {
            standingOnPacDot = standOnPacDot(direction, spielMap, xPosition, yPosition + 1);
            return moveSouth(spielMap);
        } else if (newDirection == Direction.WEST) {
            standingOnPacDot = standOnPacDot(direction, spielMap, xPosition - 1, yPosition);
            return moveWest(spielMap);
        } else if (newDirection == Direction.EAST) {
            standingOnPacDot = standOnPacDot(direction, spielMap, xPosition + 1, yPosition);
            return moveEast(spielMap);
        }

        return spielMap;
    }


    private boolean standOnPacDot(Direction newDirection, SpielElement[][] spielMap, int newXPos, int newYPos) {
        return spielMap[newXPos][newYPos] instanceof PacDot;
    }


    private SpielElement[][] moveNorth(SpielElement[][] spielMap) {
        spielMap[xPosition][yPosition - 1] = spielMap[xPosition][yPosition];
        if (standingOnPacDot) spielMap[xPosition][yPosition] = new PacDot(xPosition, yPosition);
        else spielMap[xPosition][yPosition] = new EmptySpace(xPosition, yPosition);
        yPosition--;
        return spielMap;
    }

    private SpielElement[][] moveWest(SpielElement[][] spielMap) {
        if (xPosition - 1 < 0) xPosition = spielMap.length - 1;
        spielMap[xPosition - 1][yPosition] = spielMap[xPosition][yPosition];
        if (standingOnPacDot) spielMap[xPosition][yPosition] = new PacDot(xPosition, yPosition);
        else spielMap[xPosition][yPosition] = new EmptySpace(xPosition, yPosition);
        xPosition--;
        return spielMap;
    }

    private SpielElement[][] moveEast(SpielElement[][] spielMap) {
        if (xPosition + 1 > spielMap.length - 1) xPosition = 0;
        spielMap[xPosition + 1][yPosition] = spielMap[xPosition][yPosition];
        if (standingOnPacDot) spielMap[xPosition][yPosition] = new PacDot(xPosition, yPosition);
        else spielMap[xPosition][yPosition] = new EmptySpace(xPosition, yPosition);
        xPosition++;
        return spielMap;
    }

    private SpielElement[][] moveSouth(SpielElement[][] spielMap) {
        spielMap[xPosition][yPosition + 1] = spielMap[xPosition][yPosition];
        if (standingOnPacDot) spielMap[xPosition][yPosition] = new PacDot(xPosition, yPosition);
        else spielMap[xPosition][yPosition] = new EmptySpace(xPosition, yPosition);
        yPosition++;
        return spielMap;
    }

}
