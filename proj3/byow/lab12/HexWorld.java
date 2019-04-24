package byow.lab12;
import org.junit.Test;
import static org.junit.Assert.*;

import byow.TileEngine.TERenderer;
import byow.TileEngine.TETile;
import byow.TileEngine.Tileset;

import javax.swing.text.Position;
import java.util.Random;

/**
 * Draws a world consisting of hexagonal regions.
 */
public class HexWorld {

    /**
     * @source Lab 12 - Hug's code
     * */
    public static void addHexagon(TETile[][] world, Position p, int s, TETile t) {
        if (s < 2) {
            throw new IllegalArgumentException("Hexagon must be at least size 2.");
        }
        TERenderer ter = new TERenderer();
        ter.initialize(100, 100);

        for (int i = 0; i < s; i++) {
            world[s][s] = t;
            s++;
        }

        ter.renderFrame(world);
    }

    public static void main(String[] args) {
    }

}
