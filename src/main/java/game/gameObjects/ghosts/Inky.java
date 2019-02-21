package game.gameObjects.ghosts;

import game.enums.Direction;
import game.gameObjects.Movable;
import game.gameObjects.Player;
import game.gameObjects.SpielElement;
import lombok.Data;
import utils.Logger;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

@Data
public class Inky extends Ghost implements Movable {


    public Inky(int xPos, int yPos, Direction direction) {

        super(xPos, yPos, direction);
        loadSprites();
    }


    @Override
    public void loadSprites() {
        try {
            imageDown = ImageIO.read(new File(getClass().getClassLoader().getResource("images/Inky_MoveDown.gif").getFile()));
            imageUp = ImageIO.read(new File(getClass().getClassLoader().getResource("images/Inky_MoveUp.gif").getFile()));
            imageLeft = ImageIO.read(new File(getClass().getClassLoader().getResource("images/Inky_MoveLeft.gif").getFile()));
            imageRight = ImageIO.read(new File(getClass().getClassLoader().getResource("images/Inky_MoveRight.gif").getFile()));
        } catch (IOException ioe) {
            Logger.error("Sprite not found for Ghost " + getClass().getName());
            ioe.printStackTrace();
        }
    }

    @Override
    protected Direction getBestMove(ArrayList<Direction> possibleDirections, SpielElement[][][] spielMap) {
        Player spieler = super.getSpieler(spielMap);
        Direction playerDirection = Player.direction;
        int newXposTwoTilesAway = spieler.getXPosition(), newYposFourTilesAway = spieler.getYPosition();
        switch (playerDirection) {
            case WEST:
                newXposTwoTilesAway -= 2;
                break;
            case EAST:
                newXposTwoTilesAway += 2;
                break;
            case NORTH:
                newYposFourTilesAway -= 2;
                break;
            case SOUTH:
                newYposFourTilesAway += 2;
                break;
        }
        Blinky blinky = findBlinkyOnMap(spielMap);
        int xVector = spieler.getXPosition() - blinky.getXPosition();
        int yVector = spieler.getYPosition() - blinky.getYPosition();

        int targetX = newXposTwoTilesAway + xVector;
        int targetY = newYposFourTilesAway + yVector;


        int spielerX = spieler.getXPosition();
        int spielerY = spieler.getYPosition();

        int xDiff = Math.abs(spielerX - xPosition);
        int yDiff = Math.abs(spielerY - yPosition);


        if (xDiff > yDiff) {
            if (targetX > this.xPosition && possibleDirections.contains(Direction.EAST))
                return Direction.EAST;
            if (targetX < this.xPosition && possibleDirections.contains(Direction.WEST))
                return Direction.WEST;
        } else {
            if (targetY > this.yPosition && possibleDirections.contains(Direction.SOUTH))
                return Direction.SOUTH;
            if (targetY < this.yPosition && possibleDirections.contains(Direction.NORTH))
                return Direction.NORTH;
        }
        return possibleDirections.get((int) (possibleDirections.size() * Math.random() - 1));
    }


    /**
     *
     * @param spielMap
     * @return
     */
    private Blinky findBlinkyOnMap(SpielElement[][][] spielMap) {
        for (SpielElement[][] ebene : spielMap) {
            for (SpielElement[] reihe : ebene) {
                if (reihe[1] instanceof Blinky) {
                    return (Blinky) reihe[1];
                }
            }
        }
        Logger.error("Blinky could not be found on the map -> Inky targetTile can not be calculated");
        return null;
    }
}
