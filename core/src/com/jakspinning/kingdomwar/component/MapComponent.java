package com.jakspinning.kingdomwar.component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.artemis.Component;
import com.artemis.Entity;
import com.jakspinning.kingdomwar.KingdomWarGame;
import com.jakspinning.kingdomwar.map.GridPosition;
import com.jakspinning.kingdomwar.map.HexTile;

/**
 * Created by emmanuel_payet on 16/04/15.
 */
public class MapComponent extends Component{

	private HashMap<GridPosition, HexTile> tiles;
	public int mapWidth;
	public int mapHeight;

	private Comparator<Entry<GridPosition,HexTile>> tileYComparator = new Comparator<Entry<GridPosition,HexTile>>() {

		@Override
		public int compare(Entry<GridPosition, HexTile> o1,
				Entry<GridPosition, HexTile> o2) { 
			return o2.getKey().yGrid - o1.getKey().yGrid;
		}
	};


	public MapComponent(HashMap<GridPosition, HexTile> tiles,int mapW,int mapH){
		this.tiles = tiles;
		this.mapWidth = mapW;
		this.mapHeight = mapH;
	}

	public HashMap<GridPosition, HexTile>  getTiles() {
		return tiles;
	}

	public HexTile getTile(int x, int y) {
		GridPosition key = new GridPosition(x, y);
		return getTile(key);			
	}

	public HexTile getTile(GridPosition key) {		
		return tiles.get(key);			
	}

	public List<Entry<GridPosition,HexTile>> getSortedTiles(){
		ArrayList<Entry<GridPosition,HexTile>> tilesList = new ArrayList<Map.Entry<GridPosition,HexTile>>(tiles.entrySet());
		Collections.sort(tilesList,tileYComparator);	
		return tilesList;
	}

	public Integer getCost(GridPosition current, GridPosition neighbor) {
		return 0;
	}

	public Entity getUnitOnTile(GridPosition position){
		return KingdomWarGame.hero;
	}
}
