package com.jakspinning.kingdomwar.map

import com.jakspinning.kingdomwar.component.MapComponent
import com.jakspinning.kingdomwar.map.GridPosition
import com.jakspinning.kingdomwar.map.HexGridHelper
import com.jakspinning.kingdomwar.map.PathFindingHelper

/**
 * Created by emmanuel_payet on 11/05/15.
 */
class MapComponentTest extends spock.lang.Specification {
    def "GetNeighbors: simple grid 6 neighbors"() {
        given:
        GridPosition center = new GridPosition(1, 1)

        when:
        List<GridPosition> neighbors = HexGridHelper.getNeighbors(center)

        then:
        neighbors.size() == 6
        neighbors.stream().allMatch {gridPosition -> PathFindingHelper.getDistance(center, gridPosition) == 1}
    }
}
