public class Search {
    public static int find_first_true(boolean[] A, int begin, int end) {
        int returnVal = 0;
        if (begin <= end && 0<= begin && begin <= A.length && 0 <= end && end <= A.length) {
            for (int i = begin; i != end; ++i) {
                if (A[i]) {
                    return i;
                }
            }
        }
        return end;
    }

    public static int find_first_equal(int[] A, int x) {

        for (int i = 0; i != A.length; ++i) {
            if (A[i] == x){
                return i;
            }

        }
        return A.length;
    }
    public static int find_first_true_sorted(boolean[] A, int begin, int end) {
        if (begin <= end && 0<= begin && begin <= A.length && 0 <= end && end <= A.length) {

            while (begin < end) {
                int i = begin + (end - begin) / 2;

                if (A[i]) {
                    end = i;
                } else {
                    begin = i + 1;
                }
            }
        }

        return begin;
    }
}