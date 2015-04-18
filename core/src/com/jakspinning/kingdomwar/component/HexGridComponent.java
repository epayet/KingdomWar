package com.jakspinning.kingdomwar.component;

import com.artemis.Component;
import com.badlogic.gdx.maps.tiled.TiledMap;

/**
 * Created by emmanuel_payet on 16/04/15.
 */
public class HexGridComponent extends Component{

	public TiledMap map;

    public HexGridComponent(TiledMap map){

        //this.width = width;
        //this.height = height;
        this.map = map;
    }


}
