package bearmaps;

import java.util.ArrayList;
import java.util.List;

public class NaivePointSet {
    private List<Point> points;

    /* You can assume points has at least size 1. */
    public NaivePointSet(List<Point> points) {
        this.points = new ArrayList<>(points);
    }

    /* Returns the closest point to the inputted coordinates.
    This should take θ(N) time where N is the number of points. */
    public Point nearest(double x, double y) {
        Point closest = points.get(0);
        Point current = new Point(x, y);
        for (int i = 1; i < points.size(); i++) {
            Point p = points.get(i);
            if (Point.distance(current, p) < Point.distance(current, closest)) {
                closest = p;
            }
        }
        return closest;
    }
}
