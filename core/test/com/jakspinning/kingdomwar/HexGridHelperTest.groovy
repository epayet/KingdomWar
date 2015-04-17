package com.jakspinning.kingdomwar

import com.badlogic.gdx.math.Vector2
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

        when:
        Vector2 pos = HexGridHelper.toWorldCoord(xGrid,yGrid,size)

        then:
        Math.round(pos.x) == 8
        Math.round(pos.y) == 10
    }
}
