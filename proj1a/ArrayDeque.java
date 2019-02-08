public class ArrayDeque<Item> {
    private int size;
    private Item[] storage;
    private int first;
    private int last;

    /** Returns an updated integer using i. If increasing, increases the index of i. If not,
     * decreases the i. If decreasing and i is 0, the method loops i to the end
     * of the list.
     * @param i
     * @param increase
     * @return updated i
     */
    private int update_first(int i, boolean increase) {
        if (increase) {
            if (i == storage.length - 1) {
                return 0;
            } else {
                return i + 1;
            }
        } else if (i == last) {
            return i;
        } else {
            if (i == 0) {
                return storage.length - 1;
            } else {
                return i - 1;
            }
        }
    }

    private void update_last(boolean increase) {
        if (increase) {
            if (last == storage.length - 1) {
                last = 0;
            } else {
                last += 1;
            }
        } else if (first == last) {
            last += 1;
        } else {
            last -= 1;
        }
    }

    public ArrayDeque() {
        storage = (Item []) new Object[8];
        first = 0;
        last = 1;
        size = 0;
    }

    public ArrayDeque(int s) {
        storage = (Item []) new Object[s];
        first = 0;
        last = 1;
        size = s;
    }

    public ArrayDeque(ArrayDeque other) {
        ArrayDeque result = new ArrayDeque(other.size());

    }

    public void addFirst(Item object) {
        storage[first] = object;
        size += 1;
        first = update_first(first, false);
    }

    public void addLast(Item object) {
        storage[last] = object;
        size += 1;
        update_last(true);
    }

    public Item removeFirst() {
        Item result;
        if (first == storage.length - 1) {
            result = storage[0];
            storage[0] = null;
        } else {
            result = storage[first + 1];
            storage[first + 1] = null;
        }
        first = update_first(first, true);
        size -= 1;
        return result;
    }

    public Item removeLast() {
        Item result = storage[last - 1];
        storage[last - 1] = null;
        update_last(false);
        size -= 1;
        return result;
    }

    public boolean isEmpty() {
        if (size == 0) {
            return true;
        }
        return false;
    }

    public int size() {
        return size;
    }

    public void printDeque() {
        for (int p_first = update_first(first, true); storage[p_first ] != null; p_first = update_first(p_first, true)) {
            System.out.print(storage[p_first] + " ");
        }
        System.out.println("");
    }

    public Item get(int index) {
        return storage[index];
    }
}
