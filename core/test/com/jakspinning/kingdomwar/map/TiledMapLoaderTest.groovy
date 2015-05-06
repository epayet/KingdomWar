package com.jakspinning.kingdomwar.map

import com.jakspinning.kingdomwar.component.MapComponent
import com.jakspinning.kingdomwar.map.gateway.TiledMapGateway
import com.jakspinning.kingdomwar.map.tmx.TmxMapLoaderMock
import spock.lang.Specification

/**
 * Created by emmanuel_payet on 28/04/15.
 */
class TiledMapLoaderTest extends Specification {
    def "loadMap"() {
        given:
        int mapHeight = 2
        int mapWidth = 2

        TiledMapGateway tiledMapGateway = new TiledMapGateway()
        TmxMapLoaderMock tmxMapLoaderMock = new TmxMapLoaderMock(tiledMapGateway)
        TiledMapLoader tiledMapLoader = new TiledMapLoader(tmxMapLoaderMock)

        when:
        MapComponent mapComponent = tiledMapLoader.loadMap('test.tmx')

        then:
        mapComponent.mapHeight == mapHeight
        mapComponent.mapWidth == mapWidth
        mapComponent.tiles.size == mapWidth
    }
}
