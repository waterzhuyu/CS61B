package synthesizer;

import java.util.Iterator;

public interface BoundedQueue<T> extends Iterable<T> {
    /** return the size of buffer. */
    int capacity();

    /** return number of items currently in the buffer. */
    int fillCount();

    /** add item x to the end. */
    void enqueue(T x);

    /** delete and return item from the front. */
    T dequeue();

    /** return (but not delete) item from the front. */
    T peek();

    /** return an iterator. */
    Iterator<T> iterator();

    /** return whether the buffer empty. */
    default boolean isEmpty() {
        return fillCount() == 0;
    }

    /** return whether the buffer is full. */
    default boolean isFull() {
        return capacity() == fillCount();
    }
}
