import org.junit.Test;
import static org.junit.Assert.*;

public class TestOffByN {
    static Palindrome palindrome = new Palindrome();
    OffByN offBy0 = new OffByN(0);
    OffByN offBy1 = new OffByN(1);
    OffByN offBy5 = new OffByN(5);
    OffByN offBy6 = new OffByN(6);

    @Test
    public void testNChar() {
        assertTrue(offBy0.equalChars('b', 'b'));
        assertFalse(offBy0.equalChars('a', 'c'));

        assertTrue(offBy1.equalChars('a', 'b'));
        assertTrue(offBy1.equalChars('b', 'a'));
        assertFalse(offBy1.equalChars('a', 'c'));
        assertFalse(offBy1.equalChars('c', 'a'));

        assertTrue(offBy5.equalChars('a', 'f'));
        assertTrue(offBy5.equalChars('h', 'c'));
        assertFalse(offBy5.equalChars('a', 'x'));
        assertFalse(offBy5.equalChars('c', 'a'));

        assertTrue(offBy6.equalChars('a', 'g'));
        assertTrue(offBy6.equalChars('i', 'c'));
        assertFalse(offBy6.equalChars('a', 'z'));
        assertFalse(offBy6.equalChars('d', 'd'));
    }

    @Test
    public void testNPalindrome() {
        assertFalse(palindrome.isPalindrome("aba", offBy5));
        assertTrue(palindrome.isPalindrome("abf", offBy5));
        assertTrue(palindrome.isPalindrome("abcdihgf", offBy5));
    }
}
