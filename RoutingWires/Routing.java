import java.util.*;

public class Routing {

    /**
     * TODO
     * <p>
     * The findPaths function takes a board and a list of goals that contain
     * endpoints that need to be connected. The function returns a list of
     * Paths that connect the points.
     */
    public static ArrayList<Wire>
    findPaths(Board board, ArrayList<Endpoints> goals) {
        HashMap<Coord,Coord> parents = new HashMap<Coord,Coord>();
        ArrayList<Wire> paths = new ArrayList<>(backTrack(board, goals, parents));
        return paths;
    }
    public static boolean BFS(Endpoints points, Board board, Set<Coord> visited, Map<Coord,Coord> parent){
        Coord start = points.start;
        Coord end = points.end;
        Queue<Coord> queue = new LinkedList<>();
        queue.add(start);
        boolean endIsFound = false;
        while(!queue.isEmpty()){
            Coord i = queue.remove();
            visited.add(i);
            if (i.equals(end)) {
                endIsFound = true;
                return endIsFound;
            }
            for(Coord neighbor : board.adj(i)){
                if(!visited.contains(neighbor) && !board.isObstacle(neighbor) && (!board.isOccupied(neighbor)) || neighbor.equals(end)){
                    parent.put(neighbor,i);
                    queue.add(neighbor);
                }
            }
        }
        return endIsFound;
    }
    public static Wire placeWire(Board board, Endpoints line, Map<Coord, Coord> parent){
        HashSet<Coord> visited = new HashSet<Coord>();
        boolean found = BFS(line,board,visited,parent);
        if(found) {
            ArrayList<Coord> coord = new ArrayList<Coord>();
            Coord curr = line.end;
            while (curr != null){
                coord.add(curr);
                curr = parent.get(curr);
            }
            Collections.reverse(coord);
            Wire path = new Wire(line.id,coord);
            board.placeWire(path);
            return path;
        }
        else{
            return null;
        }
    }
    public static ArrayList<Wire> backTrack(Board board, ArrayList<Endpoints> goal, Map<Coord,Coord> parent) {
        if(goal.isEmpty()){
            return new ArrayList<Wire>();
        }
        else {
            for(Endpoints goals : goal) {
                Wire path = placeWire(board,goals,parent);
                if(path != null){
                    ArrayList<Endpoints> goalCopy = new ArrayList<Endpoints>(goal);
                    goalCopy.remove(goals);

                    HashMap<Coord,Coord> parent2 = new HashMap<Coord,Coord>();
                    ArrayList<Wire> lines = backTrack(board,goalCopy,parent2);

                    if(lines == null){
                        board.removeWire(path);
                        continue;
                    }
                    else{
                        lines.add(path);
                        return lines;
                    }
                }
            }
        }
        return null;
    }
}