package hw3.hash;

import org.junit.Test;
import static org.junit.Assert.*;

import java.util.Set;
import java.util.HashSet;
import java.util.List;
import java.util.ArrayList;


public class TestSimpleOomage {

    @Test
    public void testHashCodeDeterministic() {
        SimpleOomage so = SimpleOomage.randomSimpleOomage();
        int hashCode = so.hashCode();
        for (int i = 0; i < 100; i += 1) {
            assertEquals(hashCode, so.hashCode());
        }
    }

    @Test
    public void testHashCodePerfect() {
        /* TODO: Write a test that ensures the hashCode is perfect,
          meaning no two SimpleOomages should EVER have the same
          hashCode UNLESS they have the same red, blue, and green values!
         */
        SimpleOomage ooA = new SimpleOomage(5, 5, 10);
        SimpleOomage ooA2 = new SimpleOomage(10, 10, 0);
        SimpleOomage ooA3 = new SimpleOomage(0, 5, 15);
        SimpleOomage ooA4 = new SimpleOomage(10, 15, 45);
        SimpleOomage ooA5 = new SimpleOomage(20, 35, 15);
        SimpleOomage ooA6 = new SimpleOomage(0, 5, 15);
        HashSet<SimpleOomage> hashSet1 = new HashSet<>();
        HashSet<SimpleOomage> hashSet2 = new HashSet<>();
        HashSet<SimpleOomage> hashSet3 = new HashSet<>();
        HashSet<SimpleOomage> hashSet4 = new HashSet<>();
        HashSet<SimpleOomage> hashSet5 = new HashSet<>();
        HashSet<SimpleOomage> hashSet6 = new HashSet<>();
        hashSet1.add(ooA);
        hashSet2.add(ooA2);
        hashSet3.add(ooA3);
        hashSet4.add(ooA4);
        hashSet5.add(ooA5);
        hashSet6.add(ooA6);
        assertNotEquals(hashSet1, hashSet2);
        assertNotEquals(hashSet1, hashSet3);
        assertNotEquals(hashSet2, hashSet3);
        assertNotEquals(hashSet4, hashSet5);
        assertEquals(hashSet3, hashSet6);
    }

    @Test
    public void testEquals() {
        SimpleOomage ooA = new SimpleOomage(5, 10, 20);
        SimpleOomage ooA2 = new SimpleOomage(5, 10, 20);
        SimpleOomage ooB = new SimpleOomage(50, 50, 50);
        assertEquals(ooA, ooA2);
        assertNotEquals(ooA, ooB);
        assertNotEquals(ooA2, ooB);
        assertNotEquals(ooA, "ketchup");
    }

    @Test
    public void testHashCodeAndEqualsConsistency() {
        SimpleOomage ooA = new SimpleOomage(5, 10, 20);
        SimpleOomage ooA2 = new SimpleOomage(5, 10, 20);
        HashSet<SimpleOomage> hashSet = new HashSet<>();
        hashSet.add(ooA);
        assertTrue(hashSet.contains(ooA2));
    }

    /* TODO: Uncomment this test after you finish haveNiceHashCodeSpread in OomageTestUtility */
    @Test
    public void testRandomOomagesHashCodeSpread() {
        List<Oomage> oomages = new ArrayList<>();
        int N = 10000;

        for (int i = 0; i < N; i += 1) {
            oomages.add(SimpleOomage.randomSimpleOomage());
        }

        assertTrue(OomageTestUtility.haveNiceHashCodeSpread(oomages, 10));
    }

    /** Calls tests for SimpleOomage. */
    public static void main(String[] args) {
        jh61b.junit.textui.runClasses(TestSimpleOomage.class);
    }
}
