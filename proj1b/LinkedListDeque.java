public class LinkedListDeque<T> implements Deque<T>{
    private class Node<T> {
        private T item;
        private Node<T> next;
        private Node<T> prev;

        public Node() {
            this.item = null;
            this.next = null;
            this.prev = null;
        }

        public Node(T item, Node<T> prev, Node<T> next) {
            this.item = item;
            this.next = next;
            this.prev = prev;
        }
    }
    private int size;
    private Node<T> sentinel;

    public LinkedListDeque() {
        this.size = 0;
        this.sentinel = new Node<>();
        this.sentinel.next = this.sentinel;
        this.sentinel.prev = this.sentinel;
    }

    @Override
    public void addFirst(T item) {
        this.size++;
        Node<T> n = new Node<>(item, sentinel, sentinel.next);
        this.sentinel.next.prev = n;
        this.sentinel.next = n;
    }

    @Override
    public void addLast(T item) {
        this.size++;
        Node<T> n = new Node<>(item, sentinel.prev, sentinel);
        this.sentinel.prev.next = n;
        this.sentinel.prev = n;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int size() {
        return size;
    }

    /** Print the Item in the deque from first to last, separated by space. */
    @Override
    public void printDeque() {
        Node<T> ptr = this.sentinel.next;
        while (ptr != this.sentinel) {
            System.out.print(ptr.item + " ");
            ptr = ptr.next;
        }
    }

    /** Remove and return the item at the front of the deque. If no such item exists, return null. */
    @Override
    public T removeFirst() {
        if (isEmpty()) {
            return null;
        }
        Node<T> first = sentinel.next;
        first.next.prev = sentinel;
        sentinel.next = first.next;
        size--;

        /* garbage collecting. */
        T result = first.item;
        first = null;

        return result;
    }

    @Override
    public T removeLast() {
        if (isEmpty()) {
            return null;
        }
        Node<T> last = sentinel.prev;
        last.prev.next = sentinel;
        sentinel.prev = last.prev;
        size--;

        /* garbage collecting. */
        T result = last.item;
        last = null;

        return result;
    }

    /** Get the item at the given index, where 0 is the front, 1 is the next item. and so forth.
     *  If no such item exists, return null.
     */
    @Override
    public T get(int index) {
        if (index < 0 || index > size - 1) {
            return null;
        }
        Node<T> ptr = sentinel.next;
        while (index > 0) {
            ptr = ptr.next;
            index--;
        }
        return ptr.item;
    }

    public T getRecursive(int index) {
        if (index < 0 || index > size - 1) {
            return null;
        }
        return getRecursiveHelper(this.sentinel.next, index);
    }

    private T getRecursiveHelper(Node<T> node, int index) {
        if (index == 0) {
            return node.item;
        } else {
            return getRecursiveHelper(node.next, index - 1);
        }
    }

}
