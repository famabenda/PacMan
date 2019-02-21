package game.gameObjects;

import lombok.Data;

@Data
/**
 * This is the collectable item which the player has to collect in order to win the java.game and
 * gain java.score
 */
public class PacDot extends SpielElement {

    public PacDot(int xPos, int yPos) {

        super(xPos, yPos);
    }
}
