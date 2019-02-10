package game.gameObjects;

public interface Movable {

    SpielElement[][][] move(SpielElement[][][] spielMap);

    void loadSprites();
}
