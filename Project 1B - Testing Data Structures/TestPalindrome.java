import org.junit.Test;
        import static org.junit.Assert.*;

public class TestPalindrome {
    // You must use this palindrome, and not instantiate
    // new Palindromes, or the autograder might be upset.
    static Palindrome palindrome = new Palindrome();

    @Test
    public void testWordToDeque() {
        Deque d = palindrome.wordToDeque("persiflage");
        String actual = "";
        for (int i = 0; i < "persiflage".length(); i++) {
            actual += d.removeFirst();
        }
        assertEquals("persiflage", actual);
    }

    @Test
    public void testisPalindrome() {
        assertTrue(palindrome.isPalindrome("a"));
        assertTrue(palindrome.isPalindrome(""));
        assertFalse(palindrome.isPalindrome(null));

        assertTrue(palindrome.isPalindrome("racecar"));
        assertTrue(palindrome.isPalindrome("ded"));
        assertFalse(palindrome.isPalindrome("hello"));
    }

    OffByOne offByOne = new OffByOne();

    @Test
    public void testnewisPalindrome() {
        assertTrue(palindrome.isPalindrome("a", offByOne));
        assertTrue(palindrome.isPalindrome("", offByOne));
        assertFalse(palindrome.isPalindrome(null, offByOne));

        assertFalse(palindrome.isPalindrome("racecar", offByOne));
        assertFalse(palindrome.isPalindrome("hello", offByOne));
        assertFalse(palindrome.isPalindrome("Racecar", offByOne));
        assertFalse(palindrome.isPalindrome("Flake", offByOne));
        assertFalse(palindrome.isPalindrome("aCefdb", offByOne));
        assertFalse(palindrome.isPalindrome("ded", offByOne));
        assertTrue(palindrome.isPalindrome("flake", offByOne));
        assertTrue(palindrome.isPalindrome("acefdb", offByOne));
        assertTrue(palindrome.isPalindrome("AcEFdB", offByOne));
        assertTrue(palindrome.isPalindrome("ReQ", offByOne));
    }
}
