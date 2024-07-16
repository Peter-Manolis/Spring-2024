import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

public class BinaryTreeTest {

    BinaryTree<Integer> T;



    @BeforeEach
    public void setUp() throws Exception {
        ArrayList<Integer> a = new ArrayList<Integer>();
        a.add(1);
        a.add(2);
        a.add(3);
        a.add(4);
        a.add(5);
        a.add(6);
        a.add(7);
        a.add(8);
        a.add(9);
        a.add(10);
        T = new BinaryTree<>(a);
        //BinaryTree<Integer>.Node n = T.getRoot().first();
    }

    @Test
    public void test() {
        first_test();
        last_test();
        next_ancestor_test();
        prev_ancestor_test();
        next_test();
        previous_test();
        iter_test();
    }
    public void first_test(){
        BinaryTree<Integer>.Node n = T.getRoot().first();
        assertEquals(1,n.first().data);
    }
    public void last_test(){
        BinaryTree<Integer>.Node n = T.getRoot().last();
        assertEquals(10,n.first().data);
    }
    public void next_ancestor_test(){

        BinaryTree<Integer>.Node n = T.getRoot();

        assertEquals(2,n.first().nextAncestor().data);


    }
    public void prev_ancestor_test(){
        BinaryTree<Integer>.Node n = T.getRoot();
        BinaryTree<Integer>.Node n1 = T.getRoot().last();

        assertEquals(9,n1.prevAncestor().data);
        assertEquals(6,n1.prevAncestor().prevAncestor().data);

        assertEquals(9,n.last().prevAncestor().data);
        assertEquals(6,n.last().prevAncestor().prevAncestor().data);

    }

    public void next_test(){
        BinaryTree<Integer>.Node n = T.getRoot();
        BinaryTree<Integer>.Node n1 = T.getRoot().last();

        assertEquals(2, n.first().next().data);
        assertEquals(3, n.first().next().next().data);
        assertTrue(n1.equals(n.next().next().next().next()));
    }

    public void previous_test(){
        BinaryTree<Integer>.Node n = T.getRoot();
        BinaryTree<Integer>.Node n1 = T.getRoot().first();
        assertEquals(9, n.last().previous().data);
        assertEquals(8, n.last().previous().previous().data);
        assertNull(n1.previous());
    }



    public void iter_test(){
        BinaryTree<Integer>.Iter A = T.begin();
        BinaryTree<Integer>.Iter B = T.begin().clone();

        assertEquals(1,A.get());

        assertEquals(1,B.get());
        B.advance();
        B.retreat();


        assertTrue(A.equals(B));
    }



}
