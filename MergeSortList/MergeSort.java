
public class MergeSort {

    static Node merge(Node A, Node B) {
        Node combinedNode = Utils.append(A, B);
        int[] arr = Utils.list_to_array(combinedNode);
        for (int i = 0; i < arr.length; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                int tmp = 0;
                if (arr[i] > arr[j]) {
                    tmp = arr[i];
                    arr[i] = arr[j];
                    arr[j] = tmp;
                }
            }
        }
        Node list = Utils.array_to_list(arr);
        return list;
    }

    static Node sort(Node N) {
        int[] A = Utils.list_to_array(N);
        for (int i = 0; i < A.length; i++) {
            for (int j = i + 1; j < A.length; j++) {
                int tmp = 0;
                if (A[i] > A[j]) {
                    tmp = A[i];
                    A[i] = A[j];
                    A[j] = tmp;
                }
            }
        }
        Node list = Utils.array_to_list(A);
        return list;
    }

    static Node merge_in_place(Node A, Node B) {
        Node mergedNode = null;

        if (A == null){
            return B;
        }
        else if (B == null){
            return A;
        }
        if (A.data <= B.data){
            mergedNode = A;
            mergedNode.next = merge_in_place(A.next, B);

        }
        else {
            mergedNode = B;
            mergedNode.next = merge_in_place(A, B.next);
        }
        return mergedNode;
    }


    static Node sort_in_place(Node N) {
        if (N == null || N.next == null) {
            return N;
        }

        Node middleNode = splitNodes(N);
        Node nextofmiddle = middleNode.next;
        middleNode.next = null;

        Node firstHalf = sort_in_place(N);
        Node secondHalf = sort_in_place(nextofmiddle);

        Node sortedlist = merge_in_place(firstHalf, secondHalf);
        return sortedlist;
    }

   static Node splitNodes(Node N){
       if (N == null) {
           return N;
       }
       int n = (Utils.length(N))/2;
       if (n == 2){
           return Utils.drop(N, 1);
       }
       if (n == 1){
           return Utils.drop(N, 0);
       }
       return Utils.drop(N, n);

    }


public static void main(String[] args) {
    Node A = new Node(4, null);
    Node B = new Node(2, A);
    Node C = new Node(5, B);
    Node D = new Node(1, C);

    Node E = new Node(10, D);
    Node F = new Node(6, E);
    Node G = new Node(11, F);
    Node H = new Node(1, G);

   // Node sortFinalNode = sort(H);
    Node mergeFinalNode = sort_in_place(H);
    System.out.println(mergeFinalNode);

    Node A1 = Utils.array_to_list(new int []{4,5,2,10,10,6,7});
    Node B2 = MergeSort.sort_in_place(A1);
    System.out.println(A1);
    System.out.println(B2);

    }
}


