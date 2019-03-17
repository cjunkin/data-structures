package bearmaps;

import edu.princeton.cs.algs4.Stopwatch;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

public class ArrayHeapMinPQTest {

    @Test
    public void testBasic() {
        ArrayHeapMinPQ<Integer> test = new ArrayHeapMinPQ<>();
        assertEquals(0, test.size());

        for (int i = 1; i < 6; i++) {
            test.add(i, i);
        }
        assertEquals(5, test.size());
        assertEquals((Integer) 1, test.getSmallest());

        test.add(0, 0);
        assertEquals(6, test.size());
        assertNotEquals((Integer) 1, test.getSmallest());
        assertEquals((Integer) 0, test.getSmallest());
        assertTrue(test.contains(0));
        assertTrue(test.contains(5));

        test.add(10, -1);
        assertEquals(7, test.size());
        assertNotEquals((Integer) 0, test.getSmallest());
        assertEquals((Integer) 10, test.getSmallest());
        assertTrue(test.contains(10));
        assertTrue(test.contains(0));

        try {
            test.add(10, -2);
        } catch (IllegalArgumentException expectedException) {
            System.out.println("don't add repeated objects");
        }
    }

    @Test
    public void testSamePriority() {
        ArrayHeapMinPQ<Integer> test = new ArrayHeapMinPQ<>();
        for (int i = 0; i < 5; i++) {
            test.add(i, i);
        }
        test.add(5, -1);
        test.add(6, -1);
        test.add(7, -1);
        test.add(8, -1);
        test.add(9, -1);
        assertEquals((Integer) 5, test.getSmallest());
    }

    @Test
    public void testChangePriority() {
        ArrayHeapMinPQ<Integer> test = new ArrayHeapMinPQ<>();
        for (int i = 1; i < 6; i++) {
            test.add(i, i);
        }
        test.add(6, 0);
        test.add(7, -1);
        test.add(8, -2);
        test.changePriority(6, 5);
        test.changePriority(7, 6);
        test.changePriority(3, 5);
        test.changePriority(4, -3);
        assertEquals((Integer) 4, test.getSmallest());
        assertTrue(test.contains(8));
        assertTrue(test.contains(7));
    }

    @Test
    public void testRemove() {
        ArrayHeapMinPQ<Integer> test = new ArrayHeapMinPQ<>();
        for (int i = 0; i < 5; i++) {
            test.add(i, i);
        }
        test.add(6, -1);
        test.removeSmallest();
        assertEquals((Integer) 0, test.getSmallest());
    }

    @Test
    public void testTiming() {
        Stopwatch basic1 = new Stopwatch();
        ArrayHeapMinPQ<Integer> test1 = new ArrayHeapMinPQ<>();
        for (int i = 0; i < 10; i++) {
            test1.add(i, i);
        }
        System.out.println("Total time elapsed: " + basic1.elapsedTime() +  " seconds.");

        Stopwatch basic2 = new Stopwatch();
        ArrayHeapMinPQ<Integer> test2 = new ArrayHeapMinPQ<>();
        for (int i = 0; i < 10000; i++) {
            test2.add(i, i);
        }
        System.out.println("Total time elapsed: " + basic2.elapsedTime() +  " seconds.");

        Stopwatch basic3 = new Stopwatch();
        ArrayHeapMinPQ<Integer> test3 = new ArrayHeapMinPQ<>();
        for (int i = 0; i < 1000000; i++) {
            test3.add(i, i);
        }
        System.out.println("Total time elapsed: " + basic3.elapsedTime() +  " seconds.");

        Stopwatch naive = new Stopwatch();
        NaiveMinPQ<Integer> gross = new NaiveMinPQ<>();
        for (int i = 0; i < 1000000; i++) {
            gross.add(i, i);
        }
        System.out.println("Naive time elapsed: " + naive.elapsedTime() +  " seconds.");

        Stopwatch remove = new Stopwatch();
        test3.removeSmallest();
        System.out.println("Remove time elapsed: " + remove.elapsedTime() +  " seconds.");

        ArrayHeapMinPQ<Integer> test4 = new ArrayHeapMinPQ<>();
        for (int i = 0; i < 1000000; i++) {
            test4.add(i, i);
        }

        Stopwatch remove2 = new Stopwatch();
        test4.removeSmallest();
        System.out.println("Remove2 time elapsed: " + remove2.elapsedTime() +  " seconds.");

        Stopwatch contain = new Stopwatch();
        test3.contains(5000);
        System.out.println("Contains time elapsed: " + contain.elapsedTime() +  " seconds.");

        Stopwatch contain2 = new Stopwatch();
        test4.contains(500000);
        System.out.println("Contains2 time elapsed: " + contain2.elapsedTime() +  " seconds.");

        Stopwatch priority1 = new Stopwatch();
        test2.changePriority(5000, 0);
        System.out.println("Priority1 time elapsed: " + priority1.elapsedTime() +  " seconds.");

        Stopwatch priority2 = new Stopwatch();
        test4.changePriority(500000, 0);
        System.out.println("Priority2 time elapsed: " + priority2.elapsedTime() +  " seconds.");
    }
}
