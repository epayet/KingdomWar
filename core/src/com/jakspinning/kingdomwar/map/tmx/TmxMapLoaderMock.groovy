package com.jakspinning.kingdomwar.map.tmx

import com.jakspinning.kingdomwar.map.gateway.TiledMapGateway

/**
 * Created by emmanuel_payet on 05/05/15.
 */
class TmxMapLoaderMock implements ITmxMapLoader{

    private TiledMapGateway tiledMapGateway

    public TmxMapLoaderMock(TiledMapGateway tiledMapGateway) {
        this.tiledMapGateway = tiledMapGateway
    }

    @Override
    TiledMapGateway load(String path) {
        return tiledMapGateway;
    }
}
