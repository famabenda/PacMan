package game.GameObjects;

import lombok.Data;

@Data
public class PacDot extends SpielElement {

    public PacDot(int xPos, int yPos) {

        super(xPos, yPos);
    }
}
