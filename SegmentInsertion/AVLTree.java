import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.TreeMap;
import java.util.function.BiPredicate;
import java.util.Random;

/**
 * TODO: This is your second major task.
 * <p>
 * This class implements a height-balanced binary search tree,
 * using the AVL algorithm. Beyond the constructor, only the insert()
 * and remove() methods need to be implemented. All other methods are unchanged.
 */

public class AVLTree<K> extends BinarySearchTree<K> {

    /**
     * Creates an empty AVL tree as a BST organized according to the
     * lessThan predicate.
     */
    public AVLTree(BiPredicate<K, K> lessThan) {
        super(lessThan);
    }

    public boolean isAVL() {
        if (root == null)
            return true;
        else
            return root.isAVL();
    }
    /**
     * TODO
     * Inserts the given key into this AVL tree such that the ordering
     * property for a BST and the balancing property for an AVL tree are
     * maintained.
     */


    public Node<K> insert(K key) {
        Node<K> curr = super.insertHelper(root, key);
        root = curr;
        root.updateHeight();
        return balance(curr);
    }


    //Old Version
    private static final int ALLOWED_IMBALANCE = 1;
    private Node<K> balance( Node<K> t ) {
        if (t == null)
            return t;

        if (height(t.left) - height(t.right) > ALLOWED_IMBALANCE)
            if (height(t.left.left) >= height(t.left.right))
                t = rotateWithLeftChild(t);
            else
                t = doubleWithLeftChild(t);
        else if (height(t.right) - height(t.left) > ALLOWED_IMBALANCE)
            if (height(t.right.right) >= height(t.right.left))
                t = rotateWithRightChild(t);
            else
                t = doubleWithRightChild(t);

        t.height = Math.max(height(t.left), height(t.right)) + 1;
        if (t.left != null)
            t.left.parent = t;
        if (t.right != null)
            t.right.parent = t;
        return t;
    }

    private Node<K> rotateWithLeftChild( Node<K> k2 ) {
         Node<K> k1 = k2.left;
         k2.left = k1.right;
         k1.right = k2;
         k2.height = Math.max(height(k2.left), height(k2.right )) + 1;
         k1.height = Math.max(height(k1.left), height(k2)) + 1;
         return k1;
     }
    private Node<K> rotateWithRightChild(Node<K> k2) {
        Node<K> k1 = k2.right;
        k2.right = k1.left;
        k1.left = k2;
        k2.height = Math.max(height(k2.left), height(k2.right)) + 1;
        k1.height = Math.max(height(k1.right), height(k2)) + 1;
        return k1;
    }
    private Node<K> doubleWithLeftChild( Node<K>k3 ) {
         k3.left = rotateWithRightChild( k3.left );
         return rotateWithLeftChild( k3 );
         }
    private Node<K> doubleWithRightChild(Node<K> k3) {
        k3.right = rotateWithLeftChild(k3.right);
        return rotateWithRightChild(k3);
    }
    
    private int height( Node<K> t)
    {
         return t == null ? -1 : t.height;
    }
    /*


    /**
     * TODO
     * <p>
     * Removes the key from this BST. If the key is not in the tree,
     * nothing happens.
     */

    private Node<K> remove( K x, Node<K> t ){

        if (t == null)
            return t;

        boolean compareResult = lessThan.test(x, t.data);

        if (lessThan.test(x,t.data))
            t.left = remove(x, t.left);
        else if (lessThan.test(t.data,x))
            t.right = remove(x, t.right);
        else if (t.left != null && t.right != null)
        {
            t.data = findMin(t.right).data;
            t.right = remove(t.data, t.right);
        } else
            t = (t.left != null) ? t.left : t.right;
        return balance(t);
    }
    private Node<K> findMin( Node<K> t ) {
         if( t == null )
             return null;
         else if( t.left == null )
             return t;
         return findMin( t.left );
    }


    public int heightDiff(Node<K> node) {
        if (node == null) {
            return 0;
        }
        return (get_height(node.left) - get_height(node.right));
    }



    public static void main(String[] args) {

        AVLTree<Integer> avl = new AVLTree<>((Integer x, Integer y) -> x < y);
        avl.insert(3);
        avl.insert(1);
        avl.insert(2);
        avl.insert(20);
        avl.insert(22);
        avl.insert(10);
//        avl.insert(40);
//        avl.insert(28);
//        avl.insert(12);
//        avl.insert(30);
//        avl.insert(25);
//        avl.insert(30);

        System.out.println(avl);
        System.out.println(avl.isAVL());

        /*

        AVLTree<Integer> avl = new AVLTree<>((Integer x, Integer y) -> x < y);
        avl.insert(11);
        avl.insert(50);
        avl.insert(25);
        avl.insert(10);
        avl.insert(100);
        avl.insert(12);
        System.out.println(avl);
        System.out.println(avl.isAVL());



         */

        //  avl.remove(10);

        /*

        AVLTree<Integer> avl3 = new AVLTree<>((Integer x, Integer y) -> x < y);
        for(int i = 1; i < 100; i++){
            avl3.insert(i);
            System.out.println(avl3.isAVL());
        }
        System.out.println(avl3);
         */


//        AVLTree<Integer> avl1 = new AVLTree<>((Integer x, Integer y) -> x < y);
//        Random rand = new Random();
//        for(int i = 1; i < 30; i++){
//            int a =rand.nextInt(50);
//            if(!avl1.contains(a)) {
//                avl1.insert(a);
//            }
//           System.out.println(avl1.isAVL());
//        }
//       System.out.println(avl1);
    }
}