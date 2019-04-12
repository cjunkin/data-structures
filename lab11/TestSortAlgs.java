import edu.princeton.cs.algs4.Queue;

import edu.princeton.cs.algs4.Stopwatch;
import org.junit.Test;

import java.util.Iterator;

import static org.junit.Assert.assertTrue;

public class TestSortAlgs {

    @Test
    public void testQuickSort() {

    }

    @Test
    public void testMergeSort() {
        Queue<String> test = new Queue<>();
        test.enqueue("Chris");
        test.enqueue("Kyle");
        test.enqueue("Miles");
        test.enqueue("Jonah");
        Stopwatch sw = new Stopwatch();
        test = MergeSort.mergeSort(test);
        System.out.println(sw.elapsedTime() * 1000);
        assertTrue(isSorted(test));
    }

    /**
     * Returns whether a Queue is sorted or not.
     *
     * @param items  A Queue of items
     * @return       true/false - whether "items" is sorted
     */
    private <Item extends Comparable> boolean isSorted(Queue<Item> items) {
        if (items.size() <= 1) {
            return true;
        }
        Item curr = items.dequeue();
        Item prev = curr;
        while (!items.isEmpty()) {
            prev = curr;
            curr = items.dequeue();
            if (curr.compareTo(prev) < 0) {
                return false;
            }
        }
        return true;
    }
}
