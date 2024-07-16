import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;
public class StudentTest {

    @Test
    public void test() {
        test_find_first_true();
        test_find_first_equal();
        test_find_first_true_sorted();
    }
    @Test
    public void test_find_first_true() {
        boolean [] A= {false, false, true, false, true};
        boolean [] A2 = {true, false, true, false, true};
        boolean [] A3 = {false, false, false, false, false};
        boolean [] A4 = {false, false, false, false, true};
        boolean [] A5 = {false};
        boolean [] A6 = {true};
        boolean [] A7 = {};
        assertEquals(2, Search.find_first_true(A, 0, A.length));
        assertEquals(0, Search.find_first_true(A2, 0, A2.length));
        assertEquals(A3.length, Search.find_first_true(A3, 0, A3.length));
        assertEquals(4, Search.find_first_true(A4, 0, A4.length));
        assertEquals(1, Search.find_first_true(A5, 0, 1));
        assertEquals(0, Search.find_first_true(A6, 0, 1));
        assertEquals(0, Search.find_first_true(A7, 0, 0));
        assertEquals(2, Search.find_first_true(A2, 1, A2.length));
        assertEquals(4, Search.find_first_true(A2, 3, A2.length));



    }
    @Test
    public void test_find_first_equal() {
        int [] A = {32, 11, 4, 5, 99, 5, 32, 75};
        int [] A2 = {1,0,3,5,10};
        int [] A3 = {0,3,5,10,1};
        int [] A4 = {0};
        int [] A5 = {1};
        int [] A6 = {-5,-1, 5,1,1};
        int [] A7 = {};
        assertEquals(3, Search.find_first_equal(A, 5));
        assertEquals(0, Search.find_first_equal(A2, 1));
        assertEquals(4, Search.find_first_equal(A3, 1));
        assertEquals(A4.length, Search.find_first_equal(A4, 1));
        assertEquals(0, Search.find_first_equal(A5, 1));
        assertEquals(1, Search.find_first_equal(A5, 10));
        assertEquals(1, Search.find_first_equal(A6, -1));
        assertEquals(0, Search.find_first_equal(A7, 1));


    }
    @Test
    public void test_find_first_true_sorted(){
        boolean [] A = {false, false, true, true, true, true, true};
        boolean [] A2 = {false,false,true, true, true, true};
        boolean [] A3 = {false, false, false, false, false};
        boolean [] A4 = {false};
        boolean [] A5 = {true};
        boolean [] A6 = {};

        assertEquals(3, Search.find_first_true_sorted(A, 3, 5));
        assertEquals(2, Search.find_first_true_sorted(A, 0, A.length));
        assertEquals(2, Search.find_first_true_sorted(A2, 0, A2.length));
        assertEquals(A3.length, Search.find_first_true_sorted(A3, 0, A3.length));
        assertEquals(1, Search.find_first_true_sorted(A4, 0, 1));
        assertEquals(0, Search.find_first_true_sorted(A5, 0, 1));
        assertEquals(0, Search.find_first_true_sorted(A6, 0, 0));

    }
}
