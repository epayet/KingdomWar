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

        x = xGrid*hexSize* (float)Math.sqrt(3);
        y = yGrid*hexSize*2;
        return new Vector2(x,y);
    }
}
