package game.gameObjects.ghosts;

import game.Map;
import game.enums.Direction;
import game.gameObjects.Movable;
import game.gameObjects.Player;
import game.gameObjects.SpielElement;
import lombok.Data;
import utils.Logger;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

@Data
public abstract class Ghost extends SpielElement implements Movable {
    protected Direction direction;
    protected BufferedImage imageDown;
    protected BufferedImage imageUp;
    protected BufferedImage imageLeft;
    protected BufferedImage imageRight;
    private double speed = 1;

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


    protected Direction getBestMove(ArrayList<Direction> possibleDirections, SpielElement[][][] spielMap) {return null;}


    protected Player getSpieler(SpielElement[][][] spielMap) {

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

    protected SpielElement[][][] moveNorth(SpielElement[][][] spielMap) {

        spielMap[xPosition][yPosition - 1][1] = this;
        spielMap[xPosition][yPosition][1] = null;
        direction = Direction.NORTH;
        yPosition--;
        return spielMap;
    }

    protected SpielElement[][][] moveWest(SpielElement[][][] spielMap) {

        spielMap[xPosition - 1][yPosition][1] = this;
        spielMap[xPosition][yPosition][1] = null;
        direction = Direction.WEST;
        xPosition--;
        return spielMap;
    }

    protected SpielElement[][][] moveEast(SpielElement[][][] spielMap) {

        spielMap[xPosition + 1][yPosition][1] = this;
        spielMap[xPosition][yPosition][1] = null;
        direction = Direction.EAST;
        xPosition++;
        return spielMap;
    }

    protected SpielElement[][][] moveSouth(SpielElement[][][] spielMap) {

        spielMap[xPosition][yPosition + 1][1] = this;
        spielMap[xPosition][yPosition][1] = null;
        direction = Direction.SOUTH;
        yPosition++;
        return spielMap;
    }


    protected ArrayList<Direction> getPossibleDirections(SpielElement[][][] spielMap) {

        ArrayList<Direction> possibleDirections = new ArrayList<>();
        Logger.debug("x: " + xPosition + " y: " + yPosition);
        if (!Map.isWallOrGhost(xPosition, yPosition - 1, spielMap)) possibleDirections.add(Direction.NORTH);
        if (!Map.isWallOrGhost(xPosition, yPosition + 1, spielMap)) possibleDirections.add(Direction.SOUTH);
        if (!Map.isWallOrGhost(xPosition - 1, yPosition, spielMap)) possibleDirections.add(Direction.WEST);
        if (!Map.isWallOrGhost(xPosition + 1, yPosition, spielMap)) possibleDirections.add(Direction.EAST);


        return possibleDirections;

    }

    public BufferedImage getImageDown() {
        return imageDown;
    }

    public BufferedImage getImageUp() {
        return imageUp;
    }

    public BufferedImage getImageLeft() {

        return imageLeft;
    }

    public BufferedImage getImageRight() {
        return imageRight;
    }


}
