public class Palindrome {
    public Deque<Character> wordToDeque(String word) {
        Deque<Character> result = new ArrayDeque<>();
        for (int i = 0; i < word.length(); i += 1) {
            result.addLast(word.charAt(i));
        }
        return result;
    }

    private boolean check(Deque<Character> word) {
        if (word.size() <= 1) {
            return true;
        }
        else if (word.removeFirst() == word.removeLast()) {
            return check(word);
        }
        return false;
    }

    public boolean isPalindrome(String word) {
        if (word == null) {
            return false;
        }
        Deque<Character> A = wordToDeque(word);
        return check(A);
    }

    private boolean offByOneCheck(Deque<Character> word, CharacterComparator cc) {
        if (word.size() <= 1) {
            return true;
        } else if (cc.equalChars(word.removeFirst(), word.removeLast())) {
            return offByOneCheck(word, cc);
        }
        return false;

    }

    public boolean isPalindrome(String word, CharacterComparator cc) {
        if (word == null) {
            return false;
        }
        Deque<Character> A = wordToDeque(word);
        return isPalindrome(word) || offByOneCheck(A, cc);
    }
}
