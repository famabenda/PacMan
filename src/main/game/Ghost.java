package game;

import game.Enums.Direction;
import lombok.Data;

@Data
public class Ghost extends SpielElement implements Movable {
    private Direction direction;
    private double speed;
    public Ghost(int xPos, int yPos, Direction direction){
        super(xPos, yPos);
        setDirection(direction);
    }
    @Override
    public void move(Direction direction) {

    }
}
