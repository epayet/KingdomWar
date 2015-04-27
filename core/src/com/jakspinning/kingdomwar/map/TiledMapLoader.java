package com.jakspinning.kingdomwar.map;

import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.MapLayers;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.utils.Array;
import com.jakspinning.kingdomwar.Constants;

import java.util.*;

/**
 * Created by emmanuel_payet on 27/04/15.
 */
public class TiledMapLoader {

    public static Array<Array<HexTile>> loadMap(String path){
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
        for(MapLayer l : layers){
            if(l instanceof TiledMapTileLayer) {
                TiledMapTileLayer layer = (TiledMapTileLayer) l;
                final int layerWidth = layer.getWidth();
                final int layerHeight = layer.getHeight();
                final int height = Integer.parseInt(layer.getProperties().get(Constants.HEIGHT_PROPERTY).toString());

                final int col1 = 0;
                final int col2 = layerWidth;

                final int row1 = 0;
                final int row2 = layerHeight;

                for (int row = row2; row > row1; row--) {
                    if(tiles.get(row) == null){
                        tiles.add(new Array<HexTile>());
                    }
                    for (int col = col1; col < col2; col++) {
                        tiles.get(row).add(new HexTile(height));
                    }
                }
            }

        }
    }

}
