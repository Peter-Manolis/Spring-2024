import java.util.function.BiPredicate;

class BinomialHeap<K> {
    K key;
    int height;
    PList<BinomialHeap<K>> children;
    BiPredicate<K, K> lessEq;

    BinomialHeap(K k, int h, PList<BinomialHeap<K>> kids, BiPredicate<K, K> le) {
        this.key = k;
        this.height = h;
        this.children = kids;
        this.lessEq = le;
    }

    /*
     * @precondition this.height == other.height
     */
    BinomialHeap<K> link(BinomialHeap<K> other) {
        if (this.height != other.height)
            throw new UnsupportedOperationException("attempt to link trees of different height");
        if (lessEq.test(other.key, this.key)) {
            PList<BinomialHeap<K>> kids = PList.addFront(other, this.children);
            return new BinomialHeap<>(this.key, this.height + 1, kids, lessEq);
        } else {
            PList<BinomialHeap<K>> kids = PList.addFront(this, other.children);
            return new BinomialHeap<>(other.key, other.height + 1, kids, lessEq);
        }
    }

    /**
     * TODO
     * <p>
     * The isHeap method checks whether or not the subtree of this node
     * satisfies the heap property.
     */
    boolean isHeap() {
        if (children == null) {
            return true;
        }
        return isHeap_Helper(children);
    }
    private boolean isHeap_Helper(PList<BinomialHeap<K>> children) {
        if (children == null) {
            return true;
        }
        BinomialHeap<K> child = PList.getFirst(children);
        if (!lessEq.test(child.key, key) || !child.isHeap()) {
            return false;
        }
        return isHeap_Helper(PList.getNext(children));
    }

    public String toString() {
        String ret = "(" + key.toString();
        if (children != null)
            ret = ret + " " + children.toString();
        return ret + ")";
    }

}

public class BinomialQueue<K> {
    PList<BinomialHeap<K>> forest;
    BiPredicate<K, K> lessEq;

    public BinomialQueue(BiPredicate<K, K> le) {
        forest = null;
        lessEq = le;
    }

    public void push(K key) {
        BinomialHeap<K> heap = new BinomialHeap<>(key, 0, null, lessEq);
        this.forest = insert(heap, this.forest);
    }

    public K pop() {
        BinomialHeap<K> max = PList.find_max(this.forest, (h1,h2) -> this.lessEq.test(h1.key, h2.key));
        this.forest = PList.remove(max, this.forest);
        PList<BinomialHeap<K>> kids = PList.reverse(max.children, null);
        this.forest = merge(this.forest, kids);
        return max.key;
    }

    public boolean isEmpty() {
        return forest == null;
    }

    /**
     * TODO
     * The isHeap method returns whether or not the Binomial Queue (a forest of Binomial Trees)
     * satisfies the heap property.
     */
    public boolean isHeap() {
        if (forest == null) {
            return true;
        }
        return isHeap_Helper(forest);
    }

    private boolean isHeap_Helper(PList<BinomialHeap<K>> forest) {
        if (forest == null) {
            return true;
        }
        BinomialHeap<K> heap = PList.getFirst(forest);
        if (!heap.isHeap()) {
            return false;
        }
        return isHeap_Helper(PList.getNext(forest));
    }

    public String toString() {
        if (this.forest == null)
            return "";
        else
            return this.forest.toString();
    }

    /**********************************
     * Helper functions
     */

    /**
     * TODO
     * The insert method is analogous to binary addition. That is,
     * it inserts the node n into the list ns to produce a new list
     * that is still sorted by height.
     *
     * @param <K> The type of the keys.
     * @param n   The node to insert (must not be null).
     * @param ns  The binomial forest into which to insert, ordered by height. (may be null)
     * @return A new binomial forest that includes the new node.
     */
    static <K> PList<BinomialHeap<K>> insert(BinomialHeap<K> n, PList<BinomialHeap<K>> ns) {
        if (ns == null) {
            return new PList<>(n, null);
        }
        if (n.height < PList.getFirst(ns).height) {
            return new PList<>(n, ns);
        }
        return new PList<>(PList.getFirst(ns), insert(n, PList.getNext(ns)));
    }


    /**
     * TODO
     * The merge method is analogous to the merge part of merge sort. That is,
     * it takes two lists that are sorted (by height) and returns a new list that
     * contains the elements of both lists, and the new list is sorted by height.
     *
     * @param ns1
     * @param ns2
     * @return A list that is sorted and contains all and only the elements in ns1 and ns2.
     */
    static <K> PList<BinomialHeap<K>> merge(PList<BinomialHeap<K>> ns1, PList<BinomialHeap<K>> ns2) {
        if (ns1 == null) {
            return ns2;
        }
        if (ns2 == null) {
            return ns1;
        }
        if (PList.getFirst(ns1).height < PList.getFirst(ns2).height) {
            return new PList<>(PList.getFirst(ns1), merge(PList.getNext(ns1), ns2));
        } else if (PList.getFirst(ns1).height > PList.getFirst(ns2).height) {
            return new PList<>(PList.getFirst(ns2), merge(ns1, PList.getNext(ns2)));
        } else {
            BinomialHeap<K> A = PList.getFirst(ns1).link(PList.getFirst(ns2));
            return new PList<>(A, merge(PList.getNext(ns1), PList.getNext(ns2)));
        }
    }
    
}
