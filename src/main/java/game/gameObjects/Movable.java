package game.gameObjects;

public interface Movable {

    SpielElement[][][] move(SpielElement[][][] spielMap);


    /**
     *loads the sprites for each class which implements this interface (Ghost & Player)
     *
     */
    void loadSprites();
}
