package com.jakspinning.kingdomwar.map;

/**
 * Created by emmanuel_payet on 06/05/15.
 */
public class GridPosition {
    public int xGrid;
    public int yGrid;

    public GridPosition(int xGrid, int yGrid) {
        this.xGrid = xGrid;
        this.yGrid = yGrid;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof GridPosition){
            GridPosition gridPosition = (GridPosition) obj;
            return gridPosition.xGrid == this.xGrid && gridPosition.yGrid == this.yGrid;
        }else{
            return false;
        }
    }
}
