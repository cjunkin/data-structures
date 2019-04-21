import java.util.Iterator;
import java.util.Set;

/*
* @source BST code from the optional textbook
* */
public class BSTMap<K extends Comparable<K>, V> implements Map61B<K, V> {
    private Node root; // root of the map
    private int size; // keeps track of the number of key-value mappings

    private class Node {
        K key;
        V value;
        Node left, right;

        Node(K key, V value) {
            this.key = key;
            this.value = value;
        }

        Node(K key, V value, Node left, Node right) {
            this.key = key;
            this.value = value;
            this.left = left;
            this.right = right;
        }
    }

    /* Creates an empty BSTMap with a null root */
    public BSTMap() {
    }

    /** Removes all of the mappings from this map. */
    public void clear() {
        root = null;
        size = 0;
    }

    /* Returns true if this map contains a mapping for the specified key. */
    public boolean containsKey(K key) {
        if (root == null) {
            return false;
        } else if (key == null) {
            throw new IllegalArgumentException("argument to containsKey() is null");
        } else if (key.compareTo(root.key) == 0) {
            return true;
        } else if (key.compareTo(root.key) < 0) {
            return check(root.left, key);
        } else {
            return check(root.right, key);
        }
    }

    /* Streamlined version of containsKey */
    private boolean check(Node n, K key) {
        if (n == null) {
            return false;
        }
        int indicator = key.compareTo(n.key);
        if (indicator == 0) {
            return true;
        } else if (n.left == null && n.right == null) {
            return false;
        } else if (indicator < 1) {
            return check(n.left, key);
        } else {
            return check(n.right, key);
        }
    }

    /* Returns the value to which the specified key is mapped, or null if this
     * map contains no mapping for the key.
     */
    public V get(K key) {
        if (key == null) {
            throw new IllegalArgumentException("argument to get() is null");
        } else if (root == null || !containsKey(key)) {
            return null;
        }
        return get(root, key);
    }

    private V get(Node n, K key) {
        int indicator = key.compareTo(n.key);
        if (indicator == 0) {
            return n.value;
        } else if (indicator < 0) {
            return get(n.left, key);
        } else {
            return get(n.right, key);
        }
    }

    /* Returns the number of key-value mappings in this map. */
    public int size() {
        return size;
    }

    /* Associates the specified value with the specified key in this map. */
    public void put(K key, V value) {
        if (key == null || value == null) {
            return;
        }
        root = Helper(root, key, value);
    }

    /* @flag
    * ! LOOK OVER THIS TO LEARN !
    * @source 1 - 3 Lab at Soda 273
    * */
    private Node Helper(Node curr, K key, V value) {
        if (curr == null) {
            size++;
            return new Node(key, value);
        }

        int compare = key.compareTo(curr.key);
        if (compare < 0) {
            curr.left = Helper(curr.left, key, value);
        } else if (compare > 0) {
            curr.right = Helper(curr.right, key, value);
        } else {
            curr.value = value;
        }
        return curr;
    }

    /*
    * @source 11 AM - 1 PM lab on 3/7/2019
    * */
    public void printInOrder() {
        StringBuilder result = new StringBuilder();
        if (root != null) {
            printRecursive(root, result);
            result.deleteCharAt(result.length() - 1);
        }
        System.out.println(result);
    }

    private void printRecursive(Node curr, StringBuilder result) {
        if (curr != null) {
            printRecursive(curr.left, result);
            result.append(curr.key).append(" ");
            printRecursive(curr.right, result);
        }
    }

    /* Returns a Set view of the keys contained in this map. */
    public Set<K> keySet() {
        throw new UnsupportedOperationException("unsupported method");
    }

    /* Removes the mapping for the specified key from this map if present.
     * Not required for Lab 8. If you don't implement this, throw an
     * UnsupportedOperationException. */
    public V remove(K key) {
        throw new UnsupportedOperationException("unsupported method");
    }

    /* Removes the entry for the specified key only if it is currently mapped to
     * the specified value. Not required for Lab 8. If you don't implement this,
     * throw an UnsupportedOperationException.*/
    public V remove(K key, V value) {
        throw new UnsupportedOperationException("unsupported method");
    }

    public Iterator<K> iterator() {
        throw new UnsupportedOperationException("unsupported method");
    }
}
