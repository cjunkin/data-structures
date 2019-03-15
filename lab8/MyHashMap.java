import java.util.*;

public class MyHashMap<K, V> implements Map61B<K, V> {
    private static final int INIT_CAPACITY = 16;
    private static final double LOAD_FACTOR = 0.75;
    private int capacity;
    private double load;
    private int size;
    private ArrayList<Entry> table;
    private Set<K> keys;

    private class Entry extends LinkedList {
        K key;
        V value;
        Entry next;

        Entry(K key, V value) {
            this(key, value, null);
        }

        Entry(K key, V value, Entry n) {
            this.key = key;
            this.value = value;
            this.next = n;
        }
    }

    private class HashIterator implements Iterator<K> {
        Iterator<K> iter;

        HashIterator() {
            iter = keys.iterator();
        }

        @Override
        public boolean hasNext() {
            return iter.hasNext();
        }

        @Override
        public K next() {
            return iter.next();
        }
    }

    public MyHashMap() {
        this(INIT_CAPACITY);
    }

    public MyHashMap(int initialSize) {
        this(initialSize, LOAD_FACTOR);
    }

    public MyHashMap(int initialSize, double loadFactor) {
        table = new ArrayList<>(initialSize);
        load = loadFactor;
        capacity = initialSize;
        size = 0;
        keys = new HashSet<>();
    }

    public Iterator<K> iterator() {
        return new HashIterator();
    }

    private void resize() {

    }

    private void validate() {
        if (table != null && ((size * 1.0) / capacity >= load)) {
            resize();
        }
    }

    /** Removes all of the mappings from this map. */
    public void clear() {
        table = null;
        size = 0;
    }

    /** Returns true if this map contains a mapping for the specified key. */
    public boolean containsKey(K key) {
        if (table == null || key == null) {
            return false;
        }
        return keys.contains(key);
    }

    /**
     * Returns the value to which the specified key is mapped, or null if this
     * map contains no mapping for the key.
     */
    public V get(K key) {
        if (key != null && containsKey(key)) {
            int hash = key.hashCode() % capacity;
            Entry position = table.get(hash);

        } else {
            return null;
        }
    }

    /** Returns the number of key-value mappings in this map. */
    public int size() {
        return size;
    }

    /**
     * Associates the specified value with the specified key in this map.
     * If the map previously contained a mapping for the key,
     * the old value is replaced.
     */
    public void put(K key, V value) {
        if (key == null || value == null || table == null) {
            return;
        }
        validate();
        int hash = key.hashCode() % capacity;
        if (table.get(hash) == null) {
            table.add(hash, new Entry(key, value));
        } else {
            Entry storage = table.get(hash);
            if (storage.contains(key)) {

            } else {
                storage.add(new Entry(key, value));
            }
        }
        size++;
    }

    /** Returns a Set view of the keys contained in this map. */
    public Set<K> keySet() {
        return keys;
    }

    /**
     * Removes the mapping for the specified key from this map if present.
     * Not required for Lab 8. If you don't implement this, throw an
     * UnsupportedOperationException.
     */
    public V remove(K key) {
        throw new UnsupportedOperationException("method not supported");
    }

    /**
     * Removes the entry for the specified key only if it is currently mapped to
     * the specified value. Not required for Lab 8. If you don't implement this,
     * throw an UnsupportedOperationException.
     */
    public V remove(K key, V value) {
        throw new UnsupportedOperationException("method not supported");
    }
}
