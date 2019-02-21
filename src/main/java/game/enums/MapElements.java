package game.enums;


/**
 * This class defines the characters which later on will be read an translated in the corosponding
 * GameObject
 */
public enum MapElements {
    PACDOT_SYMBOL("*"),
    WALL_SYMBOL("#"),
    EMPTY_SPACE_SYMBOL(" "),
    USER_SYMBOL("U"),
    INKY_SYMBOL("I"),
    BLINKY_SYMBOL("B"),
    PINKY_SYMBOL("P"),
    CLYDE_SYMBOL("C");

    String mapCode;

    MapElements(String mapCode) {
        this.mapCode = mapCode;
    }

    public String getMapCode() {
        return mapCode;
    }
}
