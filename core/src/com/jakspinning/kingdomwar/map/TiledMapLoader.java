package com.jakspinning.kingdomwar.map;

import java.util.HashMap;

import com.badlogic.gdx.math.Vector2;
import com.jakspinning.kingdomwar.component.MapComponent;
import com.jakspinning.kingdomwar.map.gateway.CellGateway;
import com.jakspinning.kingdomwar.map.gateway.TiledMapGateway;
import com.jakspinning.kingdomwar.map.gateway.TiledMapLayerGateway;
import com.jakspinning.kingdomwar.map.tmx.ITmxMapLoader;

/**
 * Created by emmanuel_payet on 27/04/15.
 */
public class TiledMapLoader {

	private ITmxMapLoader tmxMapLoader;

	public TiledMapLoader(ITmxMapLoader tmxMapLoader) {
		this.tmxMapLoader = tmxMapLoader;
	}

	public MapComponent loadMap(String path){
		TiledMapGateway tiledMapGateway = tmxMapLoader.load(path);
		int mapH = tiledMapGateway.mapH, mapW = tiledMapGateway.mapW;
		HashMap<GridPosition, HexTile> tiles = new HashMap<GridPosition, HexTile>();

		for(TiledMapLayerGateway layerGateway : tiledMapGateway.layersGateway) {
			final int altitude = layerGateway.altitude;
			for (int row = 0; row < mapH; row++) {
				for (int col = 0; col < mapW; col++) {
					final CellGateway cellGateway = layerGateway.getCellGateway(col, row);
					if (!cellGateway.isEmpty()) {
						GridPosition axial = HexGridHelper.cubeToHexAxial(HexGridHelper.hexOffsetToCube(new Vector2(col,row)));
						tiles.put(axial, new HexTile(altitude, cellGateway.getTileTextureRegion()));
					}
				}
			}
		}

		return new MapComponent(tiles,mapW,mapH);
	}

}
