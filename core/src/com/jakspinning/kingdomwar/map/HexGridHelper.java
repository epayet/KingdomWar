package com.jakspinning.kingdomwar.map;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

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

    /**
     * even-r conversion
     */
    public static Vector3 hexToCube(Vector2 hex) {
        int x = (int) (hex.x - (hex.y + ((int) hex.y & 1)) / 2);
        int z = (int) hex.y;
        int y = - x - z;
        return new Vector3(x, y, z);
    }

    /**
     * even-r conversion
     */
    public static Vector2 cubeToHex(Vector3 cube) {
        int q = (int) (cube.x + (cube.z + ((int) cube.z & 1)) / 2);
        int r = (int) cube.z;
        return new Vector2(q, r);
    }

    public static Vector2 pixelToHex(int x, int y, int size) {
        int q = (int) ((x * Math.sqrt(3) / 3 - y / 3) / size);
        int r = y * 2 / 3 / size;
        return roundHex(new Vector2(q, r));
    }

    public static Vector2 roundHex(Vector2 hex) {
        return cubeToHex(roundCube(hexToCube(hex)));
    }

    public static Vector3 roundCube(Vector3 cube) {
        int rx = Math.round(cube.x);
        int ry = Math.round(cube.y);
        int rz = Math.round(cube.z);
        int xDiff = (int) Math.abs(rx - cube.x);
        int yDiff = (int) Math.abs(ry - cube.y);
        int zDiff = (int) Math.abs(rz - cube.z);

        if (xDiff > yDiff && xDiff > zDiff) {
            rx = - ry - rz;
        } else if (yDiff > zDiff) {
            ry = - rx - rz;
        } else {
            rz = - rx - ry;
        }
        return new Vector3(rx, ry, rz);
    }
}
