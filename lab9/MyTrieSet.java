import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

public class MyTrieSet implements TrieSet61B {
    private Node root;

    private class Node {
        Hashtable map;
        boolean isKey;
        char character;

        Node() {
            map = new Hashtable();
        }

        Node(char c) {
            new Node(c, false);
        }

        Node(char c, boolean b) {
            map = new Hashtable();
            character = c;
            isKey = b;
        }
    }

    public MyTrieSet() {
        root = new Node();
    }

    /** Clears all items out of Trie */
    public void clear() {
        root.map.clear();
    }

    /** Returns true if the Trie contains KEY, false otherwise */
    public boolean contains(String key) {
        if (key == null || key.length() < 1) {
            return false;
        }
        Node curr = root;
        for (int i = 0, n = key.length(); i < n; i++) {
            char c = key.charAt(i);
            if (!curr.map.containsKey(c)) {
                return false;
            }
            curr = (Node) curr.map.get(c);
        }
        if (curr.isKey) {
            return true;
        }
        return false;
    }

    /** Inserts string KEY into Trie */
    @Override
    public void add(String key) {
        if (key == null || key.length() < 1) {
            return;
        }
        Node curr = root;
        for (int i = 0, n = key.length(); i < n; i++) {
            char c = key.charAt(i);
            if (!curr.map.containsKey(c)) {
                curr.map.put(c, new Node(c, false));
            }
            curr = (Node) curr.map.get(c);
        }
        curr.isKey = true;
    }

    /** Returns a list of all words that start with PREFIX */
    public List<String> keysWithPrefix(String prefix) {
        if (!contains(prefix)) {
            return null;
        }
        List<String> words = new ArrayList<>();
        Node curr = root;
        for (int i = 0, n = prefix.length(); i < n; i++) {
            char c = prefix.charAt(i);
            curr = (Node) curr.map.get(c);
        }
        for (Node i : curr.map) {
            words("", i, words);
        }

    }

    private String words(String temp, Node pntr, List<String> words) {
        if (pntr.isKey) {
            return temp;
        }
        temp += pntr.character;
        words(temp, );
    }

    /** Returns the longest prefix of KEY that exists in the Trie
     * Not required for Lab 9. If you don't implement this, throw an
     * UnsupportedOperationException.
     */
    public String longestPrefixOf(String key) {
        throw new UnsupportedOperationException("unsupported method");
    }
}
