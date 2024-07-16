import java.util.Arrays;
import java.util.Objects;
import java.util.Random;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class MergeSortTest {

    @Test
    public void test() {
        mergeTest();
        sortTest();
        merge_in_place_Test();
        sort_in_place_Test();
    }
    @Test
    public void mergeTest() {

        Node A = Utils.array_to_list(new int []{1,2,4,5,6,10});
        Node B = Utils.array_to_list(new int []{1,2,3,4,5,10,12});
        Node C = Utils.array_to_list(new int []{1,4});
        Node D = Utils.array_to_list(new int []{1});
        Node E = Utils.array_to_list(new int []{});

        Node merged = MergeSort.merge(A,B);
        Node merged2 = MergeSort.merge(A,C);
        Node merged3 = MergeSort.merge(B,C);
        Node merged4 = MergeSort.merge(C,D);
        Node merged5 = MergeSort.merge(E,A);
        Node merged6 = MergeSort.merge(E,E);

        assertTrue(Utils.is_sorted(merged));
        assertFalse(Utils.equals(merged, A));
        assertFalse(Utils.equals(merged, B));
        assertTrue(Utils.equals(merged, MergeSort.merge(A,B)));
        assertTrue(Utils.equals(merged2, MergeSort.merge(A,C)));
        assertTrue(Utils.equals(merged3, MergeSort.merge(B,C)));
        assertTrue(Utils.equals(merged4, MergeSort.merge(C,D)));
        assertTrue(Utils.equals(merged5, MergeSort.merge(E,A)));
        assertTrue(Utils.equals(merged6, MergeSort.merge(E,E)));
    }
    @Test
    public void sortTest() {
        Node A = Utils.array_to_list(new int []{4,5,1,2,6,7});
        Node B = MergeSort.sort(A);
        Node C = Utils.array_to_list(new int []{4,5,1,2,6,7,10});
        Node D = Utils.array_to_list(new int []{4,1});
        Node E = Utils.array_to_list(new int []{1,4});
        Node F = Utils.array_to_list(new int []{});

        assertFalse(Utils.equals(B,A));
        assertTrue(Utils.is_permutation(A, B));
        assertTrue(Utils.is_permutation(B, A));
        assertTrue(Utils.is_sorted(B));
        assertTrue(Utils.is_sorted(MergeSort.sort(C)));
        assertTrue(Utils.is_sorted(MergeSort.sort(D)));
        assertTrue(Utils.equals(E, MergeSort.sort(D)));
        assertTrue(Utils.is_sorted(MergeSort.sort(E)));
        assertNull(F);
    }
    @Test
    public void merge_in_place_Test() {

        Node A = Utils.array_to_list(new int []{1,2,4,5,6,10});
        Node B = Utils.array_to_list(new int []{1,2,3,4,5,10,12});
        Node C = Utils.array_to_list(new int []{1,4});
        Node D = Utils.array_to_list(new int []{1});
        Node E = Utils.array_to_list(new int []{});

        Node merged = MergeSort.merge_in_place(A,B);
     //   Node merged2 = MergeSort.merge_in_place(A,C);
        //Node merged3 = MergeSort.merge_in_place(B,C);
       // Node merged4 = MergeSort.merge_in_place(C,D);
       // Node merged5 = MergeSort.merge_in_place(E,A);
       // Node merged6 = MergeSort.merge_in_place(E,E);


        assertTrue(Utils.is_sorted(merged));
        assertTrue(Utils.equals(merged, A));
        assertFalse(Utils.equals(merged, B));
       // assertTrue(Utils.equals(merged, MergeSort.merge_in_place(A,B)));
        //assertTrue(Utils.equals(merged2, MergeSort.merge_in_place(A,C)));
     //   assertTrue(Utils.equals(merged3, MergeSort.merge_in_place(B,C)));
     //   assertTrue(Utils.equals(merged4, MergeSort.merge_in_place(C,D)));
     //   assertTrue(Utils.equals(merged5, MergeSort.merge_in_place(E,A)));
      //  assertTrue(Utils.equals(merged6, MergeSort.merge_in_place(E,E)));


    }
    @Test
    public void sort_in_place_Test() {
        Node A = Utils.array_to_list(new int []{4,9,6,7,1,2});
        Node B = MergeSort.sort_in_place(A);
        Node C = Utils.array_to_list(new int []{4,5,1,2,6,7,10});
        Node D = Utils.array_to_list(new int []{4,1});
        Node E = Utils.array_to_list(new int []{1,4});
        Node F = Utils.array_to_list(new int []{});
        Node G = Utils.array_to_list(new int []{1});



        //assertTrue(Utils.equals(B,A));
       // assertTrue(Utils.is_permutation(A, B));
       // assertTrue(Utils.is_permutation(B, A));
        //assertTrue(Utils.is_permutation(C, MergeSort.sort_in_place(C)));
        //assertTrue(Utils.is_permutation(D, MergeSort.sort_in_place(D)));
        //assertTrue(Utils.is_permutation(E, MergeSort.sort_in_place(E)));
        //assertTrue(Utils.is_permutation(F, MergeSort.sort_in_place(F)));
       // assertTrue(Utils.is_permutation(A, MergeSort.sort_in_place(A)));
        assertTrue(Utils.is_sorted(A));
        assertTrue(Utils.is_sorted(B));
        assertTrue(Utils.is_sorted(MergeSort.sort_in_place(C)));
        assertTrue(Utils.is_sorted(MergeSort.sort_in_place(D)));
        assertTrue(Utils.is_sorted(MergeSort.sort_in_place(G)));
        assertTrue(Utils.is_sorted(MergeSort.sort_in_place(E)));
        assertNull(F);
        assertNull(MergeSort.sort_in_place((F)));

    }
    
}
