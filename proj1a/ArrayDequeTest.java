import org.junit.Test;
import static org.junit.Assert.*;
public class ArrayDequeTest {
    @Test
    public void testArrayDeque() {
        ArrayDeque<String> dq = new ArrayDeque<>();
        assertEquals(0, dq.size());
    }

    @Test
    public void testResize() {
        ArrayDeque<Integer> dq = new ArrayDeque<>();
        dq = dq.of(1, 2, 3, 4, 5, 6, 7, 8);
        dq.addLast(9);
        assertEquals(9, dq.size());
        assertEquals(1, (int) dq.removeFirst());
        dq.addFirst(0);
        dq.addFirst(1);
        assertEquals(9, (int) dq.removeLast());

        ArrayDeque<Double> dq2 = new ArrayDeque<>();
        while (dq2.size() < 100) {
            dq2.addLast(0.01);
        }
        while (dq2.size() > 2) {
            dq2.removeFirst();
        }
        assertEquals(0.01, (double) dq2.removeLast(), 1e-7);
    }

    @Test
    public void testGet() {
        ArrayDeque<Integer> dq = new ArrayDeque<>();
        dq = dq.of(1, 2, 3, 4, 5, 6, 7, 8);
        dq.removeFirst();
        assertEquals(6, (int) dq.get(4));
        dq.removeFirst();
    }
}
