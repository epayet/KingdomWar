package com.jakspinning.kingdomwar.map;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

/**
 * Created by emmanuel_payet on 16/04/15.
 */
public class HexGridHelper {
    public static Vector2 toWorldCoord(int xGrid,int yGrid,int zGrid,float w,float h,float depth){
        float x = 0;
        float y = 0;

        y = yGrid*h*3/4f + zGrid*depth;
        x = xGrid*w - yGrid%2*w/2f  ;
        return new Vector2(x,y);
    }

    public static Vector2 toHexCenterWorldCoord(int xGrid,int yGrid,float w,float h,float depth){
        float x = 0;
        float y = 0;

        y = yGrid*h*3/4f+h/2+depth;
        x = xGrid*w + yGrid%2*w/2f +w/2;
        return new Vector2(x,y);
    }

    public static Array<Array<HexTile>> initializeTiles(int mapW, int mapH) {
        Array<Array<HexTile>> tiles = new Array<Array<HexTile>>();

        for(int i = 0;i < mapW;i++){
            tiles.add(new Array<HexTile>());
            for(int j = 0;j < mapH;j++){
                tiles.get(i).add(null);
            }
        }

        return tiles;
    }
}
