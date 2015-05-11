package com.jakspinning.kingdomwar.map;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

/**
 * Created by emmanuel_payet on 16/04/15.
 */
public class HexGridHelper {
    public static Vector2 toWorldCoord(int xGrid,int yGrid,int zGrid,float w,float h,float depth){

        float y = yGrid * h * 3 / 4f + zGrid * depth;
        float x = xGrid * w - yGrid % 2 * w / 2f;
        return new Vector2(x,y);
    }

    public static Vector2 toHexCenterWorldCoord(int xGrid,int yGrid,float w,float h,float depth){

        float y = yGrid * h * 3 / 4f + h / 2 + depth;
        float x = xGrid * w + w / 2f - yGrid % 2 * w / 2;
        return new Vector2(x, y);
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
}
