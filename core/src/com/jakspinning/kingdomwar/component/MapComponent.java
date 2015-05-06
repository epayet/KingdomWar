package com.jakspinning.kingdomwar.component;

import com.artemis.Component;
import com.badlogic.gdx.utils.Array;
import com.jakspinning.kingdomwar.map.GridPosition;
import com.jakspinning.kingdomwar.map.HexTile;

import java.util.ArrayList;

/**
 * Created by emmanuel_payet on 16/04/15.
 */
public class MapComponent extends Component{

	private Array<Array<HexTile>> tiles;
	public int mapWidth;
	public int mapHeight;
    
	
    public MapComponent(Array<Array<HexTile>> tiles,int mapW,int mapH){
        this.tiles = tiles;
        this.mapWidth = mapW;
        this.mapHeight = mapH;
    }
    
	public Array<Array<HexTile>> getTiles() {
		return tiles;
	}

	public HexTile getTile(int x, int y) {
		if(tiles.size > x){
			Array<HexTile> col = tiles.get(x);
			if(col != null && col.size > y){
				return col.get(y);
			}
		}
		return null;
		
	}


	public ArrayList<GridPosition> getNeighbors(GridPosition key) {
		return new ArrayList<GridPosition>();
	}

	public Integer getCost(GridPosition current, GridPosition neighbor) {
		return 0;
	}
}
