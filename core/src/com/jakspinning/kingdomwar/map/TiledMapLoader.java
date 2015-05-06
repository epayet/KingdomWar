package com.jakspinning.kingdomwar.map;

import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.tiled.TiledMapTile;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.utils.Array;
import com.jakspinning.kingdomwar.Constants;
import com.jakspinning.kingdomwar.component.MapComponent;
import com.jakspinning.kingdomwar.map.gateway.CellGateway;
import com.jakspinning.kingdomwar.map.gateway.TiledMapLayerGateway;
import com.jakspinning.kingdomwar.map.gateway.TiledMapGateway;
import com.jakspinning.kingdomwar.map.tmx.ITmxMapLoader;

import java.util.List;

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
		Array<Array<HexTile>> tiles = HexGridHelper.initializeTiles(mapW, mapH);

		for(TiledMapLayerGateway layerGateway : tiledMapGateway.layers) {
			final int altitude = layerGateway.altitude;
			for (int row = mapH - 1; row >= 0; row--) {
				for (int col = 0; col < mapW; col++) {
					final CellGateway cellGateway = layerGateway.getCellGateway(col, row);
					if (!cellGateway.isEmpty()) {
						tiles.get(col).set(row, new HexTile(altitude, cellGateway.getTileTextureRegion()));
					}
				}
			}
		}

		return new MapComponent(tiles,mapW,mapH);
	}

}
