package com.jakspinning.kingdomwar.map.tmx;

import com.jakspinning.kingdomwar.map.gateway.TiledMapGateway;

/**
 * Created by emmanuel_payet on 28/04/15.
 */
public interface ITmxMapLoader {
    TiledMapGateway load(String path);
}
