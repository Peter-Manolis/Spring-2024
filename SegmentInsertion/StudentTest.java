import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.*;
import java.util.Random;


public class StudentTest {
    @Test
    public void test() {
        testInsert();
        insertSmallBST();
        insertLargeBST();
    }
    @Test
    void testInsert() {
        AVLTree<Integer> avlTree1 = new AVLTree<>((Integer x, Integer y) -> x < y);
        avlTree1.insert(10);
        avlTree1.insert(5);
        avlTree1.insert(15);
        avlTree1.insert(3);
        avlTree1.insert(7);
        assertTrue(avlTree1.isAVL());

        AVLTree<Integer> avlTree2 = new AVLTree<>((Integer x, Integer y) -> x < y);

        avlTree2.insert(10);
        avlTree2.insert(500);
        avlTree2.insert(15);
        avlTree2.insert(3);
        avlTree2.insert(7);
        avlTree2.insert(7);
        assertTrue(avlTree2.isAVL());

        AVLTree<Integer> avlTree3 = new AVLTree<>((Integer x, Integer y) -> x < y);

        assertTrue(avlTree3.isAVL());
        avlTree3.insert(10);
        avlTree3.insert(5);
        avlTree3.insert(15);
        avlTree3.insert(3);
        avlTree3.insert(7);
        assertTrue(avlTree3.isAVL());
        avlTree3.remove(15);
        assertFalse(avlTree3.contains(15));
        //assertTrue(avlTree3.isAVL());
    }


    @Test
    public void insertSmallBST() {
        BinarySearchTree<Integer> bst = new BinarySearchTree<>((Integer x, Integer y) -> x < y);
        TreeMap<Integer, Integer> map = new TreeMap<>();

        int[] a = new int[]{4, 8, 0, 2, 6, 10};
        for (Integer key : a) {
            bst.insert(key);
            map.put(key, key);
        }
        for (int i = 0; i != 11; ++i) {
            assertEquals(bst.contains(i), map.containsKey(i));
        }
    }

    @Test
    public void insertLargeBST() {
        BinarySearchTree<Integer> bst = new BinarySearchTree<>((Integer x, Integer y) -> x < y);
        TreeMap<Integer, Integer> map = new TreeMap<>();
        Random rand = new Random();
        int[] a = new int[100];
        for (int i = 0; i != 100; ++i) {
            a[i] = rand.nextInt();
        }
        for (Integer key : a) {
            bst.insert(key);
            map.put(key, key);
        }
        for (int i = 0; i != 100; ++i) {
            assertEquals(bst.contains(a[i]), map.containsKey(a[i]));
        }
    }

    @Test
    public void testEmptyBST() {
        BinarySearchTree<Integer> bst = new BinarySearchTree<>((Integer x, Integer y) -> x < y);
        bst.insert(0);
        assertTrue(bst.contains(0));
        bst.remove(0);
        assertFalse(bst.contains(0));
    }

}
