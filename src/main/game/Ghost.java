package game;

import game.Enums.Direction;
import lombok.Data;

@Data
public class Ghost extends SpielElement implements Movable {
    @Override
    public void move(Direction direction) {

    }
}
