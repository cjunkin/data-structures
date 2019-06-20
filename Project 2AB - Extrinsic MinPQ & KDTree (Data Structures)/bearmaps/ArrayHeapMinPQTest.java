package bearmaps;

import edu.princeton.cs.algs4.Stopwatch;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

public class ArrayHeapMinPQTest {
//    @Test
//    public void testTiming() {
//        Stopwatch basic2 = new Stopwatch();
//        ArrayHeapMinPQ<Integer> test2 = new ArrayHeapMinPQ<>();
//        for (int i = 0; i < 1000; i++) {
//            test2.add(i, i);
//        }
//        System.out.println("Total time elapsed: " + basic2.elapsedTime() +  " seconds.");
//
//        Stopwatch basic3 = new Stopwatch();
//        ArrayHeapMinPQ<Integer> test3 = new ArrayHeapMinPQ<>();
//        for (int i = 0; i < 100000; i++) {
//            test3.add(i, i);
//        }
//        System.out.println("Total time elapsed: " + basic3.elapsedTime() +  " seconds.");
//
//        Stopwatch bigAdd = new Stopwatch();
//        ArrayHeapMinPQ<Integer> big = new ArrayHeapMinPQ<>();
//        for (int i = 0; i < 1000000; i++) {
//            big.add(i, i);
//        }
//        System.out.println("million add time: " + bigAdd.elapsedTime() +  " seconds.");
//
//        Stopwatch naive = new Stopwatch();
//        NaiveMinPQ<Integer> gross = new NaiveMinPQ<>();
//        for (int i = 0; i < 1000000; i++) {
//            gross.add(i, i);
//        }
//        System.out.println("Naive time elapsed: " + naive.elapsedTime() +  " seconds.");
//
//        Stopwatch naiveTime = new Stopwatch();
//        for (int i = 0; i < 1000; i++) {
//            gross.removeSmallest();
//        }
//        System.out.println("Naive remove time elapsed: " + naiveTime.elapsedTime() +  " seconds.");
//
//        Stopwatch bigAddTime = new Stopwatch();
//        for (int i = 0; i < 1000; i++) {
//            big.removeSmallest();
//        }
//        System.out.println("Big remove time elapsed: " + bigAddTime.elapsedTime() +  " seconds.");
//
//
//        Stopwatch remove = new Stopwatch();
//        test3.removeSmallest();
//        System.out.println("Remove time elapsed: " + remove.elapsedTime() +  " seconds.");
//
//        ArrayHeapMinPQ<Integer> test4 = new ArrayHeapMinPQ<>();
//        for (int i = 0; i < 1000000; i++) {
//            test4.add(i, i);
//        }
//
//        Stopwatch remove2 = new Stopwatch();
//        test4.removeSmallest();
//        System.out.println("Remove2 time elapsed: " + remove2.elapsedTime() +  " seconds.");
//
//
//        Stopwatch contain = new Stopwatch();
//        test3.contains(5000);
//        System.out.println("Contains time elapsed: " + contain.elapsedTime() +  " seconds.");
//
//        Stopwatch contain2 = new Stopwatch();
//        test4.contains(500000);
//        System.out.println("Contains2 time elapsed: " + contain2.elapsedTime() +  " seconds.");
//
//
//        Stopwatch priority1 = new Stopwatch();
//        test3.changePriority(50000, 0);
//        System.out.println("Priority1 time elapsed: " + priority1.elapsedTime() +  " seconds.");
//
//        Stopwatch priority2 = new Stopwatch();
//        test4.changePriority(500000, -1);
//        System.out.println("Priority2 time elapsed: " + priority2.elapsedTime() +  " seconds.");
//    }

    @Test
    public void testBasic() {
        ArrayHeapMinPQ<Integer> test = new ArrayHeapMinPQ<>();
        assertEquals(0, test.size());

        for (int i = 0; i < 10; i++) {
            test.add(i, i);
        }
        assertEquals(10, test.size());
        assertEquals((Integer) 0, test.getSmallest());

        test.add(10, 10);
        assertEquals(11, test.size());
        assertNotEquals((Integer) 10, test.getSmallest());
        assertEquals((Integer) 0, test.getSmallest());
        assertTrue(test.contains(0));
        assertTrue(test.contains(10));

        test.add(11, -1);
        assertEquals(12, test.size());
        assertNotEquals((Integer) 0, test.getSmallest());
        assertEquals((Integer) 11, test.getSmallest());
        assertTrue(test.contains(10));
        assertTrue(test.contains(0));

        test.changePriority(10, -2);
        assertNotEquals((Integer) 0, test.getSmallest());
        assertEquals((Integer) 10, test.getSmallest());

        test.changePriority(10, 10);
        assertNotEquals((Integer) 10, test.getSmallest());
        assertEquals((Integer) 11, test.getSmallest());

        try {
            test.add(11, -2);
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

        test.changePriority(8, -10);
        test.removeSmallest();
        test.changePriority(5, -10);
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
        test.removeSmallest();
        assertEquals((Integer) 1, test.getSmallest());
        assertEquals(4, test.size());
    }

//    @Test
//    public void testAddTiming() {
//        ArrayHeapMinPQ<Integer> test1 = new ArrayHeapMinPQ<>();
//        for (int i = 0; i < 100; i++) {
//            test1.add(i, i);
//        }
//        ArrayHeapMinPQ<Integer> test2 = new ArrayHeapMinPQ<>();
//        for (int i = 0; i < 1000; i++) {
//            test2.add(i, i);
//        }
//        ArrayHeapMinPQ<Integer> test3 = new ArrayHeapMinPQ<>();
//        for (int i = 0; i < 10000; i++) {
//            test3.add(i, i);
//        }
//        ArrayHeapMinPQ<Integer> test4 = new ArrayHeapMinPQ<>();
//        for (int i = 0; i < 100000; i++) {
//            test4.add(i, i);
//        }
//        ArrayHeapMinPQ<Integer> test5 = new ArrayHeapMinPQ<>();
//        for (int i = 0; i < 1000000; i++) {
//            test5.add(i, i);
//        }
//        ArrayHeapMinPQ<Integer> test6 = new ArrayHeapMinPQ<>();
//        for (int i = 0; i < 10000000; i++) {
//            test6.add(i, i);
//        }
//
//        Stopwatch stop1 = new Stopwatch();
//        for (int i = -1; i > -10000; i--) {
//            test1.add(i, i);
//        }
//        System.out.println("100 time elapsed: " + stop1.elapsedTime() +  " seconds.");
//
//        Stopwatch stop2 = new Stopwatch();
//        for (int i = -1; i > -10000; i--) {
//            test2.add(i, i);
//        }
//        System.out.println("1000 time elapsed: " + stop2.elapsedTime() +  " seconds.");
//
//        Stopwatch stop3 = new Stopwatch();
//        for (int i = -1; i > -10000; i--) {
//            test3.add(i, i);
//        }
//        System.out.println("10000 time elapsed: " + stop3.elapsedTime() +  " seconds.");
//
//        Stopwatch stop4 = new Stopwatch();
//        for (int i = -1; i > -10000; i--) {
//            test4.add(i, i);
//        }
//        System.out.println("100000 time elapsed: " + stop4.elapsedTime() +  " seconds.");
//
//        Stopwatch stop5 = new Stopwatch();
//        for (int i = -1; i > -10000; i--) {
//            test5.add(i, i);
//        }
//        System.out.println("1000000 time elapsed: " + stop5.elapsedTime() +  " seconds.");
//
//        Stopwatch stop6 = new Stopwatch();
//        for (int i = -1; i > -10000; i--) {
//            test6.add(i, i);
//        }
//        System.out.println("10000000 time elapsed: " + stop6.elapsedTime() +  " seconds.");
//    }
}
