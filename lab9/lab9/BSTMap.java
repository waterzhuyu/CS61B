package lab9;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * Implementation of interface Map61B with BST as core data structure.
 *
 * @author Zuyu Guo, School of Artificial Intelligence, Beijing Normal University
 */
public class BSTMap<K extends Comparable<K>, V> implements Map61B<K, V> {

    private class Node {
        /* (K, V) pair stored in this Node. */
        private K key;
        private V value;

        /* Children of this Node. */
        private Node left;
        private Node right;

        private Node(K k, V v) {
            key = k;
            value = v;
        }
    }

    private Node root;  /* Root node of the tree. */
    private int size; /* The number of key-value pairs in the tree */

    /* Creates an empty BSTMap. */
    public BSTMap() {
        this.clear();
    }

    /* Removes all of the mappings from this map. */
    @Override
    public void clear() {
        root = null;
        size = 0;
    }

    /** Returns the value mapped to by KEY in the subtree rooted in P.
     *  or null if this map contains no mapping for the key.
     */
    private V getHelper(K key, Node p) {
        // This is a recursive solution.
        if (key == null) {
            throw new IllegalArgumentException("calls get() with a null key.\n");
        }

        if (p == null) return null;

        int cmp = key.compareTo(p.key);
        if (cmp < 0) return getHelper(key, p.left);
        else if (cmp > 0) {
            return getHelper(key, p.right);
        }
        else return p.value;
    }

    /** Returns the value to which the specified key is mapped, or null if this
     *  map contains no mapping for the key.
     */
    @Override
    public V get(K key) {
        return getHelper(key, root);
    }

    /** Returns a BSTMap rooted in p with (KEY, VALUE) added as a key-value mapping.
      * Or if p is null, it returns a one node BSTMap containing (KEY, VALUE).
     */
    private Node putHelper(K key, V value, Node p) {

        // This implementation is based on recursion.
        if (key == null) {
            throw new IllegalArgumentException("calls put() with a null key.\n");
        }

        if (p == null) return new Node(key, value);

        int cmp = key.compareTo(p.key);
        if (cmp < 0) p.left = putHelper(key, value, p.left);
        else if (cmp > 0) {
            p.right = putHelper(key, value, p.right);
        }
        else
            p.value = value;
        return p;
    }

    /** Inserts the key KEY
     *  If it is already present, updates value to be VALUE.
     */
    @Override
    public void put(K key, V value) {
        this.size += 1;
        this.root = putHelper(key, value, this.root);
    }

    /* Returns the number of key-value mappings in this map. */
    @Override
    public int size() {
        return size;
    }

    //////////////// EVERYTHING BELOW THIS LINE IS OPTIONAL ////////////////

    private void keys(Node p, Set<K> set) {
        // This is recursive.
        if (p == null) return;
        keys(p.left, set);
        set.add(p.key);
        keys(p.right, set);
    }

    /* Returns a Set view of the keys contained in this map. */
    @Override
    public Set<K> keySet() {
        Set<K> keyset = new HashSet<>();
        keys(this.root, keyset);
        return keyset;
    }

    private Node min(Node p) {
        if (p == null) return null;

        if (p.left == null) return p;
        else return min(p.left);
    }

    private Node removeMin(Node p) {
        if (p == null) return null;
        if (p.left == null) {
            return p.right;
        }
        p.left = removeMin(p.left);
        return p;
    }

    private Node remove(K key, Node p) {
        int cmp = key.compareTo(p.key);
        if (cmp < 0) {
            remove(key, p.left);
        } else if (cmp > 0) {
            remove(key, p.right);
        } else {
            if (p.left == null) return p.right;
            if (p.right == null) return p.left;
            Node t = p;
            p = min(p.right);
            p.right = removeMin(p);
            p.left = t.left;
        }
        return p;
    }

    /** Removes KEY from the tree if present
     *  returns VALUE removed,
     *  null on failed removal.
     */
    @Override
    public V remove(K key) {
        if (!containsKey(key)) return null;

        V retVal = get(key);
        root = remove(key, root);
        size -= 1;
        return retVal;
    }

    /** Removes the key-value entry for the specified key only if it is
     *  currently mapped to the specified value.  Returns the VALUE removed,
     *  null on failed removal.
     **/
    @Override
    public V remove(K key, V value) {
        if (get(key) != value) return null;
        return remove(key);
    }

    @Override
    public Iterator<K> iterator() {
        return keySet().iterator();
    }
}
