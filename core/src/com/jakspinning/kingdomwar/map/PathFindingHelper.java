package com.jakspinning.kingdomwar.map;

import com.badlogic.gdx.Gdx;
import com.jakspinning.kingdomwar.component.MapComponent;

import javax.xml.crypto.dsig.keyinfo.KeyValue;
import java.util.*;
import java.util.AbstractMap.SimpleEntry;


/**
 * Created by emmanuel_payet on 04/05/15.
 */
public class PathFindingHelper {


    public static List<GridPosition> getPath(MapComponent map, GridPosition from, GridPosition to) {
        List<GridPosition> path = new ArrayList<GridPosition>();

        PriorityQueue<SimpleEntry<GridPosition,Integer>> frontier = new PriorityQueue<SimpleEntry<GridPosition,Integer>>(
                new Comparator<SimpleEntry<GridPosition, Integer>>() {
                    @Override
                    public int compare(SimpleEntry<GridPosition, Integer> o1, SimpleEntry<GridPosition, Integer> o2) {
                        return o1.getValue() - o2.getValue();
                    }
                }
        );
        frontier.add(new SimpleEntry<GridPosition, Integer>(from, 0));

        HashMap<GridPosition, GridPosition> cameFrom = new HashMap<GridPosition, GridPosition>();
        HashMap<GridPosition, Integer> costSoFar = new HashMap<GridPosition, Integer>();

        cameFrom.put(from, null);
        costSoFar.put(from, 0);

        while (!frontier.isEmpty()) {
            GridPosition current = frontier.poll().getKey();

            if (current.equals(to)) {
                break;
            }

            for (GridPosition neighbor : map.getNeighbors(current)) {
                int newCost = costSoFar.get(current) + map.getCost(current, neighbor);
                if (!costSoFar.containsKey(neighbor) || newCost < costSoFar.get(neighbor)) {
                    costSoFar.put(neighbor, newCost);
                    int priority = newCost + getDistance(neighbor, to);
                    frontier.add(new SimpleEntry<GridPosition, Integer>(neighbor, priority));
                    cameFrom.put(neighbor, current);
                }
            }
        }

        GridPosition current = to;
        while(current != null){
            path.add(current);
            current = cameFrom.get(current);
        }

        return path;
    }

    private static int getDistance(GridPosition from, GridPosition to) {
        return 0;
    }
}
