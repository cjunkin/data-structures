public class ArrayDeque<T> implements Deque<T> {
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
            } else if (i >= storage.length) {
                return i - storage.length + 1;
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
            if (i == storage.length - 1 || i > storage.length) {
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
        if (length > 16 && percentage < 30) {
            length = length / 2;
            newList = (T []) new Object[length];
            for (int i = 0; i < size; i = updateFirst(i, true)) {
                newList[i] = get(i);
            }
            storage = newList;
            first = length - 1;
            last = size;
        } else if (size == length - 1) {
            length = length * 2;
            newList = (T []) new Object[length];
            for (int i = 0; i < size; i = updateFirst(i, true)) {
                newList[i] = get(i);
            }
            storage = newList;
            first = length - 1;
            last = size;
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
        storage = (T []) new Object[other.length];
        first = other.length - 1;
        last = other.size;
        size = other.size;
        length = other.length;
        for (int i = 0; i < size; i = updateFirst(i, true)) {
            storage[i] = (T) other.get(i);
        }
    }

    /** Adds an object to the first position of the array */
    public void addFirst(T object) {
        resize();
        storage[first] = object;
        first = updateFirst(first, false);
        size += 1;
    }

    /** Adds an object to the last position of the array. If the array is empty,
     * it adds an object to the zero position of the array
     * @param object
     */
    public void addLast(T object) {
        resize();
        storage[last] = object;
        last = updateLast(last, true);
        size += 1;
    }

    public T removeFirst() {
        T result;
        if (size == 0) {
            return null;
        } else {
            first = updateFirst(first, true);
            result = storage[first];
            storage[first] = null;
        }
        size -= 1;
        resize();
        return result;
    }

    public T removeLast() {
        T result;
        if (size == 0) {
            return null;
        } else {
            last = updateLast(last, false);
            result = storage[last];
            storage[last] = null;
        }
        size -= 1;
        resize();
        return result;
    }

    @Override
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
        while (storage[pFirst] != null) {
            System.out.print(storage[pFirst] + " ");
            pFirst = updateFirst(pFirst, true);
        }
        System.out.println("");
    }

    public T get(int index) {
        int pointer = updateFirst(first + index, true);
        return storage[pointer];
    }
}
