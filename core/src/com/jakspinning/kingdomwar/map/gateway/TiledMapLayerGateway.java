package com.jakspinning.kingdomwar.map.gateway;

import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.jakspinning.kingdomwar.Constants;

import java.util.List;

/**
 * Created by emmanuel_payet on 05/05/15.
 */
public class TiledMapLayerGateway {
    public int width;
    public int height;
    public int altitude;
    public List<CellGateway> cells;

    public TiledMapLayerGateway() {

    }

    public TiledMapLayerGateway(TiledMapTileLayer mapLayer) {
        width = mapLayer.getWidth();
        height = mapLayer.getHeight();
        altitude = Integer.parseInt(mapLayer.getProperties().get(Constants.HEIGHT_PROPERTY).toString());
    }

    public CellGateway getCellGateway(int col, int row) {
//        return new CellGateway(tiledMapTileLayer.getCell(col, row));
        return null;
    }
}
