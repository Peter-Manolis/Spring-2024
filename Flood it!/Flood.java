// Imports for the parameters of flood

import java.lang.reflect.Array;
import java.sql.SQLOutput;
import java.util.*;


public class Flood {

    // Students implement this flood function.
    // Time complexity 0(n^2)
    public static void flood(WaterColor color,
                              LinkedList<Coord> flooded_list,
                              Tile[][] tiles,
                              Integer board_size) {
        for (int i = 0; i < flooded_list.size(); i++) {
            Coord current = flooded_list.get(i);
            for (Coord neighbor : current.neighbors(board_size)) {
                if (tiles[neighbor.getY()][neighbor.getX()].getColor() == color && neighbor.onBoard(board_size)) {
                    tiles[neighbor.getY()][neighbor.getX()].setColor(color);
                    if (!flooded_list.contains(neighbor)) {
                        flooded_list.add(neighbor);
                    }
                }
            }
        }
    }
    // Time complexity O(n)
    public static void flood1(WaterColor color, LinkedList<Coord> flooded_list, Tile[][] tiles, Integer board_size) {
        LinkedList<Coord> unvisited = new LinkedList<>();
        HashSet<Coord> visited = new HashSet<>();

        for (Coord coordinate : flooded_list) {
            visited.add(coordinate);
            unvisited.addAll(coordinate.neighbors(board_size));
        }

        while (!unvisited.isEmpty()) {
            Coord current = unvisited.poll();
            if (visited.contains(current) || !tiles[current.getY()][current.getX()].getColor().equals(color)) {
                continue;
            }
            if (!flooded_list.contains(current)) {
                flooded_list.add(current);
                visited.add(current);
            }
            unvisited.addAll(current.neighbors(board_size));
        }
    }
}