import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Random;
import java.util.function.BiPredicate;

public class StudentTest {

    BiPredicate<Integer, Integer> lessEq = (Integer x, Integer y) -> x <= y;

    @Test
    public void test() throws Exception {
        isHeap_test();
        push_pop_test();
        big_test();
        push_pop_more_test();
        isEmpty_test();
        even_more_tests();
    }

    public void isHeap_test() {
        BinomialQueue<Integer> queue = new BinomialQueue<>(lessEq);
        assertTrue(queue.isHeap());
        queue.push(5);
        queue.push(3);
        queue.push(8);
        assertTrue(queue.isHeap());
    }

    public void push_pop_test() {
        BinomialQueue<Integer> queue = new BinomialQueue<>(lessEq);
        queue.push(5);
        queue.push(3);
        queue.push(8);
        assertTrue(queue.isHeap());
        assertEquals(8, queue.pop());
        assertEquals(5, queue.pop());
        assertEquals(3, queue.pop());
        assertTrue(queue.isEmpty());
    }

    public void big_test() {
        Random rand = new Random();
        BinomialQueue<Integer> queue = new BinomialQueue<>(lessEq);
        for(int i = 0; i < 1000; i++){
            int x = rand.nextInt(1000);
            queue.push(x);
            assertTrue(queue.isHeap());
        }
        while(!queue.isEmpty()){
            queue.pop();
            assertTrue(queue.isHeap());
        }
    }
    public void push_pop_more_test(){
        BinomialQueue<Integer> queue = new BinomialQueue<>(lessEq);
        queue.push(5);
        queue.push(3);
        queue.push(8);
        assertTrue(queue.isHeap());
        assertEquals(8, queue.pop());
        assertEquals(5, queue.pop());
        assertEquals(3, queue.pop());
        assertTrue(queue.isEmpty());
        queue.push(10);
        queue.push(1);
        queue.push(7);
        assertTrue(queue.isHeap());
        assertEquals(10, queue.pop());
        assertEquals(7, queue.pop());
        assertEquals(1, queue.pop());
        assertTrue(queue.isEmpty());
    }
    public void isEmpty_test(){
        BinomialQueue<Integer> queue = new BinomialQueue<>(lessEq);
        assertTrue(queue.isEmpty());
        queue.push(5);
        assertFalse(queue.isEmpty());
        queue.pop();
        assertTrue(queue.isEmpty());
    }
    public void even_more_tests() {
        BinomialQueue<Integer> queue = new BinomialQueue<>(lessEq);
        assertTrue(queue.isEmpty());
        queue.push(5);
        assertFalse(queue.isEmpty());
        assertEquals(5, queue.pop());
        assertTrue(queue.isEmpty());
        for (int i = 0; i < 10; i++) {
            queue.push(i);
        }
        assertFalse(queue.isEmpty());
        for (int i = 9; i >= 0; i--) {
            assertEquals(i, queue.pop());
        }
        assertTrue(queue.isEmpty());
    }

}
