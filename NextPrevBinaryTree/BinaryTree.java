import java.util.ArrayList;


public class BinaryTree<T> implements Sequence<T>, ReverseSequence<T> {

    private Node root;

    public Node getRoot() {
        return this.root;
    }

    public void setRoot(Node root) {
        this.root = root;
    }

    public BinaryTree() {
        root = null;
    }

    private Node build_inorder(ArrayList<T> A, int begin, int end) {
        if (begin == end) {
            return null;
        } else if (begin + 1 == end) {
            return new Node(A.get(begin), null, null, null);
        } else {
            int mid = begin + ((end - begin) / 2);
            Node n = new Node(A.get(mid), null, null, null);
            Node left = build_inorder(A, begin, mid);
            Node right = build_inorder(A, mid + 1, end);
            n.left = left;
            n.right = right;
            if (left != null)
                left.parent = n;
            if (right != null)
                right.parent = n;
            return n;
        }
    }

    // Build a tree from the array, inorder.
    public BinaryTree(ArrayList<T> L) {
        root = build_inorder(L, 0, L.size());
    }

    public BinaryTree(Node r) {
        root = r;
    }

    @Override
    public Iter begin() {
        if (root == null)
            return new Iter(null);
        else
            return new Iter(root.first());
    }

    @Override
    public Iter end() {
        return new Iter(null);
    }

    @Override
    public Iter rbegin() {
        if (root == null)
            return new Iter(null);
        else
            return new Iter(root.last());
    }

    @Override
    public Iter rend() {
        return new Iter(null);
    }

    class Node {
        T data;
        Node left, right, parent;

        public String toString() {
            return data.toString();
        }

        public Node(T d, Node l, Node r, Node p) {
            data = d;
            left = l;
            right = r;
            parent = p;
        }

        // Return the first node wrt. inorder in this subtree.
        public Node first() {
            Node subTree = this;
            while (subTree.left != null) {
                subTree = subTree.left;
            }
            return subTree;
        }

        // Return the last node wrt. inorder in this subtree.
        public Node last() {
            Node subTree = this;
            while (subTree.right != null) {
                subTree = subTree.right;
            }
            return subTree;
        }


        // Return the first ancestor that is next wrt. inorder
        // or null if there is none.
        public Node nextAncestor() {
            Node subTree = this;
            while (subTree.parent != null && subTree == subTree.parent.right) {
                subTree = subTree.parent;
            }
            return subTree.parent;
        }

        // Return the first ancestor that is previous wrt. inorder
        // or null if there is none.
        public Node prevAncestor() {
            Node subTree = this;
            while (subTree.parent != null && subTree == subTree.parent.left) {
                subTree = subTree.parent;
            }
            return subTree.parent;
        }

        // Return the next node wrt. inorder in the entire tree.
        public Node next() {
            Node subTree = this;
            if (subTree.right != null) {
                return right.first();
            } else {
                return subTree.nextAncestor();
            }
        }

        // Return the previous node wrt. inorder in the entire tree.
        public Node previous() {
            Node subTree = this;
            if (subTree.left != null) {
                return left.last();
            } else {
                return subTree.prevAncestor();
            }
        }

    }

    class Iter implements Iterator<T>, ReverseIterator<T> {
        private Node curr;

        Iter(Node c) {
            curr = c;
        }

        public String toString() {
            if (curr == null) return "null";
            else return curr.toString();
        }

        @Override
        public T get() {
            return curr.data;
        }

        @Override
        public void retreat() {
            curr = curr.previous();
        }

        @Override
        public void advance() {
            curr = curr.next();
        }

        @Override
        public boolean equals(Object other) {
            Iter object = (Iter)other;
            return curr == object.curr;
        }

        @Override
        public Iter clone() {
            return new Iter(this.curr);
        }
    }



}
