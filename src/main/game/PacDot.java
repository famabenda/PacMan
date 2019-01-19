package game;

import lombok.Data;

@Data
public class PacDot extends SpielElement {
    private boolean eaten = false;

    public PacDot(int xPos, int yPos){
        super(xPos,yPos);
    }
}
