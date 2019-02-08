public class LinkedListDeque<Item> {
    private class Node {
        public Node prev;
        public Item curr;
        public Node next;

        public Node(Node p, Item c, Node n) {
            prev = p;
            curr = c;
            next = n;
        }

    }

    public int size;
    public Node sentinel;

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

        for(int i = 0; i < other.size(); i += 1) {
            addLast((Item) other.get(i));
        }
    }

    public void addFirst(Item i) {
        Node pointer = sentinel.next;
        sentinel.next = new Node(sentinel, i, pointer);
        pointer.prev = sentinel.next;
        size += 1;
    }

    public void addLast(Item i) {
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

    public Item removeFirst() {
        if (size > 0){
            Node first = sentinel.next;
            Node new_first = first.next;
            sentinel.next = new_first;
            new_first.prev = sentinel;
            return first.curr;
        } else {
            return null;
        }
    }

    public Item removeLast() {
        if (size > 0){
            Node last = sentinel.prev;
            Node second_last = last.prev;
            second_last.next = sentinel;
            sentinel.prev = second_last;
            return last.curr;
        } else {
            return null;
        }
    }

    public Item get(int index) {
        Node pointer = sentinel;
        while(index >= 0) {
            pointer = pointer.next;
            index -= 1;
        }
        return pointer.curr;
    }

    /** Recursive version of get(i)  */
    public Item getRecursive(int index) {
        Node real_sentinel = sentinel;
        sentinel = sentinel.next;
        if (index == 0) {
            return sentinel.curr;
        }
        Item result = getRecursive(index - 1);
        sentinel = real_sentinel;
        return result;
    }
}
