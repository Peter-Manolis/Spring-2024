import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
public class StudentTest {

    @Test
    public void test() {
        big_test();
    }

    @Test
    public void big_test (){
        Integer[] arr = new Integer[]{3,4,9,5,1,6,2,8,7};


        ArraySequence<Integer> a = new ArraySequence<>(arr);

        Iterator<Integer> i = a.begin();
        Iterator<Integer> j = a.end();

        QuickSort.quicksort(i,j);

        Integer[] arr2 = new Integer[]{1,2,3,4,5,6,7,8,9};

        ArraySequence<Integer> b = new ArraySequence<>(arr2);

        assertTrue(Algorithms.equal_sequences(a,b));
    }

}
