package com.jakspinning.kingdomwar.map

import com.jakspinning.kingdomwar.component.MapComponent
import spock.lang.Specification

/**
 * Created by emmanuel_payet on 04/05/15.
 */
class PathFindingHelperTest extends Specification {
    def "getPath: simple"() {
        given:
        MapComponent map = new MapComponent(HexGridHelper.initializeTiles(2, 2), 2, 2);
        GridPosition from = new GridPosition(0,0)
        GridPosition to = new GridPosition(1,0)

        when:
        List<GridPosition> path = PathFindingHelper.getPath(map, from, to)

        then:
        path.size() == 2
        path.get(0).x == 0
        path.get(0).y == 0
        path.get(1).x == 1
        path.get(1).y == 0
    }

    def "computeDistance: neighbor X"(){
        given:
        GridPosition from = new GridPosition(0,0)
        GridPosition right = new GridPosition(1,0)
        GridPosition left = new GridPosition(-1,0)

        when:
        int distanceRight = PathFindingHelper.getDistance(from, right)
        int distanceLeft = PathFindingHelper.getDistance(from, left)
        then:
        distanceRight == 1
        distanceLeft == 1
    }
    //TODO MANU split les tests
    def "computeDistance: neighbor Y"(){
        given:
        GridPosition from = new GridPosition(0,0)
        GridPosition upLeft = new GridPosition(0,1)
        GridPosition downRight = new GridPosition(0,-1)
        GridPosition upRight = new GridPosition(1,-1)
        GridPosition downLeft = new GridPosition(-1,1)

        when:
        int distanceUpLeft = PathFindingHelper.getDistance(from, upLeft)
        int distanceUpRight = PathFindingHelper.getDistance(from, upRight)
        int distanceDownLeft = PathFindingHelper.getDistance(from, downLeft)
        int distanceDownRight = PathFindingHelper.getDistance(from, downRight)

        then:
        distanceUpLeft == 1
        distanceUpRight == 1
        distanceDownLeft == 1
        distanceDownRight == 1
    }

}
