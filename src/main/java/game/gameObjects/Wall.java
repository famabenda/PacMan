package game.gameObjects;

/**
 * The Gamelement which represents the boarder for the java.game. The player and the ghosts
 * can not enter this tile
 */
public class Wall extends SpielElement {
    public Wall(int xPos, int yPos) {
        super(xPos, yPos);
    }
}
