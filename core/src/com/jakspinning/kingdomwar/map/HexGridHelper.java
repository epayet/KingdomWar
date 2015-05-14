package com.jakspinning.kingdomwar.map;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;

/**
 * Created by emmanuel_payet on 16/04/15.
 */
public class HexGridHelper {
//	public static Vector2 toWorldCoord(int xGrid,int yGrid,int zGrid,float w,float h,float depth){
//		float y = yGrid * h * 3 / 4f + zGrid * depth;
//		float x = xGrid * w - yGrid % 2 * w / 2f;
//		return new Vector2(x,y);
//	}
	
	public static Vector2 hexToPixel(GridPosition pos,float w,float h){
		float x = (float)(w * (pos.xGrid + pos.yGrid/2f));
		float y = h * 3/4f * pos.yGrid;
		return new Vector2(x, y);
	}

	public static Vector2 toWorldCoord(int xGrid,int yGrid,int zGrid,float w,float h,float depth){
	Vector2 pixel = hexToPixel(new GridPosition(xGrid, yGrid), w,h);
	pixel.y += zGrid * depth;
	return pixel;
}
	
	public static GridPosition toHexCoord(float xPix,float yPix,float w,float h){
		float yGrid = yPix*4/3f/h;
		float xGrid = xPix/w - yGrid/2f;
		return roundHex(new Vector2(xGrid,yGrid));
	}

	public static Vector2  toHexCenterWorldCoord(int xGrid,int yGrid,float w,float h,float depth){

		float y = yGrid * h * 3 / 4f + h / 2 + depth;
		float x = xGrid * w + w / 2f - yGrid % 2 * w / 2;
		return new Vector2(x, y);
	}
	/**
	 * even-r conversion offset to cube
	 */
	public static Vector3 hexOffsetToCube(Vector2 hex) {
		int x = (int) (hex.x - (hex.y + ((int) hex.y & 1)) / 2);
		int z = (int) hex.y;
		int y = - x - z;
		return new Vector3(x, y, z);
	}

	/**
	 * even-r conversion offset to cube
	 */
	public static GridPosition cubeToHexOffset(Vector3 cube) {
		int q = (int) (cube.x + (cube.z + ((int) cube.z & 1)) / 2);
		int r = (int) cube.z;
		return new GridPosition(q, r);
	}

	/**
	 * conversion axial to cube
	 */
	public static Vector3 hexAxialToCube(Vector2 hex) {
		return new Vector3(hex.x, - hex.x - hex.y, hex.y);
	}

	/**
	 * conversion cube to axial
	 */
	public static GridPosition cubeToHexAxial(Vector3 cube) {
		return new GridPosition((int)cube.x,(int) cube.z);
	}


	public static GridPosition roundHex(Vector2 hex) {
		return cubeToHexAxial(roundCube(hexAxialToCube(hex)));
	}

	public static Vector3 roundCube(Vector3 cube) {
		int rx = MathUtils.round(cube.x);
		int ry = MathUtils.round(cube.y);
		int rz = MathUtils.round(cube.z);
		int xDiff = (int) Math.abs(rx - cube.x);
		int yDiff = (int) Math.abs(ry - cube.y);
		int zDiff = (int) Math.abs(rz - cube.z);

		if (xDiff > yDiff && xDiff > zDiff) {
			rx = - ry - rz;
		} else if (zDiff > yDiff) {
			ry = - rx - rz;
		} else {
			rz = - rx - ry;
		}
		return new Vector3(rx, ry, rz);
	}

	public static Array<Array<HexTile>> initializeTiles(int mapW, int mapH) {
		Array<Array<HexTile>> tiles = new Array<Array<HexTile>>();

		for(int col = 0;col < mapW;col++){
			tiles.add(new Array<HexTile>());
			for(int row = 0;row < mapH;row++){
				tiles.get(col).add(null);
			}
		}

		return tiles;
	}

	public static List<GridPosition> getNeighbors(GridPosition gridPosition) {
		List<GridPosition> neighbors = new ArrayList<GridPosition>();

		neighbors.add(new GridPosition(gridPosition.xGrid+1, gridPosition.yGrid));
		neighbors.add(new GridPosition(gridPosition.xGrid-1, gridPosition.yGrid));

		neighbors.add(new GridPosition(gridPosition.xGrid, gridPosition.yGrid+1));
		neighbors.add(new GridPosition(gridPosition.xGrid+1, gridPosition.yGrid-1));

		neighbors.add(new GridPosition(gridPosition.xGrid-1, gridPosition.yGrid+1));
		neighbors.add(new GridPosition(gridPosition.xGrid, gridPosition.yGrid-1));

		return neighbors;
	}

}
