package com.jakspinning.kingdomwar;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by emmanuel_payet on 16/04/15.
 */
public class HexGridHelper {
    public static Vector2 toWorldCoord(int xGrid,int yGrid,float hexSize){
        float x = 0;
        float y = 0;
        float h = hexSize*2;
        float w = (float)Math.sqrt(3)/2f*h;

        y = yGrid*h*3/4f;
        x = xGrid*w + y%2*w/2f  ;
        return new Vector2(x,y);
    }
}
