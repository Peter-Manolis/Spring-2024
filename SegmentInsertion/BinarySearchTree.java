import java.sql.Array;
import java.util.*;
import java.util.function.BiPredicate;

/**
 * TODO: This is your first major task.
 * <p>
 * This class implements a generic unbalanced binary search tree (BST).
 */

public class BinarySearchTree<K> implements OrderedSet<K> {

    /**
     * A Node<K> is a Location (defined in OrderedSet.java), which
     * means that it can be the return value of a search on the tree.
     */

    static class Node<K> implements Location<K> {

        protected K data;
        protected Node<K> left, right;
        protected Node<K> parent;
        protected int height;

        /**
         * Constructs a leaf Node<K> with the given key.
         */
        public Node(K key) {
            this(key, null, null);
        }

        /**
         * TODO
         * <p>
         * Constructs a new Node<K> with the given values for fields.
         */
        public Node(K data, Node<K> left, Node<K> right) {
            this.data = data;
            this.left = left;
            this.right = right;
            if(left != null) {
                this.left.parent = this;
            }
            if(right != null) {
                this.right.parent = this;
            }
        }

        /*
         * Provide the get() method required by the Location interface.
         */
        @Override
        public K get() {
            return data;
        }

        /**
         * Return true iff this Node<K> is a leaf in the tree.
         */
        protected boolean isLeaf() {
            return left == null && right == null;
        }

        /**
         * TODO
         * <p>
         * Performs a local update on the height of this Node<K>. Assumes that the
         * heights in the child Node<K>s are correct. Returns true iff the height
         * actually changed. This function *must* run in O(1) time.
         */
        protected boolean updateHeight() {
            if (this.left != null && this.right != null) {
                int h = Math.max(left.height, right.height) + 1;
                if (height != h) {
                    height = h;
                    return true;
                } else {
                    return false;
                }
            } else if (right != null) {
                int h = right.height + 1;
                if (height != h) {
                    height = h;
                    return true;
                } else {
                    return false;
                }
            } else {
                if (left != null) {
                    int h = left.height + 1;
                    if (height != h) {
                        height = h;
                        return true;
                    } else {
                        return false;
                    }
                }
            }
            return false;
        }

        /**
         * TODO
         * <p>
         * Returns the location of the Node<K> containing the inorder predecessor
         * of this Node<K>.
         */
        @Override
        public Location<K> previous() {
            if(left != null){
                return this.left.last();
            }
            else{
                Node<K> node = this;
                while(node.parent != null && node.parent.left == node){
                    node = node.parent;
                }
                return node.parent;
            }
        }
        public Location<K> first(){
            Node<K> curr = this;
            while(curr.left != null){
                curr = curr.left;
            }
            return curr;
        }
        public Location<K> last(){
            Node<K> curr = this;
            while(curr.right != null){
                curr = curr.right;
            }
            return curr;
        }
        /**
         * TODO
         * <p>
         * Returns the location of the Node<K> containing the inorder successor
         * of this Node<K>.
         */
        @Override
        public Location<K> next() {
            if(right != null){
                return this.right.first();
            }
            else{
                Node<K> node = this;
                while (node.parent != null && node == node.parent.right) {
                    node = node.parent;
                }
                return node.parent;
            }
        }
        public boolean isAVL() {
            int h1, h2;
            h1 = get_height(left);
            h2 = get_height(right);
            return Math.abs(h2 - h1) < 2;
        }

        public String toString() {
            return toStringPreorder(this);
        }
    }

    protected Node<K> root;
    protected int numNodes;
    protected BiPredicate<K, K> lessThan;

    /**
     * Constructs an empty BST, where the data is to be organized according to
     * the lessThan relation.
     */
    public BinarySearchTree(BiPredicate<K, K> lessThan) {
        this.lessThan = lessThan;
        numNodes = 0;
    }
    protected Node<K> find(K key, Node<K> curr, Node<K> parent) {
        if (curr == null)
            return parent;
        else if (lessThan.test(key, curr.data))
            return find(key, curr.left, curr);
        else if (lessThan.test(curr.data, key))
            return find(key, curr.right, curr);
        else
            return curr;
    }
    /**
     * TODO
     * <p>
     * Looks up the key in this tree and, if found, returns the
     * location containing the key.
     */
    public Node<K> search(K key) {
        Node<K> n = find(key, root, null);
        if (n == null) {
            return null;
        }
        else if (n.data.equals(key)){
            return n;
        }
        else{
            return null;
        }
    }

    /**
     * TODO
     * <p>
     * Returns the height of this tree. Runs in O(1) time!
     */
    public int height() {
        if(root == null){
            return -1;
        }
        else{
            root.updateHeight();
            return root.height;
        }
    }

    /**
     * TODO
     * <p>
     * Clears all the keys from this tree. Runs in O(1) time!
     */
    public void clear() {
        root = new Node<>(null, null, null);
    }

    /**
     * Returns the number of keys in this tree.
     */
    public int size() {
        return numNodes;
    }

    /**
     * TODO
     * <p>
     * Inserts the given key into this BST, as a leaf, where the path
     * to the leaf is determined by the predicate provided to the tree
     * at construction time. The parent pointer of the new Node<K> and
     * the heights in all Node<K> along the path to the root are adjusted
     * accordingly.
     * <p>
     * Note: we assume that all keys are unique. Thus, if the given
     * key is already present in the tree, nothing happens.
     * <p>
     * Returns the location where the insert occurred (i.e., the leaf
     * Node<K> containing the key), or null if the key is already present.
     */
    //
    public Node<K> insert(K key) {
        Node<K> curr = insertHelper(root, key);
        root = curr;
        root.updateHeight();
        return curr;
    }
    public Node<K> insertHelper(Node<K> node ,K key){
        if(node == null) {
            numNodes++;
            return new Node<>(key, null, null);
        }
        if (lessThan.test(key, node.data)) {
            node.left = insertHelper(node.left, key);
            node.left.parent = node;
        }
        else if(lessThan.test(node.data, key)){
            node.right = insertHelper(node.right,key);
            node.right.parent = node;
        }
        else{
            ;
        }
        return node;
    }

    /**
     * Returns a textual representation of this BST.
     */
    public String toString() {
        return toStringPreorder(root);
    }

    /**
     * Returns true iff the given key is in this BST.
     */
    public boolean contains(K key) {
        Node<K> p = search(key);
        return p != null;
    }

    /**
     * TODO
     * <p>
     * Removes the key from this BST. If the key is not in the tree,
     * nothing happens.
     */
    public void remove(K key) {
        Node<K> curr = removeHelper(key, root);
        root = curr;
        if(root!= null) {
            root.updateHeight();
        }
    }
    private Node<K> removeHelper(K key, Node<K> node){
        if (node == null){
            return node;
        }
        if(lessThan.test(key, node.data)){
            node.left.parent = node;
            node.left = removeHelper(key, node.left);
            return node;
        }
        else if(lessThan.test(node.data, key)){
            node.right.parent = node;
            node.right = removeHelper(key, node.right);
            return node;
        }
        else if(node.left != null && node.right != null){
            Node<K> last = (Node<K>)node.right.first();
            node.data = last.get();
            node.right = removeHelper(node.data, node.right);
        }
        else {
            Node<K> A = node.parent;
            if (node.left != null) {
                node = node.left;
                numNodes--;
            } else{
                node = node.right;
                numNodes--;
            }
            if(node != null) {
                node.parent = A;
            }
        }
        return node;
    }

    /**
     * TODO * <p> * Returns a sorted list of all the keys in this tree.
     */
    public List<K> keys() {
        if(this.root == null){
            return new ArrayList<>();
        }
        List<K> A = new ArrayList<>();
        Location<K> node = root.first();
        while (node != null) {
            A.add(node.get());
            node = node.next();
        }
        A.sort((k1, k2) -> lessThan.test(k1, k2) ? -1 : (lessThan.test(k2, k1) ? 1 : 0));
        return A;
    }

    static private <K> String toStringPreorder(Node<K> p) {
        if (p == null) return ".";
        String left = toStringPreorder(p.left);
        if (left.length() != 0) left = " " + left;
        String right = toStringPreorder(p.right);
        if (right.length() != 0) right = " " + right;
        String data = p.data.toString();
        return "(" + data + "[" + p.height + "]" + left + right + ")";
    }

    /**
     * The get_height method returns the height of the Node<K> n, which may be null.
     */
    static protected <K> int get_height(Node<K> n) {
        if (n == null) return -1;
        else return n.height;
    }
}
