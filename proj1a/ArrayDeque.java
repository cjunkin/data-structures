public class ArrayDeque<T> {
    private int size;
    private T[] storage;
    private int first;
    private int last;
    private int length;

    /** Returns an updated integer using i. If increasing, increases the index of i. If not,
     * decreases the i. If decreasing and i is 0, the method loops i to the end
     * of the list.
     * @param i
     * @param increase
     * @return updated i
     */
    private int updateFirst(int i, boolean increase) {
        if (increase) {
            if (i == storage.length - 1) {
                return 0;
            } else {
                return i + 1;
            }
        } else if (storage[first] == null) {
            return i;
        } else {
            if (i == 0) {
                return storage.length - 1;
            } else {
                return i - 1;
            }
        }
    }

    private int updateLast(int i, boolean increase) {
        if (increase) {
            if (i == storage.length - 1) {
                return 0;
            } else {
                return i + 1;
            }
        } else {
            if (i == 0) {
                return storage.length - 1;
            } else {
                return i - 1;
            }
        }
    }

    private void resize() {
        T[] newList;
        double percentage = 100.0 * size / length;
        if (length > 16 && percentage < 25) {
            int i = updateFirst(first, true);
            int place = 0;
            length = size * 2;
            newList = (T []) new Object[length];
            while (i != last) {
                newList[place] = get(i);
                i = updateFirst(i, true);
                place += 1;
            }
            storage = newList;
            first = length - 1;
            last = size;
        } else if (size == length - 1) {
            int i = updateFirst(first, true);
            int place = 0;
            length = length * 2;
            newList = (T []) new Object[length];
            while (i != last) {
                newList[place] = get(i);
                i = updateFirst(i, true);
                place += 1;
            }
            storage = newList;
            first = length - 1;
            last = size;
        } else {
            return;
        }

    }

    public ArrayDeque() {
        storage = (T []) new Object[8];
        first = 0;
        last = 1;
        size = 0;
        length = 8;
    }

    public ArrayDeque(ArrayDeque other) {
        storage = (T []) new Object[other.size];
        first = other.first;
        last = other.last;
        size = other.size;
        length = other.length;
        System.arraycopy(other.storage, 0, storage, 0, size);
    }

    public void addFirst(T object) {
        resize();
        storage[first] = object;
        size += 1;
        first = updateFirst(first, false);
    }

    public void addLast(T object) {
        resize();
        if (size == 0) {
            storage[0] = object;
            first = updateFirst(first, false);
            last = 1;
        } else {
            storage[last] = object;
            last = updateLast(last, true);
        }
        size += 1;
    }

    public T removeFirst() {
        T result;
        resize();
        if (size == 0) {
            return null;
        } else if (first == storage.length - 1) {
            first = updateFirst(first, true);
            result = storage[first];
            storage[first] = null;
        } else {
            first = updateFirst(first, true);
            result = storage[first];
            storage[first] = null;
        }
        size -= 1;
        return result;
    }

    public T removeLast() {
        resize();
        if (size == 0) {
            return null;
        }
        last = updateLast(last, false);
        T result = storage[last];
        storage[last] = null;
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
        int pFirst = updateFirst(first, true);
        while (storage[pFirst ] != null) {
            System.out.print(storage[pFirst] + " ");
            pFirst = updateFirst(pFirst, true);
        }
        System.out.println("");
    }

    public T get(int index) {
        index = updateFirst(first + index, true);
        return storage[index];
    }
}
