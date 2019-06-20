package bearmaps;

import edu.princeton.cs.algs4.Stopwatch;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class KDTreeTest {

    /*
    * @source lecture 22 slides
    * */
    @Test
    public void testConstructor() {
        Point p1 = new Point(2, 3);
        Point p2 = new Point(4, 2);
        Point p3 = new Point(4, 5);
        Point p4 = new Point(3, 3);
        Point p5 = new Point(4, 4);
        Point p6 = new Point(1, 5);
        List<Point> points = List.of(p1, p2, p3, p4, p5, p6);

        KDTree kd = new KDTree(points);
    }

    @Test
    public void testNearestSmallSize() {
        Point p1 = new Point(2, 3);
        Point p2 = new Point(4, 2);
        Point p3 = new Point(4, 5);
        Point p4 = new Point(3, 3);
        Point p5 = new Point(4, 4);
        Point p6 = new Point(1, 5);
        List<Point> points = List.of(p1, p2, p3, p4, p5, p6);

        KDTree kd = new KDTree(points);
        NaivePointSet nps = new NaivePointSet(points);
        double x = Math.random();
        double y = Math.random();
        assertEquals(nps.nearest(x, y), kd.nearest(x, y));
    }

    /*
    * @source ideas implemented from Hug's psuedo walkthrough
    * */
    @Test
    public void testNearest() {
        List<Point> test = new ArrayList<>();
        for (int i = 0; i < 1000; i++) {
            test.add(new Point(Math.random(), Math.random()));
        }
        KDTree kd = new KDTree(test);
        NaivePointSet nps = new NaivePointSet(test);

        for (int i = 0; i < 1000; i++) {
            double x = Math.random();
            double y = Math.random();
            assertEquals(nps.nearest(x, y), kd.nearest(x, y));
        }
    }

    @Test
    public void testTiming() {
        List<Point> test = new ArrayList<>();
        for (int i = 0; i < 100000; i++) {
            test.add(new Point(Math.random(), Math.random()));
        }
        Stopwatch kdc = new Stopwatch();
        KDTree kd = new KDTree(test);
        System.out.println("KDTree constructor time: " + kdc.elapsedTime());
        Stopwatch npsc = new Stopwatch();
        NaivePointSet nps = new NaivePointSet(test);
        System.out.println("Naive constructor time: " + npsc.elapsedTime());

        Stopwatch kdTime = new Stopwatch();
        for (int i = 0; i < 10000; i++) {
            double x = Math.random();
            double y = Math.random();
            kd.nearest(x, y);
        }
        double kdt = kdTime.elapsedTime();
        System.out.println("KDTree time: " + kdt);

        Stopwatch npsTime = new Stopwatch();
        for (int i = 0; i < 10000; i++) {
            double x = Math.random();
            double y = Math.random();
            nps.nearest(x, y);
        }
        double npst = npsTime.elapsedTime();
        System.out.println("Naive implementation time: " + npst);

        assertTrue(kdt / npst < 0.11);
    }
}
