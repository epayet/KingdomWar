package com.jakspinning.kingdomwar.map

import com.jakspinning.kingdomwar.component.MapComponent
import spock.lang.Specification

/**
 * Created by emmanuel_payet on 28/04/15.
 */
class TiledMapLoaderTest extends Specification {
    def "loadMap"() {
        given:
        int mapHeight = 2
        int mapWidth = 2

        when:
        MapComponent mapComponent = TiledMapLoader.loadMap('test.tmx')

        then:
        mapComponent.mapHeight == mapHeight
        mapComponent.mapWidth == mapWidth
        mapComponent.tiles.size == mapWidth
    }
}
