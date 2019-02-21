package game.gameObjects;

import game.Game;
import game.GameOrchestrator;
import game.enums.Direction;

import lombok.Data;
import utils.SoundPlayer;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

@Data
/**
 * This is the playable gamobject
 */
public class Player extends SpielElement implements Movable {
    public static Direction direction;
    public static Direction lastPressedDirection = Direction.NONE;
    public static int lives = GameOrchestrator.PLAYER_LIVES;

    private BufferedImage imageDown;
    private BufferedImage imageUp;
    private BufferedImage imageLeft;
    private BufferedImage imageRight;


    public Player(int xPos, int yPos, Direction direction) {
        super(xPos, yPos);
        this.direction = direction;
        try {
            imageDown = ImageIO.read(new File(getClass().getClassLoader().getResource("images/PacMan_MoveDown.gif").getFile()));
            imageUp = ImageIO.read(new File(getClass().getClassLoader().getResource("images/PacMan_MoveUp.gif").getFile()));
            imageLeft = ImageIO.read(new File(getClass().getClassLoader().getResource("images/PacMan_MoveLeft.gif").getFile()));
            imageRight = ImageIO.read(new File(getClass().getClassLoader().getResource("images/PacMan_MoveRight.gif").getFile()));
        } catch (IOException e) {
            e.printStackTrace();
        }
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

    @Override
    public void loadSprites() {

    }

    public static void reset(){
        lives = GameOrchestrator.PLAYER_LIVES;
    }

    private ArrayList<Direction> getPossibleDirections(SpielElement[][][] spielMap) {
        ArrayList<Direction> possibleDirections = new ArrayList<>();
        try {

            if (!(spielMap[xPosition][yPosition - 1][0] instanceof Wall)) possibleDirections.add(Direction.NORTH);
            if (!(spielMap[xPosition][yPosition + 1][0] instanceof Wall)) possibleDirections.add(Direction.SOUTH);
            if (!(spielMap[xPosition - 1][yPosition][0] instanceof Wall)) possibleDirections.add(Direction.WEST);
            if (!(spielMap[xPosition + 1][yPosition][0] instanceof Wall)) possibleDirections.add(Direction.EAST);

            return possibleDirections;
        } catch (IndexOutOfBoundsException ioobe) {
            if (xPosition - 1 < 0 && !(spielMap[spielMap.length - 1][yPosition][0] instanceof Wall))
                possibleDirections.add(Direction.WEST);
            if (xPosition + 1 > spielMap.length - 1 && !(spielMap[0][yPosition][0] instanceof Wall))
                possibleDirections.add(Direction.EAST);
        }
        return possibleDirections;
    }


    /**
     *
     * @param spielMap the current map
     * @param direction the direction in which the player should move
     * @return the map with the moved player
     */
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
            if (spielMap[newXPos][newYPos][0] instanceof PacDot) {
                SoundPlayer.playChompSound();
                Game.collectedPacDots++;
            }
            spielMap[spielerX][spielerY][0] = new EmptySpace(spielerX, spielerY);
            spielMap[newXPos][newYPos][0] = this;
            setXPosition(newXPos);
            setYPosition(newYPos);
        }

        return spielMap;
    }

}
