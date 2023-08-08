public class ArrayDeque<T> {
    private T[] items;
    private int size;
    private int first;
    private int last;
    private static double FACTOR = 0.25;
    public ArrayDeque() {
        items = (T[]) new Object[8];
        size = 0;
        first = 0;
        last = 0;
    }

    private void resize(int capacity) {
        T[] newItems = (T[]) new Object[capacity];

        if (last > first) {
            System.arraycopy(items, first, newItems,0, size);
        } else {
            System.arraycopy(items, first, newItems, 0, items.length - first);
            System.arraycopy(items, 0, newItems, items.length - first, last);
        }

        items = newItems;

        first = 0;
        last = size;
    }

    public void addFirst(T item) {
        if (items.length == size) {
            resize(size * 2);
        }
        size++;
        first = (first + items.length - 1) % items.length;
        items[first] = item;
    }

    public void addLast(T item) {
        if (items.length == size) {
            resize(size * 2);
        }
        size++;
        items[last] = item;
        last = (last + items.length + 1) % items.length;;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void printDeque() {
        for (int idx = first, cnt = 0; cnt < size; cnt++, idx = (idx + 1 + items.length) % items.length) {
            System.out.print(items[idx] + " ");
        }
    }

    public T removeFirst() {
        if ((double) size / items.length < ArrayDeque.FACTOR) {
            resize(items.length / 2);
        }
        size--;
        T result = items[first];
        items[first] = null;
        first = (first + items.length + 1) % items.length;
        return result;
    }

    public T removeLast() {
        if ((double) size / items.length < ArrayDeque.FACTOR) {
            resize(items.length / 2);
        }
        size--;
        last = (last + items.length - 1) % items.length;
        T result = items[last];
        items[last] = null;
        return result;
    }

    public T get(int index) {
        if (index < 0 || index > size - 1) {
            return null;
        }
        return items[(first + items.length + index) % items.length];
    }

    public ArrayDeque<T> of(T... lst) {
        ArrayDeque<T> dq = new ArrayDeque<>();
        for (T t: lst) {
            dq.addLast(t);
        }
        return dq;
    }
}
