package game.enums;

public enum Direction {
    EAST, NORTH, SOUTH, WEST, NONE;


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
