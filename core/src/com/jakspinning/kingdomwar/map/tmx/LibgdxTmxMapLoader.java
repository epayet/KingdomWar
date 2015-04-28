package com.jakspinning.kingdomwar.map.tmx;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;

/**
 * Created by emmanuel_payet on 28/04/15.
 */
public class LibgdxTmxMapLoader implements ITmxMapLoader{
    @Override
    public TiledMap load(String path) {
        return new TmxMapLoader().load(path);
    }
}
