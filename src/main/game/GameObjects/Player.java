package game.GameObjects;

import game.Enums.Direction;
import lombok.Data;

import java.util.ArrayList;

@Data
public class Player extends SpielElement implements Movable {
    public static Direction direction;
    public static boolean tranistion = false;
    public static int pacDotCount = 0;
    public static Direction lastPressedDirection = Direction.NONE;
    private double speed = 1;
    private int lives = 3;


    public Player(int xPos, int yPos, Direction direction) {
        super(xPos, yPos);
        this.direction = direction;
    }

    @Override
    public SpielElement[][][] move(SpielElement[][][] spielMap) {
        if (getPossibleDirections(spielMap).contains(lastPressedDirection)) {
            direction = lastPressedDirection;
            return movePlayer(spielMap, lastPressedDirection);
        }
        movePlayer(spielMap, direction);
        return spielMap;
    }

    private ArrayList<Direction> getPossibleDirections(SpielElement[][][] spielMap) {

        ArrayList<Direction> possibleDirections = new ArrayList<>();

        if (!(spielMap[xPosition][yPosition - 1][0] instanceof Wall)) possibleDirections.add(Direction.NORTH);
        if (!(spielMap[xPosition][yPosition + 1][0] instanceof Wall)) possibleDirections.add(Direction.SOUTH);
        if (!(spielMap[xPosition - 1][yPosition][0] instanceof Wall)) possibleDirections.add(Direction.WEST);
        if (!(spielMap[xPosition + 1][yPosition][0] instanceof Wall)) possibleDirections.add(Direction.EAST);

        return possibleDirections;

    }

    private SpielElement[][][] movePlayer(SpielElement[][][] spielMap, Direction direction) {
        int spielerX = getXPosition();
        int spielerY = getYPosition();
        int newXPos = 0, newYPos = 0;

        if (direction == Direction.NORTH) {
            newXPos = spielerX;
            newYPos = spielerY - 1;
        } else if (direction == Direction.SOUTH) {
            newXPos = spielerX;
            newYPos = spielerY + 1;
        } else if (direction == Direction.WEST) {
            newXPos = spielerX - 1;
            newYPos = spielerY;
        } else if (direction == Direction.EAST) {
            newXPos = spielerX + 1;
            newYPos = spielerY;
        }
        if (newXPos < 0) newXPos = spielMap.length - 1;
        if (newXPos > spielMap.length - 1) newXPos = 0;

        if (spielMap[newXPos][newYPos][0] instanceof Wall) {
            return spielMap;
        }

        if (spielMap[newXPos][newYPos][0] instanceof PacDot || spielMap[newXPos][newYPos][0] instanceof EmptySpace) {
            spielMap[spielerX][spielerY][0] = new EmptySpace(spielerX, spielerY);
            spielMap[newXPos][newYPos][0] = this;
            setXPosition(newXPos);
            setYPosition(newYPos);
            if (spielMap[newXPos][newYPos][0] instanceof PacDot) {
                pacDotCount++;
            }
        }

        return spielMap;
    }

}
