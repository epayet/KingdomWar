package com.jakspinning.kingdomwar.map

import com.jakspinning.kingdomwar.component.MapComponent
import spock.lang.Specification

/**
 * Created by emmanuel_payet on 04/05/15.
 */
class PathFindingHelperTest extends Specification {
    def "getPath: simple"() {
        given:
        MapComponent map = new MapComponent();
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
}
