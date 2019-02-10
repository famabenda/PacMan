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
public class Clyde extends Ghost implements Movable {
    public Clyde(int xPos, int yPos, Direction direction) {

        super(xPos, yPos, direction);
        loadSprites();
    }


    @Override
    public void loadSprites() {
        try {
            imageDown = ImageIO.read(new File(getClass().getClassLoader().getResource("images/Clyde_MoveDown.gif").getFile()));
            imageUp = ImageIO.read(new File(getClass().getClassLoader().getResource("images/Clyde_MoveUp.gif").getFile()));
            imageLeft = ImageIO.read(new File(getClass().getClassLoader().getResource("images/Clyde_MoveLeft.gif").getFile()));
            imageRight = ImageIO.read(new File(getClass().getClassLoader().getResource("images/Clyde_MoveRight.gif").getFile()));
        } catch (IOException ioe) {
            Logger.error("Sprite not found for Ghost " + getClass().getName());
            ioe.printStackTrace();
        }
    }


    protected Direction getBestMove(ArrayList<Direction> possibleDirections, SpielElement[][][] spielMap) {
        Player spieler = super.getSpieler(spielMap);


        int spielerX = spieler.getXPosition();
        int spielerY = spieler.getYPosition();

        int xDiff = Math.abs(spielerX - xPosition);
        int yDiff = Math.abs(spielerY - yPosition);


        if (xDiff > yDiff) {
            if (spieler.getXPosition() > this.xPosition && possibleDirections.contains(Direction.EAST))
                return Direction.EAST;
            if (spieler.getXPosition() < this.xPosition && possibleDirections.contains(Direction.WEST))
                return Direction.WEST;
        } else {
            if (spieler.getYPosition() > this.yPosition && possibleDirections.contains(Direction.SOUTH))
                return Direction.SOUTH;
            if (spieler.getYPosition() < this.yPosition && possibleDirections.contains(Direction.NORTH))
                return Direction.NORTH;
        }

        return possibleDirections.get((int) (possibleDirections.size() * Math.random() - 1));
    }


    private int calcDistanceToPlayer(Player spieler) {
        int spielerX = spieler.getXPosition(), spielerY = spieler.getYPosition();
        return (int) Math.sqrt(Math.pow(spielerX - xPosition, 2) + Math.pow(spielerY - yPosition, 2));
    }

}
