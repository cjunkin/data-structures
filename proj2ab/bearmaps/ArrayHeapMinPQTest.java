package bearmaps;

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
        assertEquals((Integer) 1, (Integer) (test.getSmallest()));

        test.add(0, 0);
        assertEquals(6, test.size());
        assertNotEquals((Integer) 1, (Integer) test.getSmallest());
        assertEquals((Integer) 0, (Integer) (test.getSmallest()));
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
}
