package com.jakspinning.kingdomwar.map.tmx;

import com.badlogic.gdx.maps.tiled.TiledMap;

/**
 * Created by emmanuel_payet on 28/04/15.
 */
public interface ITmxMapLoader {
    TiledMap load(String path);
}
