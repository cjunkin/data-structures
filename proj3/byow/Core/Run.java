package byow.Core;

import byow.TileEngine.TERenderer;
import byow.TileEngine.TETile;

public class Run {
    private static final int WIDTH = 80;
    private static final int HEIGHT = 40;

    public static void main(String[] args) {
        TERenderer ter = new TERenderer();
        ter.initialize(WIDTH, HEIGHT);
        Engine e = new Engine();
        TETile[][] world = e.interactWithInputString("Ndkjem83jS"); // N43j4df3S, N0j4df3S
        ter.renderFrame(world);
    }
}
