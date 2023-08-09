public class ArrayDeque<T> implements Deque<T> {
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

    @Override
    public void addFirst(T item) {
        if (items.length == size) {
            resize(size * 2);
        }
        size++;
        first = (first + items.length - 1) % items.length;
        items[first] = item;
    }

    @Override
    public void addLast(T item) {
        if (items.length == size) {
            resize(size * 2);
        }
        size++;
        items[last] = item;
        last = (last + items.length + 1) % items.length;;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void printDeque() {
        for (int idx = first, cnt = 0; cnt < size; cnt++, idx = (idx + 1 + items.length) % items.length) {
            System.out.print(items[idx] + " ");
        }
    }

    @Override
    public T removeFirst() {
        if ((double) size / items.length <= ArrayDeque.FACTOR && items.length >= 16) {
            resize(items.length / 2);
        }
        if (size == 0) {
            return null;
        }
        size--;
        T result = items[first];
        items[first] = null;
        first = (first + items.length + 1) % items.length;
        return result;
    }

    @Override
    public T removeLast() {
        if ((double) size / items.length <= ArrayDeque.FACTOR && items.length >= 16) {
            resize(items.length / 2);
        }
        if (size == 0) {
            return null;
        }
        size--;
        last = (last + items.length - 1) % items.length;
        T result = items[last];
        items[last] = null;
        return result;
    }

    @Override
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
