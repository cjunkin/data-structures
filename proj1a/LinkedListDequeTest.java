import org.junit.Test;

import java.lang.reflect.Array;

import static org.junit.Assert.*;


/** Performs some basic linked list tests. */
public class LinkedListDequeTest {

    /* Utility method for printing out empty checks. */
    public static boolean checkEmpty(boolean expected, boolean actual) {
        if (expected != actual) {
            System.out.println("isEmpty() returned " + actual + ", but expected: " + expected);
            return false;
        }
        return true;
    }

    /* Utility method for printing out empty checks. */
    public static boolean checkSize(int expected, int actual) {
        if (expected != actual) {
            System.out.println("size() returned " + actual + ", but expected: " + expected);
            return false;
        }
        return true;
    }

    /* Prints a nice message based on whether a test passed.
     * The \n means newline. */
    public static void printTestStatus(boolean passed) {
        if (passed) {
            System.out.println("Test passed!\n");
        } else {
            System.out.println("Test failed!\n");
        }
    }

    /** Adds a few things to the list, checking isEmpty() and size() are correct,
     * finally printing the results.
     *
     * && is the "and" operation. */
    public static void addIsEmptySizeTest() {
        System.out.println("Running add/isEmpty/Size test.");

        LinkedListDeque<String> lld1 = new LinkedListDeque<String>();

        boolean passed = checkEmpty(true, lld1.isEmpty());

        lld1.addFirst("front");

        // The && operator is the same as "and" in Python.
        // It's a binary operator that returns true if both arguments true, and false otherwise.
        passed = checkSize(1, lld1.size()) && passed;
        passed = checkEmpty(false, lld1.isEmpty()) && passed;

        lld1.addLast("middle");
        passed = checkSize(2, lld1.size()) && passed;

        lld1.addLast("back");
        passed = checkSize(3, lld1.size()) && passed;

        System.out.println("Printing out deque: ");
        lld1.printDeque();

        printTestStatus(passed);

    }

    /** Adds an item, then removes an item, and ensures that dll is empty afterwards. */
    public static void addRemoveTest() {

        System.out.println("Running add/remove test.");

        LinkedListDeque<Integer> lld1 = new LinkedListDeque<Integer>();
        // should be empty
        boolean passed = checkEmpty(true, lld1.isEmpty());

        lld1.addFirst(10);
        // should not be empty
        passed = checkEmpty(false, lld1.isEmpty()) && passed;

        lld1.removeFirst();
        // should be empty
        passed = checkEmpty(true, lld1.isEmpty()) && passed;

        lld1.addLast(5);
        passed = checkEmpty(false, lld1.isEmpty()) && passed;

        lld1.removeLast();
        passed = checkEmpty(true, lld1.isEmpty()) && passed;

        printTestStatus(passed);
    }

    public static void main(String[] args) {
//        System.out.println("Running tests.\n");
//        addIsEmptySizeTest();
//        addRemoveTest();
//        LinkedListDeque<Integer> l1 = new LinkedListDeque<>();
//        l1.addFirst(2);
//        l1.addLast(3);
//        int input = l1.getRecursive(1);
//        int expected = 3;
//        assertEquals(input, expected);

//        ArrayDeque<Integer> f = new ArrayDeque<>();
//        f.isEmpty();
//        f.addFirst(6);
//        f.removeFirst();
//        f.addLast(6);
//        f.removeLast();
//        f.size();
//        f.addFirst(6);
//        f.addFirst(6);
//        f.addFirst(6);
//        f.printDeque();

        ArrayDeque<Integer> x = new ArrayDeque<>();
        x.addLast(0);
        x.addFirst(1);
        x.addFirst(2);
        x.addFirst(3);
        x.addLast(4);
        x.addLast(4);
        x.addLast(4);
        new ArrayDeque(x);
        x.removeFirst();
        x.removeFirst();
        x.removeFirst();
        x.removeFirst();
        x.removeFirst();
        x.removeFirst();
        x.removeFirst();
        x.removeFirst();
        x.get(0);

//        ArrayDeque<Integer> s = new ArrayDeque<>();
//        int i = 15;
//        while (i > 8) {
//            s.addFirst(0);
//            i -= 1;
//        }
//        while (i > -4) {
//            s.addLast(0);
//            i -= 1;
//        }
//        s.removeLast();
//        while (i > -10) {
//            s.removeLast();
//            i += -1;
//        }
//        while (i > -16) {
//            s.removeLast();
//            i += -1;
//        }

//
//        l1.addFirst(1);
//        l1.addFirst(2);
//        l1.addFirst(3);
//        l1.removeLast();
//        l1.addFirst(5);
//        l1.addLast(6);
//        l1.removeFirst();
//        l1.getRecursive(0);
//        l1.removeFirst();
    }

    @Test
    public void TestInitialization() {
        LinkedListDeque<Integer> x = new LinkedListDeque<>();
        int input = x.size();
        int expected = 0;
        assertEquals(input, expected);
    }
}