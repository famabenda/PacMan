package game.GameObjects;

import lombok.Data;

@Data
public abstract class SpielElement {
    protected int xPosition;
    protected int yPosition;


    public SpielElement(int xPos, int yPos){
       setXPosition(xPos);
       setYPosition(yPos);
    }
}
