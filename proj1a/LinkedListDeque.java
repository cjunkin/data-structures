public class LinkedListDeque<T> {
    private class Node {
        private Node prev;
        private T curr;
        private Node next;

        private Node(Node p, T c, Node n) {
            prev = p;
            curr = c;
            next = n;
        }

    }

    private int size;
    private Node sentinel;

    public LinkedListDeque() {
        sentinel = new Node(null, null, null);
        sentinel.prev = sentinel;
        sentinel.next = sentinel;
        size = 0;
    }

    /** Copies a LinkedListDeque *
     * @source https://www.youtube.com/watch?v=JNroRiEG7U4 (Hug)
     */
    public LinkedListDeque(LinkedListDeque other) {
        sentinel = new Node(null, null, null);
        sentinel.prev = sentinel;
        sentinel.next = sentinel;
        size = 0;

        for (int i = 0; i < other.size(); i += 1) {
            addLast((T) other.get(i));
        }
    }

    public void addFirst(T i) {
        Node pointer = sentinel.next;
        sentinel.next = new Node(sentinel, i, pointer);
        pointer.prev = sentinel.next;
        size += 1;
    }

    public void addLast(T i) {
        Node pointer = sentinel.prev;
        pointer.next = new Node(pointer, i, sentinel);
        sentinel.prev = pointer.next;
        size += 1;
    }

    public boolean isEmpty() {
        if (sentinel.next == sentinel) {
            return true;
        }
        return false;
    }

    public int size() {
        return size;
    }

    public void printDeque() {
        int index = size;
        Node pointer = sentinel.next;
        while (index > 0) {
            System.out.print(pointer.curr + " ");
            pointer = pointer.next;
            index -= 1;
        }
        System.out.println("");
    }

    public T removeFirst() {
        if (size > 0) {
            Node first = sentinel.next;
            Node newFirst = first.next;
            sentinel.next = newFirst;
            newFirst.prev = sentinel;
            size -= 1;
            return first.curr;
        } else {
            return null;
        }
    }

    public T removeLast() {
        if (size > 0) {
            Node last = sentinel.prev;
            Node secondLast = last.prev;
            secondLast.next = sentinel;
            sentinel.prev = secondLast;
            size -= 1;
            return last.curr;
        } else {
            return null;
        }
    }

    public T get(int index) {
        Node pointer = sentinel;
        while (index >= 0) {
            pointer = pointer.next;
            index -= 1;
        }
        return pointer.curr;
    }

    /** Recursive version of get(i)  */
    public T getRecursive(int index) {
        Node realSentinel = sentinel;
        sentinel = sentinel.next;
        T result;
        if (index == 0) {
            result = sentinel.curr;
        } else {
            result = getRecursive(index - 1);
        }
        sentinel = realSentinel;
        return result;
    }
}
