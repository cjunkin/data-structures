package es.datastructur.synthesizer;
import javax.management.RuntimeErrorException;
import java.lang.reflect.Array;
import java.nio.Buffer;
import java.util.Iterator;

public class ArrayRingBuffer<T> implements BoundedQueue<T> {
    /* Index for the next dequeue or peek. */
    private int first;
    /* Index for the next enqueue. */
    private int last;
    /* Variable for the fillCount. */
    private int fillCount;
    /* Array for storing the buffer data. */
    private T[] rb;

    private class BufferIterator<T> implements Iterator<T> {
        private int pos;
        private int tracker;

        private BufferIterator() {
            pos = first;
            tracker = 0;
        }

        public boolean hasNext() {
            return tracker < fillCount;
        }
        public T next() {
            T item = (T) rb[pos];
            pos = (pos + 1) % capacity();
            tracker += 1;
            return item;
        }
    }

    /**
     * Create a new ArrayRingBuffer with the given capacity.
     */
    public ArrayRingBuffer(int capacity) {
        rb = (T[]) new Object[capacity];
        first = 0;
        last = 0;
        fillCount = 0;
    }

    public BufferIterator iterator() {
        return new BufferIterator<T>();
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof ArrayRingBuffer) {
            ArrayRingBuffer other = (ArrayRingBuffer) o;
            if (capacity() != other.capacity()) {
                return false;
            }
            if (other.isEmpty() && isEmpty()) {
                return true;
            }
            BufferIterator otheriter = other.iterator();
            BufferIterator current = iterator();
            while (current.hasNext() || otheriter.hasNext()) {
                if (current.next() != otheriter.next()) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    @Override
    public int capacity() {
        return rb.length;
    }

    public int fillCount() {
        return fillCount;
    }

    /**
     * Adds x to the end of the ring buffer. If there is no room, then
     * throw new RuntimeException("Ring buffer overflow").
     */
    public void enqueue(T x) {
        if (isFull()) {
            throw new RuntimeException("Ring Buffer overflow");
        }
        rb[last] = x;
        last = (last + 1) % capacity();
        fillCount += 1;
    }

    /**
     * Dequeue oldest item in the ring buffer. If the buffer is empty, then
     * throw new RuntimeException("Ring buffer underflow").
     */
    public T dequeue() {
        if (isEmpty()) {
            throw new RuntimeException("Ring buffer underflow");
        }
        T result = rb[first];
        rb[first] = null;
        first = (first + 1) % capacity();
        fillCount -= 1;
        return result;
    }

    /**
     * Return oldest item, but don't remove it. If the buffer is empty, then
     * throw new RuntimeException("Ring buffer underflow").
     */
    public T peek() {
        if (isEmpty()) {
            throw new RuntimeException("Ring buffer underflow");
        }
        return rb[first];
    }
}
