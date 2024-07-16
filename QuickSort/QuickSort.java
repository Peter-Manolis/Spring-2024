public class QuickSort {

    /**
     * Partitions the elements between begin and end based on the pivot element.
     *
     * @param begin The iterator pointing to the first element of the partition.
     * @param end   The iterator pointing one past the last element of the partition.
     * @param <E>   The element type for the sequence.
     * @return The iterator pointing to the pivot element after partitioning.
     */
    public static <E extends Comparable<? super E>>
    Iterator<E> partition(Iterator<E> begin, Iterator<E> end) {

        E pivot = Algorithms.last(begin,end).get();
        Iterator<E> i = begin.clone();
        Iterator<E> newEnd = Algorithms.last(begin,end);

        for (Iterator<E> j = begin.clone(); !(j.equals(newEnd)); j.advance()) { //need end to be end-1 somehow???
            if (j.get().compareTo(pivot) <= 0) {
                Algorithms.iter_swap(i, j);
                i.advance();
            }
        }

        Algorithms.iter_swap(i, newEnd);//need end to be end-1 somehow???
        return i;
    }

    /**
     * Sorts the elements between begin and end-1 using the quicksort algorithm.
     *
     * @param begin The iterator pointing to the first element of the sequence to be sorted.
     * @param end   The iterator pointing one past the last element of the sequence to be sorted.
     * @param <E>   The element type for the sequence.
     */
    public static <E extends Comparable<? super E>>
    void quicksort(Iterator<E> begin, Iterator<E> end) {
        if (!begin.equals(end)) {
            Iterator<E> pivot = partition(begin, end);
            quicksort(begin, pivot);
            pivot.advance();
            quicksort(pivot, end);
        }
    }


}
