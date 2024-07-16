import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Random;

public class StudentTest {

    @Test
    public void test() {
        first_test();
        test_empty();//Doesn't work
        test_one();//Doesn't work
        test_two();
        test_three();//Doesn't work
        test_rand();
        test_Representatives();
    }
    @Test
    public void first_test() {
        UndirectedAdjacencyList A = new UndirectedAdjacencyList(10);

        for (int i = 0; i < 10; i++) {
            A.addEdge(i, (i + 1) % 10);
        }
        Map<Integer, Integer> mapRep = new HashMap<>();
        ConnectedComponents.connected_components(A, mapRep);
        assertEquals(10, mapRep.size());
        int V = mapRep.values().iterator().next();
        for (int i = 0; i < 10; i++) {
            assertEquals(V, (int) mapRep.get(i));
        }
    }
    @Test
    public void test_empty()
    {
        Graph<Integer> A = new UndirectedAdjacencyList(0);
        Map<Integer, Integer> mapRep = new HashMap<>();
        ConnectedComponents.connected_components(A, mapRep);
        assertNull(mapRep.get(0));
        assertEquals(0, mapRep.size());
        assertEquals(0, A.numVertices());
    }

    @Test
    public void test_one()
    {
        Graph<Integer> A = new UndirectedAdjacencyList(1);
        Map<Integer, Integer> mapRep = new HashMap<>();
        A.addEdge(0, 0);
        ConnectedComponents.connected_components(A, mapRep);
        assertEquals(0, mapRep.get(0));
        assertEquals(1, mapRep.size());
        assertEquals(1, A.numVertices());

    }
    public void test_two() {
        UndirectedAdjacencyList A = new UndirectedAdjacencyList(5);
        A.addEdge(0, 1);
        A.addEdge(2, 3);
        A.addEdge(3, 4);

        Map<Integer, Integer> mapRep = new HashMap<>();
        ConnectedComponents.connected_components(A, mapRep);

        assertEquals(5, mapRep.size());
        assertEquals(mapRep.get(0), mapRep.get(1));
        assertEquals(mapRep.get(2), mapRep.get(3));
        assertEquals(mapRep.get(3), mapRep.get(4));
    }

    @Test
    public void test_three() {
        UndirectedAdjacencyList A = new UndirectedAdjacencyList(7);
        A.addEdge(0, 1);
        A.addEdge(1, 2);
        A.addEdge(3, 4);
        A.addEdge(5, 6);
        Map<Integer, Integer> mapRep = new HashMap<>();
        ConnectedComponents.connected_components(A, mapRep);

        assertEquals(7, mapRep.size());

        assertEquals(mapRep.get(0), mapRep.get(1));
        assertEquals(mapRep.get(1), mapRep.get(2));
        assertEquals(mapRep.get(3), mapRep.get(4));
        assertEquals(mapRep.get(5), mapRep.get(6));
        assertEquals(A.numVertices(), mapRep.size());
    }
    @Test
    public void test_Representatives() {
        UndirectedAdjacencyList graph = new UndirectedAdjacencyList(3);
        graph.addEdge(0, 1);
        graph.addEdge(1, 2);

        Map<Integer, Integer> representative = new HashMap<>();
        ConnectedComponents.connected_components(graph, representative);

        int rep0 = representative.get(0);
        int rep1 = representative.get(1);
        int rep2 = representative.get(2);

        assertEquals(0, rep1);
        assertEquals(0, rep2);
        assertEquals(0, rep0);

        assertEquals(0, rep0);
    }
    @Test
    public void test_rand()
    {

        for (int x = 0; x < 10; ++x)
        {
            Random rand = new Random();
            int randNum = rand.nextInt(1000) + 1;
            int v = 1000;
            Map<Integer, Integer> mapRep = new HashMap<>();
            Graph<Integer> A = new UndirectedAdjacencyList(v);
            for (int i = 0; i < v - 1; ++i)
            {
                int y = rand.nextInt(randNum);
                int randomNum2 = rand.nextInt(randNum);
                A.addEdge(y, randomNum2);
            }
            ConnectedComponents.connected_components(A, mapRep);
            assertEquals(v, mapRep.size());
            for (int i = 0; i < A.numVertices(); ++i)
            {
                for (int j = i + 1; j < A.numVertices(); ++j)
                {
                    if (((UndirectedAdjacencyList) A).hasEdge(i, j))
                    {
                        assertTrue(mapRep.containsKey(i));
                        assertEquals(mapRep.get(i), mapRep.get(j));
                        int currentRep = mapRep.get(i);
                        assertEquals(currentRep, mapRep.get(currentRep));
                    }
                }
            }
        }
    }

}
