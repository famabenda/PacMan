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


    @Override
    public SpielElement[][][] move(SpielElement[][][] spielMap) {
        ArrayList<Direction> possibleDirections = getPossibleDirections(spielMap);
        if (possibleDirections.size() == 0) return spielMap;
        Direction newDirection = Direction.NONE;
        if (possibleDirections.size() > 1) {
            Direction originDirection = Direction.invertDirection(direction);
            for (int i = 0; i < possibleDirections.size(); i++) {
                if (possibleDirections.get(i) == originDirection) {
                    possibleDirections.remove(i);
                    break;
                }
            }
            int random = (int) (Math.random() * possibleDirections.size());
            newDirection = possibleDirections.get(random);
        } else newDirection = possibleDirections.get(0);

        switch (newDirection) {
            case NORTH:
                return moveNorth(spielMap);
            case WEST:
                return moveWest(spielMap);
            case SOUTH:
                return moveSouth(spielMap);
            case EAST:
                return moveEast(spielMap);
        }

            return spielMap;
    }

    private SpielElement[][][] moveNorth(SpielElement[][][] spielMap) {

        spielMap[xPosition][yPosition - 1][1] = this;
        spielMap[xPosition][yPosition][1] = null;
        direction = Direction.NORTH;
        yPosition--;
        return spielMap;
    }

    private SpielElement[][][] moveWest(SpielElement[][][] spielMap) {

        spielMap[xPosition - 1][yPosition][1] = this;
        spielMap[xPosition][yPosition][1] = null;
        direction = Direction.WEST;
        xPosition--;
        return spielMap;
    }

    private SpielElement[][][] moveEast(SpielElement[][][] spielMap) {

        spielMap[xPosition + 1][yPosition][1] = this;
        spielMap[xPosition][yPosition][1] = null;
        direction = Direction.EAST;
        xPosition++;
        return spielMap;
    }

    private SpielElement[][][] moveSouth(SpielElement[][][] spielMap) {

        spielMap[xPosition][yPosition + 1][1] = this;
        spielMap[xPosition][yPosition][1] = null;
        direction = Direction.SOUTH;
        yPosition++;
        return spielMap;
    }


    private ArrayList<Direction> getPossibleDirections(SpielElement[][][] spielMap) {

        ArrayList<Direction> possibleDirections = new ArrayList<>();
        Logger.debug("x: " + xPosition + " y: " + yPosition);

        if (!Map.isWallOrGhost(xPosition, yPosition - 1, spielMap)) possibleDirections.add(Direction.NORTH);
        if (!Map.isWallOrGhost(xPosition, yPosition + 1, spielMap)) possibleDirections.add(Direction.SOUTH);
        if (!Map.isWallOrGhost(xPosition - 1, yPosition, spielMap)) possibleDirections.add(Direction.WEST);
        if (!Map.isWallOrGhost(xPosition + 1, yPosition, spielMap)) possibleDirections.add(Direction.EAST);

        return possibleDirections;

    }



}
