package com.jakspinning.kingdomwar.helper;

import com.badlogic.gdx.math.Vector2;

/**
 * Created by emmanuel_payet on 16/04/15.
 */
public class HexGridHelper {
    public static Vector2 toWorldCoord(int xGrid,int yGrid,float w,float h){
        float x = 0;
        float y = 0;

        y = yGrid*h*3/4f;
        x = xGrid*w + yGrid%2*w/2f  ;
        return new Vector2(x,y);
    }

    public static Vector2 toHexCenterWorldCoord(int xGrid,int yGrid,float w,float h,float depth){
        float x = 0;
        float y = 0;

        y = yGrid*h*3/4f+h/2+depth;
        x = xGrid*w + yGrid%2*w/2f +w/2;
        return new Vector2(x,y);
    }
}
