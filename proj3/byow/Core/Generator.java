package byow.Core;

import byow.TileEngine.TETile;
import byow.TileEngine.Tileset;

public class Generator {

    /**
     * Generates a random world given a string input
     * */
    public static TETile[][] generate(String input) {
        TETile[][] world = new TETile[100][100];
        world[0][0] = Tileset.WALL;
        return world;
    }

    private void generateHallways() {

    }

    private void generateRooms() {

    }

    private void fill() {

    }

}
