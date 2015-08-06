package com.jakspinning.kingdomwar.map.tmx;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.jakspinning.kingdomwar.map.gateway.TiledMapGateway;

/**
 * Created by emmanuel_payet on 28/04/15.
 */
public class LibgdxTmxMapLoader implements ITmxMapLoader{
    @Override
    public TiledMapGateway load(String path) {
        TiledMap tiledMap = new TmxMapLoader().load(path);

        return new TiledMapGateway(tiledMap);
    }
}
