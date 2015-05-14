package com.jakspinning.kingdomwar.map.gateway;

import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by emmanuel_payet on 05/05/15.
 */
public class TiledMapGateway {
	public int mapH = 0;
	public int mapW = 0;
	public List<TiledMapLayerGateway> layersGateway = new ArrayList<TiledMapLayerGateway>();

	public TiledMapGateway() {

	}

	public TiledMapGateway(TiledMap tiledMap) {
		for (MapLayer mapLayer : tiledMap.getLayers()) {
			if(mapLayer instanceof TiledMapTileLayer) {
				TiledMapTileLayer layer = (TiledMapTileLayer) mapLayer;            	
				if(layer.isVisible()){
					layersGateway.add(new TiledMapLayerGateway(layer));
				}
			}
		}
		computeMapSize();
		for (TiledMapLayerGateway tiledMapLayerGateway : layersGateway) {
			tiledMapLayerGateway.computeCellsGateway(mapH, mapW);
		}
	}

	private void computeMapSize() {
		for (TiledMapLayerGateway layerGateway : layersGateway) {
			final int layerWidth = layerGateway.width;
			final int layerHeight = layerGateway.height;
			mapH = Math.max(mapH, layerHeight);
			mapW = Math.max(mapW, layerWidth);
		}
	}
}
