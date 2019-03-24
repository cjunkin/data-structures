package bearmaps;

import java.util.List;

public class KDTree {
    private Node root;
    private static boolean compare = true; // true is x, false is y

    private class Node {
        boolean orientation; // true if x, false if y
        Point location;
        Node leftDown;
        Node rightUp;
        Node good;
        Node bad;

        Node(Point p) {
            location = p;
            orientation = compare;
            KDTree.compare = !KDTree.compare;
        }
    }

    public KDTree(List<Point> points) {
        root = new Node(points.get(0));
        Node pointer = root;
        for (int i = 1; i < points.size(); i++) {
            add(points.get(i), root);
        }
    }

    /* @flag
       Tree add method that I should use as reference */
    private Node add(Point p, Node pointer) {
        if (pointer == null) {
            return new Node(p);
        }
        if (pointer.orientation) {
            if (p.getX() >= pointer.location.getX()) {
                pointer.rightUp = add(p, pointer.rightUp);
            } else {
                pointer.leftDown = add(p, pointer.leftDown);
            }
        } else {
            if (p.getY() >= pointer.location.getY()) {
                pointer.rightUp = add(p, pointer.rightUp);
            } else {
                pointer.leftDown = add(p, pointer.leftDown);
            }
        }
        return pointer;
    }

    /* Returns the closest point to the inputted coordinates.
    This should take O(logN) time on average, where N is the number of points. */
    public Point nearest(double x, double y) {
        return nearest(root, new Point(x, y), root).location;
    }

    private Node nearest(Node n, Point goal, Node best) {
        if (n == null) {
            return best;
        } else if (Point.distance(best.location, goal) > Point.distance(n.location, goal)) {
            best = n;
        }
        checkChild(n, goal);
        best = nearest(n.good, goal, best);
        if (checkBad(n, goal, Point.distance(best.location, goal))) {
            best = nearest(n.bad, goal, best);
        }
        return best;
    }

    /* Determines good and bad children of a node */
    private void checkChild(Node n, Point goal) {
        if (n.orientation) {
            if (goal.getX() <= n.location.getX()) {
                n.good = n.leftDown;
                n.bad = n.rightUp;
            } else {
                n.good = n.rightUp;
                n.bad = n.leftDown;
            }
        } else {
            if (goal.getY() <= n.location.getY()) {
                n.good = n.leftDown;
                n.bad = n.rightUp;
            } else {
                n.good = n.rightUp;
                n.bad = n.leftDown;
            }
        }
    }

    /* Checks to see if there is a reason to check the bad side of a node */
    private boolean checkBad(Node n, Point goal, double best) {
        if (n.orientation) {
            Point temp = new Point(n.location.getX(), goal.getY());
            return Point.distance(temp, goal) < best;
        } else {
            Point temp = new Point(goal.getX(), n.location.getY());
            return Point.distance(temp, goal) < best;
        }
    }
}
