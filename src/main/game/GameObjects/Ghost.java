package game.GameObjects;

import game.Enums.Direction;
import game.Enums.GhostBehaviour;
import game.GameObjects.Movable;
import game.GameObjects.SpielElement;
import game.Map;
import gui.MainPanel;
import jdk.nashorn.internal.ir.annotations.Ignore;
import lombok.Data;
import sun.security.provider.ConfigFile;
import utils.Logger;

import java.util.ArrayList;

@Data
public class Ghost extends SpielElement implements Movable {
    private Direction direction;
    private double speed = 1;
    private boolean standingOnPacDot = false;
    private GhostBehaviour behaviour;

    public Ghost(int xPos, int yPos, Direction direction, GhostBehaviour behaviour) {
        super(xPos, yPos);
        setDirection(direction);
        setBehaviour(behaviour);
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

    public SpielElement[][][] moveFollow(SpielElement[][][] spielMap) {
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
            newDirection = getBestMove(possibleDirections, spielMap);

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

    private Direction getBestMove(ArrayList<Direction> possibleDirections, SpielElement[][][] spielMap) {
        Player spieler = getSpieler(spielMap);

        if (spieler.getYPosition() > this.yPosition && possibleDirections.contains(Direction.SOUTH))
            return Direction.SOUTH;
        if (spieler.getYPosition() < this.yPosition && possibleDirections.contains(Direction.NORTH))
            return Direction.NORTH;
        if (spieler.getXPosition() > this.xPosition && possibleDirections.contains(Direction.EAST))
            return Direction.EAST;
        if (spieler.getXPosition() < this.xPosition && possibleDirections.contains(Direction.WEST))
            return Direction.WEST;

        return possibleDirections.get((int) (possibleDirections.size() * Math.random() - 1));

    }


    public Player getSpieler(SpielElement[][][] spielMap) {

        for (SpielElement[][] ebene : spielMap) {
            for (SpielElement[] reihe : ebene) {
                for (SpielElement element : reihe) {
                    if (element instanceof Player) return (Player) element;
                }
            }
        }
        Logger.error("No player-object has been found on the map");
        return null;

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
