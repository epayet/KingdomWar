package com.jakspinning.kingdomwar

import com.badlogic.gdx.math.Vector2
import com.jakspinning.kingdomwar.map.HexGridHelper
import spock.lang.Specification

/**
 * Created by emmanuel_payet on 16/04/15.
 */
class HexGridHelperTest extends Specification {
    def "ToWorldCoord"() {
        given:
        int xGrid = 10
        int yGrid = 5
        float size = 1
        def depth = 1
        int zGrid = 5

        when:
        Vector2 pos = HexGridHelper.toWorldCoord(xGrid,yGrid,zGrid, size, size, depth)

        then:
        Math.round(pos.x) == 13
        Math.round(pos.y) == 9
    }
}
