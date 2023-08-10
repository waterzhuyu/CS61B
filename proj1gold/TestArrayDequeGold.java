import org.junit.Test;
import static org.junit.Assert.*;
public class TestArrayDequeGold {
    /**@source
     *
     */
    @Test
    public void testStudent() {
        StudentArrayDeque<Integer> student = new StudentArrayDeque<>();
        ArrayDequeSolution<Integer> solution = new ArrayDequeSolution<>();
        String msg = "";
        int index = 0;

        for (int i = 0; i < 500; i++) {
            int randomMethod = StdRandom.uniform(4);
            if (randomMethod == 0) {
                Integer temp = StdRandom.uniform(100);
                student.addFirst(temp);
                solution.addFirst(temp);
                index++;
                msg += "addFirst(" + temp + ")\n";
                assertEquals(msg, student.get(0), solution.get(0));
            } else if (randomMethod == 1) {
                Integer temp = StdRandom.uniform(100);
                student.addLast(temp);
                solution.addLast(temp);
                index++;
                msg += "addLast(" + temp + ")\n";
                assertEquals(msg, student.get(index - 1), solution.get(index - 1));
            } else if (randomMethod == 2) {
                if (student.isEmpty()) {
                    continue;
                }
                msg += "removeFirst()\n";
                assertEquals(msg, solution.removeFirst(), student.removeFirst());
                index--;
            } else {
                if (student.isEmpty()) {
                    continue;
                }
                msg += "removeLast()\n";
                assertEquals(msg, solution.removeLast(), student.removeLast());
                index--;
            }
        }
    }
}
