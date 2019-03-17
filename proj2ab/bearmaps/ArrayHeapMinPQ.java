package bearmaps;

import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.TreeMap;

public class ArrayHeapMinPQ<T> implements ExtrinsicMinPQ<T> {
    private ArrayList<Node> heap;
    private TreeMap<T, Integer> items;
    private int current; // keeps track of the index of most recently added node

    private class Node implements Comparable<Node> {
        T item;
        double priority;

        Node(T item, double priority) {
            this.item = item;
            this.priority = priority;
        }

        /* @source NativeMinPQ */
        @Override
        public int compareTo(Node o) {
            if (o == null) {
                return -1;
            }
            return Double.compare(this.priority, o.priority);
        }

        T item() {
            return item;
        }
    }

    public ArrayHeapMinPQ() {
        heap = new ArrayList<>();
        heap.add(new Node(null, -1000000));
        items = new TreeMap<>();
    }

    @Override
    public void add(T item, double priority) {
        if (contains(item)) {
            throw new IllegalArgumentException("item already exists");
        }
        heap.add(new Node(item, priority));
        current++;
        items.put(item, current);
        validate(current);
    }

    /* Checks if node priority is smaller than parent node */
    private void validate(int pointer) {
        Node parentNode = heap.get(pointer / 2);
        Node pointerNode = heap.get(pointer);
        int indicator = pointerNode.compareTo(parentNode);
        if (parentNode.item() == null || indicator > 0) {
            return;
        } else if (indicator < 0) {
            heap.set(pointer / 2, pointerNode);
            heap.set(pointer, parentNode);
            items.put(pointerNode.item, pointer / 2);
            items.put(parentNode.item, pointer);
        }
        validate(pointer / 2);
    }

    @Override
    public boolean contains(T item) {
        return items.containsKey(item);
    }

    @Override
    public T getSmallest() {
        return heap.get(1).item();
    }

    @Override
    public T removeSmallest() {
        Node ret = heap.get(1);
        heap.set(1, null);
        heap.set(1, heap.remove(current));
        items.remove(ret.item());
        current--;
        validate(current);
        return ret.item();
    }

    @Override
    public int size() {
        return heap.size() - 1;
    }

    private void validatePriority(int pointer) {
        if (pointer * 2 + 1 < current) {
            validatePriority(pointer * 2 + 1);
            validatePriority(pointer * 2);
        } else if (pointer * 2 < current) {
            validatePriority(pointer * 2);
        } else {
            validate(pointer);
        }
    }

    @Override
    public void changePriority(T item, double priority) {
        if (contains(item)) {
            int i = items.get(item);
            heap.get(i).priority = priority;
            validatePriority(i);
            return;
        } else {
            throw new NoSuchElementException("item does not exist in heap");
        }
    }
}
