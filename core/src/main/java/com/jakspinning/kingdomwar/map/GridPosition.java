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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GridPosition that = (GridPosition) o;

        if (xGrid != that.xGrid) return false;
        if (yGrid != that.yGrid) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = xGrid;
        result = 31 * result + yGrid;
        return result;
    }
}
