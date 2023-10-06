package synthesizer;
import org.junit.Test;
import static org.junit.Assert.*;

/** Tests the ArrayRingBuffer class.
 *  @author Josh Hug
 */

public class TestArrayRingBuffer {
    @Test
    public void testInstantiate() {
        ArrayRingBuffer arb = new ArrayRingBuffer(10);
        assertEquals(arb.capacity, 10);
        assertEquals(arb.fillCount, 0);
    }

    @Test
    public void testRemove() {
        ArrayRingBuffer arb = new ArrayRingBuffer(10);

    }

    /** Calls tests for ArrayRingBuffer. */
    public static void main(String[] args) {
        jh61b.junit.textui.runClasses(TestArrayRingBuffer.class);
    }
} 
