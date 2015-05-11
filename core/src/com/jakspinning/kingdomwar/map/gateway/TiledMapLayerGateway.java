package com.jakspinning.kingdomwar.map.gateway;

import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.utils.Array;
import com.jakspinning.kingdomwar.Constants;
import com.jakspinning.kingdomwar.map.HexTile;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by emmanuel_payet on 05/05/15.
 */
public class TiledMapLayerGateway {
    private TiledMapTileLayer mapLayer;
    public int width;
    public int height;
    public int altitude;
    public List<List<CellGateway>> cells = new ArrayList<List<CellGateway>>();

    public TiledMapLayerGateway() {

    }

    public TiledMapLayerGateway(TiledMapTileLayer mapLayer) {
        width = mapLayer.getWidth();
        height = mapLayer.getHeight();
        altitude = Integer.parseInt(mapLayer.getProperties().get(Constants.HEIGHT_PROPERTY).toString());
        this.mapLayer = mapLayer;
    }

    public void computeCellsGateway(int mapH, int mapW) {
//        for (int col = 0; col < mapW; col++) {
//            ArrayList<CellGateway> colCells = new ArrayList<CellGateway>();
//            cells.add(colCells);
//            for (int row = mapH - 1; row >= 0; row--) {
//                TiledMapTileLayer.Cell cell = mapLayer.getCell(col, row);
//                CellGateway cellGateway = new CellGateway(cell);
//                colCells.add(cellGateway);
//            }
//        }

        for(int col = 0;col < mapW;col++){
            ArrayList<CellGateway> colCells = new ArrayList<CellGateway>();
            cells.add(colCells);
            for(int row = 0;row < mapH;row++){
                TiledMapTileLayer.Cell cell = mapLayer.getCell(col, row);
                CellGateway cellGateway = new CellGateway(cell);
                colCells.add(cellGateway);
            }
        }
    }

    public CellGateway getCellGateway(int col, int row) {
        return cells.get(col).get(row);
    }
}
