package com.jakspinning.kingdomwar.map

import com.jakspinning.kingdomwar.component.MapComponent
import com.jakspinning.kingdomwar.map.gateway.TiledMapGateway
import com.jakspinning.kingdomwar.map.gateway.TiledMapLayerGateway
import com.jakspinning.kingdomwar.map.tmx.TmxMapLoaderMock
import spock.lang.Specification

/**
 * Created by emmanuel_payet on 28/04/15.
 */
class TiledMapLoaderTest extends Specification {
    def "loadMap"() {
        given:
        TiledMapGateway tiledMapGateway = new TiledMapGateway()
        tiledMapGateway.layers = new ArrayList<TiledMapLayerGateway>()
        tiledMapGateway.mapH = tiledMapGateway.mapW = 1

        def tiledMapLayerGateway = new TiledMapLayerGateway()
        tiledMapGateway.layers.add(tiledMapLayerGateway)

        TmxMapLoaderMock tmxMapLoaderMock = new TmxMapLoaderMock(tiledMapGateway)
        TiledMapLoader tiledMapLoader = new TiledMapLoader(tmxMapLoaderMock)

        when:
        MapComponent mapComponent = tiledMapLoader.loadMap('test.tmx')

        then:
        mapComponent.mapHeight == 1
        mapComponent.mapWidth == 1
        mapComponent.tiles.size == 1
    }
}
