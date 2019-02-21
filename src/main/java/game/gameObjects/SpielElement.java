package game.gameObjects;

import lombok.Data;

@Data
/**
 * the abstract class which gets specified by all objects that can exist on the map
 */
public abstract class SpielElement {
    protected int xPosition;
    protected int yPosition;


    public SpielElement(int xPos, int yPos){
       setXPosition(xPos);
       setYPosition(yPos);
    }
}
