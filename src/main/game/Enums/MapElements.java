package game.Enums;

public enum MapElements {
    PACDOT_SYMBOL("*"), WALL_SYMBOL("#"),EMPTY_SPACE_SYMBOL(" "),PLAYER_SYMBOL("P"),GHOST_SYMBOL("G");

    String mapCode;
    MapElements(String mapCode){
        this.mapCode = mapCode;
    }

    public String getMapCode() {
        return mapCode;
    }
}
