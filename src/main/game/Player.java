package game;

import game.Enums.Direction;
import lombok.Data;

@Data
public class Player extends SpielElement implements Movable {
    private Direction direction;
    @Override
    public void move(Direction direction) {

    }

    public Player(int xPos, int yPos, Direction direction){
        super(xPos,yPos);
        setDirection(direction);
    }
}
