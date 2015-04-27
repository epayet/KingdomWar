package com.jakspinning.kingdomwar.component;

import com.artemis.Component;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.utils.Array;
import com.jakspinning.kingdomwar.map.HexTile;

/**
 * Created by emmanuel_payet on 16/04/15.
 */
public class MapComponent extends Component{

	private Array<Array<HexTile>> tiles;

    public MapComponent(Array<Array<HexTile>> tiles){
        this.tiles = tiles;
    }



}
