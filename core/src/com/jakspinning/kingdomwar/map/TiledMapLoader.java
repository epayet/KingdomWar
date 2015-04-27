package com.jakspinning.kingdomwar.map;

import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.MapLayers;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTile;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.utils.Array;
import com.jakspinning.kingdomwar.Constants;
import com.jakspinning.kingdomwar.component.MapComponent;

/**
 * Created by emmanuel_payet on 27/04/15.
 */
public class TiledMapLoader {

	public static MapComponent loadMap(String path){		
		Array<Array<HexTile>> tiles = new Array<Array<HexTile>>();
		TiledMap tmap = new TmxMapLoader().load("test.tmx");
		MapLayers layers = tmap.getLayers();
		//        List<MapLayer> layerList = new ArrayList<MapLayer>();
		//        for (MapLayer layer : layers){
		//            layerList.add(layer);
		//        }
		//
		//        Collections.sort(layerList, new Comparator<MapLayer>() {
		//            @Override
		//            public int compare(MapLayer o1, MapLayer o2) {
		//                int height1 = Integer.parseInt(o1.getProperties().get(Constants.HEIGHT_PROPERTY).toString());
		//                int height2 = Integer.parseInt(o2.getProperties().get(Constants.HEIGHT_PROPERTY).toString());
		//                return height1-height2;
		//            }
		//        });
		int mapH = 0,mapW = 0;
		for(MapLayer l : layers){
			if(l instanceof TiledMapTileLayer) {
				TiledMapTileLayer layer = (TiledMapTileLayer) l;
				final int layerWidth = layer.getWidth();
				final int layerHeight = layer.getHeight();
				mapH = Math.max(mapH, layerHeight);
				mapW = Math.max(mapW, layerWidth);				
			}
		}
		
		for(int i = 0;i < mapW;i++){
			tiles.add(new Array<HexTile>());
			for(int j = 0;j < mapH;j++){
				tiles.get(i).add(null);
			}
		}
		
		for(MapLayer l : layers){
			if(l instanceof TiledMapTileLayer) {	
				TiledMapTileLayer layer = (TiledMapTileLayer) l;
				final int height = Integer.parseInt(layer.getProperties().get(Constants.HEIGHT_PROPERTY).toString());	
				for (int row = mapH-1; row >= 0; row--) {
					for (int col = 0; col < mapW; col++) {
						final TiledMapTileLayer.Cell cell = layer.getCell(col, row);
						if (cell != null) {      			
							final TiledMapTile tile = cell.getTile();							
							tiles.get(col).set(row,new HexTile(height,tile.getTextureRegion()));
						}
					}
				}
			}
		}

		return new MapComponent(tiles,mapW,mapH);
	}

}
