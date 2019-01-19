package game;

import lombok.Data;

@Data
public abstract class SpielElement {
    private int xPosition;
    private int yPosition;


    public SpielElement(int xPos, int yPos){
       setXPosition(xPos);
       setYPosition(yPos);
    }
}
