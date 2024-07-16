import java.util.HashMap;
import java.util.Map;

public class ConnectedComponents {

    /*
     * TODO
     */
    public static <V> void
    connected_components(Graph<V> G, Map<V, V> representative) {
        HashMap<V, Boolean> map = new HashMap<V, Boolean>();
        DFS(G, representative, map);
    }

    public static <V> void DFS(Graph<V> G, Map<V, V> mapRep, Map<V, Boolean> bool) {
        for (V i : G.vertices()) {
            bool.put(i, false);
            mapRep.put(i, i);
        }
        for (V u : G.vertices()) {
            if (!bool.get(u))
                DFS_visit(G, u, u, mapRep, bool);
        }
    }
    public static <V> void DFS_visit(Graph<V> G, V a, V rep, Map<V, V> mapRep, Map<V, Boolean> bool) {
        bool.put(a, true);
        for (V v : G.adjacent(a)) {
            if (!bool.get(v)) {
                mapRep.put(v, rep);
                DFS_visit(G, v, rep, mapRep, bool);
            }
        }
    }

}