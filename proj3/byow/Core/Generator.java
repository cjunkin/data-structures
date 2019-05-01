package byow.Core;

import byow.TileEngine.TETile;
import byow.TileEngine.Tileset;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class Generator {
    private TETile[][] world;
    private Point dimensions; // keeps track of size of map: x is width and y is height
    private Point origin; // origin of main hallway
    private int size;
    private long seed;

//    private ArrayList<Room> rooms = new ArrayList<>();

    /** Given a width and a height, a new, empty TETile map is generated. */
    public Generator(int width, int height) {
        world = new TETile[width][height];
        dimensions = new Point(width, height);
    }

    /** Method that starts the main hallway generation process by calling mainGenerator. Determines
     * starting coordinates. */
    public TETile[][] generate(String input, int rooms) {
        seed = Math.abs(input.hashCode());
        origin = startingPoint(seed);
        int x = origin.x; // flag
        int y = origin.y; // flag
        world[x][y] = Tileset.FLOWER; // initializing starting tile of the map

        System.out.println("initial x is: " + x);
        System.out.println("initial y is: " + y);

        mainGenerator(x, y, seed);
        /*randomlyGenRooms(seed, rooms);*/
        fill(); // fills in rest of tiles that might still be null
        return world;
    }

    /** Determines starting coordinates and prevents starting tile from spawning
     * on the edge.
     * @return a Point of the coordinates of the starting tile
     * */
    private Point startingPoint(long seed) {
        int x = (int) seed % dimensions.x; // x dimension of map
        int y = (int) seed % dimensions.y; // y dimension of map

        if (x - 1 == 0) {
            x += 1;
        } else if (x + 1 >= dimensions.x) {
            x -= 1;
        } else if (y - 1 <= 0) {
            y += 1;
        } else if (y + 1 >= dimensions.y) {
            y -= 1;
        }

        return new Point(x, y);
    }

    private Point determineDirection(int direction) {
        if (direction == 0) {
            return new Point(0, 1);
        } else if (direction == 1) {
            return new Point(1, 0);
        } else if (direction == 2) {
            return new Point(0, -1);
        } else if (direction == 3) {
            return new Point(-1, 0);
        }
        throw new IndexOutOfBoundsException("determineDirection: there are only four directions");
    }

    private int oppositeDirection(int dir) {
        if (dir == 0) {
            return 2;
        } else if (dir == 1) {
            return 3;
        } else if (dir == 2) {
            return 0;
        } else if (dir == 3) {
            return 1;
        }
        throw new IndexOutOfBoundsException("oppositeDirection: only four directions");
    }

    /** Fills any null tiles with "nothing" tiles so that unused tiles aren't null at the end of generating
     * this TETile map.
     * */
    private void fill() {
        for (int x = 0; x < dimensions.x; x++) {
            for (int y = 0; y < dimensions.y; y++) {
                if (world[x][y] == null) {
                    world[x][y] = Tileset.NOTHING;
                }
            }
        }
    }

    /*private double load() {
        return null;
    }*/

    public Point origin() {
        return origin;
    }

    /* Main generation methods. Constructs the main hallway that other hallways and rooms would branch off of. Not self-contained.
* mainGenerator() is the head of the stack of method calls. mainGenerator() continuously calls generatePath()
* (which actually calls the methods that do most of the work).
* generatePath() calls newTile(), checkNeighbors(), and buildWalls(). */

    /** Main hallway generation method. Starts generating floors and walls until it hits
     * the load factor or has nowhere else to go. */
    private void mainGenerator(int x, int y, long seed) {
        Random randomise = new Random(seed);
        Point track = new Point(x, y);
        ArrayList<Integer> restrict = new ArrayList<>();

        int direction = chooseDirection(track.x, track.y, randomise, restrict);
        restrict.add(oppositeDirection(direction));
        track = generatePath(track.x, track.y, direction, randomise, true);

        int i = 1;
        while (size < 150 /* flag: some kind of load factor*/) {
            direction = chooseDirection(track.x, track.y, randomise, restrict);
            track = generatePath(track.x, track.y, direction, randomise, true);
            if (track == null) {
                break;
            }
/*            if (*//*(RandomUtils.uniform(randomise, 5) == 1) && *//* side && size > 8) {
                generateSide(x, y, direction, randomise);
            }*/
            /* chance of side hallway */
            /* need to replace wall that's there? */
            i++;
            System.out.println(i);
        }
    }

    // need something to keep the hallway from getting trapped?
    private Point generatePath(int x, int y, int direction, Random randomise, boolean side) {
        Point delta = determineDirection(direction);
        Point newPoint = new Point(x + delta.x, y + delta.y);

        if (world[newPoint.x][newPoint.y] == null) {
            newPoint = newTile(x, y, newPoint, direction, side);
        } else if (checkNeighbors(x, y)) { // checks to see if tiles in all four direction is filled
            return null;
        }
        else { // calls itself again if no suitable direction was generated
            ArrayList<Integer> l = new ArrayList<>();
            l.add(direction);
            direction = chooseDirection(x, y, randomise, l);
            newPoint = generatePath(x, y, direction, randomise, side);
            return newPoint;
        }

        System.out.println(x + " " + y);
        System.out.println("direction: " + direction);
        buildWalls(x, y, direction);
        return newPoint;
    }

    /** Chooses a random direction given an origin point. Prevents tiles from being
     * constructed on the edge of the map. Indexes on the edge of the map are left untouched
     * to make generation simpler (checkNeighbors() errors if given coordinate is on edge of map).
     * Also makes sure that directions that are chosen towards the hallway is reversed to make it
     * go the opposite way.
     * */
    // might want to make it so that if tile is on the edge of map, make it go opposite way?
    private int chooseDirection(int x, int y, Random r, ArrayList<Integer> prevent) {
        ArrayList<Integer> directions = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            if (!prevent.contains(i)) {
                directions.add(i);
            }
        }
        if (x - 2 <= 0) {
            directions.remove((Object) 3);
            prevent.add(3);
        }
        if (x + 2 >= dimensions.x) {
            directions.remove((Object) 1);
            prevent.add(1);
        }
        if (y - 2 <= 0) {
            directions.remove((Object) 2);
            prevent.add(2);
        }
        if (y + 2 >= dimensions.y) {
            directions.remove((Object) 0);
            prevent.add(0);
        }
        int direction = directions.get(RandomUtils.uniform(r, directions.size()));
        direction = straighten(x, y, direction, prevent);
        return direction;
    }

    /** When the direction chosen is another floor tile (in most cases the tile it came from), the
     * opposite direction is chosen. Implemented so that hallways are more straight than snaky (i.e. greater
     * chance of forward direction being chosen).
     * @param prevent: direction that would lead to tile being built on edge of map
     * */
    private int straighten(int x, int y, int direction, ArrayList<Integer> prevent) {
        TETile floor = Tileset.FLOOR;

        if ((direction == 0) && (world[x][y + 1] == floor) && (!prevent.contains(2))) {
            return 2;
        } else if (direction == 1 && world[x + 1][y] == floor && !prevent.contains(3)) {
            return 3;
        } else if (direction == 2 && world[x][y - 1] == floor && !prevent.contains(0)) {
            return 0;
        } else if (direction == 3 && world[x - 1][y] == floor && !prevent.contains(1)) {
            return 1;
        }
        return direction;
    }

    /** Generates the next tile in the direction specified. There are no walls around
     * this new tile yet. */
    private Point newTile(int x, int y, Point newPoint, int direction, boolean side) {
        world[newPoint.x][newPoint.y] = Tileset.FLOOR;
        size++;
        // prevent getting trapped?
        Random randomise = new Random(123); //flag: replace
        if (/*(RandomUtils.uniform(randomise, 5) == 1) && */ side && size > 8) {
            generateSide(x, y, direction, randomise);
        } //flag: stackoverflow!
        // new room chance method here
        return new Point(newPoint.x, newPoint.y);
    }

/* This block of methods are used by generatePath() */

    /** Checks to see if neighboring tiles to the north, south, east, and west are all full. */
    private boolean checkNeighbors(int x, int y) {
        return (isNotNull(x, y + 1) && isNotNull(x + 1, y) && isNotNull(x, y - 1) && isNotNull(x - 1, y));
    }

    /**
     * @return true if not null, false if null
     * */
    private boolean isNotNull(int x, int y) {
        return world[x][y] != null;
    }

    private void trapPrevention(int x, int y) {
        // walls created 2 or 3 steps before already block in the hallway?
        x -= 1;
        int yt = y + 1;
        for (int xi = 0; xi < 3; xi++, x += 1) {
            for (int yi = 0; yi < 3; yi++, yt += 1) {
            }
        }
    }

/* Functions that construct walls. Self-contained and calls no other methods outside this block */

    /** Builds walls around given tile.
     * @from called by chooseDirection()
     * */
    private void buildWalls(int x, int y, int direction) {
        if ((direction == 0) || (direction == 2)) {
            verticalPathWalls(x, y, direction);
        } else {
            horizontalPathWalls(x, y, direction);
        }
    }

    /** Constructs walls around a floor tile. Walls are constructed around a path that's going
     * vertical. Wall construction varies when going up vs going down.
     * If going up (direction 0), tries to build walls like this (where x's are the walls
     * built and o are the tiles):
     *
     *                     o
     *                  x  o  x
     *                  x  x  x
     *
     * @from buildWalls()
     * */
    private void verticalPathWalls(int x, int y, int direction) {
        int xpntr = x - 1;
        int ypntr;
        for (int xi = 0; xi < 3; xi++, xpntr += 1) {
            if (direction == 0) {
                ypntr = y;
            } else {
                ypntr = y + 1;
            }
            for (int yi = 0; yi < 2; yi++, ypntr -= 1) {
                if (world[xpntr][ypntr] == null) {
                    world[xpntr][ypntr] = Tileset.WALL;
                }
            }
        }
    }

    /** Like verticalPathWalls() but constructs walls based on horizontal direction.
     * @from buildWalls()
     * */
    private void horizontalPathWalls(int x, int y, int direction) {
        int ypntr = y + 1;
        int xpntr;
        if (direction == 1) {
            xpntr = x - 1;
        } else {
            xpntr = x;
        }
        for (int xi = 0; xi < 2; xi++, xpntr += 1) {
            for (int yi = 0; yi < 3; yi++, ypntr -= 1) {
                if (world[xpntr][ypntr] == null) {
                    world[xpntr][ypntr] = Tileset.WALL;
                }
            }
            ypntr = y + 1;
        }
    }

/* Side hallway section. Not self-contained - calls functions like newTile() from the main hallway block. */

    /** Main function to construct side hallways */
    private void generateSide(int x, int y, int mainHallwayDirection, Random r) {
        int direction = chooseSideDirection(x, y, mainHallwayDirection, r);
        int prevent = oppositeDirection(direction);
        Point pntr = generateDoor(x, y, direction, r);
        boolean side = false;
        for (int i = 0; i < 50 /*flag: load factor */; i++) {
            if (pntr == null) {
                break;
            }
  /*          if (i > 15) {
                side = true;
            }*/
            direction = chooseSideDirection(pntr.x, pntr.y, prevent, r);
            pntr = generatePath(pntr.x, pntr.y, direction, r, side);
        }
    }

    /** Determines the best direction for the side hallway to branch off to.
     * @return an integer value that indicates direction
     * */
    private int chooseSideDirection(int x, int y, int prevent, Random r) {
        ArrayList<Integer> l = new ArrayList<>();
        l.add(prevent);
        return chooseDirection(x, y ,r, l);
    }

    /** Generates the floor that would act as a "door" to the side of the main hallway. After that tile,
     * the hallway is generated normally.
     * */
    private Point generateDoor(int x, int y, int direction, Random r) {
        Point delta = determineDirection(direction);
        Point newPoint = sideDoorTile(x, y, delta);
        if (newPoint == null || checkNeighbors(newPoint.x, newPoint.y)) { // checks to see if tiles in all four direction is filled
            return null;
        }
        buildSideWalls(newPoint.x - delta.x, newPoint.y - delta.y, direction);
        return newPoint;
    }

    private Point sideDoorTile(int x, int y, Point direction) {
        for (int i = 0; i < 2; i++) {
            x += direction.x;
            y += direction.y;
            if (/*world[x][y] == Tileset.WALL || */world[x][y] == null) {
                world[x][y] = Tileset.FLOOR;
            } else {
                return null;
            }
        }
        return new Point(x, y);
    }

    /** Builds tiles of walls on only two sides of the given coordinate based on direction that hallway
     * is heading. */
    private void buildSideWalls(int x, int y, int direction) {
        if (direction == 0 || direction == 2) {
            x -= 1;
            for (int xi = 0; xi < 3; xi++, x += 1) {
                if (world[x][y] == null) {
                    world[x][y] = Tileset.WALL;
                }
            }
        } else {
            y += 1;
            for (int yi = 0; yi < 3; yi++, y -= 1) {
                if (world[x][y] == null) {
                    world[x][y] = Tileset.WALL;
                }
            }
        }
    }

/* Room generation */

//    public void randomlyGenRooms(long seed, int numRooms) {
//        Random randomise = new Random(seed);
//        int w;
//        int h;
//        int startX;
//        int startY;
//        while (rooms.size() != numRooms) {
//            w = RandomUtils.uniform(randomise, 3,8);
//            System.out.println("width is now " + w);
//            h = RandomUtils.uniform(randomise, 3, 8);
//            System.out.println("height is now " + h);
//            startX = RandomUtils.uniform(randomise, 80);
//            startY = RandomUtils.uniform(randomise, 30);
//            System.out.println("The new starting point is now x: " + startX + " y: " + startY);
//            Point currPoint = new Point(startX, startY);
//            Room myNewRoom = new Room(world, dimensions, currPoint, w, h);
//            myNewRoom.createRoom();
//            if (myNewRoom.printed) {
//                rooms.add(myNewRoom);
//            }
//        }
//        System.out.println(rooms);
//    }
}
