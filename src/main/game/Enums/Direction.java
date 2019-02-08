package game.Enums;

public enum Direction {
    NORTH, EAST, SOUTH, WEST, NONE;


    public static Direction invertDirection(Direction d){
        switch (d){
            case EAST: return WEST;
            case SOUTH: return NORTH;
            case WEST:return EAST;
            case NORTH: return SOUTH;
            default: return Direction.NONE;
        }
    }

    public boolean equals(Direction direction) {
        if (direction == null) return false;
        if (direction == this) return true;
        if (!(direction instanceof Direction)) return false;
        Direction o =  direction;
        return o.NONE == this.NONE;
    }



}
