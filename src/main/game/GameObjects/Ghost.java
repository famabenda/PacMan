package game.GameObjects;

import game.Enums.Direction;
import game.GameObjects.Movable;
import game.GameObjects.SpielElement;
import lombok.Data;

@Data
public class Ghost extends SpielElement implements Movable {
    private Direction direction;
    private double speed;

    public Ghost(int xPos, int yPos, Direction direction) {
        super(xPos, yPos);
        setDirection(direction);
    }

    @Override
    public SpielElement[][] move(SpielElement[][] spielMap) {
        return null;
    }
}
