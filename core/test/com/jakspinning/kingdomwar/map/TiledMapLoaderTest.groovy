package com.jakspinning.kingdomwar.map

import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.jakspinning.kingdomwar.component.MapComponent
import com.jakspinning.kingdomwar.map.gateway.CellGateway
import com.jakspinning.kingdomwar.map.gateway.TiledMapGateway
import com.jakspinning.kingdomwar.map.gateway.TiledMapLayerGateway
import com.jakspinning.kingdomwar.map.tmx.TmxMapLoaderMock
import spock.lang.Specification

/**
 * Created by emmanuel_payet on 28/04/15.
 */
class TiledMapLoaderTest extends Specification {
    def "loadMap: should load a simple map with only 1 tile"() {
        given:
        TiledMapGateway tiledMapGateway = new TiledMapGateway()
        tiledMapGateway.mapH = tiledMapGateway.mapW = 1

        def tiledMapLayerGateway = new TiledMapLayerGateway()

        def colCells = new ArrayList<CellGateway>()

        def cellGateway = new CellGateway()
        cellGateway.tileTextureRegion = new TextureRegion()
        colCells.add(cellGateway)
        tiledMapLayerGateway.cells.add(colCells)
        tiledMapGateway.layersGateway.add(tiledMapLayerGateway)

        TmxMapLoaderMock tmxMapLoaderMock = new TmxMapLoaderMock(tiledMapGateway)
        TiledMapLoader tiledMapLoader = new TiledMapLoader(tmxMapLoaderMock)

        when:
        MapComponent mapComponent = tiledMapLoader.loadMap('test.tmx')

        then:
        mapComponent.mapHeight == 1
        mapComponent.mapWidth == 1
        mapComponent.tiles.size() == 1
    }
}
