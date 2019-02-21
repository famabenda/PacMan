package game.enums;


/**
 * This class contains all the possible Directions in which the player or the ghosts can face
 */
public enum Direction {
    EAST, NORTH, SOUTH, WEST, NONE;


    /**
     * inverts the given direction e.g.
     * input = <b>Direction.NORTH<b/> => <b>output = Direction.SOUTH</b>
     *
     * @param d the direction which should be inverted
     * @return the inverted direction from the input-direction
     */
    public static Direction invertDirection(Direction d) {
        switch (d) {
            case EAST:
                return WEST;
            case SOUTH:
                return NORTH;
            case WEST:
                return EAST;
            case NORTH:
                return SOUTH;
            default:
                return Direction.NONE;
        }
    }

}
